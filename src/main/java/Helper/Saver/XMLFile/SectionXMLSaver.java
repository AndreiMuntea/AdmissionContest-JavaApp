package Helper.Saver.XMLFile;

import Domain.Section;
import Helper.FileExceptions.MyFileException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Created by andrei on 2017-01-20.
 */
public class SectionXMLSaver extends XMLFileSaver<Section> {
    private String root;
    private final static String DEFAULT_ROOT = "Sections";

    public SectionXMLSaver(){root = DEFAULT_ROOT;}

    public SectionXMLSaver(String root){this.root = root;}

    @Override
    public Element createElementFromFormat(Document document, Section element) throws MyFileException {
        Element sectionElement = document.createElement("Section");

        Element sectionID = document.createElement("ID");
        sectionID.setTextContent(element.getID().toString());

        Element sectionName = document.createElement("name");
        sectionName.setTextContent(element.getName());

        Element sectionSlots = document.createElement("slots");
        sectionSlots.setTextContent(element.getAvailableSlots().toString());

        sectionElement.appendChild(sectionID);
        sectionElement.appendChild(sectionName);
        sectionElement.appendChild(sectionSlots);

        return sectionElement;
    }


    @Override
    public String getRoot() {
        return root;
    }
}
