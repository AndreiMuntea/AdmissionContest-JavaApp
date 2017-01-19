package Controller;

import Controller.ControllerExceptions.ControllerException;
import Helper.Saver.ISaver;
import Repository.IRepository;
import Utils.Exceptions.MyException;
import Utils.ObserverFramework.AbstractObservable;
import Validator.IValidator;

import java.lang.reflect.Method;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by andrei on 2017-01-04.
 */
public abstract class AbstractController<ID, T> extends AbstractObservable<T> {
    private IRepository<ID, T> repository;
    private IValidator<T> validator;
    protected HashMap<String, ISaver<T>> exporters;
    protected HashMap<String, Predicate<T>> filters;

    public AbstractController(IRepository<ID, T> repository, IValidator<T> validator) {
        this.repository = repository;
        this.validator = validator;

        this.exporters = new HashMap<>();
        this.filters = new HashMap<>();

        loadExporters();
        loadFilters();
    }

    public void Add(String... format) throws MyException {
        T entity = CreateFromFormat(format);
        validator.Validate(entity);
        repository.AddElement(entity);
        notifyObservers();
    }

    public void Remove(String... format) throws MyException {
        ID entityID = CreateIDFromFormat(format);
        repository.RemoveElement(entityID);
        notifyObservers();
    }

    public void Update(String... format) throws MyException {
        T entity = CreateFromFormat(format);
        validator.Validate(entity);
        repository.UpdateElement(entity);
        notifyObservers();
    }

    public T GetElement(String... format) throws MyException {
        ID entityID = CreateIDFromFormat(format);
        return repository.GetElement(entityID);
    }

    public List<T> GetAll() throws MyException {
        return repository.GetAll();
    }

    public List<T> GetPage(Integer pageSize, Integer pageNumber) throws MyException {
        return repository.GetPage(pageSize, pageNumber);
    }


    public List<T> FilterList(List<T> list, Predicate<T> predicate) {
        return list.stream().filter(predicate).collect(Collectors.toList());
    }

    public List<T> SortList(List<T> list, Comparator<T> comparator) {
        return list.stream().sorted(comparator).collect(Collectors.toList());
    }

    public void Export(String path, String fileName, String option) throws MyException
    {
        if (fileName.length() == 0) throw new ControllerException("File name can't be empty!\n");
        if(!exporters.containsKey(option)) throw new ControllerException("Undefined export method!\n");
        export(exporters.get(option), path + "/" + fileName + "." + option.toLowerCase());
    }


    public abstract T CreateFromFormat(String... format) throws ControllerException;

    public abstract ID CreateIDFromFormat(String... format) throws ControllerException;

    protected abstract void loadExporters();

    protected abstract void loadFilters();

    protected void export(ISaver<T> saver, String fileName) throws MyException
    {
        saver.save(repository.GetAll(),fileName);
    }
}
