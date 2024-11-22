package assignments;

import java.util.Scanner;

public class KnightsTour {
    private static int chessboardSize;

    private static void displayChessboard(int[][] chessboard) {
        for (int row = 0; row < chessboardSize; row++) {
            for (int column = 0; column < chessboardSize; column++) {
                System.out.printf("%2d ", chessboard[row][column]);
            }
            System.out.println();
        }
    }

    private static boolean isValidMove(int row, int column, int[][] chessboard) {
        return (row >= 0 && row < chessboardSize && column >= 0 && column < chessboardSize
                && chessboard[row][column] == -1);
    }

    private static boolean exploreKnightPath(int currentRow, int currentColumn, int moveCount,
                                             int[][] chessboard, int[] rowMovement, int[] columnMovement) {
        int nextRow, nextColumn;
        if (moveCount == chessboardSize * chessboardSize) {
            return true;
        }
        for (int moveIndex = 0; moveIndex < 8; moveIndex++) {
            nextRow = currentRow + rowMovement[moveIndex];
            nextColumn = currentColumn + columnMovement[moveIndex];

            if (isValidMove(nextRow, nextColumn, chessboard)) {
                chessboard[nextRow][nextColumn] = moveCount;
                if (exploreKnightPath(nextRow, nextColumn, moveCount + 1, chessboard,
                        rowMovement, columnMovement)) {
                    return true;
                } else {
                    chessboard[nextRow][nextColumn] = -1;
                }
            }
        }
        return false;
    }

    public static boolean startKnightTour(int startRow, int startColumn) {
        int[][] chessboard = new int[chessboardSize][chessboardSize];
        for (int row = 0; row < chessboardSize; row++) {
            for (int column = 0; column < chessboardSize; column++) {
                chessboard[row][column] = -1;
            }
        }
        int[] rowMovement = {2, 1, -1, -2, -2, -1, 1, 2};
        int[] columnMovement = {1, 2, 2, 1, -1, -2, -2, -1};

        chessboard[startRow][startColumn] = 0;

        if (!exploreKnightPath(startRow, startColumn, 1, chessboard, rowMovement,
                columnMovement)) {
            System.out.println("No solution exists for the given starting position.");
            return false;
        } else {
            displayChessboard(chessboard);
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);
        System.out.print("Please enter the size of the chessboard (N x N): ");
        chessboardSize = inputScanner.nextInt();
        System.out.print("Enter the starting row position of the knight (0 to " + (chessboardSize - 1)
                + "): ");
        int startRow = inputScanner.nextInt();
        System.out.print("Enter the starting column position of the knight (0 to " + (chessboardSize
                - 1) + "): ");
        int startColumn = inputScanner.nextInt();
        startKnightTour(startRow, startColumn);
        inputScanner.close();
    }
}
