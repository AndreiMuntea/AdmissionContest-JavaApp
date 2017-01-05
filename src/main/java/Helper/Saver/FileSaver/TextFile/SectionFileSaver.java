package Helper.Saver.FileSaver.TextFile;

import Domain.Section;

/**
 * Created by andrei on 2017-01-05.
 */
public class SectionFileSaver extends TextFileSaver<Section>{
    public SectionFileSaver() {
        super();
    }

    public SectionFileSaver(String separator) {
        super(separator);
    }

    @Override
    public String getFormat(Section object) {
        return object.getID() + separator +
                object.getName() + separator +
                object.getAvailableSlots();
    }

}
