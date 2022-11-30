package readability.ScoreReadability;

import static readability.TextReadabilityService.*;

public class CLScoreReadAbility {
    public static double calculate(String text) {
        int amountWords = amountOfWords(text);
        int amountSentences = amountOfSentences(text);
        int amountCharacters = amountOfCharacters(text);
        double avgL = amountCharacters / (amountWords / 100.00);
        double avgS = amountSentences / (amountWords / 100.00);
        return 0.0588 * avgL - 0.296 * avgS - 15.8;
    }
}
