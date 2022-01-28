package pl.mvwojcik.ingredient;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.mvwojcik.ingredient.data.IngredientSearchDTO;
import pl.mvwojcik.ingredient.data.dto.IngredientDTO;

@RestController
@RequestMapping("/ingredients")
public final class IngredientsController {
    private final IngredientsService ingredientsService;

    public IngredientsController(IngredientsService ingredientsService) {
        this.ingredientsService = ingredientsService;
    }

    @GetMapping
    public ResponseEntity getAll(@RequestParam int page) {
        return ingredientsService.getAll(page).toResponseEntity();
    }

    @GetMapping("/{id}")
    public ResponseEntity findIngredientByID(@PathVariable Long id) {
        return ingredientsService.findByID(id).toResponseEntity();
    }

    @GetMapping("/search/{word}")
    public ResponseEntity interactiveSearch(@PathVariable String word, @RequestBody IngredientSearchDTO searchDTO) {
        return ingredientsService.interactiveSearch(word, searchDTO).toResponseEntity();
    }

    @PostMapping
    public ResponseEntity createIngredient(@RequestBody IngredientDTO ingredientDTO) {
        return ingredientsService.createIngredient(ingredientDTO).toResponseEntity();
    }

//    @PutMapping("/{id}")
//    public ResponseEntity updateIngredient(@PathVariable Long id, @RequestBody IngredientDTO ingredientDTO) {
//        return ingredientsService.updateIngredient(id, ingredientDTO).toResponsEntity();
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        return ingredientsService.delete(id).toResponseEntity();

    }
}
