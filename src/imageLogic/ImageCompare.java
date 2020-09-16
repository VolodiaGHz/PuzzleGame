package imageLogic;

import GUI.game.PuzzleButton;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class ImageCompare {
    private static final String PROPERTY_KEY = "originPuzzle";
    private PixelHelper pixelHelper = new PixelHelper();
    private boolean isVertical;

    //method serves like template to easily way checking between different components
    private boolean checking(BufferedImage comparableImage, BufferedImage imageToCompare, boolean isVertical) {
        ArrayList<ArrayList<Integer>> comparePixels;
        ArrayList<ArrayList<Integer>> comparablePixels;
        if (!isVertical) {
            comparePixels = pixelHelper.getLeftPixels(comparableImage);
            comparablePixels = pixelHelper.getRightPixels(imageToCompare);
        } else {
            comparePixels = pixelHelper.getTopPixels(comparableImage);
            comparablePixels = pixelHelper.getBottomPixels(imageToCompare);

        }
        if (getPercentage(comparablePixels, comparePixels) > 65) {
            return true;
        }
        return false;
    }

    //check one image with left one
    public boolean checkLeft(BufferedImage currentImage, BufferedImage leftImage) {
        isVertical = false;
        return checking(currentImage, leftImage, isVertical);
    }

    //check one image with top one
    public boolean checkTop(BufferedImage currentImage, BufferedImage topImage) {
        isVertical = true;
        return checking(currentImage, topImage, isVertical);
    }

    //check one image with bottom one
    public boolean checkBottom(BufferedImage currentImage, BufferedImage bottomImage) {
        isVertical = true;
        return checking(bottomImage, currentImage, isVertical);
    }

    //check one image with right one
    public boolean checkRight(BufferedImage currentImage, BufferedImage rightImage) {
        isVertical = false;
        return checking(rightImage, currentImage, isVertical);
    }

    //checking puzzles vertically which located on middle screen
    public boolean checkVertical(ArrayList<PuzzleButton> buttons, ArrayList<BufferedImage> currentPuzzles) {
        int count = 0;
        for (PuzzleButton b : buttons) {
            if (b.getClientProperty(PROPERTY_KEY) != null) {
                int buttonIdx = (int) b.getClientProperty(PROPERTY_KEY);
                int buttonPosition = buttons.indexOf(b);
                BufferedImage currentImage = currentPuzzles.get(buttonIdx);
                if (buttonPosition == 3 || buttonPosition == 4) {
                    PuzzleButton topButton = buttons.get(buttonPosition - 3);
                    PuzzleButton bottomButton = buttons.get(buttonPosition + 3);
                    if (topButton.getClientProperty(PROPERTY_KEY) != null
                            && bottomButton.getClientProperty(PROPERTY_KEY) != null) {
                        int TopButtonIdx = (int) topButton.getClientProperty(PROPERTY_KEY);
                        int bottomButtonIdx = (int) bottomButton.getClientProperty(PROPERTY_KEY);
                        BufferedImage topImage = currentPuzzles.get(TopButtonIdx);
                        BufferedImage bottomImage = currentPuzzles.get(bottomButtonIdx);
                        if (checkTop(currentImage, topImage) && checkBottom(currentImage, bottomImage))
                            count++;
                    }
                } else if (buttonPosition == 5) {
                    PuzzleButton topButton = buttons.get(buttonPosition - 3);
                    if (topButton.getClientProperty(PROPERTY_KEY) != null) {
                        int TopButtonIdx = (int) topButton.getClientProperty(PROPERTY_KEY);
                        BufferedImage topImage = currentPuzzles.get(TopButtonIdx);
                        if (checkTop(currentImage, topImage)) {
                            count++;
                        }
                    }
                }
            }
        }
        if (count >= 3) {
            return true;
        }
        return false;
    }

    //checking puzzles horizontally which located in middle of field
    public boolean checkHorizontal(ArrayList<PuzzleButton> buttons, ArrayList<BufferedImage> currentPuzzles) {
        int count = 0;
        for (PuzzleButton b : buttons) {
            if (b.getClientProperty(PROPERTY_KEY) != null) {
                int buttonIdx = (int) b.getClientProperty(PROPERTY_KEY);
                int buttonPosition = buttons.indexOf(b);
                BufferedImage currentImage = currentPuzzles.get(buttonIdx);
                if (buttonPosition == 1 || buttonPosition == 4) {
                    PuzzleButton leftButton = buttons.get(buttonPosition - 1);
                    PuzzleButton rightButton = buttons.get(buttonPosition + 1);
                    if (rightButton.getClientProperty(PROPERTY_KEY) != null
                            && leftButton.getClientProperty(PROPERTY_KEY) != null) {
                        int rightButtonIdx = (int) rightButton.getClientProperty(PROPERTY_KEY);
                        int leftButtonImage = (int) leftButton.getClientProperty(PROPERTY_KEY);
                        BufferedImage rightImage = currentPuzzles.get(rightButtonIdx);
                        BufferedImage leftImage = currentPuzzles.get(leftButtonImage);
                        if (checkLeft(currentImage, leftImage)
                                && checkRight(currentImage, rightImage))
                            count++;
                    }
                } else if (buttonPosition == 7) {
                    PuzzleButton leftButton = buttons.get(buttonPosition - 1);
                    if (leftButton.getClientProperty(PROPERTY_KEY) != null) {
                        int leftButtonImage = (int) leftButton.getClientProperty(PROPERTY_KEY);
                        BufferedImage leftImage = currentPuzzles.get(leftButtonImage);
                        if (checkLeft(currentImage, leftImage)) {
                            count++;
                        }
                    }
                }
            }
        }
        if (count >= 3) {
            return true;
        }
        return false;
    }

    //compare images by pixels and return percentages
    private Integer getPercentage(ArrayList<ArrayList<Integer>> currentImagePixels,
                                  ArrayList<ArrayList<Integer>> pixelsToCompare) {
        int count = 0;
        for (int i = 0; i < currentImagePixels.size(); i++) {
            int matches = 0;
            for (int j = 1; j < currentImagePixels.get(i).size(); j++) {
                int pixel = currentImagePixels.get(i).get(j);
                int pixelToCompare = pixelsToCompare.get(i).get(j);
                if (pixel == pixelToCompare) {
                    matches++;
                } else if (pixel > pixelToCompare) {
                    if ((pixel - pixelToCompare) <= 15) {
                        matches++;
                    }
                } else {
                    if ((pixelToCompare - pixel) <= 15) {
                        matches++;
                    }
                }
            }
            if (matches > 2) {
                count++;
            }
        }
        return (count * 100) / (currentImagePixels.size());
    }

}
