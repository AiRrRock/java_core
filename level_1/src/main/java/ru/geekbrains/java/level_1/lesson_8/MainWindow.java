package ru.geekbrains.java.level_1.lesson_8;


import javax.swing.*;
import java.awt.*;

import static ru.geekbrains.java.level_1.lesson_8.Main.*;

public class MainWindow extends JFrame {

    public MainWindow() {
        //setting size and position on screen and title
        setSize(DEFAULT_SIZE, DEFAULT_SIZE);
        setLocationRelativeTo(null);
        setTitle(DEFAULT_TITLE);

        //setting closing on 'x' button
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //Creating inner pane
        GridLayout innerLayout = new GridLayout(2, 2);
        JPanel innerPanel = new JPanel(innerLayout);

        JTextField gridSizeText = new JTextField("Grid size");
        gridSizeText.setEditable(false);
        gridSizeText.setFont(DEFAULT_FONT);

        JTextField gridSizePanel = new JTextField("3");
        gridSizePanel.setFont(DEFAULT_FONT);
        gridSizePanel.setHorizontalAlignment(JTextField.CENTER);

        JTextField seriesToWinText = new JTextField("Series to win");
        seriesToWinText.setFont(DEFAULT_FONT);
        seriesToWinText.setEditable(false);

        JTextField seriesToWinPanel = new JTextField("3");
        seriesToWinPanel.setFont(DEFAULT_FONT);
        seriesToWinPanel.setHorizontalAlignment(JTextField.CENTER);

        innerPanel.add(gridSizeText);
        innerPanel.add(gridSizePanel);
        innerPanel.add(seriesToWinText);
        innerPanel.add(seriesToWinPanel);

        JButton play = new JButton("Play");
        play.setFont(DEFAULT_FONT);
        play.addActionListener((e) -> {
            int seriesSize = parseIntOrDefault(seriesToWinPanel.getText(), 3);
            int boardSize = parseIntOrDefault(gridSizePanel.getText(), 3);
            if (boardSize >= seriesSize) {
                TicTacToeWindow window = new TicTacToeWindow(boardSize, seriesSize);
                this.setVisible(false);
                window.setVisible(true);
            } else {
                seriesToWinPanel.setText("3");
                gridSizePanel.setText("3");
            }
        });

        JButton close = new JButton("Close");
        close.setFont(DEFAULT_FONT);
        close.addActionListener((e) -> System.exit(0));

        GridLayout layout = new GridLayout(3, 1);
        JPanel panel = new JPanel(layout);
        panel.add(innerPanel);
        panel.add(play);
        panel.add(close);

        add(panel);
    }

    private int parseIntOrDefault(String s, int defaultValue) {
        int i;
        if (s == null || s.isEmpty()) {
            i = defaultValue;
        } else {
            try {
                i = Integer.parseInt(s);
            } catch (NumberFormatException e) {
                i = defaultValue;
            }
        }
        return i;
    }
}
