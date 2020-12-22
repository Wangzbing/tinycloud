package com.tiny.core.tool.pattern.behavioral.responsibility;


import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * @author Amin
 */
public class ProcessorPipeline<T> {

    private List<ResponsibleProcessor<T>> processorContainer = new ArrayList<>();

    public ProcessorPipeline<T> addProcessor(ResponsibleProcessor<T> processor) {
        if (processor != null) {
            processorContainer.add(processor);
        }

        return this;
    }

    public T execute(T t) {

        if (processorContainer.isEmpty()) {
            return t;
        }
        Function<T, T> pipeline = processorContainer.remove(0);
        for (ResponsibleProcessor<T> p: processorContainer) {
            pipeline = pipeline.andThen(p);
        }

        return pipeline.apply(t);
    }

    public static void main(String[] args) {

        ResponsibleProcessor<String> processor1 = new ResponsibleProcessor<String>() {
            @Override
            protected boolean decide(String s) {
                return true;
            }

            @Override
            protected String processor(String s) {
                return "s1:" + s;
            }
        };


        ResponsibleProcessor<String> processor2 = new ResponsibleProcessor<String>() {
            @Override
            protected boolean decide(String s) {
                return true;
            }

            @Override
            protected String processor(String s) {
                System.out.println("s2:" + s);
                return "s2:" + s;
            }
        };


        ResponsibleProcessor<String> processor3 = new ResponsibleProcessor<String>() {
            @Override
            protected boolean decide(String s) {
                return true;
            }

            @Override
            protected String processor(String s) {
                System.out.println("s3:" + s);
                return "s3:" + s;
            }
        };

        ResponsibleProcessor<String> processor4 = new ResponsibleProcessor<String>() {
            @Override
            protected boolean decide(String s) {
                return true;
            }

            @Override
            protected String processor(String s) {
                System.out.println("s4:" + s);
                return "s4:" + s;
            }
        };


        String s = new ProcessorPipeline<String>()
                .addProcessor(processor1)
                .addProcessor(processor2)
                .addProcessor(processor3)
                .addProcessor(processor4)
                .execute("soooooo");

        System.out.println("last result:"+s);
    }
}
