package GUI.game;

import imageLogic.ImageCompare;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class PuzzlePositionHelper {
    private ImageCompare imageCompare = new ImageCompare();
    ArrayList<Integer> result;
    private BufferedImage image;
    private BufferedImage imageToCompare;

    public ArrayList<Integer> getTopSidePuzzles(ArrayList<BufferedImage> currentPuzzleImages) {
        result = new ArrayList<>();
        for (int i = 0; i < currentPuzzleImages.size(); i++) {
            int count = 0;
            image = currentPuzzleImages.get(i);
            for (int j = 0; j < currentPuzzleImages.size(); j++) {
                if (i != j) {
                    imageToCompare = currentPuzzleImages.get(j);
                    if (!imageCompare.checkTop(image, imageToCompare)) {
                        count++;
                        if (count == currentPuzzleImages.size() - 1) {
                            result.add(i);
                        }
                    }
                }
            }
        }
        return result;
    }

    public ArrayList<Integer> getBottomSidePuzzles(ArrayList<BufferedImage> currentPuzzleImages) {
        result = new ArrayList<>();
        for (int i = 0; i < currentPuzzleImages.size(); i++) {
            int count = 0;
            image = currentPuzzleImages.get(i);
            for (int j = 0; j < currentPuzzleImages.size(); j++) {
                if (i != j) {
                    imageToCompare = currentPuzzleImages.get(j);
                    if (!imageCompare.checkBottom(image, imageToCompare)) {
                        count++;
                        if (count == currentPuzzleImages.size() - 1) {
                            result.add(i);
                        }
                    }
                }
            }
        }
        return result;
    }

    public ArrayList<Integer> getLeftSidePuzzles(ArrayList<BufferedImage> currentPuzzleImages) {
        result = new ArrayList<>();
        for (int i = 0; i < currentPuzzleImages.size(); i++) {
            int count = 0;
            image = currentPuzzleImages.get(i);
            for (int j = 0; j < currentPuzzleImages.size(); j++) {
                if (i != j) {
                    imageToCompare = currentPuzzleImages.get(j);
                    if (!imageCompare.checkLeft(image, imageToCompare)) {
                        count++;
                        if (count == currentPuzzleImages.size() - 1) {
                            result.add(i);
                        }
                    }
                }
            }
        }
        return result;
    }

    public ArrayList<Integer> getRightSidePuzzles(ArrayList<BufferedImage> currentPuzzleImages) {
        result = new ArrayList<>();
        for (int i = 0; i < currentPuzzleImages.size(); i++) {
            int count = 0;
            image = currentPuzzleImages.get(i);
            for (int j = 0; j < currentPuzzleImages.size(); j++) {
                if (i != j) {
                    imageToCompare = currentPuzzleImages.get(j);
                    if (!imageCompare.checkRight(image, imageToCompare)) {
                        count++;
                        if (count == currentPuzzleImages.size() - 1) {
                            result.add(i);
                        }
                    }
                }
            }
        }
        return result;
    }

    public ArrayList<Integer> getMiddleVerticalPuzzles(ArrayList<BufferedImage> currentPuzzleImages) {
        result = new ArrayList<>();
        for (int i = 0; i < currentPuzzleImages.size(); i++) {
            int count = 0;
            image = currentPuzzleImages.get(i);
            for (int j = 0; j < currentPuzzleImages.size(); j++) {
                if (i != j) {
                    imageToCompare = currentPuzzleImages.get(j);
                    if (imageCompare.checkLeft(image, imageToCompare)) {
                        count++;
                    }
                    if (imageCompare.checkRight(image, imageToCompare)) {
                        count++;
                    }
                    if (count == 2 && !result.contains(i)) {
                        result.add(i);
                    }
                }
            }
        }
        return result;
    }

    public ArrayList<Integer> getMiddleHorizontalPuzzles(ArrayList<BufferedImage> currentPuzzleImages) {
        result = new ArrayList<>();
        for (int i = 0; i < currentPuzzleImages.size(); i++) {
            int count = 0;
            image = currentPuzzleImages.get(i);
            for (int j = 0; j < currentPuzzleImages.size(); j++) {
                if (i != j) {
                    imageToCompare = currentPuzzleImages.get(j);
                    if (imageCompare.checkBottom(image, imageToCompare)) {
                        count++;
                    }
                    if (imageCompare.checkTop(image, imageToCompare)) {
                        count++;
                    }
                    if (count == 2 && !result.contains(i)) {
                        result.add(i);
                    }
                }
            }
        }
        return result;
    }

}
