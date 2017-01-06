package Helper.Saver.PDFFile;

import Domain.Candidate;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

/**
 * Created by andrei on 2017-01-06.
 */
public class CandidatePDFSaver extends PDFFileSaver<Candidate> {
    public CandidatePDFSaver() {
    }

    @Override
    protected PdfPTable createTable() throws DocumentException {
        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100);
        table.setSpacingBefore(10f);
        table.setSpacingAfter(10f);

        float[] columnWidths = {1f, 1f, 1f, 1f, 1f};
        table.setWidths(columnWidths);

        PdfPCell cell1 = new PdfPCell(new Paragraph("ID"));
        cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
        PdfPCell cell2 = new PdfPCell(new Paragraph("Name"));
        cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
        PdfPCell cell3 = new PdfPCell(new Paragraph("Address"));
        cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
        PdfPCell cell4 = new PdfPCell(new Paragraph("Phone number"));
        cell4.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
        PdfPCell cell5 = new PdfPCell(new Paragraph("Grade"));
        cell5.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell5.setHorizontalAlignment(Element.ALIGN_CENTER);

        table.addCell(cell1);
        table.addCell(cell2);
        table.addCell(cell3);
        table.addCell(cell4);
        table.addCell(cell5);

        return table;
    }

    @Override
    protected PdfPCell[] getCells(Candidate object) {
        PdfPCell[] cells = new PdfPCell[5];
        cells[0] = new PdfPCell(new Paragraph(object.getID().toString()));
        cells[1] = new PdfPCell(new Paragraph(object.getName()));
        cells[2] = new PdfPCell(new Paragraph(object.getAddress()));
        cells[3] = new PdfPCell(new Paragraph(object.getPhoneNumber()));
        cells[4] = new PdfPCell(new Paragraph(object.getGrade().toString()));
        return cells;
    }
}
