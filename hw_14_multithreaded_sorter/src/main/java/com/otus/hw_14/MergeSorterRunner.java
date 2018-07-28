package com.otus.hw_14;

public class MergeSorterRunner implements Runnable
{
    private int[] a;
    private int threadCount;

    public MergeSorterRunner(final int[] a, final int threadCount)
    {
        this.a = a;
        this.threadCount = threadCount;
    }

    @Override
    public void run()
    {
        MergeSorter.concurrentMergeSort(a, threadCount);
    }
}
