package com.tiny.core.tool.concurrent;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 延时队列
 */
public class DelayQueueFactory {


    private static final Logger logger = LoggerFactory.getLogger(DelayQueueFactory.class);
    private static final DelayQueueFactory FACTORY = new DelayQueueFactory();
    private static final RandomTimeDelayQueueWrapper<Object> RANDOM_TIME_QUEUE = new RandomTimeDelayQueueWrapper<>();
    private static final int CORE_NUMBER = Runtime.getRuntime().availableProcessors();

    private final ConcurrentMap<Long, FixedDelayQueueWrapper<?>> fixedDelayQueueCache =
            new ConcurrentHashMap<>(CORE_NUMBER);
    private final transient ReentrantLock lock = new ReentrantLock();

    public static DelayQueueFactory factory() {
        return FACTORY;
    }

    @SuppressWarnings("unchecked")
    public <V> RandomTimeDelayQueueWrapper<V> getRandomTimeDelayQueueWrapper() {
        return (RandomTimeDelayQueueWrapper<V>) RANDOM_TIME_QUEUE;
    }

    @SuppressWarnings("unchecked")
    public <V> FixedDelayQueueWrapper<V> getFixedDelayQueueWrapper(long delayTime, TimeUnit unit) {

        long key = TimeUnit.MILLISECONDS.convert(delayTime, unit);

        if (!fixedDelayQueueCache.containsKey(key)) {
            if (fixedDelayQueueCache.size() == CORE_NUMBER) {
                logger.warn("the fixedDelayQueueCache is full,the core number is :{}", CORE_NUMBER);
                final ReentrantLock lock = this.lock;
                lock.lock();
                try {
                    for (Map.Entry<Long, FixedDelayQueueWrapper<?>> entry : fixedDelayQueueCache.entrySet()) {
                        if (entry.getValue().delayQueue.isEmpty()) {
                            logger.info("remove key:{}", entry.getKey());
                            fixedDelayQueueCache.remove(entry.getKey());
                        }
                    }
                    Thread.sleep(20);
                    if (fixedDelayQueueCache.size() == 2) {
                        return null;
                    }

                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    lock.unlock();
                }
            }

            fixedDelayQueueCache.put(key, new FixedDelayQueueWrapper<>(delayTime, unit));
        }

        return (FixedDelayQueueWrapper<V>) fixedDelayQueueCache.get(key);
    }

    public static class RandomTimeDelayQueueWrapper<V> {

        private final BlockingQueue<V> dataQueue = new LinkedBlockingQueue<>();
        private final DelayQueue<DelayItem<V>> delayQueue = new DelayQueue<>();
        private volatile boolean shutDown;
        private final ExecutorService workerExecutor = ConcurrentToolkit.newThreadPool("RandomTimeDelayQueueExecutor");

        RandomTimeDelayQueueWrapper() {
            this.init();
        }

        public int size() {
            return delayQueue.size();
        }

        public boolean put(V e, PerformItemListener<V> listener,long delayTime, TimeUnit unit) {
            DelayItem<V> item = new DelayItem<>(TimeUnit.MILLISECONDS.convert(delayTime, unit), e,listener);
            return this.delayQueue.offer(item);
        }

        public V take() throws InterruptedException {
            return dataQueue.take();
        }

        public void shutDown() {
            this.shutDown = true;
            workerExecutor.shutdown();
        }

        public boolean isShutDown() {
            return shutDown;
        }

        public void init() {
            new Thread(new Boss()).start();
        }

        private class Boss implements Runnable {

            @Override
            public void run() {

                while (!shutDown) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                    DelayItem<V> item = delayQueue.poll();
                    if (null != item) {
                        dataQueue.offer(item.data);
                        if (item.listener != null) {
                            workerExecutor.submit(()->
                                    item.listener.perform(item.data));
                        }
                    }
                }
            }
        }
    }

    public static class FixedDelayQueueWrapper<V> {

        private final BlockingQueue<V> dataQueue = new LinkedBlockingQueue<>();
        private final long delayTime;
        private final DelayQueue<DelayItem<V>> delayQueue = new DelayQueue<>();
        private volatile boolean shutDown;
        private final ExecutorService workerExecutor = ConcurrentToolkit.newThreadPool("FixedDelayQueueExecutor");

        FixedDelayQueueWrapper(long delayTime, TimeUnit unit) {
            this.delayTime = TimeUnit.MILLISECONDS.convert(delayTime, unit);
            this.init();
        }

        public int size() {
            return delayQueue.size();
        }

        public boolean put(V e,PerformItemListener<V> listener) {
            DelayItem<V> item = new DelayItem<>(this.delayTime, e,listener);
            return this.delayQueue.offer(item);
        }

        public V take() throws InterruptedException {
            return dataQueue.take();
        }

        public void init() {
            new Thread(new Boss()).start();
        }

        public void shutDown() {
            this.shutDown = true;
            workerExecutor.shutdown();
        }

        public boolean isShutDown() {
            return shutDown;
        }

        private class Boss implements Runnable {

            @Override
            public void run() {
                try {
                    while (!shutDown) {
                        Thread.sleep(10);
                        DelayItem<V> item = delayQueue.poll();
                        if (null != item) {
                            dataQueue.offer(item.data);
                            if (item.listener != null) {
                                workerExecutor.submit(()->
                                        item.listener.perform(item.data));
                            }
                        }
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    public interface PerformItemListener<V> {
        void perform(V item);
    }

    public final static class DelayItem<V> implements Delayed {

        private final long expire; // 到期时间
        private V data; // 数据
        private PerformItemListener<V> listener;


        public DelayItem(long delayTime, V data,PerformItemListener<V> listener) {
            this.expire = TimeUnit.MILLISECONDS.convert(delayTime, TimeUnit.MILLISECONDS) + System.currentTimeMillis();
            this.data = data;
            this.listener = listener;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(this.expire - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            return (int) (this.getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS));
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()){
                return false;
            }
            DelayItem<?> item = (DelayItem<?>) o;
            return expire == item.expire && Objects.equals(data, item.data);
        }

        @Override
        public int hashCode() {
            return Objects.hash(expire, data);
        }
    }

    public static void main(String[] args) {

        RandomTimeDelayQueueWrapper<String> wrapper = DelayQueueFactory.factory().getRandomTimeDelayQueueWrapper();
        DosomethingLister lister = new DosomethingLister();
        wrapper.put("1", lister,1, TimeUnit.SECONDS);
        wrapper.put("2", lister,10, TimeUnit.SECONDS);
        wrapper.put("3", lister,7, TimeUnit.SECONDS);
        wrapper.put("4", lister,3, TimeUnit.SECONDS);
        wrapper.put("5", lister,15, TimeUnit.SECONDS);


//        new Thread(() -> {
//            while (true) {
//                String s1 = null;
//
//                if (wrapper.shutDown) {
//                    break;
//                }
//
//                try {
//                    Thread.sleep(10);
//                    s1 = wrapper.take();
//                } catch (InterruptedException e) {
//                    Thread.currentThread().interrupt();
//                }
//                if (s1 != null) {
//                    System.out.println(s1);
//                }
//
//                if (wrapper.delayQueue.size() == 0) {
//                    wrapper.shutDown();
//                    System.out.println(wrapper.shutDown);
////                    wrapper.put("6", 1, TimeUnit.SECONDS);
//                }
//            }
//        }).start();

    }


    public static class DosomethingLister implements PerformItemListener<String>{
        @Override
        public void perform(String item) {
            System.out.println("Item:" + item);
        }
    }
}
