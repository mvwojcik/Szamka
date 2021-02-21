package pl.mvwojcik.recipe;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import pl.mvwojcik.ingredient.IngredientDTO;

@Builder
@Getter
@AllArgsConstructor
public class RecipeIngredientDTO {
    private IngredientDTO ingredient;
    private Double amount;
}
