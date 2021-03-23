package ru.geekbrains.java.level_1.lesson_8;


import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Random;

import static ru.geekbrains.java.level_1.lesson_8.Main.DEFAULT_FONT;
import static ru.geekbrains.java.level_1.lesson_8.Main.DEFAULT_SIZE;

public class TicTacToeWindow extends JFrame {
    private static final Random random = new Random();
    private JButton[][] gameBoard;
    private int boardSize;
    private char playersMarker = 'X';
    private char cpuMarker = 'O';
    private int seriesToWin;

    public TicTacToeWindow(int boardSize, int seriesToWin) {
        this.boardSize = boardSize;
        this.seriesToWin = seriesToWin;
        this.gameBoard = new JButton[boardSize][boardSize];
        int size = 100 * boardSize;
        size = size > DEFAULT_SIZE ? size : DEFAULT_SIZE;
        setSize(size, size);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        add(generateButtonGrid());
        setTitle("Tic Tac Toe");
    }

    private JPanel generateButtonGrid() {
        GridLayout layout = new GridLayout(boardSize, boardSize);
        JPanel panel = new JPanel(layout);
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                JButton button = generateButton(i, j);
                panel.add(button);
                gameBoard[i][j] = button;
            }
        }
        return panel;
    }

    private JButton generateButton(int i, int j) {
        JButton button = new JButton();
        button.addActionListener((e) -> {
            button.setText(String.valueOf(playersMarker));
            button.setEnabled(false);
            button.setBackground(Color.BLUE);
            button.setFont(DEFAULT_FONT);
            play();
        });
        return button;
    }

    private void play() {
        if (isVictorious(playersMarker)) {
            EndGameWindow window = new EndGameWindow("You win", boardSize, seriesToWin);
            this.setVisible(false);
            window.setVisible(true);
        } else if (isBoardFull()) {
            EndGameWindow window = new EndGameWindow("Tie", boardSize, seriesToWin);
            this.setVisible(false);
            window.setVisible(true);
        } else {
            performCpuTurn();
            if (isVictorious(cpuMarker)) {
                EndGameWindow window = new EndGameWindow("You lose!", boardSize, seriesToWin);
                this.setVisible(false);
                window.setVisible(true);
            } else if (isBoardFull()) {
                EndGameWindow window = new EndGameWindow("Tie", boardSize, seriesToWin);
                this.setVisible(false);
                window.setVisible(true);
            }
        }

    }


    private void performCpuTurn() {
        // To eliminate freezes, used if cannot get appropriate random number in N iterations, to exit the loop
        int retries = 100;
        int i, j;
        Point point = chooseNextTurn();
        if (point != null) {
            i = point.i;
            j = point.j;
        } else {
            do {
                i = random.nextInt(boardSize) - 1;
                j = random.nextInt(boardSize) - 1;
                retries--;
            } while (!canSetMarker(i, j) && retries > 0);
        }

        // if all of the above fails just get any cell(to prevent freezing)

        if (retries <= 0) {
            outer:
            for (int k = 0; k < boardSize; k++) {
                for (int l = 0; l < boardSize; l++) {
                    if (canSetMarker(k, l)) {
                        i = k;
                        j = l;
                        break outer;
                    }
                }
            }
        }
        JButton button = gameBoard[i][j];
        button.setText(String.valueOf(cpuMarker));
        button.setBackground(Color.RED);
        button.setFont(DEFAULT_FONT);
        button.setEnabled(false);
    }

    private Point chooseNextTurn() {
        Point point = null;
        outerloop:
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (canSetMarker(i, j)) {
                    gameBoard[i][j].setText(String.valueOf(cpuMarker));
                    if (isVictorious(cpuMarker)) {
                        point = new Point(i, j);
                        gameBoard[i][j].setText("");
                        break outerloop;
                    }
                    gameBoard[i][j].setText(String.valueOf(playersMarker));
                    if (isVictorious(playersMarker)) {
                        point = new Point(i, j);
                    }
                    gameBoard[i][j].setText("");

                }
            }
        }
        return point;
    }

    private boolean isVictorious(char marker) {
        for (int y = 0; y < boardSize; y++) {
            for (int x = 0; x < boardSize; x++) {
                for (Direction direction : Direction.values()) {
                    if (isVictorious(direction, marker, x, y)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean isVictorious(Direction direction, char marker, int x, int y) {
        switch (direction) {
            case DOWN:
                if ((y + seriesToWin - 1) < boardSize) {
                    for (int i = y; i < (y + seriesToWin); i++) {
                        if (isNotEqualToMarker(gameBoard[i][x], marker)) {
                            return false;
                        }
                    }
                    return true;
                }
                break;
            case RIGHT:
                if ((x + seriesToWin - 1) < boardSize) {
                    for (int j = x; j < x + seriesToWin; j++) {
                        if (isNotEqualToMarker(gameBoard[y][j], marker)) {
                            return false;
                        }
                    }
                    return true;
                }
                break;
            case RIGHT_UP:
                if ((x + seriesToWin - 1) < boardSize && (y + 1 - seriesToWin) >= 0) {
                    for (int i = y; i > (y - seriesToWin); i--) {
                        if (isNotEqualToMarker(gameBoard[i][x], marker)) {
                            return false;
                        }
                        x++;
                    }
                    return true;
                }
                break;
            case RIGHT_DOWN:
                if ((x + seriesToWin - 1) < boardSize && (y + seriesToWin - 1) < boardSize) {
                    for (int i = y; i < y + seriesToWin; i++) {
                        if (isNotEqualToMarker(gameBoard[i][x], marker)) {
                            return false;
                        }
                        x++;
                    }
                    return true;
                }
                break;

        }
        return false;
    }

    private boolean isNotEqualToMarker(JButton button, char marker) {
        return !String.valueOf(marker).equals(button.getText());
    }

    private boolean canSetMarker(int i, int j) {
        return insideGrid(i) && insideGrid(j) && gameBoard[i][j].isEnabled();
    }

    private boolean insideGrid(int number) {
        return number >= 0 && number < boardSize;
    }

    private boolean isBoardFull() {
        return Arrays.stream(gameBoard).flatMap(Arrays::stream).noneMatch(Component::isEnabled);
    }

    /*No need to have all directions cause RIGHT and LEFT, DOWN and UP will find the same victorious combination,
     same logic applies to diagonals */
    private enum Direction {
        RIGHT, DOWN, RIGHT_UP, RIGHT_DOWN,
    }

    private class Point {
        private int i;
        private int j;

        public Point(int i, int j) {
            this.i = i;
            this.j = j;
        }

        public int getI() {
            return i;
        }

        public int getJ() {
            return j;
        }
    }

}
