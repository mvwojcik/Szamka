package pl.mvwojcik.recipe;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.mvwojcik.recipe.data.dto.RecipeDTO;

@RestController
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


    @GetMapping("/{id}")
    public ResponseEntity getOne(@PathVariable Long id) {
        return ResponseEntity.ok(recipesService.findById(id));
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
