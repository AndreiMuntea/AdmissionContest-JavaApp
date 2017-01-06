package Helper.Saver.PDFFile;

import Domain.Option;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

/**
 * Created by andrei on 2017-01-06.
 */
public class OptionPDFSaver extends PDFFileSaver<Option> {
    public OptionPDFSaver() {
    }

    @Override
    protected PdfPTable createTable() throws DocumentException {
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        table.setSpacingBefore(10f);
        table.setSpacingAfter(10f);

        float[] columnWidths = {1f, 1f};
        table.setWidths(columnWidths);

        PdfPCell cell1 = new PdfPCell(new Paragraph("Candidate ID"));
        cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
        PdfPCell cell2 = new PdfPCell(new Paragraph("Section ID"));
        cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell2.setHorizontalAlignment(Element.ALIGN_CENTER);

        table.addCell(cell1);
        table.addCell(cell2);

        return table;
    }

    @Override
    protected PdfPCell[] getCells(Option object) {
        PdfPCell[] cells = new PdfPCell[2];
        cells[0] = new PdfPCell(new Paragraph(object.getCandidateID().toString()));
        cells[1] = new PdfPCell(new Paragraph(object.getSectionID().toString()));
        return cells;
    }
}
