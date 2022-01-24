package pl.mvwojcik.allergen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.mvwojcik.allergen.data.AllergenDTO;

@RestController
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

    @GetMapping("{word}")
    public final ResponseEntity getAll(@PathVariable String word) {
        return allergensService.interactiveSearch(word).toResponseEntity();
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
