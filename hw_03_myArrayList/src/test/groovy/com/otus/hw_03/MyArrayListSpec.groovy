package com.otus.hw_03

import spock.lang.Specification

/**
 * {@code MyArrayListSpec} test class.
 */
class MyArrayListSpec extends Specification {

    private MyArrayList<Object> myArrayList

    def setup() {
        myArrayList = new MyArrayList<>()
        assert myArrayList != null
        assert myArrayList.size() == 0
        assert myArrayList.isEmpty()
    }

    def 'constructed list is MyArrayList.class'() {
        expect:
        myArrayList.class == MyArrayList.class
    }

    def 'throws exception if list capacity is negative value'() {
        when:
        myArrayList = new MyArrayList<>(-1)

        then:
        thrown(IllegalArgumentException)
    }

    def "add() method adds element to end of the list"() {
        when:
        myArrayList.add("one")
        myArrayList.add("two")
        myArrayList.add("three")

        then:
        !myArrayList.isEmpty()
        myArrayList.size() == 3
        myArrayList.get(2) == "three"
    }

    def "get(int index) throws exception if 'index' is out of array boundary"() {
        when:
        myArrayList.add("one")
        myArrayList.add("two")
        myArrayList.add("three")
        myArrayList.get(3)

        then:
        thrown(IndexOutOfBoundsException)
    }

    def "get(int index) returns correct element at specified index"() {
        when:
        myArrayList.add("one")
        myArrayList.add("two")
        myArrayList.add("three")

        then:
        myArrayList.get(0) == "one"
        myArrayList.get(2) == "three"
        myArrayList.get(1) == "two"
    }

    def "array can grow in size when necessary"() {
        setup:
        int arraySize = 50
        for (int i = 0; i < arraySize; i++) {
            myArrayList.add(i)
        }

        expect:
        !myArrayList.isEmpty()
        myArrayList.size() == arraySize
    }

    def "remove(Object o) method removes first occurrence of specified element"() {
        setup:
        myArrayList.add("Alice")
        myArrayList.add("Bob")
        myArrayList.add("Alice")

        when:
        myArrayList.remove("Alice")

        then:
        myArrayList.size() == 2
        myArrayList.toString() == "[Bob, Alice]"
    }

    def "remove(Object o) returns true if element removed successfully"() {
        setup:
        myArrayList.add("Alice")
        myArrayList.add("Bob")
        myArrayList.add("Alice")

        expect:
        myArrayList.remove("Alice")
        myArrayList.size() == 2
        !myArrayList.remove("Tom")
    }

    def "toString() method returns correct string representation of the list"() {
        when:
        myArrayList.add("Alice")
        myArrayList.add("Bob")
        myArrayList.add("Alice")

        then:
        myArrayList.toString() == "[Alice, Bob, Alice]"
    }

    def "contains(Object o) returns true if list holds specified element"() {
        when:
        myArrayList.add("Alice")
        myArrayList.add("Bob")
        myArrayList.add("Bob")
        myArrayList.add(null)
        myArrayList.add("Tom")


        then:
        myArrayList.contains("Alice")
        myArrayList.contains("Bob")
        myArrayList.contains(null)
        !myArrayList.contains("Carl")
    }

    def "indexOf(Object o) method can find an index of specified element"() {
        setup:
        myArrayList.add("Alice")
        myArrayList.add("Bob")
        myArrayList.add("Bob")
        myArrayList.add(null)
        myArrayList.add("Tom")

        expect:
        myArrayList.indexOf(object) == result

        where:
        object  || result
        "Alice" || 0
        "Bob"   || 1
        "Tom"   || 4
        null    || 3
        "Bob"   || 1
        "Carl"  || -1
    }


    def "lastIndexOf(Object o) method can find the index of the last occurrence of the specified element"() {
        setup:
        myArrayList.add("Alice")
        myArrayList.add("Bob")
        myArrayList.add(null)
        myArrayList.add("Bob")
        myArrayList.add(null)
        myArrayList.add("Alice")
        myArrayList.add("Tom")
        myArrayList.add(null)

        expect:
        myArrayList.lastIndexOf(object) == result

        where:
        object  || result
        "Alice" || 5
        "Harry" || -1
        "Bob"   || 3
        "Tom"   || 6
        null    || 7
        "Bob"   || 3
        "Carl"  || -1
    }

    def "clear() method removes all of the elements from the list"() {
        setup:
        myArrayList.add("Alice")
        myArrayList.add("Bob")
        myArrayList.add(null)
        myArrayList.add("Bob")

        when:
        myArrayList.clear()

        then:
        myArrayList.size() == 0
        myArrayList.isEmpty()
    }

    def "list can grow after it has been cleared"() {
        setup:
        myArrayList.add("Alice")
        myArrayList.add("Bob")
        myArrayList.add(null)
        myArrayList.clear()
        myArrayList.add("Tom")
        myArrayList.add("Carl")
        myArrayList.add("Harry")
        myArrayList.add("Harry")

        expect:
        myArrayList.size() == 4
        !myArrayList.isEmpty()
    }

    def "toArray() can return new array based on the list"() {
        setup:
        myArrayList.add("Tom")
        myArrayList.add("Carl")
        myArrayList.add("Harry")
        myArrayList.add("Harry")

        expect:
        myArrayList.toArray().class == new Object[0].class
        myArrayList.size() == myArrayList.toArray().length
        myArrayList.toArray().toString() == "[Tom, Carl, Harry, Harry]"
    }

    def "set(int i, E e) can set an element to specified index"() {
        setup:
        myArrayList.add("Alice")
        myArrayList.add("Bob")
        myArrayList.add(null)
        myArrayList.add(null)

        expect:
        myArrayList.set(index, element) == returnedElement
        myArrayList.get(index) == newElement

        where:
        index | element || returnedElement | newElement
        0     | "Tom"   || "Alice"         | "Tom"
        1     | "Alice" || "Bob"           | "Alice"
        2     | "Carl"  || null            | "Carl"
        3     | null    || null            | null
    }

    def "add(int i, E e) can add an element at specified index"() {
        setup:
        myArrayList.add("Alice")
        myArrayList.add("Bob")
        myArrayList.add("Tom")

        expect:
        myArrayList.add(index, element)
        myArrayList.size() == newSize
        myArrayList.get(index) == newElement


        where:
        index | element || newElement | newSize
        0     | "Tom"   || "Tom"      | 4
        1     | "Harry" || "Harry"    | 4
        2     | "Tony"  || "Tony"     | 4
        3     | "Alice" || "Alice"    | 4
    }

    def "remove(int i) can remove an element at specified index"() {
        setup:
        myArrayList.add("Alice")    // 0
        myArrayList.add("Bob")      // 1
        myArrayList.add(null)       // 2
        myArrayList.add("Tom")      // 3
        myArrayList.add("Carl")     // 4

        expect:
        myArrayList.remove(index) == removedEl
        myArrayList.size() == 4

        where:
        index || removedEl
        2     || null
        4     || "Carl"
        0     || "Alice"
        3     || "Tom"
        1     || "Bob"
    }

    def "containsAll method returns true if all elements of the given collection can be found in this list"() {
        setup:
        myArrayList.add("Alice")    // 0
        myArrayList.add("Bob")      // 1
        myArrayList.add(null)       // 2
        myArrayList.add("Tom")      // 3
        myArrayList.add("Carl")     // 4
        myArrayList.add("Harry")    // 5

        Collection coll = new LinkedList()
        coll.add("Bob")      // 1
        coll.add(null)       // 2
        coll.add("Tom")      // 3
        coll.add("Carl")     // 4

        expect:
        myArrayList.containsAll(coll)
    }

    def "MyArrayList<String> can be sorted by Collections sort method"() {
        setup:
        List<String> strings = new MyArrayList<>()
        strings.add("Alice")
        strings.add("Bob")
        strings.add("Tom")
        strings.add("Harry")
        strings.add("Carl")

        expect:
        Collections.sort(strings)
        strings == ["Alice", "Bob", "Carl", "Harry", "Tom"]
    }

    def "MyArrayList<Integer> can be sorted by Collections sort method"() {
        setup:
        List<Integer> integers = new MyArrayList<>()
        integers.add(-3)
        integers.add(-30)
        integers.add(20)
        integers.add(0)
        integers.add(5)

        expect:
        Collections.sort(integers)
        integers == [-30, -3, 0, 5, 20]
    }

    def "addAll(Collection c) can append elements of specified collection to end of this list"() {
        setup:
        myArrayList.add("Tom")
        myArrayList.add("Carl")
        myArrayList.add("Harry")

        List<String> stringList = new LinkedList<>()
        stringList.add(null)
        stringList.add("Alice")
        stringList.add("Bob")

        expect:
        myArrayList.addAll(stringList)
        myArrayList == ["Tom", "Carl", "Harry", null, "Alice", "Bob"]
    }

    def "addAll method can add collection to an empty list"() {
        setup:
        List<String> coll = new MyArrayList<>()
        coll.add(null)
        coll.add("Alice")
        coll.add("Bob")
        coll.add("Tony")
        coll.add(null)

        and:
        myArrayList.addAll(coll)

        expect:
        myArrayList.size() == coll.size()
        myArrayList.containsAll(coll)
        myArrayList == [null, "Alice", "Bob", "Tony", null]
    }

    def "Collections.copy method can make a copy of list according to the method documentation"() {
        setup:
        List<String> myArrayList = new MyArrayList<>()
        myArrayList.add("Tom")
        myArrayList.add("Carl")
        myArrayList.add(null)

        List<Object> dest = new MyArrayList<>()
        dest.add(null)
        dest.add("Alice")
        dest.add("Bob")
        dest.add("Tony")
        dest.add(null)

        and:
        Collections.copy(dest, myArrayList)

        expect:
        dest.size() == 5
        dest == ["Tom", "Carl", null, "Tony", null]
    }

    def "Collections.addAll(Collection<? super T> c, T... elements) can add list to empty collection"() {
        setup:
        Collections.addAll(myArrayList, "1", null, "3")

        expect:
        !myArrayList.isEmpty()
        myArrayList.size() == 3
        myArrayList == ["1", null, "3"]
    }


    def "Collections.addAll(Collection<? super T> c, T... elements) can add list to an existing collection"() {
        setup:
        myArrayList.add("Alice")
        myArrayList.add("Bob")
        myArrayList.add(null)
        myArrayList.add("Tony")

        and:
        String[] strings = ["Harry", "Carl", "Tom", null] as String[]
        Collections.addAll(myArrayList, strings)

        expect:
        !myArrayList.isEmpty()
        myArrayList.size() == 8
        myArrayList.containsAll(strings)
        myArrayList == ["Alice", "Bob", null, "Tony", "Harry", "Carl", "Tom", null]
    }
}
