package pl.mvwojcik.plan;


import org.springframework.stereotype.Service;
import pl.mvwojcik.utils.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DietPlanService {

    private final DietPlanRepository repository;

    public DietPlanService(DietPlanRepository repository) {
        this.repository = repository;
    }

    public List<DietPlanDTO> getAll() {
        return repository.findAll()
                         .stream()
                         .map(Mapper::mapToDTO)
                         .collect(Collectors.toList());
    }
}
