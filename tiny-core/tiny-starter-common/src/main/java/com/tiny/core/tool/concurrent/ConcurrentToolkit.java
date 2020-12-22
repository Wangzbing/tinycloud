package com.tiny.core.tool.concurrent;

import java.util.concurrent.*;

public final class ConcurrentToolkit {


    private static int nThreads = Runtime.getRuntime().availableProcessors();

    public static ExecutorService newThreadPool(String threadGruopName) {
        return newThreadPool(threadGruopName, nThreads, nThreads * 2);
    }

    public static ExecutorService newThreadPool(String threadGruopName, int corePoolSize, int maximumPoolSize) {
        return new ThreadPoolExecutor(corePoolSize, maximumPoolSize, 3L, TimeUnit.MINUTES,
                new LinkedBlockingQueue<>(), new SimpleThreadFactory(threadGruopName));
    }

    public static ExecutorService newThreadPool(String threadGruopName, int corePoolSize, int maximumPoolSize,
                                                RejectedExecutionHandler rejectedExecutionHandler) {
        return new ThreadPoolExecutor(corePoolSize, maximumPoolSize, 3L, TimeUnit.MINUTES,
                new LinkedBlockingQueue<>(), new SimpleThreadFactory(threadGruopName),rejectedExecutionHandler);
    }

    public static <T> CompletionService<T> newCompletionService() {
        return new ExecutorCompletionService<>(newThreadPool("CompletionService-Thread-Pool"));
    }

    public static <T> CompletionService<T> newCompletionService(ExecutorService executor) {
        return new ExecutorCompletionService<>(executor);
    }
}
