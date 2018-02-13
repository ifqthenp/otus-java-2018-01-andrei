package com.otus.hw02.instrumentation;

import java.lang.instrument.Instrumentation;
import java.util.function.Supplier;

public class InstrumentationUtil
{
    private static Instrumentation instrumentation;

    public static void premain(String args, Instrumentation inst)
    {
        instrumentation = inst;
    }

    public static <T> long getObjectSize(Supplier<T> supplier)
    {
        return instrumentation.getObjectSize(supplier.get());
    }
}
