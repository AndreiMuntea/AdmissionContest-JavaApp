package Helper.Saver.PDFFile;

import Helper.FileExceptions.MyFileException;
import Helper.Saver.AbstractSaver;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.*;
import java.nio.file.FileSystems;
import java.util.List;

/**
 * Created by andrei on 2017-01-06.
 */
public abstract class PDFFileSaver<T> extends AbstractSaver<T> {

    public PDFFileSaver() {
    }


    @Override
    public void save(List<T> list, String fileName) throws MyFileException {
        File file = FileSystems.getDefault().getPath(fileName).toFile();
        Document document = new Document();
        try {
            PdfWriter out = PdfWriter.getInstance(document,new FileOutputStream(file));
            document.open();
            PdfPTable table = createTable();

            for(T object : list)
            {
                PdfPCell[] allCells = getCells(object);
                for(PdfPCell cell : allCells) table.addCell(cell);
            }

            document.add(table);
            document.close();
            out.close();

        } catch (IOException e) {
            throw new MyFileException(e.getMessage());
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    protected abstract PdfPTable createTable() throws DocumentException;

    protected abstract PdfPCell[] getCells(T object);

}
