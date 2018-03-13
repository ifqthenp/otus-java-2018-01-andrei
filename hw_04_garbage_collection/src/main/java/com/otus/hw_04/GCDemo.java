package com.otus.hw_04;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

/**
 * VM options:
 * <p>
 * -agentlib:jdwp=transport=dt_socket,address=14000,server=y,suspend=n
 * -Xms64m
 * -Xmx64m
 * -XX:MaxMetaspaceSize=24m
 * -XX:+UseG1GC
 * -XX:+CMSParallelRemarkEnabled
 * -XX:+UseCMSInitiatingOccupancyOnly
 * -XX:CMSInitiatingOccupancyFraction=70
 * -XX:+ScavengeBeforeFullGC
 * -XX:+CMSScavengeBeforeRemark
 * -verbose:hw_04
 * -Xloggc:hw_04_garbage_collection/logs/gc_%p.log
 * -XX:+PrintGCDateStamps
 * -XX:+PrintGCDetails
 * -XX:+UseGCLogFileRotation
 * -XX:NumberOfGCLogFiles=10
 * -XX:GCLogFileSize=1M
 * -Dcom.sun.management.jmxremote.port=15000
 * -Dcom.sun.management.jmxremote.authenticate=false
 * -Dcom.sun.management.jmxremote.ssl=false
 * -XX:+HeapDumpOnOutOfMemoryError
 * -XX:HeapDumpPath=hw_04_garbage_collection/dumps/
 */
public class GCDemo
{
    public static void main(String[] args) throws Exception
    {
        System.out.println("Starting pid: " + ManagementFactory.getRuntimeMXBean().getName());

        int size = 5 * 1000 * 1000;

        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        ObjectName name = new ObjectName("com.otus:type=Benchmark");
        Benchmark mbean = new Benchmark();
        mbs.registerMBean(mbean, name);

        mbean.setSize(size);
        mbean.run();
    }
}
