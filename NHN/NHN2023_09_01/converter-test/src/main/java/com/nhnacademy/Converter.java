package com.nhnacademy;

/**
 * X진수 -> Y진수로 변경하는 프로그램입니다.
 */
public class Converter {

    public static int numberToDecimal(String input, int num) {

        int index = 1;
        int result = 0;

        for (int i = input.length() - 1; i >= 0; i--) {
            String str = String.valueOf(input.charAt(i));
            if ('A' <= str.charAt(0) && str.charAt(0) <= 'Z') {
                str = "1" + (str.charAt(0) - 'A');
            }

            int val = Integer.parseInt(str);
            if (val > num - 1) {
                throw new IllegalArgumentException(num + "진수가 가질 수 없는 값 입니다.");
            }
            if (val > 0) {
                result += val * index;
            }
            index *= num;
        }

        return result;

    }


    public static String decimalToNumber(int decimal, int num) {

        String result = "";
        while (decimal > 0) {
            int front = decimal % num;
            if (decimal % num == 0) {
                result = front + result;
            } else {
                if (front > 9 && num == 16) {
                    result = (char) ('A' + front - 10) + result;
                    decimal /= num;
                    continue;
                }
                result = front + result;
            }
            decimal /= num;
        }
        return result;
    }

    public static String binaryToOctal(String binary) {
        return decimalToNumber(numberToDecimal(binary, 2), 8);

    }

    public static String octalToBinary(String octal) {
        return decimalToNumber(numberToDecimal(octal, 8), 2);
    }

    public static String binaryToHexadecimal(String binary) {
        return decimalToNumber(numberToDecimal(binary, 2), 16);
    }

    public static String hexadecimalToBinary(String hexadecimal) {
        return decimalToNumber(numberToDecimal(hexadecimal, 16), 2);

    }

    public static String octalToHexadecimal(String octal) {
        return decimalToNumber(numberToDecimal(octal, 8), 16);
    }

    public static String hexadecimalToOctal(String hexadecimal) {
        return decimalToNumber(numberToDecimal(hexadecimal, 16), 8);
    }


    public static void main(String[] args) {

        /**
         * 2진수 : binary
         * 8진수 : Octal
         * 10진수 : Decimal
         * 16진수 : Hexadecimal
         */

        System.out.println("[X진수 -> 10진수로 변환]");
        System.out.println(numberToDecimal("1100111", 2));
        System.out.println(numberToDecimal("173", 8));
        System.out.println(numberToDecimal("1A", 16));

        System.out.println("\n[10진수 -> X진수로 변환]");
        System.out.println(decimalToNumber(103, 2));
        System.out.println(decimalToNumber(123, 8));
        System.out.println(decimalToNumber(26, 16));

        System.out.println("\n[2진수 <-> 8진수 변환]");
        System.out.println(binaryToOctal("10011"));
        System.out.println(octalToBinary("23"));

        System.out.println("\n[2진수 <-> 16진수 변환]");
        System.out.println(binaryToHexadecimal("111111"));
        System.out.println(hexadecimalToBinary("3F"));

        System.out.println("\n[8진수 <-> 16진수 변환]");
        System.out.println(octalToHexadecimal("2733"));
        System.out.println(hexadecimalToOctal("5DB"));
    }
}