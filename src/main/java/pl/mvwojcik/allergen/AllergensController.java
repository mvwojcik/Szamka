package pl.mvwojcik.allergen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.mvwojcik.utils.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/allergens")
public final class AllergensController {
    private final AllergensService allergensService;

    @Autowired
    public AllergensController(AllergensService allergensService) {
        this.allergensService = allergensService;
    }

    //finally service.getAll().AndThen(service.mapListToDTO)
    @GetMapping
    public final ResponseEntity<List<AllergenDTO>> getAll() {
        return ResponseEntity
                .ok(allergensService.getAll()
                                    .stream()
                                    .map(Mapper::mapAllergenToAllergenDTO)
                                    .collect(Collectors.toList()));
    }

    @PostMapping
    public final ResponseEntity<AllergenDTO> create(AllergenDTO allergenDTO) {
        return ResponseEntity.ok()
                             .body(Mapper.mapAllergenToAllergenDTO(allergensService.save(allergenDTO)));
    }

    @DeleteMapping("/{id}")
    public final ResponseEntity delete(@PathVariable Integer id) {
        //check if ingredient hasnt that allergen
        allergensService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
