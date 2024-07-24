package com.junit.practice;

import org.junit.jupiter.api.*;

@Order(2)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TestsOrderedByIndex {

    @Order(2)
    @Test
    void testB() {
        System.out.println("Running test B");
    }

    @Order(1)
    @Test
    void testA() {
        System.out.println("Running test A");
    }

    @Test
    void testC() {
        System.out.println("Running test C");
    }

    @Order(3)
    @Test
    void testD() {
        System.out.println("Running test D");
    }
}
