package com.dominiccobo.cs3004.assignment.scoring;

import org.junit.Before;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

/**
 * @author Dominic Cobo (contact@dominiccobo.com)
 */
public class LongStraightScoringOptionTest {

    private LongStraightScoringOption fixture;

    @Before
    public void setUp() throws Exception {
        fixture = new LongStraightScoringOption();
    }

    @Test
    public void givenRolledDice_whenFiveSequentialDiceStartingFromOne_scoreIs40() {
        int[] rolledDice = new int []{1,2,3,4,5};

        fixture.checkAgainstDice(rolledDice);

        assertThat(fixture.getScore()).isEqualTo(40);
        assertThat(fixture.canScoreBeAwarded()).isTrue();
    }

    @Test
    public void givenRolledDice_whenFiveSequentialDiceStartingFromTwo_scoreIs40() {
        int[] rolledDice = new int []{2,3,4,5,6};

        fixture.checkAgainstDice(rolledDice);

        assertThat(fixture.getScore()).isEqualTo(40);
        assertThat(fixture.canScoreBeAwarded()).isTrue();
    }
}
