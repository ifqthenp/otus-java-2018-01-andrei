package com.otus.hw_04;

import com.sun.management.GarbageCollectionNotificationInfo;

import javax.management.NotificationEmitter;
import javax.management.NotificationListener;
import javax.management.openmbean.CompositeData;
import java.lang.management.GarbageCollectorMXBean;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Benchmark implements BenchmarkMBean
{
    private static ArrayList<String> arrayList = new ArrayList<>();
    private volatile int size = 0;

    @SuppressWarnings("InfiniteLoopStatement")
    void run() throws InterruptedException
    {
        while (true) {

            for (int i = 0; i < 6; i++) {
                arrayList.add(String.valueOf(new char[0]));
            }

            arrayList.remove(arrayList.size() - 1);

            try (Stream<GarbageCollectorMXBean> gcMXBeanStream = ManagementFactory.getGarbageCollectorMXBeans().stream()) {
                gcMXBeanStream
                    .collect(Collectors.groupingBy(
                                     GarbageCollectorMXBean::getName,
                                     summingLong(GarbageCollectorMXBean::getCollectionTime)))
                    .forEach((s, aLong) -> System.out.printf("%-20s: %s min %n", s, new BigDecimal(aLong / 1000.00 / 60).setScale(6, BigDecimal.ROUND_HALF_UP)));
            }

            Thread.sleep(0, 1);
        }
    }

    @Override
    public int getSize()
    {
        return this.size;
    }

    @Override
    public void setSize(final int size)
    {
        this.size = size;
    }
}
