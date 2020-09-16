package GUI.game;

import imageLogic.ImageEditor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class PuzzleButton extends JButton {
    private volatile int screenX = 0;
    private volatile int screenY = 0;
    private volatile int myX = 0;
    private volatile int myY = 0;
    private boolean isDragged;
    private int chosenButton;
    private GameLogic gameLogic;
    private ImageEditor imageEditor;

    public void initUi(ArrayList<PuzzleButton> buttons, JPanel panel,
                       ArrayList<BufferedImage> puzzles,
                       ArrayList<BufferedImage> resizedImages,
                       ArrayList<BufferedImage> rotatedImages) {
        gameLogic = new GameLogic();
        mouseWork(buttons, panel, puzzles, resizedImages, rotatedImages);
        mouseDrag();
    }

    private void mouseDrag() {
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                super.mouseDragged(e);
                isDragged = true;
                int deltaX = e.getXOnScreen() - screenX;
                int deltaY = e.getYOnScreen() - screenY;
                setLocation(myX + deltaX, myY + deltaY);
            }
        });
    }

    private void mouseWork(ArrayList<PuzzleButton> buttons, JPanel panel,
                           ArrayList<BufferedImage> currentPuzzles,
                           ArrayList<BufferedImage> correctPuzzles,
                           ArrayList<BufferedImage> rotatedPuzzles) {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                JButton button = (JButton) e.getSource();
                imageEditor = new ImageEditor();
                imageEditor.rotatePuzzle(button, currentPuzzles, correctPuzzles, rotatedPuzzles);
                gameLogic.checkSolution(buttons, correctPuzzles, currentPuzzles);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                JButton button = (JButton) e.getSource();
                chosenButton = buttons.indexOf(button);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                JButton b = (JButton) e.getSource();
                if (isDragged) {
                    gameLogic.swapButtons(buttons, chosenButton, panel);
                    gameLogic.checkSolution(buttons, correctPuzzles, currentPuzzles);
                }
                isDragged = !isDragged;
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);

                setBorder(BorderFactory.createLineBorder(Color.RED));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                setBorder(BorderFactory.createLineBorder(Color.GRAY));

            }
        });
    }

}
