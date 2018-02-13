# HW 2. Calculate size of Java object.

Write a class to measure object size in Java memory. Measure the size of
empty string and empty array. Find out the growth of array size depending
on the number of the elements it contains.

For example
- Object: 8 bytes
- Empty String: 40 bytes
- Array: from 12 bytes

Application utilises two additional tools to measure object size:
1. Java Object Layout by OpenJDK
2. Java Instrumentation

To run application type in the command line and hit enter:
```mvn clean package && java -javaagent:target/hw-02-java-object-size.jar -jar target/hw-02-java-object-size.jar```
