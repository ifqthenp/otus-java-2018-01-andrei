package com.otus.hw_05.tests;

import com.otus.hw_05.annotations.After;
import com.otus.hw_05.annotations.Before;
import com.otus.hw_05.annotations.Test;

/**
 * {@code FrameworkThirdTest} class with several @Before and @After annotations.
 */
public class FrameworkThirdTest
{
    private String testString;

//    @Before
//    public void firstSetUp()
//    {
//        this.testString = "Hello, ";
//        System.out.println("@Before first: " + this.testString);
//    }

//    @Before
//    public void secondSetUp()
//    {
//        this.testString = "Hello, ";
//        System.out.println("@Before second: " + this.testString);
//    }

    @Test
    public void firstTestMethod()
    {
        this.testString += "Harry Hacker!";
        System.out.println("FrameworkThirdTest.firstTestMethod");
        System.out.println("@Test: " + this.testString);
    }

    @Test
    public void secondTestMethod()
    {
        this.testString += "Carl Cracker!";
        System.out.println("FrameworkThirdTest.secondTestMethod");
        System.out.println("@Test: " + this.testString);
    }

    @Test
    public void thirdTestMethod()
    {
        this.testString += "Tony Tester!";
        System.out.println("FrameworkThirdTest.thirdTestMethod");
        System.out.println("@Test: " + this.testString);
    }

    public void fourthTestMethod()
    {
        System.out.println("FrameworkThirdTest.fourthTestMethod");
        System.err.println("Fail. Should not be invoked.");
    }

//    @After
//    public void firstTearDown()
//    {
//        this.testString = null;
//        System.out.println("@After first: " + null + "\n");
//    }

//    @After
//    public void secondTearDown()
//    {
//        this.testString = null;
//        System.out.println("@After second: " + null + "\n");
//    }

}
