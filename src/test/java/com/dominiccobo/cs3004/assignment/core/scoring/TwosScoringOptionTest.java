package com.dominiccobo.cs3004.assignment.core.scoring;

import org.junit.Before;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

/**
 * @author Dominic Cobo (contact@dominiccobo.com)
 */
public class TwosScoringOptionTest {

    private TwosScoringOption fixture;

    @Before
    public void setUp() throws Exception {
        fixture = new TwosScoringOption();
    }

    @Test
    public void givenRolledDice_whenFiveTwos_scoreIsNTimesTwos() {
        int[] rolledDice = new int []{2,2,2,2,2};

        fixture.checkAgainstDice(rolledDice);

        assertThat(fixture.getScore()).isEqualTo(10);
        assertThat(fixture.canScoreBeAwarded()).isTrue();
    }
}
