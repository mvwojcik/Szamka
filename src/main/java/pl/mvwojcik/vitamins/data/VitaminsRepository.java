package pl.mvwojcik.vitamins.data;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.mvwojcik.vitamins.data.model.Vitamin;

import java.util.Set;

@Repository
public interface VitaminsRepository extends CrudRepository<Vitamin, Long> {

    @Cacheable(cacheNames = "Vitamins")
    Set<Vitamin> findAll();

    Set<Vitamin> findByNameInIgnoreCase(Set<String> vitamins);
}
