package com.dominiccobo.cs3004.assignment.core.scoring;

import org.junit.Before;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

/**
 * @author Dominic Cobo (contact@dominiccobo.com)
 */
public class ShortStraightScoringOptionTest {

    private ShortStraightScoringOption fixture;

    @Before
    public void setUp() throws Exception {
        fixture = new ShortStraightScoringOption();
    }

    @Test
    public void givenRolledDice_whenFourSequentialDice_scoreIs30() {
        int[] rolledDice = new int []{1,2,3,4,2};

        fixture.checkAgainstDice(rolledDice);

        assertThat(fixture.getScore()).isEqualTo(30);
        assertThat(fixture.canScoreBeAwarded()).isTrue();
    }

}
