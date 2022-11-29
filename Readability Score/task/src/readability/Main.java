package readability;

import static readability.TextReadabilityService.*;

public class Main {
    public static void main(String[] args) {
        String filePath = args[0];
        String text = readTextFromFile(filePath);
        printTextStat(text);
    }
}
