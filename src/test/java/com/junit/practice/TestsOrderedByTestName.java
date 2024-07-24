package com.junit.practice;

import org.junit.jupiter.api.*;

class TestsOrderedByTestName {

    @Test
    void test1() {
        System.out.println("Running test 1");
    }

    @Test
    void testA() {
        System.out.println("Running test A");
    }

    @Test
    void testB() {
        System.out.println("Running test B");
    }

    @Test
    void testC() {
        System.out.println("Running test C");
    }

    @Test
    void testD() {
        System.out.println("Running test D");
    }
}
