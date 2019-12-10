package ua.gov.smida.demo.service;

import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.stereotype.Service;
import ua.gov.smida.demo.models.Dividend;
import ua.gov.smida.demo.models.DividendsHistory;
import ua.gov.smida.demo.models.PublicDividend;
import ua.gov.smida.demo.repo.HistoryRepo;
import ua.gov.smida.demo.repo.RegistryRepo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class RegistryServiceImpl implements RegistryService {

    private final RegistryRepo registryRepo;
    private final HistoryRepo historyRepo;

    public RegistryServiceImpl(RegistryRepo registryRepo, HistoryRepo historyRepo) {
        this.registryRepo = registryRepo;
        this.historyRepo = historyRepo;
    }

//    ============    saving service    ============      //

    @Override
    public void save(Dividend dividend) {
        registryRepo.save(dividend);
        registryRepo.getByEdrpou(dividend.getEdrpou()).setAddTime(LocalDateTime.now());
        updateTotNomValue(dividend.getEdrpou());
        saveToHistory(dividend.getEdrpou());
    }


//    ============    editing services    ============      //

    @Override
    public void editEdrpou(int edrpou, int edrpou2) {
        registryRepo.editEdrpou(edrpou, edrpou2);
        saveToHistory(edrpou);
    }

    @Override
    public void editAmount(int edrpou, int amount) {
        registryRepo.editAmount(edrpou, amount);
        updateTotNomValue(edrpou);
        saveToHistory(edrpou);
    }

    @Override
    public void editCapitalAmount(int edrpou, int capAm) {
        registryRepo.editCapitalAmount(edrpou, capAm);
        saveToHistory(edrpou);
    }

    @Override
    public void editNominalValue(int edrpou, double nomVal) {
        registryRepo.editNominalValue(edrpou, nomVal);
        updateTotNomValue(edrpou);
        saveToHistory(edrpou);
    }

    @Override
    public void editReleaseDate(int edrpou, LocalDateTime relDate) {
        registryRepo.editReleaseDate(edrpou, relDate);
        saveToHistory(edrpou);
    }

    @Override
    public void editStateDutyPaid(int edrpou, double stDutyPaid) {
        registryRepo.editStateDutyPaid(edrpou, stDutyPaid);
        saveToHistory(edrpou);
    }

    @Override
    public void editComment(int edrpou, String comment) {
        registryRepo.editComment(edrpou, comment);
        saveToHistory(edrpou);
    }


//    ============    sorting services    ============      //

    @Override
    public void search() {
    }

    @Override
    public void sort() {

    }

//    ============    sorting services    ============      //

    public List<Dividend> first10ByAmountPr(int amount){
        return  registryRepo.findFirst10ByAmountGreaterThan(amount);
    }

    public List<PublicDividend> first10ByAmountPb(int amount){
        List<PublicDividend> listPb = new ArrayList<>();
        List<Dividend> list = registryRepo.findFirst10ByAmountGreaterThan(amount);
        for (Dividend pb: list) {
            listPb.add(getPublicData(pb.getEdrpou()));
        }
        return  listPb;
    }


//    ============    get data services    ============      //


    @Override
    public List<DividendsHistory> getPrivateData(int edrpou) {
        return historyRepo.getAllByEdrpou(edrpou);
    }

    @Override
    public List<Dividend> getAllData() {
        return null;

    }


    @Override
    public PublicDividend getPublicData(int edrpou) {
        PublicDividend publicDiv = new PublicDividend();
        Dividend one = registryRepo.getByEdrpou(edrpou);
        publicDiv.setEdrpou(one.getEdrpou());
        publicDiv.setAmount(one.getAmount());
        publicDiv.setNominalValue(one.getNominalValue());
        publicDiv.setTotalNominalValue(one.getTotalNominalValue());
        publicDiv.setReleaseDate(one.getReleaseDate());
        return publicDiv;
    }


//    ============    another private technical functions    ============      //

    private void updateTotNomValue(int edrpou) {
        registryRepo.getByEdrpou(edrpou).setTotalNominalValue(registryRepo.getByEdrpou(edrpou).getNominalValue() * registryRepo.getByEdrpou(edrpou).getAmount());

    }

    private DividendsHistory convToHistoryObj(int edrpou) {
        DividendsHistory dividendsHistory = new DividendsHistory();
        dividendsHistory.setEdrpou(edrpou);
        dividendsHistory.setAmount(registryRepo.getByEdrpou(edrpou).getAmount());
        dividendsHistory.setCapitalAmount(registryRepo.getByEdrpou(edrpou).getCapitalAmount());
        dividendsHistory.setNominalValue(registryRepo.getByEdrpou(edrpou).getNominalValue());
        dividendsHistory.setTotalNominalValue(registryRepo.getByEdrpou(edrpou).getTotalNominalValue());
        dividendsHistory.setStateDutyPaid(registryRepo.getByEdrpou(edrpou).getStateDutyPaid());
        dividendsHistory.setReleaseDate(registryRepo.getByEdrpou(edrpou).getReleaseDate());
        dividendsHistory.setComment(registryRepo.getByEdrpou(edrpou).getComment());
        dividendsHistory.setAddTime(LocalDateTime.now());
        return dividendsHistory;
    }

//    private Dividend convToDivObj(int edrpou) {
//        Dividend dividend = new Dividend();
//        dividend.setEdrpou(edrpou);
//        dividend.setAmount(historyRepo.getByEdrpou(edrpou).getAmount());
//        dividend.setCapitalAmount(historyRepo.getByEdrpou(edrpou).getCapitalAmount());
//        dividend.setNominalValue(historyRepo.getByEdrpou(edrpou).getNominalValue());
//        dividend.setStateDutyPaid(historyRepo.getByEdrpou(edrpou).getStateDutyPaid());
//        dividend.setTotalNominalValue(historyRepo.getByEdrpou(edrpou).getTotalNominalValue());
//        dividend.setReleaseDate(historyRepo.getByEdrpou(edrpou).getReleaseDate());
//        dividend.setComment(historyRepo.getByEdrpou(edrpou).getComment());
//        dividend.setAddTime(historyRepo.getByEdrpou(edrpou).getAddTime());
//        return dividend;
//    }


    private void saveToHistory(int edrpou) {
        historyRepo.save(convToHistoryObj(edrpou));
        historyRepo.getByEdrpou(edrpou).setAddTime(LocalDateTime.now());
    }

}
