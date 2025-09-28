// utils/InputHelper.java
package main.utils;

import java.util.Scanner;

public class InputHelper {
    private Scanner scanner;

    public InputHelper(Scanner scanner) {
        this.scanner = scanner;
    }

    public int getIntInRange(String prompt, int min, int max) {
        int value = -1;
        while (value < min || value > max) {
            System.out.print(prompt);
            try {
                value = Integer.parseInt(scanner.nextLine());
                if (value < min || value > max) {
                    System.out.println("❌ Please enter a number between " + min + " and " + max + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid input. Please enter a number.");
            }
        }
        return value;
    }

    public String getDirection(String prompt) {
        String dir = "";
        while (!dir.equals("H") && !dir.equals("V")) {
            System.out.print(prompt);
            dir = scanner.nextLine().trim().toUpperCase();
            if (!dir.equals("H") && !dir.equals("V")) {
                System.out.println("❌ Invalid direction. Enter H (horizontal) or V (vertical).");
            }
        }
        return dir;
    }

    public String getYesOrNo(String prompt) {
        String input = "";
        while (!input.equals("Y") && !input.equals("N")) {
            System.out.print(prompt);
            input = scanner.nextLine().trim().toUpperCase();
            if (!input.equals("Y") && !input.equals("N")) {
                System.out.println("❌ Please enter Y or N.");
            }
        }
        return input;
    }

    public String getNonEmptyString(String prompt) {
        String input = "";
        while (input.isEmpty()) {
            System.out.print(prompt);
            input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("❌ Input cannot be empty.");
            }
        }
        return input;
    }
}