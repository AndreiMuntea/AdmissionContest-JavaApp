package Helper.Saver.FileSaver.CSVFile;

import Domain.Option;
import Utils.Pair.Pair;

/**
 * Created by andrei on 2017-01-05.
 */
public class OptionCSVFileSaver extends CSVFileSaver<Option> {

    public OptionCSVFileSaver() {
    }

    @Override
    public String getFormat(Option object) {
        Pair<?, ?> option = object.getID();
        return option.getFirst().toString() + separator + option.getSecond().toString();
    }
}
