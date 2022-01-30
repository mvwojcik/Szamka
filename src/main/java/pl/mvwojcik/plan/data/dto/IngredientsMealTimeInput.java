package pl.mvwojcik.plan.data.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.mvwojcik.plan.MealTime;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class IngredientsMealTimeInput {
    private MealTime mealTime;
    private List<IngredientAmount> ingredientAmounts;
    public Set<String> getIngredientsNames() {
        return ingredientAmounts.stream().map(IngredientAmount::getIngredientName).collect(Collectors.toSet());
    }
}
