package com.nhnacademy.exam;

import java.util.function.DoublePredicate;

public class Mathx {
    private Mathx() {
    }

    public static int fibonacci(int n) {

        switch (n) {
            case 0:
            case 1:
                return n;
            case -1:
                throw new IllegalArgumentException("fibonacci: n < 0");
        }
        return Math.addExact(fibonacci(n - 1), fibonacci(n - 2));
    }

    public static double sphereVolume(double r) {
        if (r == 0) {
            throw new IllegalArgumentException();
        }
        return ((4.0 / 3.0) * Math.pow(r, 3) * Math.PI);
    }

    public static boolean equals(double radius, double result) {
        return (radius == result);

    }

    public static long sum(Range range) {
        final var max = range.max();
        final var min = range.min();
        return (max - min + 1) * (max + min) / 2;
    }

    public static double reduce(java.util.function.DoubleBinaryOperator binaryOperator, double init,
                                IteratorAsDouble iterator) {
        double result = init;
        while (iterator.hasMore()) {
            result = binaryOperator.applyAsDouble(result, iterator.next());
        }
        return result;
    }

    public static double product(IteratorAsDouble numbers) {
        return reduce((x, y) -> x * y, 1.0, numbers);
    }

    public static double sum(IteratorAsDouble numbers) {
        return reduce((x, y) -> x + y, 0.0, numbers);
    }

    public static double product(double... numbers) {
        return reduce((x, y) -> x * y, 1.0, IteratorAsDouble.of(numbers));
    }

    public static final DoublePredicate even = x -> x % 2 == 0;

    public static double sum(double... numbers) {
        return reduce((x, y) -> x + y, 0.0, IteratorAsDouble.of(numbers));
    }

    public static boolean odd(double x) {
        return x % 2 == 1;
    }

    public static int random(Range range) {
        int max = range.max();
        int min = range.min();
        return (int) ((Math.random() * (max - min)) + min);
    }

    public static IteratorAsDouble randomNumbers(Range range) {
        return new IteratorAsDouble() {

            public boolean hasMore() {
                return true;
            } // infinite

            public double next() {
                return random(range);
            }
        };
    }


}
