package pl.mvwojcik.allergen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.mvwojcik.allergen.data.AllergenDTO;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/allergens")
public final class AllergensController {
    private final AllergensService allergensService;

    @Autowired
    public AllergensController(AllergensService allergensService) {
        this.allergensService = allergensService;
    }

    @GetMapping
    public final ResponseEntity getAll(@RequestParam int page) {
        return allergensService.getAll(page).toResponseEntity();
    }

    @PostMapping
    public final ResponseEntity create(@RequestBody AllergenDTO allergenDTO) {
        return allergensService.createAllergen(allergenDTO).toResponseEntity();
    }

    @DeleteMapping("/{id}")
    public final ResponseEntity delete(@PathVariable Long id) {
        return allergensService.deleteAllergen(id).toResponseEntity();
    }
}
