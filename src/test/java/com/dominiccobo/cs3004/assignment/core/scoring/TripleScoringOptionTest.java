package com.dominiccobo.cs3004.assignment.core.scoring;

import org.junit.Before;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

/**
 * @author Dominic Cobo (contact@dominiccobo.com)
 */
public class TripleScoringOptionTest {

    private TripleScoringOption fixture;

    @Before
    public void setUp() throws Exception {
        fixture = new TripleScoringOption();
    }

    @Test
    public void givenRolledDice_whenThreeSameDice_scoreIsSumOfAllDice() {
        int[] rolledDice = new int []{6,6,6,4,5};

        fixture.checkAgainstDice(rolledDice);

        assertThat(fixture.getScore()).isEqualTo(27);
        assertThat(fixture.canScoreBeAwarded()).isTrue();
    }


}
