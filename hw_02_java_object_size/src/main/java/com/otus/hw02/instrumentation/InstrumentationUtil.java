package com.otus.hw02.instrumentation;

import java.lang.instrument.Instrumentation;
import java.util.Arrays;
import java.util.function.Supplier;

public class InstrumentationUtil
{
    private static volatile Instrumentation instrumentation;

    public static void premain(final String args, final Instrumentation inst)
    {
        instrumentation = inst;
    }

    public static <T> long getObjectSize(Supplier<T> supplier)
    {
        if (instrumentation == null) {
            throw new IllegalStateException("Agent is not initialised.");
        }
        return instrumentation.getObjectSize(supplier.get());
    }

    public static void printInfo()
    {
        System.out.println(Arrays.toString(instrumentation.getAllLoadedClasses()));
    }
}
