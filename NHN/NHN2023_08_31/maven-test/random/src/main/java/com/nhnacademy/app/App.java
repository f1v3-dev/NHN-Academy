package com.nhnacademy.app;

import java.util.Objects;
import java.util.Random;
import org.apache.commons.math3.random.RandomDataGenerator;
import org.apache.commons.lang3.StringUtils;

public class App {
    public static void main(String[] args) {

        Random random = new Random();
        int num1 = random.nextInt(100) + 1;
        System.out.println(num1);

        RandomDataGenerator randomGenerator = new RandomDataGenerator();
        int num2 = randomGenerator.nextInt(1, 100);
        System.out.println(num2);

        String str = "";
        stringIsEmpty(str);
        stringUtilsIsEmpty(str);

    }

    public static void stringIsEmpty(String str) {
        // null or empty()
        if (Objects.isNull(str) || str.isEmpty()) {
            System.out.println("빈 문자열 입니다!");
        } else {
            System.out.println("빈 문자열이 아닙니다.");
        }
    }

    public static void stringUtilsIsEmpty(String str) {
        // StringUtils.isEmpty() -> null && empty 둘 다 검사
        if (StringUtils.isEmpty(str)) {
            System.out.println("빈 문자열 입니다!");
        } else {
            System.out.println("빈 문자열이 아닙니다.");
        }
    }
}
