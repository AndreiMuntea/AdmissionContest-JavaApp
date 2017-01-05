package Helper.Saver.FileSaver.TextFile;

import Domain.Candidate;

/**
 * Created by andrei on 2017-01-05.
 */
public class CandidateFileSaver extends TextFileSaver<Candidate> {
    public CandidateFileSaver() {
    }

    public CandidateFileSaver(String separator) {
        super(separator);
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
