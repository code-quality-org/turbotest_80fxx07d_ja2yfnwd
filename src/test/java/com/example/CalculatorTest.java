package com.example;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CalculatorTest {

    private final Calculator calc = new Calculator();

    @Test
    void add() {
        assertEquals(5, calc.add(2, 3));
    }

    @Test
    void subtract() {
        assertEquals(1, calc.subtract(3, 2));
    }

    @Test
    void multiply() {
        assertEquals(6, calc.multiply(2, 3));
    }

    @Test
    void divide() {
        assertEquals(2, calc.divide(6, 3));
    }

    @Test
    void divideByZero() {
        assertThrows(ArithmeticException.class, () -> calc.divide(1, 0));
    }

    @Test
    void modulo() {
        assertEquals(1, calc.modulo(7, 3));
    }

    @Test
    void power() {
        assertEquals(8, calc.power(2, 3));
    }

    @Test
    void powerOfZero() {
        assertEquals(1, calc.power(5, 0));
    }
}
