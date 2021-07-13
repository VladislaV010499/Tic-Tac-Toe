package tictactoe;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final String SHOULD_BE_NUMBERS = "You should enter numbers!";
        final String NUMBERS_IN_RANGE = "Coordinates should be from 1 to 3!";
        final String SHOULD_BE_EMPTY = "This cell is occupied! Choose another one!";

        Scanner scanner = new Scanner(System.in);
        String[][] board = new String[3][3];
        boolean isWinner = false;
        int countTurn = 0;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = " ";
            }
        }

        drawBoard(board);

        while (!isWinner) {
            System.out.print("Enter the coordinates: ");
            String[] coordinates1 = scanner.nextLine().split(" ");

            try {
                int x = Integer.parseInt(coordinates1[0]);
                int y = Integer.parseInt(coordinates1[1]);

                if (x > 3 || x < 1 || y > 3 || y < 1) {
                    System.out.println(NUMBERS_IN_RANGE);
                } else {
                    String target = board[x - 1][y - 1];

                    if (target.equals("X") || target.equals("O") ) {
                        System.out.println(SHOULD_BE_EMPTY);
                    } else {
                        if (countTurn % 2 == 0) {
                            board[x - 1][y - 1] = "X";
                        } else {
                            board[x - 1][y - 1] = "O";
                        }
                        countTurn++;
                        drawBoard(board);
                        if (showWinner(board)) {
                            isWinner = true;
                        } else if (countTurn > 8){
                            System.out.println("Draw");
                            isWinner = true;
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println(SHOULD_BE_NUMBERS);
            }
        }
    }

    static void drawBoard(String[][] board) {
        System.out.println("---------");
        System.out.printf("| %s %s %s | \n", board[0][0], board[0][1], board[0][2]);
        System.out.printf("| %s %s %s | \n", board[1][0], board[1][1], board[1][2]);
        System.out.printf("| %s %s %s | \n", board[2][0], board[2][1], board[2][2]);
        System.out.println("---------");
    }

    static boolean showWinner(String[][] board) {
        boolean isWinner = true;
        String winner = checkWinner(board);

        switch (winner) {
            case "X":
                System.out.println("X wins");
                break;
            case "O":
                System.out.println("O wins");
                break;
            default:
                isWinner = false;
                break;
        }
        return isWinner;
    }

    static String checkWinner(String[][] board) {
        String winner = "null";
        for (int a = 0; a < 8; a++) {
            String line = null;

            switch (a) {
                case 0:
                    line = board[0][0] + board[0][1] + board[0][2];
                    break;
                case 1:
                    line = board[1][0] + board[1][1] + board[1][2];
                    break;
                case 2:
                    line = board[2][0] + board[2][1] + board[2][2];
                    break;
                case 3:
                    line = board[0][0] + board[1][0] + board[2][0];
                    break;
                case 4:
                    line = board[0][1] + board[1][1] + board[2][1];
                    break;
                case 5:
                    line = board[0][2] + board[1][2] + board[2][2];
                    break;
                case 6:
                    line = board[0][0] + board[1][1] + board[2][2];
                    break;
                case 7:
                    line = board[0][2] + board[1][1] + board[2][0];
                    break;
            }

            if (line.equals("XXX")) {
                winner =  "X";
            } else if (line.equals("OOO")) {
                winner =  "O";
            }
        }
        return winner;
    }
}
