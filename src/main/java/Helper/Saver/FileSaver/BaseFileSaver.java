package Helper.Saver.FileSaver;

import Helper.FileExceptions.MyFileException;
import Helper.Saver.AbstractSaver;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
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
    public void save(List<E> list, String fileName) throws MyFileException{
        File file = FileSystems.getDefault().getPath(fileName).toFile();
        try (BufferedWriter out = new BufferedWriter(new FileWriter(file))) {
            for (E obj : list) {
                String line = getFormat(obj);
                out.write(line + "\n");
            }
            out.close();
        } catch (IOException e) {
            throw new MyFileException(e.getMessage());
        }
    }

    public abstract String getFormat(E object);
}
