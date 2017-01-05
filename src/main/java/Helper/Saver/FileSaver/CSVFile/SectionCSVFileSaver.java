package Helper.Saver.FileSaver.CSVFile;

import Domain.Section;


/**
 * Created by andrei on 2017-01-05.
 */
public class SectionCSVFileSaver extends CSVFileSaver<Section>{

    public SectionCSVFileSaver() {
        super();
    }

    @Override
    public String getFormat(Section object) {
        return object.getID() + separator +
                object.getName() + separator +
                object.getAvailableSlots();
    }
}
