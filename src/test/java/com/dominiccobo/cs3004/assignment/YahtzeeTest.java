package com.dominiccobo.cs3004.assignment;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static com.google.common.truth.Truth.assertThat;

/**
 * TODO: add class description.
 *
 * @author Dominic Cobo (contact@dominiccobo.com)
 */
public class YahtzeeTest {

    @Test
    public void givenRolledDive_whenFiveOfKind_IsYahtzee() {

        Yahtzee.RoundResult[] roundResults = new Yahtzee.RoundResult[13];
        roundResults = Collections.nCopies(13, new Yahtzee.RoundResult()).toArray(new Yahtzee.RoundResult[13]);

        int[] rolledDice = new int []{5,5,5,5,5};

        final Yahtzee.RoundResult[] canBeScored = Yahtzee.whatCanBeScored(roundResults, rolledDice);

        assertThat(canBeScored[Yahtzee.YAHZEE_SCORE_IDX].score).isEqualTo(50);
        assertThat(canBeScored[Yahtzee.YAHZEE_SCORE_IDX].status).isEqualTo(1);
    }

    @Test
    public void givenRolledDice_whenFiveTwos_scoreIsNTimesTwos() {

        final Yahtzee.RoundResult[] roundResults = new Yahtzee.RoundResult[13];
        Arrays.fill(roundResults, new Yahtzee.RoundResult());

        int[] rolledDice = new int []{2,2,2,2,2};

        final Yahtzee.RoundResult[] canBeScored = Yahtzee.whatCanBeScored(roundResults, rolledDice);

        int expectedResult = 10;

        assertThat(canBeScored[Yahtzee.TWOS_SCORE_IDX].score).isEqualTo(expectedResult);
        assertThat(canBeScored[Yahtzee.TWOS_SCORE_IDX].status).isEqualTo(1);
    }

    @Test
    public void givenRolledDice_whenFiveThrees_scoreIsNTimesThrees() {

        final Yahtzee.RoundResult[] roundResults = new Yahtzee.RoundResult[13];
        Arrays.fill(roundResults, new Yahtzee.RoundResult());

        int[] rolledDice = new int []{3,3,3,3,3};

        final Yahtzee.RoundResult[] canBeScored = Yahtzee.whatCanBeScored(roundResults, rolledDice);

        int expectedResult = 15;

        assertThat(canBeScored[Yahtzee.THREES_SCORE_IDX].score).isEqualTo(expectedResult);
        assertThat(canBeScored[Yahtzee.THREES_SCORE_IDX].status).isEqualTo(1);
    }

    @Test
    public void givenRolledDice_whenFiveFours_scoreIsNTimesFours() {

        final Yahtzee.RoundResult[] roundResults = new Yahtzee.RoundResult[13];
        Arrays.fill(roundResults, new Yahtzee.RoundResult());

        int[] rolledDice = new int []{4,4,4,4,4};

        final Yahtzee.RoundResult[] canBeScored = Yahtzee.whatCanBeScored(roundResults, rolledDice);

        int expectedResult = 20;

        assertThat(canBeScored[Yahtzee.FOURS_SCORE_IDX].score).isEqualTo(expectedResult);
        assertThat(canBeScored[Yahtzee.FOURS_SCORE_IDX].status).isEqualTo(1);
    }

    @Test
    public void givenRolledDice_whenFiveFives_scoreIsNTimesFives() {

        final Yahtzee.RoundResult[] roundResults = new Yahtzee.RoundResult[13];
        Arrays.fill(roundResults, new Yahtzee.RoundResult());

        int[] rolledDice = new int []{5,5,5,5,5};

        final Yahtzee.RoundResult[] canBeScored = Yahtzee.whatCanBeScored(roundResults, rolledDice);

        int expectedResult = 25;

        assertThat(canBeScored[Yahtzee.FIVES_SCORE_IDX].score).isEqualTo(expectedResult);
        assertThat(canBeScored[Yahtzee.FIVES_SCORE_IDX].status).isEqualTo(1);
    }

    @Test
    public void givenRolledDice_whenFiveSixes_scoreIsNTimesSixes() {

        final Yahtzee.RoundResult[] roundResults = new Yahtzee.RoundResult[13];
        Arrays.fill(roundResults, new Yahtzee.RoundResult());

        int[] rolledDice = new int []{6,6,6,6,6};

        final Yahtzee.RoundResult[] canBeScored = Yahtzee.whatCanBeScored(roundResults, rolledDice);

        int expectedResult = 30;

        assertThat(canBeScored[Yahtzee.SIX_SCORE_IDX].score).isEqualTo(expectedResult);
        assertThat(canBeScored[Yahtzee.SIX_SCORE_IDX].status).isEqualTo(1);
    }

    @Test
    public void givenRolledDice_whenThreeSameDice_scoreIsSumOfAllDice() {
        final Yahtzee.RoundResult[] roundResults = new Yahtzee.RoundResult[13];
        Arrays.fill(roundResults, new Yahtzee.RoundResult());

        int[] rolledDice = new int []{6,6,6,4,5};

        final Yahtzee.RoundResult[] canBeScored = Yahtzee.whatCanBeScored(roundResults, rolledDice);

        int expectedResult = 27;

        assertThat(canBeScored[Yahtzee.TRIPPLE_SCORE_IDX].score).isEqualTo(expectedResult);
        assertThat(canBeScored[Yahtzee.TRIPPLE_SCORE_IDX].status).isEqualTo(1);
    }

    @Test
    public void givenRolledDice_whenFourSameDice_scoreIsSumOfAllDice() {
        final Yahtzee.RoundResult[] roundResults = new Yahtzee.RoundResult[13];
        Arrays.fill(roundResults, new Yahtzee.RoundResult());

        int[] rolledDice = new int []{6,6,6,6,5};

        final Yahtzee.RoundResult[] canBeScored = Yahtzee.whatCanBeScored(roundResults, rolledDice);

        int expectedResult = 29;

        assertThat(canBeScored[Yahtzee.QUAD_SCORE_IDX].score).isEqualTo(expectedResult);
        assertThat(canBeScored[Yahtzee.QUAD_SCORE_IDX].status).isEqualTo(1);
    }

    @Test
    public void givenRolledDice_whenThreeSameDiceAndTwoOfSameDice_scoreIs25() {
        final Yahtzee.RoundResult[] roundResults = new Yahtzee.RoundResult[13];
        Arrays.fill(roundResults, new Yahtzee.RoundResult());

        int[] rolledDice = new int []{6,6,6,5,5};

        final Yahtzee.RoundResult[] canBeScored = Yahtzee.whatCanBeScored(roundResults, rolledDice);

        int expectedResult = 25;

        assertThat(canBeScored[Yahtzee.FULL_HOUSE_SCORE_IDX].score).isEqualTo(expectedResult);
        assertThat(canBeScored[Yahtzee.FULL_HOUSE_SCORE_IDX].status).isEqualTo(1);
    }

    @Test
    public void givenRolledDice_whenFourSequentialDice_scoreIs30() {
        final Yahtzee.RoundResult[] roundResults = new Yahtzee.RoundResult[13];
        Arrays.fill(roundResults, new Yahtzee.RoundResult());

        int[] rolledDice = new int []{1,2,3,4,2};

        final Yahtzee.RoundResult[] canBeScored = Yahtzee.whatCanBeScored(roundResults, rolledDice);

        int expectedResult = 30;

        assertThat(canBeScored[Yahtzee.SHORT_STRAIGHT_SCORE_IDX].score).isEqualTo(expectedResult);
        assertThat(canBeScored[Yahtzee.SHORT_STRAIGHT_SCORE_IDX].status).isEqualTo(1);
    }

    @Test
    public void givenRolledDice_whenFiveSequentialDiceStartingFromOne_scoreIs40() {
        final Yahtzee.RoundResult[] roundResults = new Yahtzee.RoundResult[13];
        Arrays.fill(roundResults, new Yahtzee.RoundResult());

        int[] rolledDice = new int []{1,2,3,4,5};

        final Yahtzee.RoundResult[] canBeScored = Yahtzee.whatCanBeScored(roundResults, rolledDice);

        int expectedResult = 40;

        assertThat(canBeScored[Yahtzee.LONG_STRAIGHT_SCORE_IDX].score).isEqualTo(expectedResult);
        assertThat(canBeScored[Yahtzee.LONG_STRAIGHT_SCORE_IDX].status).isEqualTo(1);
    }

    @Test
    public void givenRolledDice_whenFiveSequentialDiceStartingFromTwo_scoreIs40() {
        final Yahtzee.RoundResult[] roundResults = new Yahtzee.RoundResult[13];
        Arrays.fill(roundResults, new Yahtzee.RoundResult());

        int[] rolledDice = new int []{2,3,4,5,6};

        final Yahtzee.RoundResult[] canBeScored = Yahtzee.whatCanBeScored(roundResults, rolledDice);

        int expectedResult = 40;

        assertThat(canBeScored[Yahtzee.LONG_STRAIGHT_SCORE_IDX].score).isEqualTo(expectedResult);
        assertThat(canBeScored[Yahtzee.LONG_STRAIGHT_SCORE_IDX].status).isEqualTo(1);
    }

    @Test
    public void testCountNumberOfKind() {

        int[] numberOfEqual = new int[] {2, 2, 2, 4, 4};
        int numberToCheck = 2;
        int expected = 3;
        final int result = Yahtzee.countNumberOfKind(numberToCheck, numberOfEqual);
        assertThat(result).isEqualTo(expected);
    }
}
