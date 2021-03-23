package ru.geekbrains.java.level_1.lesson_8;

import javax.swing.*;
import java.awt.*;

import static ru.geekbrains.java.level_1.lesson_8.Main.*;

public class EndGameWindow extends JFrame {

    public EndGameWindow(String message, int boardSize, int seriesToWin) {
        setSize(DEFAULT_SIZE, DEFAULT_SIZE);
        setLocationRelativeTo(null);
        setTitle(DEFAULT_TITLE);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        GridLayout layout = new GridLayout(4, 1);
        JPanel panel = new JPanel(layout);


        JTextField field = new JTextField(message);
        field.setHorizontalAlignment(JTextField.CENTER);
        field.setEditable(false);
        field.setFont(DEFAULT_FONT);
        if (message.toLowerCase().contains("tie")) {
            field.setBackground(Color.GRAY);
        } else if (message.toLowerCase().contains("win")) {
            field.setBackground(Color.GREEN);
        } else {
            field.setBackground(Color.RED);
        }

        JButton replayButton = new JButton("Replay");
        replayButton.setFont(DEFAULT_FONT);
        replayButton.addActionListener((e) -> {
            TicTacToeWindow window = new TicTacToeWindow(boardSize, seriesToWin);
            this.setVisible(false);
            window.setVisible(true);
        });

        JButton changeRules = new JButton("Change rules");
        changeRules.setFont(DEFAULT_FONT);
        changeRules.addActionListener((e) -> {
            MainWindow window = new MainWindow();
            this.setVisible(false);
            window.setVisible(true);
        });

        JButton closeButton = new JButton("Close");
        closeButton.setFont(DEFAULT_FONT);
        closeButton.addActionListener((e) -> System.exit(0));

        panel.add(field);
        panel.add(replayButton);
        panel.add(changeRules);
        panel.add(closeButton);

        add(panel);
    }
}
