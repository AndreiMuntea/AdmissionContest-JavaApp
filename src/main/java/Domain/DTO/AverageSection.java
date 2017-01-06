package Domain.DTO;

/**
 * Created by andrei on 2017-01-06.
 */
public class AverageSection {
    private String sectionName;
    private Double sectionAverage;

    public AverageSection(String sectionName, Double sectionAverage) {
        this.sectionName = sectionName;
        this.sectionAverage = sectionAverage;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public Double getSectionAverage() {
        return sectionAverage;
    }

    public void setSectionAverage(Double sectionAverage) {
        this.sectionAverage = sectionAverage;
    }
}
