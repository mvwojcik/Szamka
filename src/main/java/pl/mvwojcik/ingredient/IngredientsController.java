package pl.mvwojcik.ingredient;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.mvwojcik.ingredient.data.dto.IngredientDTO;

@CrossOrigin(origins = "http://localhost:3000")
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

    @PostMapping
    public ResponseEntity createIngredient(@RequestBody IngredientDTO ingredientDTO) {
        return ingredientsService.createIngredient(ingredientDTO).toResponseEntity();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        return ingredientsService.delete(id).toResponseEntity();

    }
}
