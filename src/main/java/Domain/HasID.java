package Domain;

/**
 * Created by andrei on 2017-01-03.
 */
public abstract class HasID<T> {

    private T ID;

    public HasID()
    {
        ID = null;
    }

    public HasID(T ID) {
        this.ID = ID;
    }

    public T getID() {
        return ID;
    }

    public void setID(T ID) {
        this.ID = ID;
    }

    @Override
    public String toString() {
        return "HasID{" +
                "ID=" + ID +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HasID)) return false;

        HasID<?> hasID = (HasID<?>) o;

        return getID() != null ? getID().equals(hasID.getID()) : hasID.getID() == null;
    }

    @Override
    public int hashCode() {
        return getID() != null ? getID().hashCode() : 0;
    }
}
