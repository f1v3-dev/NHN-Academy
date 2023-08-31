package com.nhnacademy.app;

import java.util.Random;
import org.apache.commons.math3.random.RandomDataGenerator;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {

        Random random = new Random();
        int num1 = random.nextInt(100);
        System.out.println(num1);

        RandomDataGenerator randomDataGenerator = new RandomDataGenerator();
        int num2 = randomDataGenerator.nextInt(0, 100);
        System.out.println(num2);

    }
}
