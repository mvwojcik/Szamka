package pl.mvwojcik.plan.data.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class DietPlanInputDTO {
    private String name;
    private String description;
    private Set<IngredientsMealTimeInput> mealTimes;

    public Set<String> getIngredientsNames() {
        return mealTimes.stream().flatMap(a->a.getIngredientsNames().stream()).collect(Collectors.toSet());
    }
}
