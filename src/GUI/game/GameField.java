package GUI.game;

import imageLogic.ImageEditor;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.util.ArrayList;
import java.util.Collections;

public class GameField extends JFrame {
    private ArrayList<PuzzleButton> buttons;
    private JPanel gamePanel;
    private JPanel mainPanel;
    private GameLogic gameLogic = new GameLogic();
    private ArrayList<BufferedImage> correctPuzzleList;
    private ArrayList<BufferedImage> puzzleList;
    private ArrayList<BufferedImage> rotatedPuzzleList;

    public GameField(String path) {
        buildUi(path);
    }

    //build whole UI part of game
    private void buildUi(String path) {
        buttons = new ArrayList<>();
        gamePanel = new JPanel();
        ImageEditor imageEditor = new ImageEditor();
        correctPuzzleList = new ArrayList<>();
        rotatedPuzzleList = new ArrayList<>();
        gamePanel.setLayout(new GridLayout(3, 3));
        correctPuzzleList = imageEditor.getCorrectImages(path);
        rotatedPuzzleList = imageEditor.getRotatedPuzzles(correctPuzzleList);
        puzzleList = gameLogic.getPuzzleList(correctPuzzleList, rotatedPuzzleList);
        if (puzzleList != null) {
            buttons = initButtons(puzzleList);
            Collections.shuffle(buttons);
            initComponents();
            add(mainPanel, BorderLayout.CENTER);
            pack();
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setResizable(false);
            setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Something gone wrong!");

        }
    }

    //initialization all needed components
    private void initComponents() {
        PuzzleButton emptyButton = new PuzzleButton();
        emptyButton.setBorderPainted(false);
        emptyButton.setContentAreaFilled(false);
        emptyButton.putClientProperty("originPuzzle", null);
        buttons.add(emptyButton);
        for (PuzzleButton button : buttons) {
            button.initUi(buttons, gamePanel, puzzleList, correctPuzzleList, rotatedPuzzleList);
            gamePanel.add(button);
        }
        mainPanel = new JPanel();
        ShowResultButton showResultButton = new ShowResultButton("GET RESULT");
        showResultButton.initUi(buttons, gamePanel, correctPuzzleList, puzzleList);
        mainPanel.setLayout(new FlowLayout());
        mainPanel.add(gamePanel);
        mainPanel.add(showResultButton);
    }

    //init buttons with icons
    private ArrayList<PuzzleButton> initButtons(ArrayList<BufferedImage> puzzleList) {
        buttons = new ArrayList<>();
        int count = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (j == 2 && i == 2) {
                    return buttons;
                }
                if (count < puzzleList.size()) {
                    int width = puzzleList.get(count).getWidth();
                    int height = puzzleList.get(count).getHeight();
                    PuzzleButton button = new PuzzleButton();
                    button.putClientProperty("originPuzzle", count);
                    BufferedImage puzzle = puzzleList.get(count);
                    Image image = createImage(new FilteredImageSource(puzzle.getSource(),
                            new CropImageFilter(0, 0, width, height)));
                    button.setIcon(new ImageIcon(image));
                    button.putClientProperty("position", new Point(i, j));
                    buttons.add(button);
                    count++;
                }

            }
        }
        return buttons;
    }
}
