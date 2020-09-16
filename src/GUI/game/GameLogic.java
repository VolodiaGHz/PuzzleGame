package GUI.game;

import imageLogic.ImageCompare;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;

public class GameLogic {

    //swap buttons between each other
    public void swapButtons(ArrayList<PuzzleButton> buttons,
                            int chosenButton, JPanel panel) {
        int emptyButtonIndex = 0;
        for (JButton button : buttons) {
            if (button.getIcon() == null) {
                emptyButtonIndex = buttons.indexOf(button);
            }
        }

        if ((chosenButton - 1 == emptyButtonIndex) || (chosenButton + 1 == emptyButtonIndex)
                || (chosenButton - 3 == emptyButtonIndex) || (chosenButton + 3 == emptyButtonIndex)) {
            Collections.swap(buttons, emptyButtonIndex, chosenButton);
        }
        updateButtons(panel, buttons);


    }

    //randomly swap rotated images with origin
    public ArrayList<BufferedImage> getPuzzleList(ArrayList<BufferedImage> correctPuzzleList,
                                                  ArrayList<BufferedImage> rotatedPuzzleList) {
        ArrayList<BufferedImage> resultList = new ArrayList<>();
        BufferedImage rotatedPuzzle;
        BufferedImage originPuzzle;
        int randomNumber = (int) (Math.random() * 10);
        for (int i = 0; i < correctPuzzleList.size(); i++) {
            if (randomNumber > 5) {
                rotatedPuzzle = rotatedPuzzleList.get(i);
                resultList.add(rotatedPuzzle);
            } else {
                originPuzzle = correctPuzzleList.get(i);
                resultList.add(originPuzzle);
            }
        }
        return resultList;
    }

    //rebuild field with buttons
    public void updateButtons(JPanel panel, ArrayList<PuzzleButton> buttons) {
        panel.removeAll();
        for (JComponent button : buttons) {
            panel.add(button);
        }
        panel.validate();

    }


    public boolean checkSolution(ArrayList<PuzzleButton> buttons, ArrayList<BufferedImage> correctPuzzleList,
                                 ArrayList<BufferedImage> currentPuzzles) {
        if (checkCorrectRotate(correctPuzzleList, currentPuzzles)) {
            ImageCompare imageCompare = new ImageCompare();
            if (imageCompare.checkVertical(buttons, currentPuzzles) &&
                    imageCompare.checkHorizontal(buttons, currentPuzzles)) {
                JOptionPane.showMessageDialog(null, "CONGRATS, YOU WIN!");
                return true;
            }
        }
        return false;
    }

    private boolean checkCorrectRotate(ArrayList<BufferedImage> correctPuzzles,
                                       ArrayList<BufferedImage> currentPuzzles) {

        return correctPuzzles.toString().contentEquals(currentPuzzles.toString());

    }
}

