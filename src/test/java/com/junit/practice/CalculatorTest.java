package com.junit.practice;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

class CalculatorTest {

    Calculator calculator;

    @Test
    void test_integerDivide() {
        //Arrange
        calculator = new Calculator();

        //Act
        int result = calculator.integerDivide(4, 2);

        //Assert
        assertEquals(2, result);
    }

    @ParameterizedTest
    @MethodSource()
    void test_integerDivide_Parameterized(int a, int b, int resultNumber) {
        //Arrange
        calculator = new Calculator();

        //Act
        int result = calculator.integerDivide(a, b);

        //Assert
        assertEquals(result, resultNumber);
    }

    private static Stream<Arguments> test_integerDivide_Parameterized() {
        return Stream.of(Arguments.of(4, 2, 2),
                Arguments.of(10, 5, 2));
    }


    @Test
    void test_integerDivide_throwArithmeticExceptionWhenDivideByZero() {
        //Arrange
        calculator = new Calculator();
        String errorMessage = "/ by zero";

        //Act & Assert
        ArithmeticException arithmeticException = assertThrowsExactly(ArithmeticException.class, () ->
                calculator.integerDivide(2, 0), "Should throw arithmetic exception");

        //Assert
        assertEquals(errorMessage, arithmeticException.getMessage());
    }

    @DisplayName("Integer Subtraction")
    @Test
    void test_integerSubtraction() {
        //Arrange
        calculator = new Calculator();

        //Act
        int result = calculator.integerSubtraction(2, 1);

        //Assert
        assertEquals(result, 1);
    }

    @DisplayName("Integer Subtraction Parameterized")
    @ParameterizedTest
    @CsvSource({"6,2,4",
            "10,2,8"})
    void test_integerSubtraction_Parameterized(int a, int b, int resultNumber) {
        //Arrange
        calculator = new Calculator();

        //Act
        int result = calculator.integerSubtraction(a, b);

        //Assert
        assertEquals(result, resultNumber);
    }

}