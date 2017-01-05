package Helper.Saver;

import Helper.FileExceptions.MyFileException;

import java.util.List;

/**
 * Created by andrei on 2017-01-05.
 */
public abstract class AbstractSaver<E> implements ISaver<E> {

    public abstract void save(List<E> list, String fileName) throws MyFileException;
}
