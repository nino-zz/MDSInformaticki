package nino.rs.mdsinformaticki.respository;

import nino.rs.mdsinformaticki.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

}
