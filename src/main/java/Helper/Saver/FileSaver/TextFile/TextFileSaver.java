package Helper.Saver.FileSaver.TextFile;

import Helper.Saver.FileSaver.BaseFileSaver;

/**
 * Created by andrei on 2017-01-05.
 */
public abstract class TextFileSaver<T> extends BaseFileSaver<T> {
    public TextFileSaver() {
    }

    public TextFileSaver(String separator) {
        super(separator);
    }
}
