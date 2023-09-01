package com.nhnacademy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyLogback {

    private static final Logger logger = LoggerFactory.getLogger(MyLogback.class);

    public static int sum(int a, int b) {
        logger.trace("테스트 용 = {}, {}", a, b);
        logger.info("덧셈 연산 수행 : {} + {}", a, b);
        return a + b;
    }

    public static double divide(int a, int b) {
        if (b == 0) {
            logger.warn("나눗셈 연산 실패! 0으로 나눌 수 없습니다.");
            throw new ArithmeticException("0으로 나눌 수 없습니다.");
        }
        return a / b;
    }

    public static void main(String[] args) {
        int a = 1;
        int b = 2;
        System.out.println(sum(a, b));
        System.out.println(divide(a, 0));
        ;
    }
}

