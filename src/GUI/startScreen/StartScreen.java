package GUI.startScreen;

import javax.swing.*;
import java.awt.*;

public class StartScreen extends JFrame {
    private final static int FRAME_HEIGHT = 500;
    private final static int FRAME_WIDTH = 500;


    public StartScreen() {
        buildUi();
    }

    private void buildUi() {
        JPanel panel = new JPanel();
        JLabel greet = new JLabel();
        StartButton startButton = new StartButton(this);
        startButton.setText("Start Game");
        setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        panel.setLayout(new FlowLayout());
        greet.setText("Welcome to puzzles");
        panel.add(startButton);
        panel.add(greet);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(panel);
        pack();
        setVisible(true);
    }
}
