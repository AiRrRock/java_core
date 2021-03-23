package ru.geekbrains.java.level_1.lesson_4;


import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TicTacToeGame {

    private static final char X_MARKER = 'X';
    private static final char O_MARKER = 'O';
    private static final char EMPTY_CELL_MARKER = ' ';
    private static final Random random = new Random();
    private static char[][] GAME_BOARD;
    private static int BOARD_SIZE = 3;
    private static int SERIES_TO_WIN = 3;
    private static Scanner scanner;
    private static char playersMarker;
    private static char cpuMarker;
    private static boolean aiEnabled = true;

    public static void main(String[] args) {
        playTicTacToe();
    }

    private static void playTicTacToe() {
        scanner = new Scanner(System.in);
        System.out.println("Do you want to play as noughts(O) or crosses(X)?");
        do {
            playersMarker = scanner.nextLine().toUpperCase().charAt(0);
        } while (playersMarker != X_MARKER && playersMarker != O_MARKER);
        cpuMarker = playersMarker == X_MARKER ? O_MARKER : X_MARKER;
        System.out.println(String.format("You are playing as %s\nThe cpu is playing as %s", playersMarker, cpuMarker));
        System.out.println("\nWhat board size do you want?");
        do {
            BOARD_SIZE = scanner.nextInt();
        } while (BOARD_SIZE <= 2);
        prepareBoard();
        System.out.println("How many markers do you want to match in a row?");
        do {
            SERIES_TO_WIN = scanner.nextInt();
        } while (SERIES_TO_WIN <= 0 && SERIES_TO_WIN > BOARD_SIZE);
        System.out.println("\nDo you want to play(e)asy-(1) or (h)ard - (2)?");
        char mode = ' ';
        do {
            String input = scanner.nextLine();
            if (input != null && !input.isEmpty()) {
                mode = input.toLowerCase().charAt(0);
            }
        } while (mode != 'e' && mode != '1' && mode != 'h' && mode != '2');
        aiEnabled = mode == 'h' || mode == '2';
        drawBoard();
        do {
            performPlayersTurn();
            if (isVictorious(playersMarker)) {
                System.out.println("You win!");
                break;
            } else if (isBoardFull()) {
                System.out.println("Tie!");
                break;
            }

            drawBoard();
            performCpuTurn();
            if (isVictorious(cpuMarker)) {
                System.out.println("You lose!");
                break;
            } else if (isBoardFull()) {
                System.out.println("Tie!");
                break;
            }
            drawBoard();
        } while (true);
        drawBoard();
        scanner.close();

    }

    /*Having ability to use multiple board sizes and different series of markers in a row to win,
    We have to check each and every marker in each and every direction possible */
    private static boolean isVictorious(char marker) {
        for (int y = 0; y < BOARD_SIZE; y++) {
            for (int x = 0; x < BOARD_SIZE; x++) {
                for (Direction direction : Direction.values()) {
                    if (isVictorious(direction, marker, x, y)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    //Checking one direction for being victorious.
    private static boolean isVictorious(Direction direction, char marker, int x, int y) {
        switch (direction) {
            case UP:
                if ((y + 1 - SERIES_TO_WIN) >= 0) {
                    for (int i = y; i > (y - SERIES_TO_WIN); i--) {
                        if (GAME_BOARD[i][x] != marker) {
                            return false;
                        }
                    }
                    return true;
                }
                break;
            case DOWN:
                if ((y + SERIES_TO_WIN - 1) < BOARD_SIZE) {
                    for (int i = y; i < (y + SERIES_TO_WIN); i++) {
                        if (GAME_BOARD[i][x] != marker) {
                            return false;
                        }
                    }
                    return true;
                }
                break;
            case LEFT:
                if ((x + 1 - SERIES_TO_WIN) >= 0) {
                    for (int j = x; j > (x - SERIES_TO_WIN); j--) {
                        if (GAME_BOARD[y][j] != marker) {
                            return false;
                        }
                    }
                    return true;
                }
                break;
            case RIGHT:
                if ((x + SERIES_TO_WIN - 1) < BOARD_SIZE) {
                    for (int j = x; j < x + SERIES_TO_WIN; j++) {
                        if (GAME_BOARD[y][j] != marker) {
                            return false;
                        }
                    }
                    return true;
                }
                break;
            case RIGHT_UP:
                if ((x + SERIES_TO_WIN - 1) < BOARD_SIZE && (y + 1 - SERIES_TO_WIN) >= 0) {
                    for (int i = y; i > (y - SERIES_TO_WIN); i--) {
                        if (GAME_BOARD[i][x] != marker) {
                            return false;
                        }
                        x++;
                    }
                    return true;
                }
                break;
            case RIGHT_DOWN:
                if ((x + SERIES_TO_WIN - 1) < BOARD_SIZE && (y + SERIES_TO_WIN - 1) < BOARD_SIZE) {
                    for (int i = y; i < y + SERIES_TO_WIN; i++) {
                        if (GAME_BOARD[i][x] != marker) {
                            return false;
                        }
                        x++;
                    }
                    return true;
                }
                break;
            case LEFT_UP:
                if ((x + 1 - SERIES_TO_WIN) >= 0 && (y + 1 - SERIES_TO_WIN) >= 0) {
                    for (int i = y; i > (y - SERIES_TO_WIN); i--) {
                        if (GAME_BOARD[i][x] != marker) {
                            return false;
                        }
                        x--;
                    }
                    return true;
                }
                break;
            case LEFT_DOWN:
                if ((x + 1 - SERIES_TO_WIN) >= 0 && (y + SERIES_TO_WIN - 1) < BOARD_SIZE) {
                    for (int i = y; i < y + SERIES_TO_WIN; i++) {
                        if (GAME_BOARD[i][x] != marker) {
                            return false;
                        }
                        x--;
                    }
                    return true;
                }
                break;
        }
        return false;
    }


    private static boolean isBoardFull() {
        boolean full = true;
        for (char[] internal : GAME_BOARD) {
            for (char ch : internal) {
                if (ch == EMPTY_CELL_MARKER) {
                    full = false;
                }
            }
        }
        return full;
    }

    private static void performPlayersTurn() {
        int x, y;
        do {
            System.out.println("Enter you turn coordinates Y X starting with 0(instead of 1)");
            y = scanner.nextInt();
            x = scanner.nextInt();
        } while (!canSetMarker(x, y));
        GAME_BOARD[y][x] = playersMarker;
    }

    private static void performCpuTurn() {
        int x, y;
        int[] xyArr = null;
        int retries = 100; // Freezes sometimes, if cannot get random in 100 time will set marker if first available cell
        if (aiEnabled) {
            xyArr = chooseNextTurn();
        }
        if (xyArr != null) {
            x = xyArr[0];
            y = xyArr[1];
        } else {
            do {
                x = random.nextInt(BOARD_SIZE) - 1;
                y = random.nextInt(BOARD_SIZE) - 1;
                retries--;
            } while (!canSetMarker(x, y) && retries > 0);
        }
        // if all of the above fails just get any cell(to prevent freezing)
        if (retries <= 0) {
            outer:
            for (int i = 0; i < BOARD_SIZE; i++) {
                for (int j = 0; j < BOARD_SIZE; j++) {
                    if (canSetMarker(j, i)) {
                        x = j;
                        y = i;
                        break outer;
                    }
                }
            }
        }
        System.out.println("The CPU placed its Marker at " + y + x);
        GAME_BOARD[y][x] = cpuMarker;

    }

    private static int[] chooseNextTurn() {
        int[] xyArr = null;
        outerloop:
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (canSetMarker(j, i)) {
                    GAME_BOARD[i][j] = cpuMarker;
                    if (isVictorious(cpuMarker)) {
                        xyArr = new int[]{j, i};
                        GAME_BOARD[i][j] = EMPTY_CELL_MARKER;
                        break outerloop;
                    }
                    GAME_BOARD[i][j] = playersMarker;
                    if (isVictorious(playersMarker)) {
                        xyArr = new int[]{j, i};
                    }
                    GAME_BOARD[i][j] = EMPTY_CELL_MARKER;

                }
            }
        }
        return xyArr;
    }

    private static boolean canSetMarker(int x, int y) {
        return x >= 0 && x < BOARD_SIZE && y >= 0 && y < BOARD_SIZE && GAME_BOARD[y][x] == EMPTY_CELL_MARKER;
    }

    private static void prepareBoard() {
        GAME_BOARD = new char[BOARD_SIZE][BOARD_SIZE];
        Arrays.stream(GAME_BOARD).forEach(subArray -> Arrays.fill(subArray, EMPTY_CELL_MARKER));
    }

    private static void drawBoard() {
        String horizontalBorder = IntStream.range(0, BOARD_SIZE * 4).mapToObj(o -> "_").collect(Collectors.joining());
        System.out.println(horizontalBorder);
        for (char[] row : GAME_BOARD) {
            System.out.print("|");
            for (char element : row) {
                System.out.print(" " + element + " |");
            }
            System.out.println("\n" + horizontalBorder);
        }

    }

    private enum Direction {
        RIGHT, LEFT, UP, DOWN, RIGHT_UP, RIGHT_DOWN, LEFT_UP, LEFT_DOWN
    }

}
