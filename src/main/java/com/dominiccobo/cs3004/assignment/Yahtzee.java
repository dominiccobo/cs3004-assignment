package com.dominiccobo.cs3004.assignment;

import java.util.*;
public class Yahtzee {

    public static final String[] OPTIONS = new String[]{"Yahtzee", "Full-House", "Long-Straight", "Short-Straight", "Quad", "Triple", "Ones", "Twos", "Threes", "Fours", "Fives", "Sixes", "Chance"};
    public static final int YAHZEE_SCORE_IDX = 0;
    public static final int FULL_HOUSE_SCORE_IDX = 1;
    public static final int LONG_STRAIGHT_SCORE_IDX = 2;
    public static final int SHORT_STRAIGHT_SCORE_IDX = 3;
    public static final int QUAD_SCORE_IDX = 4;
    public static final int TRIPPLE_SCORE_IDX = 5;
    public static final int ONES_SCORE_IDX = 6;
    public static final int TWOS_SCORE_IDX = 7;
    public static final int THREES_SCORE_IDX = 8;
    public static final int FOURS_SCORE_IDX = 9;
    public static final int FIVES_SCORE_IDX = 10;
    public static final int SIX_SCORE_IDX = 11;
    public static final int CHANCE_SCORE_IDX = 12;
    public static final int NUMBER_OF_DICE = 5;
    public static final int NUMBER_OF_SCORING_OPTIONS = 13;
    public static final int NUMBER_OF_ROUNDS = 13;
    public static final int MAX_ALLOWED_REROLLS = 3;

    private static int showCurrentScore(RoundResult[] currentScoreRecord) {
        int score = 0;
        for (int i=0; i <currentScoreRecord.length; i++) {
            score = score + currentScoreRecord[i].score;
            System.out.println(OPTIONS[i] + " scoring " + currentScoreRecord[i].score + " points");
        }
        return score;
    }

    public static RoundResult[] whatCanBeScored(RoundResult[] currentScoreRecord, int[] theDice) {
        RoundResult[] canScoreThisRound = new RoundResult[NUMBER_OF_SCORING_OPTIONS];
//        Arrays.sort(theDice);

        canScoreThisRound[YAHZEE_SCORE_IDX] = checkForYahtzee(currentScoreRecord, theDice);
        canScoreThisRound[FULL_HOUSE_SCORE_IDX] = checkFullHouse(currentScoreRecord, theDice);
        canScoreThisRound[LONG_STRAIGHT_SCORE_IDX] = checkLongStraight(currentScoreRecord, theDice);
        canScoreThisRound[SHORT_STRAIGHT_SCORE_IDX] = checkShortStraight(currentScoreRecord, theDice);

        canScoreThisRound[ONES_SCORE_IDX] = checkForOnes(currentScoreRecord, theDice);
        canScoreThisRound[TWOS_SCORE_IDX] = checkForTwos(currentScoreRecord, theDice);
        canScoreThisRound[THREES_SCORE_IDX] = checkForThrees(currentScoreRecord, theDice);
        canScoreThisRound[FOURS_SCORE_IDX] = checkForFours(currentScoreRecord, theDice);
        canScoreThisRound[FIVES_SCORE_IDX] = checkForFives(currentScoreRecord, theDice);
        canScoreThisRound[SIX_SCORE_IDX] = checkForSixes(currentScoreRecord, theDice);

        canScoreThisRound[TRIPPLE_SCORE_IDX] = checkForTripleScore(currentScoreRecord, theDice);
        canScoreThisRound[QUAD_SCORE_IDX] = checkForQuads(currentScoreRecord, theDice);
        canScoreThisRound[CHANCE_SCORE_IDX] = checkChanceScore(currentScoreRecord, theDice);
        return canScoreThisRound;
    }

    private static RoundResult checkForTripleScore(RoundResult[] roundResults, int[] theDice) {
        RoundResult canScoreThisRound = new RoundResult();
        if (!roundResults[TRIPPLE_SCORE_IDX].hasScoredAlready()) {
            //CHECK FOR 3 OF A KIND
            int count = 0;
            boolean found3K = false;
            //Check first set are the same
            for (int i = 0; i < 3; i++){
                if(theDice[0] != theDice[i]){
                    count++;
                }
            }
            if (count == 0) {
                found3K = true;
//	        	System.out.println("3K true here first");
            }

            count = 0;
            //Check last set are the same
            for (int i = 1; i < 4; i++){
                if(theDice[1] != theDice[i]){
                    count++;
                }
            }
            if (count == 0) {
                found3K = true;
//	        	System.out.println("3K true here second");

            }

            count = 0;
            //Check last set are the same
            for (int i = 2; i < 5; i++){
                if(theDice[1] != theDice[i]){
                    count++;
                }
            }
            if (count == 0) {
                found3K = true;
//	        	System.out.println("4K true here third");

            }

            if (found3K) {
//	        	System.out.println("It's 3K");
                int score = 0;
                for (int i = 0; i < theDice.length; i++){
                    score = score + theDice[i];
                }
                canScoreThisRound.status = 1;
                canScoreThisRound.score = score;
            }

        }
        return canScoreThisRound;
    }

    private static RoundResult checkForQuads(RoundResult[] roundResults, int[] theDice) {
        RoundResult canScoreThisRound = new RoundResult();

        if (!roundResults[QUAD_SCORE_IDX].hasScoredAlready()) {

            int score = 0;
            //CHECK FOR 4 OF A KIND
            int count = 0;
            boolean found4ofAKind = false;
            //Check first set are the same
            for (int i = 0; i < 4; i++){
                if(theDice[0] != theDice[i]){
                    count++;
                }
            }
            if (count == 0) {
                found4ofAKind = true;
//	        	System.out.println("4K true here first");
            }
            count = 0;
            //Check last set are the same
            for (int i = 1; i < 5; i++){
                if(theDice[1] != theDice[i]){
                    count++;
                }
            }
            if (count == 0) {
                found4ofAKind = true;
            }
            if (found4ofAKind) {
                for (int i = 0; i < theDice.length; i++){
                    score = score + theDice[i];
                }
                canScoreThisRound.status = 1;
                canScoreThisRound.score = score;
            }

        }//4K
        return canScoreThisRound;
    }

    private static RoundResult checkChanceScore(RoundResult[] roundResults, int[] theDice) {
        RoundResult canScoreThisRound = new RoundResult();

        if (!roundResults[CHANCE_SCORE_IDX].hasScoredAlready()) {
            //Check chance
            canScoreThisRound.status = 1;
            canScoreThisRound.score = 0;

            for (int value : theDice) {
                canScoreThisRound.score = canScoreThisRound.score + value;
            }
        }
        return canScoreThisRound;
    }

    private static RoundResult checkForOnes(RoundResult[] roundResults, int[] theDice) {
        RoundResult canScoreThisRound = new RoundResult();
        if (!roundResults[ONES_SCORE_IDX].hasScoredAlready()) {
            canScoreThisRound.status = 1;
            //noinspection PointlessArithmeticExpression
            canScoreThisRound.score = countNumberOfKind(1, theDice) * 1;
        }
        return canScoreThisRound;
    }

    private static RoundResult checkForTwos(RoundResult[] roundResults, int[] theDice) {
        RoundResult canScoreThisRound = new RoundResult();
        if (!roundResults[TWOS_SCORE_IDX].hasScoredAlready()) {
            canScoreThisRound.status = 1;
            canScoreThisRound.score = countNumberOfKind(2, theDice) * 2;
        }
        return canScoreThisRound;
    }

    private static RoundResult checkForThrees(RoundResult[] roundResults, int[] theDice) {
        RoundResult canScoreThisRound = new RoundResult();
        if (!roundResults[THREES_SCORE_IDX].hasScoredAlready()) {
            canScoreThisRound.status = 1;
            canScoreThisRound.score = countNumberOfKind(3, theDice) * 3;
        }
        return canScoreThisRound;
    }

    private static RoundResult checkForFours(RoundResult[] roundResults, int[] theDice) {
        RoundResult canScoreThisRound = new RoundResult();
        if (!roundResults[FOURS_SCORE_IDX].hasScoredAlready()) {
            canScoreThisRound.status = 1;
            canScoreThisRound.score = countNumberOfKind(4, theDice) * 4;
        }
        return canScoreThisRound;
    }

    private static RoundResult checkForFives(RoundResult[] roundResults, int[] theDice) {
        RoundResult canScoreThisRound = new RoundResult();
        if (!roundResults[FIVES_SCORE_IDX].hasScoredAlready()) {
            canScoreThisRound.status = 1;
            canScoreThisRound.score = countNumberOfKind(5, theDice) * 5;
        }
        return canScoreThisRound;
    }

    private static RoundResult checkForSixes(RoundResult[] roundResults, int[] theDice) {
        RoundResult canScoreThisRound = new RoundResult();
        if (!roundResults[SIX_SCORE_IDX].hasScoredAlready()) {
            canScoreThisRound.status = 1;
            canScoreThisRound.score = countNumberOfKind(6, theDice) * 6;
        }
        return canScoreThisRound;
    }

    static int countNumberOfKind(int value, int[] rolledDice) {
        return Arrays.stream(rolledDice).filter(iterVal -> iterVal == value).toArray().length;
    }

    private static RoundResult checkShortStraight(RoundResult[] roundResults, int[] theDice) {
        RoundResult canScoreThisRound = new RoundResult();
        if (!roundResults[SHORT_STRAIGHT_SCORE_IDX].hasScoredAlready()) {
            //CHECK SHORT STRAIGHT

            //Check for Short Straight D1-4, D2-5
            if ((theDice[0] == theDice[1] - 1) && (theDice[1] == theDice[2] - 1) && (theDice[2] == theDice[3] - 1)){
                canScoreThisRound.status = 1;
                canScoreThisRound.score = 30;
            }
            if ((theDice[1] == theDice[2] - 1) && (theDice[2] == theDice[3] - 1) && (theDice[3] == theDice[4] - 1)){
                canScoreThisRound.status = 1;
                canScoreThisRound.score = 30;
            }
        }//SS
        return canScoreThisRound;
    }

    private static RoundResult checkLongStraight(RoundResult[] roundResults, int[] theDice) {
        RoundResult canScoreThisRound = new RoundResult();
        if (!roundResults[LONG_STRAIGHT_SCORE_IDX].hasScoredAlready()) {
            //CHECK LONG STRAIGHT
            //Check for Straight D1-5
            if ((theDice[0] == theDice[1] - 1) && (theDice[1] == theDice[2] - 1) && (theDice[2] == theDice[3] - 1) && (theDice[3] == theDice[4] - 1)){
                canScoreThisRound.status = 1;
                canScoreThisRound.score = 40;
            }
        }
        return canScoreThisRound;
    }

    private static RoundResult checkFullHouse(RoundResult[] roundResults, int[] theDice) {
        RoundResult canScoreThisRound = new RoundResult();
        if (!roundResults[FULL_HOUSE_SCORE_IDX].hasScoredAlready()) {
            //CHECK FULL HOUSE
            int count = 0;
            //Check it's not a Y
            for (int i = 0; i < 4; i++){
                if(theDice[0] != theDice[i]){
                    count++;
                }
            }
            if(count >0){
                //Two conditions 2 then 3 or 3 then 2

                count = 0;

                //Check first two the same then check last three
                for (int i = 0; i < 2; i++){
                    if(theDice[0] != theDice[i]){
                        count++;
                    }
                }

                //Check last three the same
                for (int i = 2; i < 5; i++){
                    if(theDice[2] != theDice[i]){
                        count++;
                    }
                }

                if (count == 0) {
                    canScoreThisRound.status = 1;
                    canScoreThisRound.score = 25;
                } else {
                    count = 0;
                    //Check first three the same then check last two
                    for (int i = 0; i < 3; i++){
                        if(theDice[0] != theDice[i]){
                            count++;
                        }
                    }

                    //Check last three the same
                    for (int i = 3; i < 5; i++){
                        if(theDice[3] != theDice[i]){
                            count++;
                        }
                    }

                    if (count == 0) {
                        canScoreThisRound.status = 1;
                        canScoreThisRound.score = 25;
                    }

                } //end if
            }

        }//FH
        return canScoreThisRound;
    }

    private static RoundResult checkForYahtzee(RoundResult[] roundResult, int[] theDice) {
        RoundResult canScoreThisRound = new RoundResult();
        if (!roundResult[YAHZEE_SCORE_IDX].hasScoredAlready()) {
            //CHECK FOR YAHTZEE
            int countedDifferences = countDifferentDices(theDice);
            if(countedDifferences == 0){
                canScoreThisRound.status = 1;
                canScoreThisRound.score = 50;
            }
        }//Y
        return canScoreThisRound;
    }

    private static int countDifferentDices(int[] theDice) {
        int count = 0;
        // horrible way of checking for 5 of a kind
        for (int value : theDice) {
            if (theDice[0] != value) {
                count++;
            }
        }
        return count;
    }


    private static RoundResult[] chooseWhatToScore(RoundResult[] currentScoreRecord, RoundResult[] canScoreThisRound){
        //Scoring Y FH LS SS 4K 3K On Tw Th Fo Fi Si C

        RoundResult[] newScoreRecord = new RoundResult[NUMBER_OF_SCORING_OPTIONS];
        Arrays.fill(newScoreRecord, new RoundResult());
        RoundResult[] potentialChoice = new RoundResult[NUMBER_OF_SCORING_OPTIONS];
        Arrays.fill(potentialChoice, new RoundResult());
        int choice = 0;

        //Check to see if something has been scored and if it can be scored
        //Is current score = 0 (it's not been scored) and can score = 1 (it can be scored)
        //PotentialChoice is 0 (it's not been scored), 1 (it's been scored)

        newScoreRecord = currentScoreRecord;
        System.out.println("With your roll you can select...");
        //Present choices - check if it has been scored and if it can be scored
        for (int i = 0; i < NUMBER_OF_SCORING_OPTIONS; i++) {
            if ((currentScoreRecord[i].status == 0) && (canScoreThisRound[i].status == 1)){
                potentialChoice[i].status = 1;
                potentialChoice[i].score = canScoreThisRound[i].score;
                System.out.println("Select " + i + " for " + OPTIONS[i] + " scoring " + canScoreThisRound[i].score + " points");
            }
        }

        //Choose and update score
        choice = InputUtils.readIntegerConsoleInput("Choose one choice!");
        System.out.println("You have chosen " + OPTIONS[choice]);
        newScoreRecord[choice].status = 1;
        newScoreRecord[choice].score = canScoreThisRound[choice].score;

        return newScoreRecord;
    }//chooseWhatToScore

    static class RoundResult {
        int status = 0;
        int score = 0;

        @SuppressWarnings("BooleanMethodIsAlwaysInverted")
        boolean hasScoredAlready() {
            return status == 1;
        }

    }

    static class ScoreBoard {

        RoundResult yahtzee;
        RoundResult fullHand;
        RoundResult longStraight;
        RoundResult shortStraight;
        RoundResult quad;
        RoundResult triple;
        RoundResult ones;
        RoundResult twos;
        RoundResult threes;
        RoundResult fours;
        RoundResult fives;
        RoundResult sixes;
        RoundResult chance;

        ScoreBoard(RoundResult[] roundResults) {
            yahtzee = roundResults[YAHZEE_SCORE_IDX];
            fullHand = roundResults[FULL_HOUSE_SCORE_IDX];
            longStraight = roundResults[LONG_STRAIGHT_SCORE_IDX];
            shortStraight = roundResults[SHORT_STRAIGHT_SCORE_IDX];
            quad = roundResults[QUAD_SCORE_IDX];
            triple = roundResults[TRIPPLE_SCORE_IDX];
            ones = roundResults[ONES_SCORE_IDX];
            twos = roundResults[TWOS_SCORE_IDX];
            threes = roundResults[THREES_SCORE_IDX];
            fours = roundResults[FOURS_SCORE_IDX];
            fives = roundResults[FIVES_SCORE_IDX];
            sixes = roundResults[SIX_SCORE_IDX];
            chance = roundResults[CHANCE_SCORE_IDX];
        }
    }

    //Let's play...

    public static void playGame() {
    /*
    Welcome to Yahtzee - Simon Taylor Sept 2019
    Scoring - Y FH LS SS 4K 3K On Tw Th Fo Fi Si C

    currentScoreRecord - For each of the above {status, score}
    canScoreThisRound - For each of the above  {can it be scored? i.e. can be scored and not previously scored, score}
    theDice - {what's been rolled this turn}
    currentScore - current score

    showCurrentScore - calculate and show the current score from currentScoreRecord
    whatCanBeScored - update canScoreThisRound from theDice and currentScoreRecord
    chooseWhatToScore - user chooses from canScoreThisRound and update currentScoreRecord

    Game - 	13 rounds
            Show what's been scored
            Roll/wantsToReRoll dice max 3 times
            Check what can be scored
            Choose what can be scored
            End of 13 rounds show final score and status
    */

        RoundResult[] currentScoreRecord = new RoundResult[NUMBER_OF_SCORING_OPTIONS];
        Arrays.fill(currentScoreRecord, new RoundResult());
        RoundResult[] canScoreThisRound = new RoundResult[NUMBER_OF_SCORING_OPTIONS];
        Arrays.fill(canScoreThisRound, new RoundResult());
        int currentScore = 0;

        int[] theDice = new int[NUMBER_OF_DICE];// dice scores
        int numberOfReRolls = 0;


        System.out.println("Welcome to Yahtzee!");


        for (int round = 1; round <= NUMBER_OF_ROUNDS; round++) {

            //Print current status and score
            System.out.println("\n\n\nRound " + round + " of " + NUMBER_OF_ROUNDS);
            System.out.println("Current score is " + currentScore);
            System.out.println("Your current scoring status is:");
            currentScore = showCurrentScore(currentScoreRecord);

            //Roll the dice
            theDice = Dice.roll(NUMBER_OF_DICE);
            Dice.printDice(theDice);

            //Check rerolls - three dice to wantsToReRoll
            System.out.println("Three chances to wantsToReRoll");
            numberOfReRolls = 0;

            while (numberOfReRolls++ < MAX_ALLOWED_REROLLS) {

                int howManyDiceToReRoll = askHowManyDiceToReRoll();

                if(howManyDiceToReRoll > 0) {
                    int[] rerolledDice = reRollDice(theDice, howManyDiceToReRoll);
                    theDice = rerolledDice.clone();
                }
                else break;
            }

            canScoreThisRound = whatCanBeScored(currentScoreRecord, theDice);
            currentScoreRecord = chooseWhatToScore(currentScoreRecord, canScoreThisRound);
            showCurrentScore(currentScoreRecord);

        }

        currentScore = showCurrentScore(currentScoreRecord);
        System.out.println("Your final score is " + currentScore);
        System.out.println("You scored:");
        showCurrentScore(currentScoreRecord);
    }

    static int[] reRollDice(int[] currentDice, int howManyDiceToReRoll) {
        int[] theDice = currentDice.clone();
        int[] diceToReRoll = new int[NUMBER_OF_DICE];

        if (howManyDiceToReRoll > 0) {
            for (int i = 0; i < howManyDiceToReRoll; i++) {
                int dieToReRoll = InputUtils.readIntegerConsoleInput("Select a die (1-5)", 1, 5);
                diceToReRoll[i] = dieToReRoll - 1; //adjust for array index
            }
            for (int i = 0; i < howManyDiceToReRoll; i++) {
                theDice[diceToReRoll[i]] = Dice.roll();
            }
            Dice.printDice(theDice);
        }
        return theDice;
    }

    private static int askHowManyDiceToReRoll() {
        return InputUtils.readIntegerConsoleInput("How many dice do you want to wantsToReRoll? (1-5 - 0 for no dice)", 0, 5);
    }

}

