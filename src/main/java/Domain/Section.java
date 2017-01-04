package Domain;

/**
 * Created by andrei on 2017-01-03.
 */
public class Section extends HasID<Integer> {

    private String name;

    private Integer availableSlots;

    public Section()
    {
        super();
        name = null;
        availableSlots = null;
    }

    public Section(Integer ID, String name, Integer availableSlots) {
        super(ID);
        this.name = name;
        this.availableSlots = availableSlots;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAvailableSlots() {
        return availableSlots;
    }

    public void setAvailableSlots(Integer availableSlots) {
        this.availableSlots = availableSlots;
    }

    @Override
    public String toString() {
        return "Section{" +
                "name='" + name + '\'' +
                ", availableSlots=" + availableSlots +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Section)) return false;
        if (!super.equals(o)) return false;

        Section section = (Section) o;

        if (getName() != null ? !getName().equals(section.getName()) : section.getName() != null) return false;
        return getAvailableSlots() != null ? getAvailableSlots().equals(section.getAvailableSlots()) : section.getAvailableSlots() == null;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
