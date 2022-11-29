package readability;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TextReadabilityService {
    protected static double calculateReadabilityScore(String text) {
        double score;
        int amountSentences = amountOfSentences(text);
        int amountWords = amountOfWords(text);
        int amountCharacters = amountOfCharacters(text);
        score = 4.71 * ((double) amountCharacters / amountWords) + (0.5 * ((double) amountWords / amountSentences)) - 21.43;
        return score;

    }

    private static int amountOfWords(String text) {
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

    private static int amountOfSentences(String text) {
        String[] sentencesFromText = text.split("[.!?]");
        return sentencesFromText.length;
    }

    protected static void printTextStat(String text) {
        int amountSentences = amountOfSentences(text);
        int amountWords = amountOfWords(text);
        int amountCharacters = amountOfCharacters(text);
        double textScore = calculateReadabilityScore(text);
        System.out.println("The text is:");
        System.out.println(text);
        System.out.println();
        System.out.println("Words: " + amountWords);
        System.out.println("Sentences: " + amountSentences);
        System.out.println("Characters: " + amountCharacters);
        System.out.println("The score is: " + String.format("%.2f", textScore));
        System.out.println("This text should be understood by " + ageTextRecommendation(textScore) + " year-olds.");
    }

    private static int amountOfCharacters(String text) {
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

    protected static String ageTextRecommendation(double score) {
        int scoreRounder = (int) Math.ceil(score);
        int startAge = 4 + scoreRounder;
        int endAge = startAge + 1;
        return startAge + "-" + endAge;

    }
}
