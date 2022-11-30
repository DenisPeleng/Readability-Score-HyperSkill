package readability.ScoreReadability;

import static readability.TextReadabilityService.*;

public class SMOGScoreReadability {

    public static double calculate(String text) {
        int amountSentences = amountOfSentences(text);
        int amountPolysyllables = amountOfPolysyllables(text);
        return 1.043 * Math.sqrt(amountPolysyllables * (30.0 / amountSentences)) + 3.1291;
    }
}
