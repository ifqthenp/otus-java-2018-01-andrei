package com.otus.hw_14;

import java.util.Arrays;

public class MergeSorter
{
    public static void concurrentMergeSort(final int[] a)
    {
        final int threads = 4;
        concurrentMergeSort(a, threads);
    }

    public static void concurrentMergeSort(final int[] a, final int threadCount)
    {
        if (threadCount <= 1) {
            sort(a);
        } else if (a.length >= 2) {
            int[] first = Arrays.copyOfRange(a, 0, a.length / 2);
            int[] second = Arrays.copyOfRange(a, a.length / 2, a.length);

            Thread leftThread = new Thread(new MergeSorterRunner(first, threadCount / 2));
            Thread rightThread = new Thread(new MergeSorterRunner(second, threadCount / 2));
            leftThread.start();
            rightThread.start();

            try {
                leftThread.join();
                rightThread.join();
            }
            catch (InterruptedException ignored) {
            }

            merge(first, second, a);
        }
    }

    public static void sort(int[] a)
    {
        if (a.length <= 1) {
            return;
        }
        int[] first = new int[a.length / 2];
        int[] second = new int[a.length - first.length];
        System.arraycopy(a, 0, first, 0, first.length);
        System.arraycopy(a, first.length, second, 0, second.length);
        sort(first);
        sort(second);
        merge(first, second, a);
    }

    private static void merge(int[] first, int[] second, int[] a)
    {
        int iFirst = 0;
        int iSecond = 0;
        int j = 0;

        while (iFirst < first.length && iSecond < second.length) {
            if (first[iFirst] < second[iSecond]) {
                a[j] = first[iFirst];
                iFirst++;
            } else {
                a[j] = second[iSecond];
                iSecond++;
            }
            j++;
        }

        while (iFirst < first.length) {
            a[j] = first[iFirst];
            iFirst++;
            j++;
        }

        while (iSecond < second.length) {
            a[j] = second[iSecond];
            iSecond++;
            j++;
        }
    }
}
