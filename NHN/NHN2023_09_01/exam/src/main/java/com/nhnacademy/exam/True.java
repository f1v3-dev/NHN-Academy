package com.nhnacademy.exam;

public class True {
    public True() {

    }

    public static void preCondition(String... args) {
        if (args.length < 3) {
            throw new IllegalArgumentException("TrueTest: three boolean arguments required");
        }
    }

    public static boolean twoMoreTrue(String... args) {
        preCondition(args);
        boolean firstBool = Boolean.valueOf(args[0]);
        boolean secondBool = Boolean.valueOf(args[1]);
        boolean thirdBool = Boolean.valueOf(args[2]);
        return (firstBool ? 1 : 0) + (secondBool ? 1 : 0) + (thirdBool ? 1 : 0) >= 2;

    }

    public static void main(String[] args) {

        try {
            System.out.println(twoMoreTrue(args));
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }
}
