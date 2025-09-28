package main;

// Board.java
public class Board {
    private int rows, cols;
    private Box[][] boxes;
    private boolean lastMoveCompletedBox;

    public Board(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        boxes = new Box[rows][cols];

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                boxes[r][c] = new Box();
            }
        }
    }

    public void display() {
        for (int r = 0; r < rows; r++) {
            // 顯示上邊
            for (int c = 0; c < cols; c++) {
                System.out.print("•");
                System.out.print(boxes[r][c].hasTop() ? "──" : "  ");
            }
            System.out.println("•");

            // 顯示左邊與格子擁有者
            for (int c = 0; c < cols; c++) {
                System.out.print(boxes[r][c].hasLeft() ? "│" : " ");
                String owner = boxes[r][c].getOwnerName();
                System.out.print(owner == null ? "  " : owner.substring(0, Math.min(2, owner.length())));
            }
            System.out.println(boxes[r][cols - 1].hasRight() ? "│" : " ");
        }

        // 最底邊
        for (int c = 0; c < cols; c++) {
            System.out.print("•");
            System.out.print(boxes[rows - 1][c].hasBottom() ? "──" : "  ");
        }
        System.out.println("•");
    }

    public boolean claimEdge(int row, int col, String direction, Player player) {
        lastMoveCompletedBox = false;

        if (row < 0 || row >= rows || col < 0 || col >= cols) return false;

        boolean valid = false;

        switch (direction) {
            case "H": // 水平邊（上）
                if (!boxes[row][col].hasTop()) {
                    boxes[row][col].setTop(true);
                    valid = true;
                }
                if (row > 0 && !boxes[row - 1][col].hasBottom()) {
                    boxes[row - 1][col].setBottom(true);
                    valid = true;
                }
                break;
            case "V": // 垂直邊（左）
                if (!boxes[row][col].hasLeft()) {
                    boxes[row][col].setLeft(true);
                    valid = true;
                }
                if (col > 0 && !boxes[row][col - 1].hasRight()) {
                    boxes[row][col - 1].setRight(true);
                    valid = true;
                }
                break;
            default:
                return false;
        }

        // 檢查是否完成格子
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (boxes[r][c].isComplete() && boxes[r][c].getOwnerName() == null) {
                    boxes[r][c].setOwner(player);
                    player.addPoint();
                    lastMoveCompletedBox = true;
                }
            }
        }

        return valid;
    }

    public boolean isFull() {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (!boxes[r][c].isComplete()) return false;
            }
        }
        return true;
    }

    public boolean lastMoveCompletedBox() {
        return lastMoveCompletedBox;
    }
}