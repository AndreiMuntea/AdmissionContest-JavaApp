package Domain;

/**
 * Created by andrei on 2017-01-03.
 */
public class Candidate extends HasID<Integer> {

    private String name;

    private String address;

    private Double grade;

    private String phoneNumber;

    public Candidate()
    {
        super();
        name = null;
        address = null;
        grade = null;
        phoneNumber = null;
    }

    public Candidate(Integer ID, String name, String address, Double grade, String phoneNumber) {
        super(ID);
        this.name = name;
        this.address = address;
        this.grade = grade;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Candidate{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", grade=" + grade +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Candidate)) return false;
        if (!super.equals(o)) return false;

        Candidate candidate = (Candidate) o;

        if (getName() != null ? !getName().equals(candidate.getName()) : candidate.getName() != null) return false;
        if (getAddress() != null ? !getAddress().equals(candidate.getAddress()) : candidate.getAddress() != null) return false;
        if (getGrade() != null ? !getGrade().equals(candidate.getGrade()) : candidate.getGrade() != null) return false;
        return getPhoneNumber() != null ? getPhoneNumber().equals(candidate.getPhoneNumber()) : candidate.getPhoneNumber() == null;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
