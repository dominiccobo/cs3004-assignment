package com.dominiccobo.cs3004.assignment.scoring;

import org.junit.Before;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

/**
 * @author Dominic Cobo (contact@dominiccobo.com)
 */
public class FoursScoringOptionTest {

    private FoursScoringOption fixture;

    @Before
    public void setUp() throws Exception {
        fixture = new FoursScoringOption();
    }

    @Test
    public void givenRolledDice_whenFiveFours_scoreIsNTimesFours() {
        int[] rolledDice = new int []{4,4,4,4,4};

        fixture.checkAgainstDice(rolledDice);

        assertThat(fixture.getScore()).isEqualTo(20);
        assertThat(fixture.canScoreBeAwarded()).isTrue();
    }

}
