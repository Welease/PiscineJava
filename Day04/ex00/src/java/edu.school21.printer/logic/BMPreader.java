package src.java.edu.school21.printer.app;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.FileInputStream;
import java.awt.Color;
import java.io.IOException;

public class BMPreader {
    private BufferedImage image;
    private char white;
    private char black;
    public BMPreader(char white, char black, FileInputStream file) throws IOException {
        this.white = white;
        this.black = black;
        image = ImageIO.read(file);
    }
    public void printBMP() {
        int [][] arr = new int[image.getHeight()][image.getWidth()];

        for (int i = 0; i < image.getHeight(); i++) {
            for (int j = 0; j < image.getWidth(); j++) {
                int clr = image.getRGB(j,i);
                if (clr == Color.BLACK.getRGB())
                    arr[i][j] = 1;
                else
                    arr[i][j] = 0;
            }
        }

        for (int i = 0;i < arr.length; ++i) {
            for (int j = 0; j < arr[i].length; ++j) {
                System.out.print(arr[i][j] == 1 ? white : black);
            }
            System.out.print("\n");
        }
    }
}
