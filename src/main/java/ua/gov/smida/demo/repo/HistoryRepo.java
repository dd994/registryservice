package ua.gov.smida.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.gov.smida.demo.models.DividendsHistory;

import java.util.List;

public interface HistoryRepo  extends JpaRepository<DividendsHistory,Integer> {

    List<DividendsHistory> getAllByEdrpou(int edrpou);
    DividendsHistory getByEdrpou(int edrpou);

}
