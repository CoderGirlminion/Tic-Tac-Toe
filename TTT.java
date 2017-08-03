
/**
 * Created by Michelle Trusgnich on 8/1/2017.
 */
import java.util.Scanner;
import static java.lang.System.out;

public class TTT {
    static Scanner keyboard = new Scanner(System.in);

    // 3 by 3 grid from two dimensional array
    public static char[][] gridBoard() {
        char[][] board = {{'1', '2', '3'}, {'4', '5', '6'}, {'7', '8', '9'}};
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                board[row][col] = board[row][col];
            }
        }
        return board;
    }

    public static void drawBoard(char[][] board) {
        for (int row = 0; row < board.length; row++) {
            out.println(" " + board[row][0] + " | " + board[row][1] + " | " + board[row][2]);
            if (row == 0 || row == 1) {
                out.println("------------");
            }
        }
        out.println();
    }

    public static boolean fullBoard(char[][] board) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col] != 'X' && board[row][col] != 'O')
                {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean Win(char[][] board) {
        return (verticalWin(board)) || (horizontalWin(board)) || (diagonalWin(board));
    }

    public static boolean verticalWin(char[][] board) {
        for (int row = 0; row < board.length; row++) {
            if (board[row][0] == board[row][1] && board[row][1] == board[row][2]) {
                return true;
            }
        }
        return false;
    }

    public static boolean horizontalWin(char[][] board) {
        for (int col = 0; col < board.length; col++) {
            if (board[0][col] == board[1][col] && board[1][col] == board[2][col]) {
                return true;
            }
        }
        return false;
    }

    public static boolean diagonalWin(char[][] board) {
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            return true;
        }
        if (board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            return true;
        }
        return false;
    }

    public static void printWelcome() {
        out.println("Welcome to Tic Tac Toe!");
        out.println();
        out.println("To play, enter a number for which box to play in:");
        out.println();
        out.println("You'll need a buddy to play with. Ready to begin?");
        out.println();
        out.println("X moves first.");
    }

    public static boolean playAgain()
    {
        out.println("Would you like to play again? ");
        keyboard.nextLine();
        String answer = keyboard.nextLine();
        return (answer.equalsIgnoreCase("y"));
    }
    // position on the board
    public static char checkBoard(int pos, char[][] board) {
        int row = (pos - 1) / 3;
        int col = (pos - 1) % 3;
        return board[row][col];
    }
    // check to see if the position is occupied  on the board
    public static void checkPosition(boolean IsXturn, int pos, char[][] board) {
        boolean full = true;

        do {
            out.println("What position ?");
            out.println("-----------");

            if (IsXturn) {
                out.println(" x turn");
                pos = keyboard.nextInt();
                if (pos < 1 || pos > 9) {
                    out.println("Please choose another number that lies between 1 and 9.");
                }
                full = ((checkBoard(pos, board) == 'X') || (checkBoard(pos, board) == 'O'));
                if (full) {
                    out.println("Sorry, position " + pos + " is already taken.");
                }
            } else {
                out.println(" o turn ");
                pos = keyboard.nextInt();
                if (pos < 1 || pos > 9) {
                    out.println("Please choose another number that lies between 1 and 9.");
                }
                full = ((checkBoard(pos, board) == 'X') || (checkBoard(pos, board) == 'O'));
                if (full) {
                    out.println("Sorry, position " + pos + " is already taken.");
                }
            }

        } while ((pos < 1 || pos > 9) || full);
        positionGrid(IsXturn, pos, board);
    }
    // place either 'X' or 'O' to the assigned position
    public static void positionGrid(boolean IsXturn, int pos, char[][] board) {
        if (IsXturn) {
            if (pos == 1) {
                board[0][0] = 'X';
            }
            if (pos == 2) {
                board[0][1] = 'X';
            }
            if (pos == 3) {
                board[0][2] = 'X';
            }
            if (pos == 4) {
                board[1][0] = 'X';
            }
            if (pos == 5) {
                board[1][1] = 'X';
            }
            if (pos == 6) {
                board[1][2] = 'X';
            }
            if (pos == 7) {
                board[2][0] = 'X';
            }
            if (pos == 8) {
                board[2][1] = 'X';
            }
            if (pos == 9) {
                board[2][2] = 'X';
            }
        } else {
            if (pos == 1) {
                board[0][0] = 'O';
            }
            if (pos == 2) {
                board[0][1] = 'O';
            }
            if (pos == 3) {
                board[0][2] = 'O';
            }
            if (pos == 4) {
                board[1][0] = 'O';
            }
            if (pos == 5) {
                board[1][1] = 'O';
            }
            if (pos == 6) {
                board[1][2] = 'O';
            }
            if (pos == 7) {
                board[2][0] = 'O';
            }
            if (pos == 8) {
                board[2][1] = 'O';
            }
            if (pos == 9) {
                board[2][2] = 'O';
            }
        }
    }

    public static void main(String[] args) {
        printWelcome();
        out.println();

        int xWins = 0;
        int oWins = 0;
        int draws = 0;
        int pos = 0;
        boolean IsXturn = true;

        do {
            char[][] board = gridBoard();
            drawBoard(board);
            boolean gameStillGoing = true;

            do {
                checkPosition(IsXturn, pos, board);
                drawBoard(board);
                if (Win(board)) {
                    gameStillGoing = false;
                    if (IsXturn) {
                        xWins++;
                        out.println(" X wins!");
                    } else {
                        oWins++;
                        out.println(" O wins !");
                    }
                } else if (fullBoard(board)) {
                    gameStillGoing = false;
                    draws++;
                    out.println("It is a draw. No one wins");
                }
                IsXturn = !IsXturn;
            } while (gameStillGoing);
            out.println("Score: X= " + xWins + ", O= " + oWins + ", draws= " + draws);
        }while(playAgain());
        out.println("Thanks for playing! ");
    }
}




