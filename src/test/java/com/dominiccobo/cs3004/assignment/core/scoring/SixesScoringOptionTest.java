package com.dominiccobo.cs3004.assignment.core.scoring;

import org.junit.Before;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

/**
 * @author Dominic Cobo (contact@dominiccobo.com)
 */
public class SixesScoringOptionTest {

    private SixesScoringOption fixture;

    @Before
    public void setUp() throws Exception {
        fixture = new SixesScoringOption();
    }

    @Test
    public void givenRolledDice_whenFiveSixes_scoreIsNTimesSixes() {
        int[] rolledDice = new int []{6,6,6,6,6};

        fixture.checkAgainstDice(rolledDice);

        assertThat(fixture.getScore()).isEqualTo(30);
        assertThat(fixture.canScoreBeAwarded()).isTrue();
    }
}
