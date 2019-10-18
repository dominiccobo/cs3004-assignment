package com.dominiccobo.cs3004.assignment;

import java.util.ArrayList;

/**
 * TODO: add class description.
 *
 * @author Dominic Cobo (contact@dominiccobo.com)
 */
class Round {

    public static final int MAX_ALLOWED_REROLLS = 3;

    private final InputOutputStreams ioStreams;

    private int currentScore;
    private final int roundNumber;
    private final ScoreBoard currentScoreRecord;

    Round(InputOutputStreams ioStreams, int roundNumber, ScoreBoard currentScoreRecord) {
        this.ioStreams = ioStreams;
        this.roundNumber = roundNumber;
        this.currentScoreRecord = currentScoreRecord;
        this.currentScore = currentScoreRecord.calculateCurrentScore();
    }

    ScoreBoard play() {
        //Print current status and score
        ioStreams.println("\n\nRound " + roundNumber + " of " + Yahtzee.NUMBER_OF_ROUNDS);
        ioStreams.println("Current score is " + currentScore);
        ioStreams.println("Your current scoring status is:");
        currentScore += currentScoreRecord.calculateCurrentScore();

        //Roll the dice
        int[] theDice = Dice.roll(Yahtzee.NUMBER_OF_DICE);
        Dice.printDice(theDice);

        // offer the chance to reroll
        theDice = offerReRoll(theDice);

        currentScoreRecord.whatCanBeScored(theDice);
        chooseWhatToScore();
        return currentScoreRecord;
    }

    private void chooseWhatToScore() {
        //Scoring Y FH LS SS 4K 3K On Tw Th Fo Fi Si C
        ioStreams.println("With your roll you can select: ");
        //Present choices - check if it has been scored and if it can be scored
        ArrayList<ScoringOption> scoringOptions = currentScoreRecord.scoringOptions;
        for (int i = 0; i < scoringOptions.size(); i++) {
            ScoringOption scoringOption = scoringOptions.get(i);
            if (!scoringOption.hasScoredAlready() && scoringOption.canBeAwarded) {
                ioStreams.println(i + " for " + scoringOption.getScoringOptionName() + " scoring " + scoringOption.score + " points");
            }
        }

        //Choose and update score
        int choice = ioStreams.readIntegerConsoleInput("Choose one choice!");
        ScoringOption chosenScore = scoringOptions.get(choice);
        ioStreams.println("You have chosen " + chosenScore.getScoringOptionName());
        currentScoreRecord.markOptionAsScored(chosenScore);
        currentScoreRecord.resetAwarded();
    }

    private int[] offerReRoll(int[] theDice) {
        ioStreams.println(MAX_ALLOWED_REROLLS + " chances to re roll.");
        int numberOfReRolls = 0;

        while (numberOfReRolls++ < MAX_ALLOWED_REROLLS) {

            int howManyDiceToReRoll = askHowManyDiceToReRoll();

            if (howManyDiceToReRoll > 0) {
                int[] rerolledDice = reRollDice(theDice, howManyDiceToReRoll);
                theDice = rerolledDice.clone();
            } else break;
        }
        return theDice;
    }

    private int[] reRollDice(int[] currentDice, int howManyDiceToReRoll) {
        int[] theDice = currentDice.clone();
        int[] diceToReRoll = new int[Yahtzee.NUMBER_OF_DICE];

        if (howManyDiceToReRoll > 0) {
            for (int i = 0; i < howManyDiceToReRoll; i++) {
                int dieToReRoll = ioStreams.readIntegerConsoleInput("Select a die (1-5)", 1, 5);
                diceToReRoll[i] = dieToReRoll - 1; //adjust for array index
            }
            for (int i = 0; i < howManyDiceToReRoll; i++) {
                theDice[diceToReRoll[i]] = Dice.roll();
            }
            Dice.printDice(theDice);
        }
        return theDice;
    }

    private int askHowManyDiceToReRoll() {
        return ioStreams.readIntegerConsoleInput("How many dice do you want to re roll? (1-5 - 0 for no dice)", 0, 5);
    }
}
