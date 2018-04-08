package com.otus.hw_08.main;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.otus.hw_08.classes.ObjectProcessor;
import com.otus.hw_08.interfaces.JsonSerializer;
import com.otus.hw_08.testClasses.BagOfArrays;

public class JsonSerializerDemo
{
    public static void main(String[] args)
    {
        JsonSerializer processor = new ObjectProcessor();
        final String jsonFrom = processor.getJsonFrom(new BagOfArrays());
        System.out.println(jsonFrom);

        Gson gson = new GsonBuilder().create();
        final String toJson = gson.toJson(new BagOfArrays());
        System.out.println(toJson);
    }
}
