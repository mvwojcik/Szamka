package pl.mvwojcik.plan;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import pl.mvwojcik.ingredient.IngredientDTO;

@Builder
@Getter
@AllArgsConstructor
public class DietPlanIngredientEntryDTO {
    private MealTime mealTime;
    private IngredientDTO ingredient;
    private Double amount;
}
