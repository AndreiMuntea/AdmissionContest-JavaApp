package Helper.Saver.FileSaver.HTMLFile;

import Domain.Candidate;

/**
 * Created by andrei on 2017-01-06.
 */
public class CandidateHTMLSaver extends HTMLFileSaver<Candidate> {
    public CandidateHTMLSaver() {
    }

    @Override
    public String getFormat(Candidate object) {
        return "<tr>\n" +
                "<td>" + object.getID().toString() + "</td>\n" +
                "<td>" + object.getName() + "</td>\n" +
                "<td>" + object.getAddress() + "</td>\n" +
                "<td>" + object.getPhoneNumber() + "</td>\n" +
                "<td>" + object.getGrade().toString() + "</td>\n" +
                "</tr>\n";
    }

    @Override
    protected String getColumns() {
       return  "<tr>\n" +
                "<th>" + "ID" + "</th>\n" +
                "<th>" + "Name" + "</th>\n" +
                "<th>" + "Address" + "</th>\n" +
                "<th>" + "Phone number" + "</th>\n" +
                "<th>" + "Grade" + "</th>\n" +
                "</tr>\n";
    }
}
