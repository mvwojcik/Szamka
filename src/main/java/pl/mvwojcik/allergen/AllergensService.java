package pl.mvwojcik.allergen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.mvwojcik.utils.Mapper;

import java.util.List;

@Service
public final class AllergensService {

    private final AllergensRepository repository;

    @Autowired
    public AllergensService(AllergensRepository repository) {
        this.repository = repository;
    }

    public final Allergen save(AllergenDTO allergenDTO) {
        return this.repository.save(Mapper.mapAllergenDTOToAllergen(allergenDTO));
    }

    public final List<Allergen> getAll() {
        return repository.findAll();
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
