package ua.gov.smida.demo.service;

import org.springframework.stereotype.Service;
import ua.gov.smida.demo.models.Dividend;
import ua.gov.smida.demo.models.DividendsHistory;
import ua.gov.smida.demo.models.PublicDividend;

import java.time.LocalDateTime;
import java.util.List;

@Service
public interface RegistryService {

    void save(Dividend dividend);
    void editEdrpou(int edrpou,  int edrpou2);
    void editAmount(int edrpou,  int amount);
    void editCapitalAmount( int edrpou,  int capAm);
    void editNominalValue(int edrpou, double nomVal);
    void editStateDutyPaid(int edrpou, double stDutyPaid);
    void editReleaseDate( int edrpou, LocalDateTime relDate);
    void editComment( int edrpou,  String comment);
    void search();
    void sort();
    PublicDividend getPublicData(int edrpou);
    List<DividendsHistory> getPrivateData(int edrpou);
    List<Dividend> getAllData();


}
