package Helper.Saver.FileSaver.CSVFile;

import Domain.Candidate;

/**
 * Created by andrei on 2017-01-05.
 */
public class CandidateCSVFileSaver extends CSVFileSaver<Candidate> {

    public CandidateCSVFileSaver() {
    }

    @Override
    public String getFormat(Candidate object) {
        return object.getID() + separator +
                object.getName() + separator +
                object.getAddress() + separator +
                object.getPhoneNumber() + separator +
                object.getGrade();
    }
}
