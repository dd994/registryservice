package ua.gov.smida.demo.service;

import org.springframework.stereotype.Service;
import ua.gov.smida.demo.models.Dividend;
import ua.gov.smida.demo.models.DividendsHistory;
import ua.gov.smida.demo.models.PublicDividend;
import ua.gov.smida.demo.repo.HistoryRepo;
import ua.gov.smida.demo.repo.RegistryRepo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
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

//    ============    get data services    ============

    @Override
    public Dividend getDividend(int edrpou) {
        return registryRepo.getByEdrpou(edrpou);
    }

    @Override
    public List<Dividend> getAllPublicData() {
        return registryRepo.findAll();
    }

    @Override
    public List<DividendsHistory> getPrivateData(int edrpou) {
        return historyRepo.getAllByEdrpou(edrpou);
    }

//    ============    saving service    ============

    @Override
    public void save(Dividend dividend) {
        dividend.setAddTime(LocalDateTime.now());
        registryRepo.save(dividend);
        updateTotNomValue(dividend.getEdrpou());
        saveToHistory(dividend.getEdrpou());
    }

//    ============    editing services    ============

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
    public void editReleaseDate(int edrpou, LocalDate relDate) {
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
    public List sortByEdrpouFromHistoryToMaxValue() {
        return historyRepo.findAll()
                .stream()
                .sorted(Comparator.comparing(DividendsHistory::getEdrpou))
                .collect(Collectors.toList());
    }

    @Override
    public List sortByAmountFromMinToMax(boolean isPrivate) {
        List<Dividend> datalist = registryRepo.findAll().stream()
                .sorted(Comparator.comparing(Dividend::getAmount))
                .collect(Collectors.toList());
        return accessDataComparator(isPrivate, datalist);
    }

    @Override
    public List sortByAmountFromMaxToMin(boolean isPrivate) {
        List<Dividend> datalist = registryRepo.findAll().stream()
                .sorted(Comparator.comparing(Dividend::getAmount).reversed())
                .collect(Collectors.toList());
        return accessDataComparator(isPrivate, datalist);
    }

    @Override
    public List sortByNominalValueFromMinToMax(boolean isPrivate) {
        List<Dividend> datalist = registryRepo.findAll().stream()
                .sorted(Comparator.comparing(Dividend::getNominalValue))
                .collect(Collectors.toList());
        return accessDataComparator(isPrivate, datalist);
    }

    @Override
    public List sortByNominalValueFromMaxToMin(boolean isPrivate) {
        List<Dividend> datalist = registryRepo.findAll().stream()
                .sorted(Comparator.comparing(Dividend::getNominalValue).reversed())
                .collect(Collectors.toList());
        return accessDataComparator(isPrivate, datalist);
    }

    @Override
    public List sortByTotalNominalValueFromMinToMax(boolean isPrivate) {
        List<Dividend> datalist = registryRepo.findAll().stream()
                .sorted(Comparator.comparing(Dividend::getTotalNominalValue))
                .collect(Collectors.toList());
        return accessDataComparator(isPrivate, datalist);
    }

    @Override
    public List sortByTotalNominalValueFromMaxToMin(boolean isPrivate) {
        List<Dividend> datalist = registryRepo.findAll().stream()
                .sorted(Comparator.comparing(Dividend::getTotalNominalValue).reversed())
                .collect(Collectors.toList());
        return accessDataComparator(isPrivate, datalist);
    }

    @Override
    public List sortByReleaseDateFromLatestToOld(boolean isPrivate) {
        List<Dividend> datalist = registryRepo.findAll().stream()
                .sorted(Comparator.comparing(Dividend::getReleaseDate).reversed())
                .collect(Collectors.toList());
        return accessDataComparator(isPrivate, datalist);
    }

    @Override
    public List sortByReleaseDateFromOldToLatest(boolean isPrivate) {
        List<Dividend> datalist = registryRepo.findAll().stream()
                .sorted(Comparator.comparing(Dividend::getReleaseDate))
                .collect(Collectors.toList());
        return accessDataComparator(isPrivate, datalist);
    }

    // ========================================    filter services    ==============================================
    // фільтрування данних або в публічних  даних поточного реєстру, або в приватних даних - історії змін(згідно ТЗ)

    @Override
    public List findAllByAmountGreaterThanEqual(int amount, boolean isPrivate) {
        List<PublicDividend> publicDivList = new ArrayList<>();

        if (!isPrivate) {
            for (Dividend div : registryRepo.findAllByAmountGreaterThanEqual(amount)) {
                publicDivList.add(convertToPublicDividendObject(div.getEdrpou()));
            }
            return publicDivList;
        } else
            return historyRepo.findAllByAmountGreaterThanEqual(amount);
    }

    @Override
    public List findAllByAmountLessThanEqual(int amount, boolean isPrivate) {
        List<PublicDividend> publicDivList = new ArrayList<>();

        if (!isPrivate) {
            for (Dividend div : registryRepo.findAllByAmountLessThanEqual(amount)) {
                publicDivList.add(convertToPublicDividendObject(div.getEdrpou()));
            }
            return publicDivList;
        } else
            return historyRepo.findAllByAmountLessThanEqual(amount);
    }

    @Override
    public List findAllByNominalValueGreaterThanEqual(double nv, boolean isPrivate) {
        List<PublicDividend> publicDivList = new ArrayList<>();

        if (!isPrivate) {
            for (Dividend div : registryRepo.findAllByNominalValueGreaterThanEqual(nv)) {
                publicDivList.add(convertToPublicDividendObject(div.getEdrpou()));
            }
            return publicDivList;
        } else
            return historyRepo.findAllByNominalValueGreaterThanEqual(nv);
    }

    @Override
    public List findAllByNominalValueLessThanEqual(double nv, boolean isPrivate) {
        List<PublicDividend> publicDivList = new ArrayList<>();

        if (!isPrivate) {
            for (Dividend div : registryRepo.findAllByNominalValueLessThanEqual(nv)) {
                publicDivList.add(convertToPublicDividendObject(div.getEdrpou()));
            }
            return publicDivList;
        } else
            return historyRepo.findAllByNominalValueLessThanEqual(nv);
    }

    @Override
    public List findAllByTotalNominalValueGreaterThanEqual(double tnv, boolean isPrivate) {
        List<PublicDividend> publicDivList = new ArrayList<>();

        if (!isPrivate) {
            for (Dividend div : registryRepo.findAllByTotalNominalValueGreaterThanEqual(tnv)) {
                publicDivList.add(convertToPublicDividendObject(div.getEdrpou()));
            }
            return publicDivList;
        } else
            return historyRepo.findAllByTotalNominalValueGreaterThanEqual(tnv);
    }

    @Override
    public List findAllByTotalNominalValueLessThanEqual(double tnv, boolean isPrivate) {
        List<PublicDividend> publicDivList = new ArrayList<>();

        if (!isPrivate) {
            for (Dividend div : registryRepo.findAllByTotalNominalValueLessThanEqual(tnv)) {
                publicDivList.add(convertToPublicDividendObject(div.getEdrpou()));
            }
            return publicDivList;
        } else
            return historyRepo.findAllByTotalNominalValueLessThanEqual(tnv);
    }

    @Override
    public List findAllByReleaseDateIsAfter(LocalDate date, boolean isPrivate) {
        List<PublicDividend> publicDivList = new ArrayList<>();

        if (!isPrivate) {
            for (Dividend div : registryRepo.findAllByReleaseDateIsAfter(date)) {
                publicDivList.add(convertToPublicDividendObject(div.getEdrpou()));
            }
            return publicDivList;
        } else
            return historyRepo.findAllByReleaseDateIsAfter(date);
    }

    @Override
    public List findAllByReleaseDateIsBefore(LocalDate date, boolean isPrivate) {
        List<PublicDividend> publicDivList = new ArrayList<>();

        if (!isPrivate) {
            for (Dividend div : registryRepo.findAllByReleaseDateIsBefore(date)) {
                publicDivList.add(convertToPublicDividendObject(div.getEdrpou()));
            }
            return publicDivList;
        } else
            return historyRepo.findAllByReleaseDateIsBefore(date);
    }

    @Override
    public List findAllByStateDutyPaidGreaterThanEqual(double sdp) {
        return historyRepo.findAllByStateDutyPaidGreaterThanEqual(sdp);
    }

    @Override
    public List findAllByStateDutyPaidLessThanEqual(double sdp) {
        return historyRepo.findAllByStateDutyPaidLessThanEqual(sdp);
    }

    @Override
    public List findAllByCapitalAmountGreaterThanEqual(double capAmount) {
        return historyRepo.findAllByCapitalAmountGreaterThanEqual(capAmount);
    }

    @Override
    public List findAllByCapitalAmountLessThanEqual(double capAmount) {
        return historyRepo.findAllByCapitalAmountLessThanEqual(capAmount);
    }

//    ============    another private technical functions    ============

    private void saveToHistory(int edrpou) {
        historyRepo.save(convertationToHistoryObject(edrpou));
        historyRepo.getByEdrpou(edrpou).setAddTime(LocalDateTime.now());
    }

    private void updateTotNomValue(int edrpou) {
        registryRepo.getByEdrpou(edrpou).setTotalNominalValue(registryRepo.getByEdrpou(edrpou).getNominalValue() * registryRepo.getByEdrpou(edrpou).getAmount());
        registryRepo.save(registryRepo.getByEdrpou(edrpou));
    }

    private DividendsHistory convertationToHistoryObject(int edrpou) {
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

    private PublicDividend convertToPublicDividendObject(int edrpou) {
        PublicDividend publicDiv = new PublicDividend();
        Dividend one = registryRepo.getByEdrpou(edrpou);
        publicDiv.setEdrpou(one.getEdrpou());
        publicDiv.setAmount(one.getAmount());
        publicDiv.setNominalValue(one.getNominalValue());
        publicDiv.setTotalNominalValue(one.getTotalNominalValue());
        publicDiv.setReleaseDate(one.getReleaseDate());
        return publicDiv;
    }

    private List accessDataComparator(boolean isPrivate, List<Dividend> currentList) {
        List<PublicDividend> publicDivList = new ArrayList<>();

        if (isPrivate) {
            return currentList;
        } else
            for (Dividend div : currentList) {
                publicDivList.add(convertToPublicDividendObject(div.getEdrpou()));
            }
        return publicDivList;
    }

}
