package pl.mvwojcik.plan.data.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import pl.mvwojcik.ingredient.data.dto.IngredientDTO;
import pl.mvwojcik.plan.MealTime;

@Builder
@Getter
@AllArgsConstructor
public class DietPlanIngredientDTO {
    private MealTime mealTime;
    private IngredientDTO ingredient;
    private Double amount;

    @JsonIgnore
    public String getIngredientName() {
        return ingredient.getName();
    }
}
