package imageLogic;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class PixelHelper {
    private boolean isVertical;

    //get pixels of puzzle border
    private ArrayList<ArrayList<Integer>> getPixels(BufferedImage image, int x, int y, boolean isVertical) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        for (int i = 0; i < image.getHeight() - 1; i++) {
            ArrayList<Integer> pixels = new ArrayList<>();
            if (isVertical) {
                y = i;
            } else {
                x = i;
            }

            int p = image.getRGB(x, y);
            int a = (p >> 24) & 0xff;
            int r = (p >> 16) & 0xff;
            int g = (p >> 8) & 0xff;
            int b = p & 0xff;
            pixels.add(a);
            pixels.add(r);
            pixels.add(g);
            pixels.add(b);
            result.add(pixels);
        }
        return result;
    }

    //help us to get correctly pixels by some  their positions(this one get only left/right positions )
    private ArrayList<ArrayList<Integer>> getVerticalPixels(BufferedImage image, int x, int y) {
        isVertical = true;
        return getPixels(image, x, y, isVertical);
    }

    //help us to get correctly pixels by some  their positions(this one get only top/bottom positions )
    private ArrayList<ArrayList<Integer>> getHorizontalPixels(BufferedImage image, int x, int y) {
        isVertical = false;
        return getPixels(image, x, y, isVertical);
    }

    /*
    Next methods serves to get pixels in correctly way
     */
    public ArrayList<ArrayList<Integer>> getTopPixels(BufferedImage image) {
        int x = 0;
        int y = 0;
        return getHorizontalPixels(image, x, y);
    }

    public ArrayList<ArrayList<Integer>> getBottomPixels(BufferedImage image) {
        int x = 0;
        int y = image.getHeight() - 1;
        return getHorizontalPixels(image, x, y);
    }

    public ArrayList<ArrayList<Integer>> getLeftPixels(BufferedImage image) {
        int x = 0;
        int y = 0;
        return getVerticalPixels(image, x, y);
    }

    public ArrayList<ArrayList<Integer>> getRightPixels(BufferedImage image) {
        int x = image.getWidth() - 1;
        int y = 0;
        return getVerticalPixels(image, x, y);
    }

}
