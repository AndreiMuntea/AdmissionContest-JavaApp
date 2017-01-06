package Utils.ObserverFramework;

import java.util.ArrayList;

/**
 * Created by andrei on 2017-01-05.
 */
public class AbstractObservable<T> implements IObservable<T> {
    ArrayList<IObserver<T>> observers;

    public AbstractObservable() {
        observers = new ArrayList<>();
    }

    @Override
    public void addObserver(IObserver<T> observer) {
        if(!observers.contains(observer)) observers.add(observer);
    }

    @Override
    public void removeObserver(IObserver<T> observer) {
        if(observers.contains(observer)) observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        observers.forEach(IObserver::update);
    }
}
