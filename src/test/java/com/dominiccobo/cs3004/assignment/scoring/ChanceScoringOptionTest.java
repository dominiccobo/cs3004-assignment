package com.dominiccobo.cs3004.assignment.scoring;

import org.junit.Before;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

/**
 * TODO: add class description.
 *
 * @author Dominic Cobo (contact@dominiccobo.com)
 */
public class ChanceScoringOptionTest {
    
    private ChanceScoringOption fixture;

    @Before
    public void setUp() throws Exception {
        fixture = new ChanceScoringOption();
    }

    @Test
    public void givenRolledDice_whenAnyCombination_scoreIsSumOfAllDice() {
        int[] theDice = new int[]{1,2,3,5,6};
        fixture.checkAgainstDice(theDice);

        assertThat(fixture.getScore()).isEqualTo(17);
    }
}
