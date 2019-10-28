package com.dominiccobo.cs3004.assignment.scoring;

import org.junit.Before;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

/**
 * @author Dominic Cobo (contact@dominiccobo.com)
 */
public class QuadScoringOptionTest {

    private QuadScoringOption fixture;

    @Before
    public void setUp() throws Exception {
        fixture = new QuadScoringOption();
    }

    @Test
    public void givenRolledDice_whenFourSameDice_scoreIsSumOfAllDice() {
        int[] rolledDice = new int []{6,6,6,6,5};

        fixture.checkAgainstDice(rolledDice);

        assertThat(fixture.getScore()).isEqualTo(29);
        assertThat(fixture.canScoreBeAwarded()).isTrue();
    }

}
