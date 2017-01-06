package Helper.Saver.PDFFile;

import Domain.Section;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

/**
 * Created by andrei on 2017-01-06.
 */
public class SectionPDFSaver extends PDFFileSaver<Section> {

    public SectionPDFSaver() {
    }

    @Override
    protected PdfPTable createTable() throws DocumentException {
        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100);
        table.setSpacingBefore(10f);
        table.setSpacingAfter(10f);

        float[] columnWidths = {1f, 1f, 1f};
        table.setWidths(columnWidths);

        PdfPCell cell1 = new PdfPCell(new Paragraph("ID"));
        cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
        PdfPCell cell2 = new PdfPCell(new Paragraph("Name"));
        cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
        PdfPCell cell3 = new PdfPCell(new Paragraph("Available slots"));
        cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell3.setHorizontalAlignment(Element.ALIGN_CENTER);

        table.addCell(cell1);
        table.addCell(cell2);
        table.addCell(cell3);
        return table;
    }

    @Override
    protected PdfPCell[] getCells(Section object) {
        PdfPCell[] cells = new PdfPCell[3];
        cells[0] = new PdfPCell(new Paragraph(object.getID().toString()));
        cells[1] = new PdfPCell(new Paragraph(object.getName()));
        cells[2] = new PdfPCell(new Paragraph(object.getAvailableSlots().toString()));
        return cells;
    }
}
