package com.otus.hw02.jol;

import org.openjdk.jol.info.ClassLayout;

import static java.lang.System.out;

/**
 * {@code JavaObjectLayoutSimpleTest} class to output Java object size
 * using OpenJDK JOL library. Simply run it in the IDE to see the results.
 */
public class JavaObjectLayoutSimpleTest
{
    public static void main(String[] args)
    {
        out.println(ClassLayout.parseClass(Object.class));
        out.println(ClassLayout.parseClass(String.class));
        out.println(ClassLayout.parseClass(int[].class));
        out.println(ClassLayout.parseClass(com.otus.hw02.myObjectSize.ObjectSizeDemo.MyClass.class));
        out.println(ClassLayout.parseClass(com.otus.hw02.myObjectSize.ObjectSizeDemo.MyClassWithString.class));
    }
}
