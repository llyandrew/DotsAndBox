package main;
import java.util.Scanner;

public class GameManager {
    private Scanner scanner;
    private boolean isRunning = true;

    public GameManager(Scanner scanner) {
        this.scanner = scanner;
    }

    public void startDotsAndBoxes(DotsAndBoxes game) {
        while (isRunning) {
            game.setup();
            game.play();
            game.showResult();
            promptNextAction(game);
        }
    }

    private void promptNextAction(DotsAndBoxes currentGame) {
        System.out.println("\nWhat would you like to do next?");
        System.out.println("1. Play again");
        System.out.println("2. Quit");

        int choice = -1;
        while (choice < 1 || choice > 2) {
            System.out.print("Enter your choice (1 or 2): ");
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter 1 or 2.");
            }
        }

        if (choice == 1) {
            currentGame.reset();
        } else {
            isRunning = false;
            System.out.println("Thanks for playing! Goodbye 👋");
        }
    }
}