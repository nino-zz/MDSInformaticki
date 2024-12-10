package nino.rs.mdsinformaticki.respository;

import nino.rs.mdsinformaticki.model.Stock;
import nino.rs.mdsinformaticki.model.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ValueRepository extends JpaRepository<Value, Long> {
    List<Value> findAllByStockAndDateBetween(Stock stock, LocalDate startDate, LocalDate endDate);
}
