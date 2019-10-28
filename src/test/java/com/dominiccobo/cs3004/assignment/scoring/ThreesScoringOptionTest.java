package com.dominiccobo.cs3004.assignment.scoring;

import org.junit.Before;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

/**
 * @author Dominic Cobo (contact@dominiccobo.com)
 */
public class ThreesScoringOptionTest {

    private ThreesScoringOption fixture;

    @Before
    public void setUp() throws Exception {
        fixture = new ThreesScoringOption();
    }

    @Test
    public void givenRolledDice_whenFiveThrees_scoreIsNTimesThrees() {
        int[] rolledDice = new int []{3,3,3,3,3};

        fixture.checkAgainstDice(rolledDice);

        assertThat(fixture.getScore()).isEqualTo(15);
        assertThat(fixture.canScoreBeAwarded()).isTrue();
    }
}
