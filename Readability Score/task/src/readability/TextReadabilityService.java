package readability;

public class TextReadabilityService {
    protected static String checkReadability(String text) {
        String[] sentencesFromText = text.split("[.!?]");
        int totalWords = 0;
        for (String sentence : sentencesFromText
        ) {
            totalWords += sentence.split(" ").length;
        }
        int avrWords = totalWords / sentencesFromText.length;
        if (avrWords > 10) {
            return "HARD";
        } else {
            return "EASY";
        }
    }
}
