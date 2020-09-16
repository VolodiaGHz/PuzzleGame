package imageLogic;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class ImageEditor {

    private ArrayList<BufferedImage> imagesList;

    private final static int GAME_WIDTH = 600;

    //build a list with correctly inverted images
    public ArrayList<BufferedImage> getCorrectImages(String path) {
        try {
            ArrayList<BufferedImage> correctPuzzleList = new ArrayList<>();
            imagesList = loadImages(path);
            if (imagesList != null) {
                for (int i = 0; i < imagesList.size(); i++) {
                    BufferedImage image = imagesList.get(i);
                    int h = getNewHeight(image.getWidth(), image.getHeight());
                    BufferedImage resized = resizeImage(image, GAME_WIDTH, h, BufferedImage.TYPE_INT_ARGB);
                    correctPuzzleList.add(resized);
                }
            } else {
                return null;
            }
            return correctPuzzleList;
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private ArrayList<BufferedImage> loadImages(String path) throws IOException {
        imagesList = new ArrayList<>();
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();
        if (listOfFiles != null) {
            for (File file : listOfFiles) {
                BufferedImage image = ImageIO.read(new File(file.getAbsolutePath()));
                imagesList.add(image);
            }
        } else {
            return null;
        }
        return imagesList;
    }

    //method to get new size image for the best quality of puzzles
    private BufferedImage resizeImage(BufferedImage origin, int width, int height, int type) {
        BufferedImage resizeImage = new BufferedImage(width / 3, height, type);

        Graphics2D gr = resizeImage.createGraphics();

        gr.drawImage(origin, 0, 0, width / 3, height, null);
        gr.dispose();
        return resizeImage;
    }

    //rotate puzzles
    public void rotatePuzzle(JButton button, ArrayList<BufferedImage> currentPuzzles,
                             ArrayList<BufferedImage> correctPuzzleList,
                             ArrayList<BufferedImage> rotatedPuzzleList) {
        int correctIdx = (int) button.getClientProperty("originPuzzle");
        if (currentPuzzles.get(correctIdx).getSource().equals(correctPuzzleList.get(correctIdx).getSource())) {
            button.setIcon(new ImageIcon(rotatedPuzzleList.get(correctIdx)));
            Collections.replaceAll(currentPuzzles, currentPuzzles.get(correctIdx), rotatedPuzzleList.get(correctIdx));
        } else if (currentPuzzles.get(correctIdx).getSource().equals(rotatedPuzzleList.get(correctIdx).getSource())) {
            button.setIcon(new ImageIcon(correctPuzzleList.get(correctIdx)));
            Collections.replaceAll(currentPuzzles, currentPuzzles.get(correctIdx), correctPuzzleList.get(correctIdx));
        }
    }

    //build a list with incorrectly inverted images
    public ArrayList<BufferedImage> getRotatedPuzzles(ArrayList<BufferedImage> images) {
        ArrayList<BufferedImage> finalImageList = new ArrayList<>();
        BufferedImage rotatedImage;
        for (int i = 0; i < images.size(); i++) {

            rotatedImage = images.get(i);
            AffineTransform tx = AffineTransform.getScaleInstance(-1, -1);
            tx.translate(-rotatedImage.getWidth(null), -rotatedImage.getHeight(null));
            AffineTransformOp op = new AffineTransformOp(tx,
                    AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
            rotatedImage = op.filter(rotatedImage, null);
            finalImageList.add(rotatedImage);
        }
        return finalImageList;
    }

    //calculate new height of image for best quality of puzzles
    public int getNewHeight(int w, int h) {
        double ratio = (GAME_WIDTH / 3) / (double) w;
        int newHeight = (int) (h * ratio);
        return newHeight;
    }

}
