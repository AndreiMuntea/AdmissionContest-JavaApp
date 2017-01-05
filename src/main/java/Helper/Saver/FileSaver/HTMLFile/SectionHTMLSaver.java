package Helper.Saver.FileSaver.HTMLFile;

import Domain.Section;

/**
 * Created by andrei on 2017-01-06.
 */
public class SectionHTMLSaver extends HTMLFileSaver<Section> {
    public SectionHTMLSaver() {
    }

    @Override
    public String getFormat(Section object) {
        return "<tr>\n" +
                "<td>" + object.getID().toString() + "</td>\n" +
                "<td>" + object.getName() + "</td>\n" +
                "<td>" + object.getAvailableSlots().toString() + "</td>\n" +
                "</tr>\n";
    }

    @Override
    protected String getColumns() {
        return "<tr>\n" +
                "<th>" + "ID" + "</th>\n" +
                "<th>" + "Name" + "</th>\n" +
                "<th>" + "Available slots" + "</th>\n" +
                "</tr>\n";
    }
}
