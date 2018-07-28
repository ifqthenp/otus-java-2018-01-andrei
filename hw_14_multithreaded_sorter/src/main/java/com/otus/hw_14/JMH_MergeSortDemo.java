package com.otus.hw_14;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(value = { Mode.AverageTime, Mode.SingleShotTime })
@Warmup(iterations = 2)
@Measurement(iterations = 5)
@OutputTimeUnit(TimeUnit.SECONDS)
@State(Scope.Thread)
public class JMH_MergeSortDemo
{
    private static final int SIZE = 10_000_000;
    private static final int RANDOM_VALUE_BOUNDARY = 2_000_000;

    private int[] array;

    @Setup
    public void prepare()
    {
        array = ArrayUtil.randomIntArray(SIZE, RANDOM_VALUE_BOUNDARY);
    }

    @Benchmark
    public void testArrayWithJavaLibrarySort()
    {
        Arrays.sort(array);
    }

    @Benchmark
    public void testArrayWithJavaLibraryParallelSort()
    {
        Arrays.parallelSort(array);
    }

    @Benchmark
    public void testArrayWithMyConcurrentMergeSorter()
    {
        MergeSorter.concurrentMergeSort(array);
    }

    public static void main(String[] args) throws RunnerException
    {
        Options opt = new OptionsBuilder()
                .include(JMH_MergeSortDemo.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();
    }
}
