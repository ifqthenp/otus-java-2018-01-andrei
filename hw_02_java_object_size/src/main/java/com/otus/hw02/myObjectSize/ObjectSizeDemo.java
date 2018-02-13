package com.otus.hw02.myObjectSize;

import com.otus.hw02.instrumentation.InstrumentationUtil;

import java.lang.management.ManagementFactory;

import static java.lang.System.out;

/**
 * Class to demonstrate the size of Java object.
 */
@SuppressWarnings({"RedundantStringConstructorCall", "InfiniteLoopStatement"})
public class ObjectSizeDemo
{
    private static final int SIZE = 100_000;

    public static void main(String[] args)
    {
        out.println("pid: " + ManagementFactory.getRuntimeMXBean().getName());

        agentObjectSizePrinter();

        myObjectSizePrinter();
    }

    public static void myObjectSizePrinter()
    {
        System.out.println("--- My object size solution ---");
        ObjectSizeMeasurer osm = new ObjectSizeMeasurer();

        osm.getObjectSize(Object::new, SIZE);
        osm.getObjectSize(() -> new String(""), SIZE);
        osm.getObjectSize(() -> new String(new char[]{}), SIZE);

        osm.getObjectSize(MyClass::new, SIZE);
        osm.getObjectSize(MyClassWithString::new, SIZE);

        osm.getObjectSize(() -> new int[0], SIZE);
        osm.getObjectSize(() -> new int[100], SIZE);
    }

    public static void agentObjectSizePrinter()
    {
        System.out.println("--- Java Instrumentation object size ---");

        final String obj = Object.class.getCanonicalName();
        final long objSize = InstrumentationUtil.getObjectSize(Object::new);
        out.println(String.format("%-60s%d", obj, objSize));

        final String str = String.class.getCanonicalName();
        final long strSize = InstrumentationUtil.getObjectSize(() -> new String(""));
        out.println(String.format("%-60s%d", str, strSize));

        final String strChar = String.class.getCanonicalName();
        final long strCharSize = InstrumentationUtil.getObjectSize(() -> new String(new char[]{}));
        out.println(String.format("%-60s%d", strChar, strCharSize));

        final String myClass = MyClass.class.getCanonicalName();
        final long myClassSize = InstrumentationUtil.getObjectSize(MyClass::new);
        out.println(String.format("%-60s%d", myClass, myClassSize));

        final String myClassStr = MyClassWithString.class.getCanonicalName();
        final long myClassStrSize = InstrumentationUtil.getObjectSize(MyClassWithString::new);
        out.println(String.format("%-60s%d", myClassStr, myClassStrSize));

        final String intArr = int[].class.getCanonicalName();
        final long intArrSize = InstrumentationUtil.getObjectSize(() -> new int[0]);
        out.println(String.format("%-60s%d%n", intArr, intArrSize));
    }

    public static class MyClass
    {
        private int i = 0;
        private long l = 1L;
    }

    public static class MyClassWithString
    {
        private int i = 0;
        private long l = 1L;
        private String s = "";
    }
}
