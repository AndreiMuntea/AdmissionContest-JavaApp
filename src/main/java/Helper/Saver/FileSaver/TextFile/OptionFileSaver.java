package Helper.Saver.FileSaver.TextFile;

import Domain.Option;
import Utils.Pair.Pair;

/**
 * Created by andrei on 2017-01-05.
 */
public class OptionFileSaver extends TextFileSaver<Option> {
    public OptionFileSaver() {
    }

    public OptionFileSaver(String separator) {
        super(separator);
    }

    @Override
    public String getFormat(Option object) {
        Pair<?, ?> option = object.getID();
        return option.getFirst().toString() + separator + option.getSecond().toString();
    }
}
