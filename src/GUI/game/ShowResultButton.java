package GUI.game;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;

public class ShowResultButton extends JButton {
    private GameLogic gameLogic;

    public ShowResultButton(String name) {
        super(name);
    }

    public void initUi(ArrayList<PuzzleButton> buttons,
                       JPanel panel, ArrayList<BufferedImage> correctPuzzleImage,
                       ArrayList<BufferedImage> currentPuzzleImage) {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                getResult(buttons, panel, correctPuzzleImage, currentPuzzleImage);
            }
        });
    }

    //get correct rotate of images
    private void getCorrectRotate(ArrayList<PuzzleButton> buttons,
                                  ArrayList<BufferedImage> correctPuzzleImages,
                                  ArrayList<BufferedImage> currentPuzzleImages) {
        for (int i = 0; i < buttons.size() - 1; i++) {
            if (buttons.get(i).getIcon() != null) {
                int correctIdx = (int) buttons.get(i).getClientProperty("originPuzzle");
                if (!currentPuzzleImages.get(correctIdx).getSource().equals(correctPuzzleImages.get(correctIdx).getSource())) {
                    buttons.get(i).setIcon(new ImageIcon(correctPuzzleImages.get(correctIdx)));
                    Collections.replaceAll(currentPuzzleImages, currentPuzzleImages.get(correctIdx), correctPuzzleImages.get(correctIdx));
                }
            }
        }
    }

    //get final result of puzzles
    private void getResult(ArrayList<PuzzleButton> buttons,
                           JPanel panel, ArrayList<BufferedImage> correctPuzzleImages,
                           ArrayList<BufferedImage> currentPuzzleImages) {
        gameLogic = new GameLogic();
        getCorrectRotate(buttons, correctPuzzleImages, currentPuzzleImages);
        PuzzleResult puzzleResult = new PuzzleResult();
        ArrayList<Integer> correctPositions = puzzleResult.getResult(currentPuzzleImages);
        for (int i = 0; i < correctPositions.size(); i++) {
            int imageIndex = correctPositions.get(i);
            for (PuzzleButton button : buttons) {
                if (button.getIcon() != null) {
                    int imageInButton = (int) button.getClientProperty("originPuzzle");
                    if (imageIndex == imageInButton) {
                        Collections.swap(buttons, buttons.indexOf(buttons.get(i)), buttons.indexOf(button));
                    }
                }
            }

        }
        gameLogic.updateButtons(panel, buttons);
        gameLogic.checkSolution(buttons, correctPuzzleImages, currentPuzzleImages);
    }


}
