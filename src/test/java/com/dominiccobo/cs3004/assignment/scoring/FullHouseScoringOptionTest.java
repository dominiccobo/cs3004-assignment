package com.dominiccobo.cs3004.assignment.scoring;

import org.junit.Before;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

/**
 * @author Dominic Cobo (contact@dominiccobo.com)
 */
public class FullHouseScoringOptionTest {

    private FullHouseScoringOption fixture;

    @Before
    public void setUp() throws Exception {
        fixture = new FullHouseScoringOption();
    }

    @Test
    public void givenRolledDice_whenThreeSameDiceAndTwoOfSameDice_scoreIs25() {
        int[] rolledDice = new int []{6,6,6,5,5};

        fixture.checkAgainstDice(rolledDice);

        assertThat(fixture.getScore()).isEqualTo(25);
        assertThat(fixture.canScoreBeAwarded()).isTrue();
    }
}
