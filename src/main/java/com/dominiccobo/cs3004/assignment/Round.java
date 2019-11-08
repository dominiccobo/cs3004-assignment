package com.dominiccobo.cs3004.assignment;

import com.dominiccobo.cs3004.assignment.connection.InputOutputStreams;

import java.util.ArrayList;

/**
 * Models a round in a game of Yahtzee. A round consists of a player exercising their
 * right to roll a dice and re-roll 3 times in order to achieve their desired score.
 *
 * @author Dominic Cobo (contact@dominiccobo.com)
 */
class Round {

    @SuppressWarnings("WeakerAccess")
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
        showRoundStartMessage();
        //Roll the dice
        int[] theDice = Dice.roll(Yahtzee.NUMBER_OF_DICE);

        ioStreams.println(Dice.printDice(theDice));
        theDice = offerReRoll(theDice);

        currentScoreRecord.whatCanBeScored(theDice);
        chooseWhatToScore();
        return currentScoreRecord;
    }

    private void showRoundStartMessage() {
        ioStreams.println("\n\nRound " + roundNumber + " of " + Yahtzee.NUMBER_OF_ROUNDS);
        ioStreams.println("Current score is " + currentScore);
        ioStreams.println("Your current scoring status is:");
        currentScore += currentScoreRecord.calculateCurrentScore();
    }

    private void chooseWhatToScore() {
        ioStreams.println(generateScoreChoicePrompt());

        //Choose and update score
        int choice = ioStreams.readIntegerConsoleInput("Choose one choice!", 0, currentScoreRecord.scoringOptions.size() - 1);
        ScoringOption chosenScore = currentScoreRecord.scoringOptions.get(choice);
        ioStreams.println("You have chosen " + chosenScore.getScoringOptionName());
        currentScoreRecord.markOptionAsScored(chosenScore);
        currentScoreRecord.resetAwarded();
    }

    private String generateScoreChoicePrompt() {
        StringBuilder promptBuilder = new StringBuilder();
        promptBuilder.append("With your roll you can select: \n");
        ArrayList<ScoringOption> scoringOptions = currentScoreRecord.scoringOptions;
        for (int i = 0; i < scoringOptions.size(); i++) {
            ScoringOption scoringOption = scoringOptions.get(i);
            if (!scoringOption.hasOptionBeenScored() && scoringOption.canScoreBeAwarded()) {
                promptBuilder.append(i + " for " + scoringOption.getScoringOptionName() + " scoring " + scoringOption.getScore() + " points\n");
            }
        }
        return promptBuilder.toString();
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
