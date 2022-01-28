package pl.mvwojcik.plan.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import pl.mvwojcik.plan.MealTime;

import java.util.List;

@Builder
@AllArgsConstructor
@Getter
public class IngredientsMealTime {
    private MealTime mealTime;
    private List<IngredientAmount> ingredientAmounts;
    private List<String> allergens;
    private Integer kcal;
    private Double protein;
    private Double carbs;
    private Double fat;

}

