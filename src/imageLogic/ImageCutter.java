package imageLogic;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageCutter {
    private final static int ROW = 3;
    private final static int COL = 3;
    private final static String RESULT_PATH = "puzzles/";
    private final static String FORMAT = ".jpg";

    //cut image on pieces
    public String doCut(String path, String name) {
        File formatterFile = new File(RESULT_PATH + name);
        try {
            BufferedImage originalImage = ImageIO.read(new File(path));

            int tWidth = originalImage.getWidth();
            int tHeight = originalImage.getHeight();
            int eWidth = tWidth / COL;
            int eHeight = tHeight / ROW;
            int x = 0;
            int y;
            int count = 0;
            if (!formatterFile.exists()) {
                for (int i = 0; i < ROW; i++) {
                    y = 0;
                    for (int j = 0; j < COL; j++) {
                        try {
                            BufferedImage SubImage = originalImage.getSubimage(y, x, eWidth, eHeight);
                            File outputFile = new File(RESULT_PATH + name);
                            outputFile.mkdirs();
                            if (count != 8) {
                                int photoName = (int) (Math.random() * 1000);
                                File outputImage = new File(RESULT_PATH + name + "/" + photoName + FORMAT);
                                ImageIO.write(SubImage, "jpg", outputImage);
                                y += eWidth;
                                count++;
                            } else {
                                String lastPuzzle = "lastPuzzle";
                                File outputImage = new File(RESULT_PATH + name + "/" + lastPuzzle + FORMAT);
                                ImageIO.write(SubImage, "jpg", outputImage);
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    x += eHeight;
                }
                return formatterFile.getAbsolutePath();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return formatterFile.getAbsolutePath();
    }
}
