package fa.training.jswf102.repository;

import fa.training.jswf102.entities.Lookup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LookupRepository extends JpaRepository<Lookup,Integer> {
}
