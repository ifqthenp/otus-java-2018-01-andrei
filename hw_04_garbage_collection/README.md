# HW 4. GC Activity Log

Write an application that logs GC activity for each type (young, old) 
and the time spent for garbage collection in minutes.
- Make sure that the application crashes with ```OutOfMemory``` error
due to slow memory leak. For example, by constantly adding elements to
 ```List``` and removing only half of them.
- Get the application crashing with ```OutOfMemory``` error after 5
minutes have elapsed.
- Log the stats of GC activity (number of collections, time spent in
minutes) for different types of GC.

#### To get application running, type:**

```
$ mvn clean package
$ ./start.sh
```

To choose from different GC algorithms comment/uncomment ```GC``` variable 
in ```start.sh``` script then run the script again.

#### Available combinations of GC algorithms in HotSpot JVM for Java 8

| Young Collector | Old Collector | JVM Flag | 
|-----------------|---------------|---------
| Serial (DefNew) | Serial Mark Sweep Compact | -XX:+UseSerialGC
| Parallel scavenge (PSYoungGen) | Serial Mark Sweep Compact (PSOldGen) | -XX:+UseParallelGC
| Parallel scavenge (PSYoungGen) | Parallel Mark Sweep Compact (ParOldGen) | -XX:+UseParallelOldGC
| Parallel (ParNew) | Serial Mark Sweep Compact | -XX:+UseParNewGC
| Concurrent Mark Sweep | Serial (DefNew) | -XX:-UseParNewGC -XX:+UseConcMarkSweepGC 
| Parallel (ParNew) | Concurrent Mark Sweep | -XX:+UseParNewGC -XX:+UseConcMarkSweepGC 
| GarbageFirst (G1) | GarbageFirst (G1) | -XX:+UseG1GC 

#### Results

![-XX:+UseSerialGC](https://github.com/ifqthenp/files/blob/master/1%20Serial%20(DefNew)%20%26%20Serial%20Mark%20Sweep%20Compact.png?raw=true)

![-XX:+UseParallelGC](https://github.com/ifqthenp/files/blob/master/2.%20Parallel%20Scavenge%20(PSYoungGen)%20%26%20Serial%20Mark%20Sweep%20Compact%20(PSOldGen).png?raw=true)

![-XX:+UseParallelOldGC](https://github.com/ifqthenp/files/blob/master/3.%20Parallel%20Scavenge%20(PSYoungGen)%20%26%20Parallel%20Mark%20Sweep%20Compact%20(ParOldGen).png?raw=true)

![-XX:+UseParNewGC](https://github.com/ifqthenp/files/blob/master/4.%20Parallel%20(ParNew)%20%26%20Serial%20Mark%20Sweep%20Compact.png?raw=true)

![-XX:-UseParNewGC -XX:+UseConcMarkSweepGC](https://github.com/ifqthenp/files/blob/master/5.%20Serial%20(DefNew)%20%26%20Concurrent%20Mark%20Sweep.png?raw=true)

![-XX:+UseParNewGC -XX:+UseConcMarkSweepGC](https://github.com/ifqthenp/files/blob/master/6.%20Parallel%20(ParNew)%20%26%20Concurrent%20Mark%20Sweep.png?raw=true)

![-XX:+UseG1GC](https://github.com/ifqthenp/files/blob/master/7.%20Garbage%20First%20(G1).png?raw=true)





