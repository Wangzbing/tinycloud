package com.tiny.core.tool.pattern.creational;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * 通用建造者模式实现，最多可以支持一个方法5个参数
 * @author Amin
 * @param <T>
 */
public class Builder<T> {

    private final Supplier<T> instantiator;

    private List<Consumer<T>> modifiers = new ArrayList<>();

    public Builder(Supplier<T> instantiator) {
        this.instantiator = instantiator;
    }

    public T build() {
        T value = instantiator.get();
        modifiers.forEach(modifier -> modifier.accept(value));
        modifiers.clear();
        return value;
    }

    public static <T> Builder<T> of(Supplier<T> instantiator) {
        return new Builder<>(instantiator);
    }

    public <P1> Builder<T> with(Consumer1<T, P1> consumer, P1 p1) {
        Consumer<T> c = instance -> consumer.accept(instance, p1);
        modifiers.add(c);
        return this;
    }

    public <P1,P2> Builder<T> with(Consumer2<T, P1, P2> consumer, P1 p1, P2 p2) {
        Consumer<T> c = instance -> consumer.accept(instance, p1,p2);
        modifiers.add(c);
        return this;
    }

    public <P1,P2,P3> Builder<T> with(Consumer3<T, P1, P2, P3> consumer, P1 p1, P2 p2, P3 p3) {
        Consumer<T> c = instance -> consumer.accept(instance, p1,p2,p3);
        modifiers.add(c);
        return this;
    }

    public <P1,P2,P3,P4> Builder<T> with(Consumer4<T, P1, P2, P3, P4> consumer, P1 p1, P2 p2
            , P3 p3, P4 p4) {
        Consumer<T> c = instance -> consumer.accept(instance, p1,p2,p3,p4);
        modifiers.add(c);
        return this;
    }

    public <P1,P2,P3,P4,P5> Builder<T> with(Consumer5<T, P1, P2, P3, P4, P5> consumer, P1 p1, P2 p2
            , P3 p3, P4 p4, P5 p5) {
        Consumer<T> c = instance -> consumer.accept(instance, p1,p2,p3,p4,p5);
        modifiers.add(c);
        return this;
    }

    /**
     * 1 参数 Consumer
     */
    @FunctionalInterface
    public interface Consumer1<T, P1> {
        void accept(T t, P1 p1);
    }

    /**
     * 2 参数 Consumer
     */
    @FunctionalInterface
    public interface Consumer2<T, P1, P2> {
        void accept(T t, P1 p1, P2 p2);
    }


    /**
     * 3 参数 Consumer
     */
    @FunctionalInterface
    public interface Consumer3<T, P1, P2, P3> {
        void accept(T t, P1 p1, P2 p2, P3 p3);
    }

    /**
     * 4 参数 Consumer
     */
    @FunctionalInterface
    public interface Consumer4<T, P1, P2, P3, P4> {
        void accept(T t, P1 p1, P2 p2, P3 p3, P4 p4);
    }

    /**
     * 5 参数 Consumer
     */
    @FunctionalInterface
    public interface Consumer5<T, P1, P2, P3, P4, P5> {
        void accept(T t, P1 p1, P2 p2, P3 p3, P4 p4, P5 p5);
    }
}
