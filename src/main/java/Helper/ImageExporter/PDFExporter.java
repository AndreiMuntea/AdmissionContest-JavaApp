package Helper.ImageExporter;

import Utils.Exceptions.MyException;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by andrei on 2017-01-06.
 */
public class PDFExporter implements IImageExporter {

    @Override
    public void export(BufferedImage image, String fileName, String format) throws MyException {
        BufferedImage newBufferedImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
        newBufferedImage.createGraphics().drawImage(image, 0, 0, Color.WHITE, null);
        File file = new File(fileName);


        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(file));
            document.open();
            Image img1 = Image.getInstance(newBufferedImage, null);
            img1.scaleAbsolute(600,350);
            document.add(img1);
            document.close();
        } catch(Exception e){
            throw new MyException(e.getMessage());
        }
    }
}
