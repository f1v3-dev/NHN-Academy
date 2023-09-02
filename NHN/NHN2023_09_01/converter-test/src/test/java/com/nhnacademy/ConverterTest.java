package com.nhnacademy;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ConverterTest {

    @Test
    @DisplayName("[Converter] 각 진수가 가질 수 없는 값 테스트")
    void falseTest() {
        Converter converter = new Converter();
        assertThrows(IllegalArgumentException.class, () -> converter.binaryToOctal("123"));
        assertThrows(IllegalArgumentException.class, () -> converter.octalToBinary("888"));
        assertThrows(IllegalArgumentException.class, () -> converter.hexadecimalToBinary("KKKK"));
    }

    @Test
    @DisplayName("[Converter] 2진수 -> 10진수 테스트")
    void binaryToDecimalTest() {
        Converter converter = new Converter();
        assertEquals(123, Converter.numberToDecimal("1111011", 2));
    }

    @Test
    @DisplayName("[Converter] 8진수 -> 10진수 테스트")
    void octalToDecimalTest() {
        Converter converter = new Converter();
        assertEquals(123, Converter.numberToDecimal("173", 8));
    }

    @Test
    @DisplayName("[Converter] 16진수 -> 10진수 테스트")
    void hexadecimalToDecimalTest() {
        Converter converter = new Converter();
        assertEquals(123, Converter.numberToDecimal("7B", 16));
    }

    @Test
    @DisplayName("[Converter] 10진수 -> 2진수 테스트")
    void decimalToBinaryTest() {
        Converter converter = new Converter();
        assertEquals("1111011", Converter.decimalToNumber(123, 2));
    }

    @Test
    @DisplayName("[Converter] 10진수 -> 8진수 테스트")
    void decimalToOctalTest() {
        Converter converter = new Converter();
        assertEquals("173", Converter.decimalToNumber(123, 8));
    }

    @Test
    @DisplayName("[Converter] 10진수 -> 16진수 테스트")
    void decimalToHexadecimalTest() {
        Converter converter = new Converter();
        assertEquals("7B", Converter.decimalToNumber(123, 16));
    }

    @Test
    @DisplayName("[Converter] 2진수 -> 8진수 테스트")
    void binaryToOctalTest() {
        Converter converter = new Converter();
        assertEquals("173", converter.binaryToOctal("1111011"));
    }

    @Test
    @DisplayName("[Converter] 8진수 -> 2진수 테스트")
    void octalToBinary() {
        Converter converter = new Converter();
        assertEquals("1111011", converter.octalToBinary("173"));
    }

    @Test
    @DisplayName("[Converter] 2진수 -> 16진수 테스트")
    void binaryToHexadecimalTest() {
        Converter converter = new Converter();
        assertEquals("7B", converter.binaryToHexadecimal("1111011"));
    }

    @Test
    @DisplayName("[Converter] 16진수 -> 2진수 테스트")
    void hexadecimalToBinaryTest() {
        Converter converter = new Converter();
        assertEquals("1111011", converter.hexadecimalToBinary("7B"));
    }

    @Test
    @DisplayName("[Converter] 8진수 -> 16진수 테스트")
    void octalToHexadecimalTest() {
        Converter converter = new Converter();
        assertEquals("7B", converter.octalToHexadecimal("173"));
    }

    @Test
    @DisplayName("[Converter] 16진수 -> 8진수 테스트")
    void hexadecimalToOctalTest() {
        Converter converter = new Converter();
        assertEquals("173", converter.hexadecimalToOctal("7B"));
    }

}