package com.dominiccobo.cs3004.assignment.core.scoring;

import org.junit.Before;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

/**
 * @author Dominic Cobo (contact@dominiccobo.com)
 */
public class YahtzeeScoringOptionTest {

    private YahtzeeScoringOption fixture;

    @Before
    public void setUp() throws Exception {
        fixture = new YahtzeeScoringOption();
    }

    @Test
    public void givenRolledDive_whenFiveOfKind_IsYahtzee() {
        int[] rolledDice = new int []{5,5,5,5,5};

        fixture.checkAgainstDice(rolledDice);

        assertThat(fixture.getScore()).isEqualTo(50);
        assertThat(fixture.canScoreBeAwarded()).isTrue();
    }
}
