package pl.mvwojcik.plan;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import pl.mvwojcik.recipe.RecipeDTO;

@Builder
@Getter
@AllArgsConstructor
public class DietPlanRecipeEntryDTO {
    private MealTime mealTime;
    private RecipeDTO recipe;
    private Double amount;
}
