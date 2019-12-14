package ua.gov.smida.demo.service;

import org.springframework.stereotype.Service;
import ua.gov.smida.demo.models.Dividend;
import ua.gov.smida.demo.models.DividendsHistory;

import java.time.LocalDate;
import java.util.List;

@Service
public interface RegistryService {

//  --------------------------   basic    --------------------------------
    void save(Dividend dividend);
    Dividend getDividend(int edrpou);
    List<Dividend> getAllPublicData() ;
    List<DividendsHistory> getPrivateData(int edrpou);

//  --------------------------   edit     --------------------------------
    void editEdrpou(int edrpou,  int edrpou2);
    void editAmount(int edrpou,  int amount);
    void editCapitalAmount( int edrpou,  int capAm);
    void editNominalValue(int edrpou, double nomVal);
    void editStateDutyPaid(int edrpou, double stDutyPaid);
    void editReleaseDate( int edrpou, LocalDate relDate);
    void editComment( int edrpou,  String comment);

//  --------------------------   filter  ---------------------------------

    List findAllByAmountGreaterThanEqual(int amount, boolean isPrivate);
    List findAllByAmountLessThanEqual(int amount, boolean isPrivate);
    List findAllByNominalValueGreaterThanEqual(double nv,  boolean isPrivate);
    List findAllByNominalValueLessThanEqual(double nv,  boolean isPrivate);
    List findAllByTotalNominalValueGreaterThanEqual(double tnv,  boolean isPrivate);
    List findAllByTotalNominalValueLessThanEqual(double tnv,  boolean isPrivate);
    List findAllByReleaseDateIsAfter(LocalDate date,boolean isPrivate);
    List findAllByReleaseDateIsBefore(LocalDate date,boolean isPrivate);
    List findAllByStateDutyPaidGreaterThanEqual(double sdp);
    List findAllByStateDutyPaidLessThanEqual(double sdp);
    List findAllByCapitalAmountGreaterThanEqual(double capAmount);
    List findAllByCapitalAmountLessThanEqual(double capAmount);

//  -------------------------    sort    ---------------------------------

    List sortByEdrpouFromHistoryToMaxValue();
    List sortByAmountFromMinToMax(boolean isPrivate);
    List sortByAmountFromMaxToMin(boolean isPrivate);
    List sortByNominalValueFromMinToMax(boolean isPrivate);
    List sortByNominalValueFromMaxToMin(boolean isPrivate);
    List sortByTotalNominalValueFromMinToMax(boolean isPrivate);
    List sortByTotalNominalValueFromMaxToMin(boolean isPrivate);
    List sortByReleaseDateFromLatestToOld(boolean isPrivate);
    List sortByReleaseDateFromOldToLatest(boolean isPrivate);

}
