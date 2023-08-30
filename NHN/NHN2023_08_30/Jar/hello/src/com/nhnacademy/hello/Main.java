package com.nhnacademy.hello;

import com.nhnacademy.hello.util.Calculator;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        System.out.println("Plus: " + Calculator.plus(20, 10));
        System.out.println("Subtract: " + Calculator.subtract(20, 10));
        System.out.println("Divide: " + Calculator.divide(20, 10));
        System.out.println("Multiply: " + Calculator.multiply(20, 10));
    }

}