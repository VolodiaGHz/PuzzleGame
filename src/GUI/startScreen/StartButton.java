package GUI.startScreen;

import GUI.game.GameField;
import imageLogic.ImageCutter;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class StartButton extends JButton {
    private JFileChooser jFileChooser;
    private String path;
    private String fullName;
    private String name;
    private BufferedImage image;
    private ImageCutter pc;

    public StartButton(JFrame frame) {
        initUi(frame);
    }

    private void initUi(JFrame frame) {
        jFileChooser = new JFileChooser();
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFileChooser.showSaveDialog(null);
                File selectedFile = jFileChooser.getSelectedFile();
                path = selectedFile.getAbsolutePath();
                fullName = selectedFile.getName();
                name = fullName.substring(0, fullName.length() - 4);
                try {
                    pc = new ImageCutter();
                    String folderPath = pc.doCut(path, name);
                    image = ImageIO.read(new File(path));
                    GameField gui = new GameField(folderPath);

                    frame.dispose();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}
