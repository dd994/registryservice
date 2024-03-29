package ua.gov.smida.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.gov.smida.demo.models.Dividend;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface RegistryRepo extends JpaRepository<Dividend,Integer> {

    //    ---------------------       get requests      --------------------------------

    Dividend getByEdrpou(int edrpou);

    //    ---------------------       filtration.      ----------------------

    List<Dividend> findAllByAmountGreaterThanEqual(int amount);
    List<Dividend> findAllByAmountLessThanEqual(int amount);
    List<Dividend> findAllByNominalValueGreaterThanEqual(double nomValue);
    List<Dividend> findAllByNominalValueLessThanEqual(double nomValue);
    List<Dividend> findAllByTotalNominalValueGreaterThanEqual(double tnv);
    List<Dividend> findAllByTotalNominalValueLessThanEqual(double tnv);
    List<Dividend> findAllByReleaseDateIsAfter(LocalDate date);
    List<Dividend> findAllByReleaseDateIsBefore(LocalDate date);


    //    ------------------------     db queries      ---------------------------------

    @Modifying
    @Transactional
    @Query(value = "update  DIVIDENDS t set t.EDRPOU = :edrpou2 where t.EDRPOU = :edrpou", nativeQuery = true)
    void editEdrpou(@Param("edrpou") int edrpou, @Param("edrpou2") int edrpou2);

    @Modifying
    @Transactional
    @Query(value = "update  DIVIDENDS t set t.AMOUNT = :amount where t.EDRPOU = :edrpou", nativeQuery = true)
    void editAmount(@Param("edrpou") int edrpou, @Param("amount") int amount);

    @Modifying
    @Transactional
    @Query(value = "update  DIVIDENDS t set t.CAPITAL_AMOUNT = :capAm where t.EDRPOU = :edrpou", nativeQuery = true)
    void editCapitalAmount(@Param("edrpou") int edrpou, @Param("capAm") int capAm);

    @Modifying
    @Transactional
    @Query(value = "update  DIVIDENDS t set t.NOMINAL_VALUE = :nomVal where t.EDRPOU = :edrpou", nativeQuery = true)
    void editNominalValue(@Param("edrpou") int edrpou, @Param("nomVal") double nomVal);

    @Modifying
    @Transactional
    @Query(value = "update  DIVIDENDS t set t.RELEASE_DATE = :relDate where t.EDRPOU = :edrpou", nativeQuery = true)
    void editReleaseDate(@Param("edrpou") int edrpou, @Param("relDate") LocalDate relDate);

    @Modifying
    @Transactional
    @Query(value = "update  DIVIDENDS t set t.STATE_DUTY_PAID = :stDutyPaid where t.EDRPOU = :edrpou", nativeQuery = true)
    void editStateDutyPaid(@Param("edrpou") int edrpou, @Param("stDutyPaid") double stDutyPaid);

    @Modifying
    @Transactional
    @Query(value = "update  DIVIDENDS t set t.COMMENT = :comment where t.EDRPOU = :edrpou", nativeQuery = true)
    void editComment(@Param("edrpou") int edrpou, @Param("comment") String comment);

}

