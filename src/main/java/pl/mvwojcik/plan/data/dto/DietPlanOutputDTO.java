package pl.mvwojcik.plan.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Set;

@Builder
@Getter
@AllArgsConstructor
public class DietPlanOutputDTO {
    private Long id;
    private String name;
    private String description;
    //groupby mealtime
    private List<IngredientsMealTime> mealTimes;
    private Set<String> allergens;
    private Integer kcal;
    private Double protein;
    private Double carbs;
    private Double fat;
    private List<VitaminValue> vitaminValues;
}
