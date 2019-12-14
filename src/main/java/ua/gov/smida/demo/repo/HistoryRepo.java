package ua.gov.smida.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.gov.smida.demo.models.DividendsHistory;

import java.time.LocalDate;
import java.util.List;

public interface HistoryRepo  extends JpaRepository<DividendsHistory,Integer> {

    List<DividendsHistory> getAllByEdrpou(int edrpou);
    DividendsHistory getByEdrpou(int edrpou);

    List<DividendsHistory> findAllByAmountGreaterThanEqual(int amount);
    List<DividendsHistory> findAllByAmountLessThanEqual(int amount);
    List<DividendsHistory> findAllByNominalValueGreaterThanEqual(double nomValue);
    List<DividendsHistory> findAllByNominalValueLessThanEqual(double nomValue);
    List<DividendsHistory> findAllByTotalNominalValueGreaterThanEqual(double nomValue);
    List<DividendsHistory> findAllByTotalNominalValueLessThanEqual(double nomValue);
    List<DividendsHistory> findAllByReleaseDateIsAfter(LocalDate date);
    List<DividendsHistory> findAllByReleaseDateIsBefore(LocalDate date);
    List<DividendsHistory> findAllByStateDutyPaidGreaterThanEqual(double sdp);
    List<DividendsHistory> findAllByStateDutyPaidLessThanEqual(double sdp);
    List<DividendsHistory> findAllByCapitalAmountGreaterThanEqual(double capAmount);
    List<DividendsHistory> findAllByCapitalAmountLessThanEqual(double capAmount);
}
