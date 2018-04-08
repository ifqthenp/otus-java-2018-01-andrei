package com.otus.hw_08.classes

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.otus.hw_08.testClasses.*
import spock.lang.Specification

import java.awt.*

class ObjectProcessorImpSpec extends Specification {

    Gson gson
    ObjectProcessor processor

    void setup() {
        gson = new GsonBuilder().create()
        processor = new ObjectProcessor()
        assert processor != null
    }

    def "Object processor returns correct result if object is null"() {
        expect:
        processor.getJsonFrom(null) == gson.toJson(null).toString()
    }

    def "Object processor returns correct result if object is primitive type"() {
        expect:
        processor.getJsonFrom(primitive) == gson.toJson(primitive).toString()

        where:
        primitive        || result
        1 as int         || _
        0 as int         || _
        -1 as int        || _
        2.35 as double   || _
        0.0 as double    || _
        -2.35 as double  || _
        'a' as char      || _
        97 as char       || _
        'abc' as String  || _
        '' as String     || _
        true as boolean  || _
        false as boolean || _
        5 as short       || _
        50 as byte       || _
        5.5 as float     || _
        1000L as long    || _
    }

    def "Object processor returns correct result if object is an array"() {
        expect:
        processor.getJsonFrom(array) == gson.toJson(array).toString()

        where:
        array                                                                                                                                        || result
        [Integer.MAX_VALUE, Integer.MIN_VALUE] as Integer[]                                                                                          || _
        [-101.53443, 2.5, 0.0, Double.MIN_VALUE] as Double[]                                                                                         || _
        [Character.MAX_VALUE, Character.MIN_VALUE, null] as Character[]                                                                              || _
        ["abc", "Hello", "", '', 'World'] as String[]                                                                                                || _
        [true, false, true, true, false] as Boolean[]                                                                                                || _
        [Short.MAX_VALUE, Short.MIN_VALUE, null] as Short[]                                                                                          || _
        [Byte.MAX_VALUE, Byte.MIN_VALUE] as Byte[]                                                                                                   || _
        [Long.MAX_VALUE, Long.MIN_VALUE] as Long[]                                                                                                   || _
        [new Object(), null, new Object()] as Object[]                                                                                               || _
        [new Person(), new Person(), new Person()] as Person[]                                                                                       || _
        [new BagOfObjects(new Person(), new Object()), new BagOfObjects(new Person("John", 33), new Object()), new BagOfObjects()] as BagOfObjects[] || _
    }

    def "Object processor returns correct result if object is collection"() {
        expect:
        processor.getJsonFrom(collection) == gson.toJson(collection).toString()

        where:
        collection                                || result
        [Integer.MAX_VALUE, Integer.MIN_VALUE, 0] || _
        ["abc", null, "xyz"]                      || _
        [true, null, false].toSet()               || _
    }

    def "Object processor returns correct result if argument is an object"() {
        expect:
        processor.getJsonFrom(obj) == gson.toJson(obj).toString()

        println processor.getJsonFrom(obj)

        where:
        obj                                                    || result
        null                                                   || _
        new Object()                                           || _
        new BagOfPrimitives()                                  || _
        new BagOfPrimitives(-10, null, Integer.MAX_VALUE)      || _
        new Person()                                           || _
        new Person("John", 33)                                 || _
        new BagOfObjects()                                     || _
        new BagOfObjects(new Person(), new Object())           || _
        new BagOfObjects(new Person("John", 33), new Object()) || _
        new BagOfArrays()                                      || _
        new Point()                                            || _
        new ArrayList()                                        || _
        new File("")                                           || _
        new BigDecimal("1000000000000000000")                  || _
        new Employee("John Smith", 55, 999)                    || _
        new Employee(999)                                      || _
        new Employee()                                         || _
    }
}
