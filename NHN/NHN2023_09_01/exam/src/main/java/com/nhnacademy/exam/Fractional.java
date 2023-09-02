package com.nhnacademy.exam;

import java.math.BigInteger;

public class Fractional {

    private long numerator;
    private long denominator;
    private boolean normalized;

    private Fractional(long numerator, long denominator) {
        if (denominator == 0) {
            throw new IllegalArgumentException("분자가 0이야! 공부 다시해!");
        }
        this.numerator = numerator;
        this.denominator = denominator;
        normalize();
    }

    public long getNumerator() {
        return numerator;
    }

    public long getDenominator() {
        return denominator;
    }

    private void normalize() {
        if (normalized) {
            return;
        }
        long gcd = BigInteger.valueOf(numerator).gcd(BigInteger.valueOf(denominator)).longValue();
        this.numerator /= gcd;
        this.denominator /= gcd;
        if (this.denominator < 0) {
            this.numerator = -this.numerator;
            this.denominator = -this.denominator;
        }
        normalized = true;
    }

    public Fractional add(Fractional r) {
        return new Fractional(
                this.getNumerator() * r.getDenominator() + r.getNumerator() * this.getDenominator(),
                this.getDenominator() * r.getDenominator()
        );

    }

    @Override
    public String toString() {
        return "Fractional [numerator=" + numerator + ", denominator=" + denominator + "]";
    }

    @Override
    public int hashCode() {
        int prime = 31;
        return prime * Long.valueOf(numerator).hashCode() + Long.valueOf(denominator).hashCode();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        return that instanceof Fractional && equals((Fractional) that);
    }

    private boolean equals(Fractional that) {
        return this.getNumerator() == that.getNumerator() && this.getDenominator() == that.getDenominator();
    }

    public static Fractional of(int numerator, int denominator) {
        return new Fractional(numerator, denominator);
    }
}