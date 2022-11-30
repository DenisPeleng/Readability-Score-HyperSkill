package readability.ScoreReadability;

import static readability.TextReadabilityService.*;

public class ARIScoreReadability {
    public static double calculate(String text) {
        int amountSentences = amountOfSentences(text);
        int amountWords = amountOfWords(text);
        int amountCharacters = amountOfCharacters(text);
        return 4.71 * ((double) amountCharacters / amountWords) + (0.5 * ((double) amountWords / amountSentences)) - 21.43;
    }
}
