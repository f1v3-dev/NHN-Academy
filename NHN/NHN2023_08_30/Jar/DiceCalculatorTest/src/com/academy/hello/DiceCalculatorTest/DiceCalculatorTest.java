package com.academy.hello.DiceCalculatorTest;

import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class DiceCalculatorTest {

    /**
     * DiceCalculator 메서드 테스트 작성
     */

    static Stream<Arguments> diceValue() {
        return Stream.of(
                Arguments.of(1, 2, 3),
                Arguments.of(4, 5, 9),
                Arguments.of(6, 6, 12),
                Arguments.of(1, 1, 2),
                Arguments.of(3, 2, 5)
        );
    }

    @ParameterizedTest(name = "Dice {0} + Dice {1} = {2}")
    @MethodSource("diceValue")
    @DisplayName("DiceCalculator.addDice Param 테스트")
    void diceAddTest(int first, int second, int result) {
        Dice firstDice = new Dice(first);
        Dice secondDice = new Dice(second);

        Assertions.assertEquals(result, DiceCalculator.addDice(firstDice, secondDice));
    }

    @Test
    @DisplayName("DiceCalculator.getIndexOf 테스트")
    void getIndexOfTest() {
        Dice dice = new Dice(5);
        String str = "a1b2c3d4";

        Assertions.assertEquals("a1b2c3", DiceCalculator.getIndexOf(str, dice.getNum()));
    }

    @Test
    @DisplayName("DiceCalculator.odd 테스트")
    void oddTest() {
        Dice evenDice = new Dice(2);
        Dice oddDice = new Dice(3);

        Assertions.assertFalse(DiceCalculator.odd(evenDice));
        Assertions.assertTrue(DiceCalculator.odd(oddDice));
    }
}
