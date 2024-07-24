package com.junit.practice;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

@Order(1)
class TestsOrderedRandomly {

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
