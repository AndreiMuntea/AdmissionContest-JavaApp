package Controller;

import Controller.ControllerExceptions.ControllerException;
import Domain.Candidate;
import Domain.Option;
import Domain.Section;
import Helper.Saver.FileSaver.CSVFile.OptionCSVFileSaver;
import Helper.Saver.FileSaver.HTMLFile.OptionHTMLSaver;
import Helper.Saver.FileSaver.TextFile.OptionFileSaver;
import Helper.Saver.PDFFile.OptionPDFSaver;
import Repository.IRepository;
import Repository.RepositoryExceptions.RepositoryException;
import Utils.Exceptions.MyException;
import Utils.Pair.Pair;
import Validator.IValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Created by andrei on 2017-01-04.
 */
public class OptionController extends AbstractController<Pair<Integer, Integer>, Option> {

    private IRepository<Integer, Candidate> candidateRepository;
    private IRepository<Integer, Section> sectionRepository;

    public OptionController(IRepository<Pair<Integer, Integer>, Option> repository,
                            IValidator<Option> validator,
                            IRepository<Integer, Candidate> candidateRepository,
                            IRepository<Integer, Section> sectionRepository) {
        super(repository, validator);
        this.candidateRepository = candidateRepository;
        this.sectionRepository = sectionRepository;
    }

    @Override
    public Option CreateFromFormat(String... format) throws ControllerException {
        return new Option(CreateIDFromFormat(format));
    }

    @Override
    public Pair<Integer, Integer> CreateIDFromFormat(String... format) throws ControllerException {
        if (format.length != 2) throw new ControllerException("Invalid number of parameters!\n");

        try {
            Integer candidateID = Integer.parseInt(format[0]);
            Integer sectionID = Integer.parseInt(format[1]);

            return new Pair<>(candidateID, sectionID);
        } catch (NumberFormatException e) {
            throw new ControllerException("ID should be a positive Integer: " + e.getMessage() + "\n");
        }
    }

    @Override
    public void Add(String... format) throws MyException {
        Option o = CreateFromFormat(format);
        if (!candidateRepository.ExistsElement(o.getCandidateID()))
            throw new ControllerException(String.format("Candidate with ID %d doesn't exist!\n", o.getCandidateID()));
        if (!sectionRepository.ExistsElement(o.getSectionID()))
            throw new ControllerException(String.format("Section with ID %d doesn't exist!\n", o.getSectionID()));
        super.Add(format);
    }

    @Override
    public void Remove(String... format) throws MyException {
        super.Remove(format);
    }

    @Override
    public void Update(String... format) throws MyException {
        super.Update(format);
    }

    public List<Candidate> getAllCandidates() throws MyException {
        return candidateRepository.GetAll();
    }

    public List<Section> getAllSections() throws MyException {
        return sectionRepository.GetAll();
    }

    public List<Candidate> getPageCandidates(Integer pageSize, Integer pageNumber) throws MyException {
        return candidateRepository.GetPage(pageSize, pageNumber);
    }

    public List<Section> getPageSections(Integer pageSize, Integer pageNumber) throws MyException {
        return sectionRepository.GetPage(pageSize, pageNumber);
    }

    public List<Section> getSectionsForCandidate(String candidateID) throws MyException {
        Integer IDCandidate;
        try {
            IDCandidate = Integer.parseInt(candidateID);
        } catch (NumberFormatException e) {
            throw new ControllerException("Candidate ID should be an integer!\n");
        }
        Predicate<Option> filter = o->o.getCandidateID().equals(IDCandidate);
        List<Option> options = super.FilterList(super.GetAll(),filter);

        List<Section> allSections = new ArrayList<>();
        options.forEach(o -> {try {allSections.add(sectionRepository.GetElement(o.getSectionID()));} catch (RepositoryException ignored) {}});
        return allSections;
    }

    public List<Candidate> getCandidatesForSection(String sectionID) throws MyException{
        Integer IDSection;
        try {
            IDSection = Integer.parseInt(sectionID);
        } catch (NumberFormatException e) {
            throw new ControllerException("Section ID should be an integer!\n");
        }
        Predicate<Option> filter = o->o.getSectionID().equals(sectionID);
        List<Option> options = super.FilterList(super.GetAll(),filter);

        List<Candidate> allCandidates = new ArrayList<>();
        options.forEach(o -> {try {allCandidates.add(candidateRepository.GetElement(o.getCandidateID()));} catch (RepositoryException ignored) {}});
        return allCandidates;
    }

    @Override
    protected void loadExporters() {
        exporters.put("PDF", new OptionPDFSaver());
        exporters.put("HTML", new OptionHTMLSaver());
        exporters.put("CSV", new OptionCSVFileSaver());
        exporters.put("TXT", new OptionFileSaver());
    }
}
