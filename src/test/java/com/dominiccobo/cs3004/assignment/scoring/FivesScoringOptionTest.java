package com.dominiccobo.cs3004.assignment.scoring;

import org.junit.Before;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

/**
 * @author Dominic Cobo (contact@dominiccobo.com)
 */
public class FivesScoringOptionTest {

    private FivesScoringOption fixture;

    @Before
    public void setUp() throws Exception {
        fixture = new FivesScoringOption();
    }

    @Test
    public void givenRolledDice_whenFiveFives_scoreIsNTimesFives() {
        int[] rolledDice = new int []{5,5,5,5,5};

        fixture.checkAgainstDice(rolledDice);

        assertThat(fixture.getScore()).isEqualTo(25);
        assertThat(fixture.canScoreBeAwarded()).isTrue();
    }

}
