package main;
// Main.java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GameManager gameManager = new GameManager(scanner);

        System.out.println("Hi! Welcome to Dots and Boxes!");
        DotsAndBoxes game = new DotsAndBoxes(scanner);
        gameManager.startDotsAndBoxes(game);
    }
}