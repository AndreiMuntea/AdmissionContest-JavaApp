package Helper.ImageExporter;

import Utils.Exceptions.MyException;

import java.awt.image.BufferedImage;

/**
 * Created by andrei on 2017-01-06.
 */
public interface IImageExporter {
    void export(BufferedImage image, String fileName, String format) throws MyException;
}
