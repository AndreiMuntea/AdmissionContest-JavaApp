package Controller;

import Controller.ControllerExceptions.ControllerException;
import Repository.IRepository;
import Utils.Exceptions.MyException;
import Validator.IValidator;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by andrei on 2017-01-04.
 */
public abstract class AbstractController<ID, T> {
    private IRepository<ID, T> repository;
    private IValidator<T> validator;

    public AbstractController(IRepository<ID, T> repository, IValidator<T> validator) {
        this.repository = repository;
        this.validator = validator;
    }

    public void Add(String... format) throws MyException {
        T entity = CreateFromFormat(format);
        validator.Validate(entity);
        repository.AddElement(entity);
    }

    public void Remove(String... format) throws MyException {
        ID entityID = CreateIDFromFormat(format);
        repository.RemoveElement(entityID);
    }

    public void Update(String... format) throws MyException {
        T entity = CreateFromFormat(format);
        validator.Validate(entity);
        repository.UpdateElement(entity);
    }

    public T GetElement(String... format) throws MyException {
        ID entityID = CreateIDFromFormat(format);
        return repository.GetElement(entityID);
    }

    public List<T> GetAll() throws MyException {
        return repository.GetAll();
    }

    public List<T> GetPage(int page) throws MyException {
        if (page < 0) throw new ControllerException("Invalid page number!\n");
        return repository.GetPage(page);
    }


    public List<T> FilterList(List<T> list, Predicate<T> predicate) {
        return list.stream().filter(predicate).collect(Collectors.toList());
    }

    public List<T> SortList(List<T> list, Comparator<T> comparator) {
        return list.stream().sorted(comparator).collect(Collectors.toList());
    }

    public abstract T CreateFromFormat(String... format) throws ControllerException;

    public abstract ID CreateIDFromFormat(String... format) throws ControllerException;
}
