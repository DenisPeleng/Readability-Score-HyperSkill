package readability.ScoreReadability;

import static readability.TextReadabilityService.*;

public class FKScoreReadability {

    public static double calculate(String text) {
        int amountWords = amountOfWords(text);
        int amountSentences = amountOfSentences(text);
        int amountSyllables = amountOfSyllables(text);
        return 0.39 * ((double) amountWords / amountSentences) + 11.8 * ((double) amountSyllables / amountWords) - 15.59;
    }
}
