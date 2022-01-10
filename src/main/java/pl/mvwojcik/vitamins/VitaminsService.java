package pl.mvwojcik.vitamins;

import org.springframework.stereotype.Service;
import pl.mvwojcik.vitamins.data.VitaminsRepository;
import pl.mvwojcik.vitamins.data.model.Vitamin;

import java.util.List;
import java.util.Set;

@Service
public final class VitaminsService {
    private final VitaminsRepository vitaminsRepository;

    public VitaminsService(VitaminsRepository vitaminsRepository) {
        this.vitaminsRepository = vitaminsRepository;
    }

    public Set<Vitamin> getAll() {
        return vitaminsRepository.findAll();
    }

    public Vitamin save(Vitamin vitaminDTO) {
        return this.vitaminsRepository.save(vitaminDTO);
    }

    public Set<Vitamin> findByNameInIgnoreCase(Set<String> vitamins) {
        return this.vitaminsRepository.findByNameInIgnoreCase(vitamins);
    }
}
