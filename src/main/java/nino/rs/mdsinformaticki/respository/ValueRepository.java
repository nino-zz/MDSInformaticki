package nino.rs.mdsinformaticki.respository;

import nino.rs.mdsinformaticki.model.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ValueRepository extends JpaRepository<Value, Long> {

}
