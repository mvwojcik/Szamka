package pl.mvwojcik.ingredient;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.mvwojcik.utils.Mapper;

import java.util.List;

@RestController
@RequestMapping("/ingredients")
public final class IngredientsController {
    private final IngredientsService ingredientsService;

    public IngredientsController(IngredientsService ingredientsService) {
        this.ingredientsService = ingredientsService;
    }

    @GetMapping
    public ResponseEntity<List<Ingredient>> getAll() {
        return ResponseEntity.ok(ingredientsService.getAll());
    }

    @PostMapping
    public ResponseEntity<IngredientDTO> save(@RequestBody IngredientDTO ingredientDTO) {
        return ResponseEntity.status(201)
                             .body(Mapper.mapIngredientToIngredientDTO(ingredientsService.save(ingredientDTO)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        ingredientsService.delete(id);
        return ResponseEntity.noContent()
                             .build();
    }
}
