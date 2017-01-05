package Helper.Saver.FileSaver.HTMLFile;

import Helper.FileExceptions.MyFileException;
import Helper.Saver.FileSaver.BaseFileSaver;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.List;

/**
 * Created by andrei on 2017-01-06.
 */
public abstract class HTMLFileSaver<T> extends BaseFileSaver<T> {
    public HTMLFileSaver() {
        super(" ");
    }

    @Override
    public void save(List<T> list, String fileName) throws MyFileException {
        File file = FileSystems.getDefault().getPath(fileName).toFile();
        try (BufferedWriter out = new BufferedWriter(new FileWriter(file))) {

            out.write("<!DOCTYPE html>\n" +
                    "<html>\n" +
                    "<style>\n" +
                    "table { font-family: arial, sans-serif; border-collapse: collapse; width: 100%;}\n" +
                    "td, th { border: 1px solid #dddddd; text-align: left; padding: 8px;}\n" +
                    "tr:nth-child(even) { background-color: #dddddd;}\n" +
                    "</style>\n" +
                    "</head>\n"+
                    "<body>\n" +
                    "<table>\n" +
                    getColumns());

            for (T obj : list) {
                String line = getFormat(obj);
                out.write(line + "\n");
            }
            out.write("</table>\n" +
                    "</body>\n" +
                    "</html>\n");

            out.close();
        } catch (IOException e) {
            throw new MyFileException(e.getMessage());
        }
    }

    protected abstract String getColumns();
}
