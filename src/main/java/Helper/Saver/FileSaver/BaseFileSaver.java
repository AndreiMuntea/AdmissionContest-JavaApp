package Helper.Saver.FileSaver;

import Helper.Saver.AbstractSaver;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Created by andrei on 2017-01-05.
 */
public abstract class BaseFileSaver<E> extends AbstractSaver<E> {
    private static final String DEFAULT_SEPARATOR = ",";
    protected String separator;

    public BaseFileSaver() {
        this(DEFAULT_SEPARATOR);
    }

    public BaseFileSaver(String separator) {
        this.separator = separator;
    }

    public String getSeparator() {
        return separator;
    }

    public void setSeparator(String separator) {
        this.separator = separator;
    }

    @Override
    public void save(List<E> list, String fileName) {
        try (BufferedWriter out = new BufferedWriter(new FileWriter(fileName))) {
            for (E obj : list) {
                String line = getFormat(obj);
                out.write(line + "\n");
            }
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public abstract String getFormat(E object);
}
