package ru.geekbrains.java.level_1.lesson_8;

import java.awt.*;

public class Main {

    public static Font DEFAULT_FONT = new Font("MS UI Gothic", Font.BOLD, 40);
    public static int DEFAULT_SIZE = 800;
    public static final String DEFAULT_TITLE = "Tic Tac Toe";

    public static void main(String[] args) {
        MainWindow window = new MainWindow();
        window.setVisible(true);
        // TicTacToeWindow window = new TicTacToeWindow(3,3);
        //  EndGameWindow window1 = new EndGameWindow("Wictory");
    }
}
