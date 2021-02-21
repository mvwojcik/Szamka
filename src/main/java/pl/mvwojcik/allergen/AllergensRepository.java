package pl.mvwojcik.allergen;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.mvwojcik.allergen.Allergen;

import java.util.List;
import java.util.Set;

@Repository
public interface AllergensRepository extends JpaRepository<Allergen, Integer> {

    List<Allergen> findByNameInIgnoreCase(Set<String> names);
}
