package Domain.DTO;

/**
 * Created by andrei on 2017-01-06.
 */
public class TopSections {
    private String sectionName;
    private Integer sectionRegisteredCandidates;

    public TopSections(String sectionName, Integer sectionRegisteredCandidates) {
        this.sectionName = sectionName;
        this.sectionRegisteredCandidates = sectionRegisteredCandidates;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public Integer getSectionRegisteredCandidates() {
        return sectionRegisteredCandidates;
    }

    public void setSectionRegisteredCandidates(Integer sectionRegisteredCandidates) {
        this.sectionRegisteredCandidates = sectionRegisteredCandidates;
    }
}
