package com.otus.hw_08.main;

import com.otus.hw_08.classes.ObjectProcessor;
import com.otus.hw_08.testClasses.BagOfArrays;

public class JsonSerializerDemo
{
    public static void main(String[] args)
    {
        ObjectProcessor processor = new ObjectProcessor();

        System.out.println(processor.process(new BagOfArrays()));
    }
}
