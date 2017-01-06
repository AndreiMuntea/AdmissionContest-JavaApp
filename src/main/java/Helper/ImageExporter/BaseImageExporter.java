package Helper.ImageExporter;

import Utils.Exceptions.MyException;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.WritableImage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;

/**
 * Created by andrei on 2017-01-06.
 */
public abstract class BaseImageExporter implements  IImageExporter{
    @Override
    public void export(BufferedImage image, String fileName, String format) throws MyException {
        File file = new File(fileName);
        BufferedImage newBufferedImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
        newBufferedImage.createGraphics().drawImage(image, 0, 0, Color.WHITE, null);
        try {
            ImageIO.write(newBufferedImage, format.toUpperCase(), file);
        } catch (IOException e) {
            throw new MyException(e.getMessage());
        }
    }
}
