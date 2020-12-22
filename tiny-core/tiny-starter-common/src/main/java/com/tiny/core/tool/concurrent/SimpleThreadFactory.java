package com.tiny.core.tool.concurrent;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Amin
 */
public final class SimpleThreadFactory implements ThreadFactory {


    private final String namePrefix;
    private final AtomicInteger nextId = new AtomicInteger(1);

    public SimpleThreadFactory(String whatFeaturOfGroup) {
        namePrefix = "From SimpleThreadFactory's " + whatFeaturOfGroup + "-Worker-";
    }

    /**
     * Constructs a new {@code Thread}.  Implementations may also initialize
     * priority, name, daemon status, {@code ThreadGroup}, etc.
     *
     * @param task a runnable to be executed by new thread instance
     * @return constructed thread, or {@code null} if the request to
     * create a thread is rejected
     */
    @Override
    public Thread newThread(Runnable task) {
        String name = namePrefix + nextId.getAndIncrement();
        return new Thread(null, task, name, 0);
    }
}
