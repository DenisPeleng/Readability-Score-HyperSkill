package readability;

import readability.ScoreReadability.ARIScoreReadability;
import readability.ScoreReadability.CLScoreReadAbility;
import readability.ScoreReadability.FKScoreReadability;
import readability.ScoreReadability.SMOGScoreReadability;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TextReadabilityService {

    public static int amountOfWords(String text) {
        String[] sentencesFromText = text.split("[.!?]");
        int amountWords = 0;
        for (String sentence : sentencesFromText
        ) {
            String[] wordsArr = sentence.split(" ");
            for (String word : wordsArr
            ) {
                if (word.matches("\\S+")) {
                    amountWords++;
                }
            }
        }
        return amountWords;
    }

    public static int amountOfSentences(String text) {
        String[] sentencesFromText = text.split("[.!?]");
        return sentencesFromText.length;
    }

    public static int amountOfSyllables(String text) {
        String[] sentencesFromText = text.split("[.!?]");
        int amountSyllables = 0;
        for (String sentence : sentencesFromText
        ) {
            String[] wordsArr = sentence.split(" ");
            for (String word : wordsArr
            ) {
                if (word.matches("\\S+")) {
                    int currentSyllables = 0;
                    String[] wordCharacters = word.split("");
                    for (int i = 0; i < wordCharacters.length; i++) {
                        if (isVowel(wordCharacters[i])) {
                            if (i == 0) {
                                currentSyllables++;
                            } else if (!isVowel(wordCharacters[i - 1])) {
                                if (i != wordCharacters.length - 1) {
                                    currentSyllables++;
                                } else if (!wordCharacters[i].toLowerCase().matches("e")) {
                                    currentSyllables++;
                                }
                            }
                        }
                    }
                    amountSyllables += currentSyllables > 0 ? currentSyllables : 1;
                }
            }
        }
        return amountSyllables;
    }

    public static int amountOfPolysyllables(String text) {
        int amountPolysyllables = 0;
        String[] sentencesFromText = text.split("[.!?]");
        for (String sentence : sentencesFromText
        ) {
            String[] wordsArr = sentence.split(" ");
            for (String word : wordsArr
            ) {
                if (word.matches("\\S+")) {
                    if (amountOfSyllables(word) > 2) {
                        amountPolysyllables++;
                    }
                }
            }
        }
        return amountPolysyllables;
    }

    protected static void printTextStat(String text) {
        int amountSentences = amountOfSentences(text);
        int amountWords = amountOfWords(text);
        int amountCharacters = amountOfCharacters(text);
        int amountOfSyllables = amountOfSyllables(text);
        int amountOfPolysyllables = amountOfPolysyllables(text);
        System.out.println("The text is:");
        System.out.println(text);
        System.out.println();
        System.out.println("Words: " + amountWords);
        System.out.println("Sentences: " + amountSentences);
        System.out.println("Characters: " + amountCharacters);
        System.out.println("Syllables: " + amountOfSyllables);
        System.out.println("Polysyllables: " + amountOfPolysyllables);
        startMenuForScoreCalculate(text);
    }

    public static int amountOfCharacters(String text) {
        String[] wholeText = text.split("");
        int amountCharacters = 0;
        for (String currentChr : wholeText
        ) {
            if (currentChr.matches("\\S")) {
                amountCharacters++;
            }
        }
        return amountCharacters;
    }

    protected static String readTextFromFile(String filePath) {
        Scanner scanner;
        try {
            scanner = new Scanner(new File(filePath));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return scanner.nextLine();
    }

    protected static int ageTextRecommendation(double score) {
        int INITIAL_AGE = 5;
        int ADDITIONAL_AGE = 3;
        int scoreRounder = (int) Math.ceil(score);
        int readingAge;
        if (scoreRounder > 13) {
            readingAge = INITIAL_AGE + scoreRounder + ADDITIONAL_AGE;
        } else {
            readingAge = INITIAL_AGE + scoreRounder;
        }
        return readingAge;

    }

    protected static void startMenuForScoreCalculate(String text) {
        System.out.println("Enter the score you want to calculate (ARI, FK, SMOG, CL, all):");
        Scanner scanner = new Scanner(System.in);
        String scoreType = scanner.nextLine();
        System.out.println();
        switch (scoreType.toLowerCase()) {
            case "all": {
                printAriScoreReadAbility(text);
                printFKScoreReadAbility(text);
                printSMOGScoreReadAbility(text);
                printCLScoreReadAbility(text);
                System.out.println();
                printAverageAge(text);
                break;
            }
            case "ari": {
                printAriScoreReadAbility(text);
                break;
            }
            case "fk": {
                printFKScoreReadAbility(text);
                break;
            }
            case "smog": {
                printSMOGScoreReadAbility(text);
                break;
            }
            case "cl": {
                printCLScoreReadAbility(text);
            }
            default: {
                System.out.println("Wrong input");
            }

        }
    }

    private static void printAverageAge(String text) {
        double AMOUNT_OF_METHODS = 4.0;
        double averageAge;
        double summaryAge = 0;

        summaryAge += ARIScoreReadability.calculate(text);
        summaryAge += FKScoreReadability.calculate(text);
        summaryAge += SMOGScoreReadability.calculate(text);
        summaryAge += CLScoreReadAbility.calculate(text);
        averageAge = summaryAge / AMOUNT_OF_METHODS;
        System.out.printf("This text should be understood in average by %.2f-years-olds\n", averageAge);

    }

    protected static void printAriScoreReadAbility(String text) {
        double ariReadAbilityScore = ARIScoreReadability.calculate(text);
        int readersAge = ageTextRecommendation(ariReadAbilityScore);
        System.out.printf("Automated Readability Index: %.2f (about %s-years-olds).\n", ariReadAbilityScore, readersAge);
    }

    protected static void printFKScoreReadAbility(String text) {
        double ariReadAbilityScore = FKScoreReadability.calculate(text);
        int readersAge = ageTextRecommendation(ariReadAbilityScore);
        System.out.printf("Flesch–Kincaid readability tests: %.2f (about %s-years-olds).\n", ariReadAbilityScore, readersAge);
    }

    protected static void printCLScoreReadAbility(String text) {
        double ariReadAbilityScore = CLScoreReadAbility.calculate(text);
        int readersAge = ageTextRecommendation(ariReadAbilityScore);
        System.out.printf("Coleman–Liau index: %.2f (about %s-years-olds).\n", ariReadAbilityScore, readersAge);
    }

    protected static void printSMOGScoreReadAbility(String text) {
        double ariReadAbilityScore = SMOGScoreReadability.calculate(text);
        int readersAge = ageTextRecommendation(ariReadAbilityScore);
        System.out.printf("Simple Measure of Gobbledygook: %.2f (about %s-years-olds).\n", ariReadAbilityScore, readersAge);
    }

    private static boolean isVowel(String character) {
        return character.toLowerCase().matches("[aeiouy]");
    }
}
