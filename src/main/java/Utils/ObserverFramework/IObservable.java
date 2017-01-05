package Utils.ObserverFramework;

/**
 * Created by andrei on 2017-01-05.
 */
public interface IObservable<T> {
    void addObserver(IObserver<T> observer);

    void removeObserver(IObserver<T> observer);

    void notifyObservers();
}
