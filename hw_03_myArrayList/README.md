# HW 2. Array-based implementation of List interface

```class MyArrayList<T> implements List<T>{...}```

Make sure that the following methods from ```java.utils.Collections```
 class work work correctly with your implementation of ```ArrayList```:

- ```static <T> boolean addAll(Collection<? super T> c, T... elements)```
- ```static <T> void copy(List<? super T> dest, List<? extends T> src)```
- ```static <T> void sort(List<T> list, Comparator<? super T> c)```

To run tests type:

```
$ mvn clean test
```

Tests are implemented using groovy-based Spock framework.
