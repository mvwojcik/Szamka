package pl.mvwojcik.allergen.data;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.mvwojcik.allergen.data.Allergen;

import java.util.Optional;
import java.util.Set;

@Repository
public interface AllergensRepository extends JpaRepository<Allergen, Long> {

//    Page<Allergen> findAll(Pageable pageable);

    Set<Allergen> findByNameInIgnoreCase(Set<String> names);

    Boolean existsByName(String name);

    @Override
    Optional<Allergen> findById(Long id);


}
