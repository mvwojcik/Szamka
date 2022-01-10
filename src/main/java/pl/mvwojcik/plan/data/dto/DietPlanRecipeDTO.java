package pl.mvwojcik.plan.data.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import pl.mvwojcik.plan.MealTime;
import pl.mvwojcik.recipe.data.dto.RecipeDTO;

@Builder
@Getter
@AllArgsConstructor
public class DietPlanRecipeDTO {
    private MealTime mealTime;
    private RecipeDTO recipe;
    private Double amount;

    @JsonIgnore
    public String getRecipeName() {
        return recipe.getName();
    }
}
