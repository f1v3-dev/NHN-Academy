package com.nhnacademy.exam;

import java.util.function.DoubleConsumer;
import java.util.function.DoublePredicate;

public interface IteratorAsDouble {

    boolean hasMore();

    double next();

    default double reduce(java.util.function.DoubleBinaryOperator binaryOperator, double init) {
        double result = init;
        while (this.hasMore()) {
            result = binaryOperator.applyAsDouble(result, this.next());
        }
        return result;
    }

    default IteratorAsDouble take(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("take: n <= 0, where n == " + n);
        }
        return new IteratorAsDouble() {

            private double nextElement;
            private int current = 0;


            @Override
            public boolean hasMore() {
                if (current < n) {
                    nextElement = IteratorAsDouble.this.next();
                    current++;
                    return true;
                }
                return false;
            }


            @Override
            public double next() {
                return nextElement;
            }
        };
    }

    default void forEachRemaining(DoubleConsumer f) {
        f.accept(this.reduce((x, y) -> x + y, 0));
    }

    public static IteratorAsDouble of(double... numbers) {
        return new IteratorAsDouble() {

            private int current = 0;

            @Override
            public boolean hasMore() {
                return current < numbers.length;
            }

            @Override
            public double next() {
                return numbers[current++];
            }
        };
    }

    default IteratorAsDouble filter(DoublePredicate predicate) {
        return new IteratorAsDouble() {
            private double nextElement;

            public boolean hasMore() {
                while (IteratorAsDouble.this.hasMore()) {
                    nextElement = IteratorAsDouble.this.next();
                    if (predicate.test(nextElement)) {
                        return true;
                    }
                }
                return false;
            }

            public double next() {
                return nextElement;
            }
        };
    }
}
