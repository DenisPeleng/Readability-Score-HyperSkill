package readability;

import java.util.Scanner;

import static readability.TextReadabilityService.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        System.out.println(checkReadability(str));
    }
}
