package com.nhnacademy.exam;

import java.util.Objects;

public final class Range {

    private int min;
    private int max;

    public static Range of(int min, int max) {
        return new Range(min, max);
    }

    private Range(int min, int max) {
        this.min = min;
        this.max = max;
        classInvariant();
    }

    public void classInvariant() {
        if (this.min() > this.max()) {
            throw new IllegalArgumentException("Range: " + this.min() + " > " + this.max());
        }
    }

    public int max() {
        return this.max;
    }

    public int min() {
        return this.min;
    }

    public IteratorAsDouble iterator() {

        return new IteratorAsDouble() {
            private int current = min();

            public boolean hasMore() {
                return current <= max();
            }

            public double next() {
                return current++;
            }
        };
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Range range = (Range) o;
        return min == range.min && max == range.max;
    }

    @Override
    public int hashCode() {
        return Objects.hash(min, max);
    }
}
