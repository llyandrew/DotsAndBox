package main;

import java.util.Scanner;

public class DotsAndBoxes {
    private Scanner scanner;
    private Player player1;
    private Player player2;
    private Board board;
    private Player currentPlayer;

    public DotsAndBoxes(Scanner scanner) {
        this.scanner = scanner;
    }

    public void setup() {
        System.out.println("\n🎯 Setting up Dots and Boxes...");

        System.out.print("Enter Player 1 name: ");
        String name1 = scanner.nextLine();
        player1 = new Player(name1);

        System.out.print("Enter Player 2 name: ");
        String name2 = scanner.nextLine();
        player2 = new Player(name2);

        System.out.print("Enter number of rows: ");
        int rows = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter number of columns: ");
        int cols = Integer.parseInt(scanner.nextLine());

        board = new Board(rows, cols);
        currentPlayer = player1;
    }

    public void play() {
        while (!board.isFull()) {
            board.display();
            System.out.println(currentPlayer.getName() + "'s turn. Score: " + currentPlayer.getScore());

            boolean validMove = false;
            while (!validMove) {
                System.out.print("Enter row: ");
                int row = Integer.parseInt(scanner.nextLine());

                System.out.print("Enter column: ");
                int col = Integer.parseInt(scanner.nextLine());

                System.out.print("Enter direction (H for horizontal, V for vertical): ");
                String dir = scanner.nextLine().toUpperCase();

                validMove = board.claimEdge(row, col, dir, currentPlayer);
                if (!validMove) {
                    System.out.println("❌ Invalid move. Try again.");
                }
            }

            if (!board.lastMoveCompletedBox()) {
                switchPlayer();
            }
        }
    }

    public void showResult() {
        board.display();
        int score1 = player1.getScore();
        int score2 = player2.getScore();

        System.out.println("\n🏁 Game Over!");
        System.out.println(player1.getName() + ": " + score1 + " points");
        System.out.println(player2.getName() + ": " + score2 + " points");

        if (score1 > score2) {
            System.out.println("🎉 " + player1.getName() + " wins!");
        } else if (score2 > score1) {
            System.out.println("🎉 " + player2.getName() + " wins!");
        } else {
            System.out.println("🤝 It's a tie!");
        }
    }

    private void switchPlayer() {
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
    }

    public void reset() {
        player1.resetScore();
        player2.resetScore();
        setup(); // 重新設定玩家與棋盤
    }
}