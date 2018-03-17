package com.otus.hw_05.tests;

import com.otus.hw_05.annotations.Test;

public class ExceptionTest
{
    @Test
    public void fail()
    {
        throw new IllegalStateException("Some error");
    }
}
