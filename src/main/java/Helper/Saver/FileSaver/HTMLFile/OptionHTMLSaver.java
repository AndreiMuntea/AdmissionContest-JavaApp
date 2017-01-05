package Helper.Saver.FileSaver.HTMLFile;

import Domain.Option;

/**
 * Created by andrei on 2017-01-06.
 */
public class OptionHTMLSaver extends HTMLFileSaver<Option> {

    public OptionHTMLSaver() {
    }

    @Override
    public String getFormat(Option object) {
        return "<tr>\n" +
                "<td>" + object.getCandidateID().toString() + "</td>\n" +
                "<td>" + object.getSectionID().toString() + "</td>\n" +
                "</tr>\n";
    }

    @Override
    protected String getColumns() {
        return "<tr>\n" +
                "<th>" + "Candidate ID" + "</th>\n" +
                "<th>" + "Section ID" + "</th>\n" +
                "</tr>\n";
    }
}
