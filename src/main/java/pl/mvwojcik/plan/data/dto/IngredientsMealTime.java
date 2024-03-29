package pl.mvwojcik.plan.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import pl.mvwojcik.plan.MealTime;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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


    public Set<String> getIngredientsNames() {
        return ingredientAmounts.stream().map(IngredientAmount::getIngredientName).collect(Collectors.toSet());
    }

}

