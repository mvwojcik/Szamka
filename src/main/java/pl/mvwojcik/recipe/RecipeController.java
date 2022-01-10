package pl.mvwojcik.recipe;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.mvwojcik.recipe.data.dto.RecipeDTO;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/recipes")
public class RecipeController {

    private final RecipesService recipesService;

    public RecipeController(RecipesService recipesService) {
        this.recipesService = recipesService;
    }

    @GetMapping
    public ResponseEntity<Page<RecipeDTO>> getAll(@RequestParam int page) {
        return ResponseEntity.ok(recipesService.findAll(page));
    }

    @PostMapping
    public ResponseEntity create(@RequestBody RecipeDTO recipeDTO) {
        return recipesService.create(recipeDTO).toResponseEntity();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        return recipesService.delete(id).toResponseEntity();
    }

}
