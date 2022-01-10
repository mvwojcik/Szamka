package pl.mvwojcik.recipe.data.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import pl.mvwojcik.ingredient.data.dto.IngredientDTO;

@Builder
@Getter
@AllArgsConstructor
public class RecipeIngredientDTO {
    private IngredientDTO ingredient;
    private Double amount;

    @JsonIgnore
    public String getIngredientName() {
        return ingredient.getName();
    }

}
