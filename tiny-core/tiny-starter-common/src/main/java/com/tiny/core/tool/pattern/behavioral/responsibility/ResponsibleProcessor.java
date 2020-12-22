package com.tiny.core.tool.pattern.behavioral.responsibility;

import java.util.function.UnaryOperator;

/**
 * @author Amin
 */
public abstract class ResponsibleProcessor<T> implements UnaryOperator<T> {


    @Override
    public T apply(T t) {
        if (decide(t)) {
            return processor(t);
        } else {
            return t;
        }

    }

    /**
     * 决定是否执行
     * @param t
     * @return
     */
    protected abstract boolean decide(T t);

    /**
     * 执行器
     * @param t
     * @return
     */
    protected abstract T processor(T t);
}
