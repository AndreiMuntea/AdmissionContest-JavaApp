package Domain;

import Utils.Pair.Pair;

/**
 * Created by andrei on 2017-01-04.
 */
public class Option extends HasID<Pair<Integer, Integer>> {

    public Option() {
    }

    public Option(Pair<Integer, Integer> ID) {
        super(ID);
    }

    public Option(Integer candidateID, Integer sectionID){
        super(new Pair<Integer, Integer>(candidateID, sectionID));
    }

    public Integer getCandidateID()
    {
        return super.getID().getFirst();
    }

    public Integer getSectionID()
    {
        return super.getID().getSecond();
    }

    @Override
    public String toString() {
        return getCandidateID() + " " + getSectionID();
    }
}
