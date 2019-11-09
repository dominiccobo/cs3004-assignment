package com.dominiccobo.cs3004.assignment.core;

import com.dominiccobo.cs3004.assignment.core.Dice;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;


/**
 * @author Dominic Cobo (contact@dominiccobo.com)
 */
public class DiceTest {

    @Test
    public void testRepeatedlyDiceRollGeneratesIntegerBetweenZeroAndSix() {

        for(int i = 0; i < 30; i++) {
            final int result = Dice.roll();

            assertThat(result).isAtLeast(1);
            assertThat(result).isAtMost(6);
        }
    }

    @Test
    public void testRollingDice_generatesExpectedNumberOfResults() {

        int times = 5;
        final int[] results = Dice.roll(times);

        assertThat(results).hasLength(times);
    }

    @Test
    public void testCountNumberOfKind() {

        int[] numberOfEqual = new int[] {2, 2, 2, 4, 4};
        int numberToCheck = 2;
        int expected = 3;
        final int result = Dice.countNumberOfKind(numberToCheck, numberOfEqual);
        assertThat(result).isEqualTo(expected);
    }

}
