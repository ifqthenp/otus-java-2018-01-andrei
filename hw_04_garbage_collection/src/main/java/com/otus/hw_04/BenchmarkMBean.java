package com.otus.hw_04;

public interface BenchmarkMBean
{
    int getSize();

    void setSize(int size);

    void subscribeToGcEvents();
}
