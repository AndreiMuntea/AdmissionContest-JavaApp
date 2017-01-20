package Helper.Saver.XMLFile;

import Domain.Candidate;
import Helper.FileExceptions.MyFileException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Created by andrei on 2017-01-20.
 */
public class CandidatesXMLSaver extends XMLFileSaver<Candidate>{
    private String root;
    private final static String DEFAULT_ROOT = "Candidates";

    public CandidatesXMLSaver(){root = DEFAULT_ROOT;}

    public CandidatesXMLSaver(String root){this.root = root;}

    @Override
    public Element createElementFromFormat(Document document, Candidate element) throws MyFileException {
        Element candidateElement = document.createElement("Candidate");

        Element candidateID = document.createElement("ID");
        candidateID.setTextContent(element.getID().toString());

        Element candidateName = document.createElement("name");
        candidateName.setTextContent(element.getName());

        Element candidateAddress = document.createElement("address");
        candidateAddress.setTextContent(element.getAddress());

        Element candidateGrade = document.createElement("grade");
        candidateGrade.setTextContent(element.getGrade().toString());

        Element candidatePhoneNumber = document.createElement("phoneNumber");
        candidatePhoneNumber.setTextContent(element.getPhoneNumber());

        candidateElement.appendChild(candidateID);
        candidateElement.appendChild(candidateName);
        candidateElement.appendChild(candidateAddress);
        candidateElement.appendChild(candidateGrade);
        candidateElement.appendChild(candidatePhoneNumber);

        return candidateElement;
    }


    @Override
    public String getRoot() {
        return root;
    }
}
