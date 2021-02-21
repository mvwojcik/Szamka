package pl.mvwojcik.plan;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;


@AllArgsConstructor
@Builder
@Getter
public class DietPlanDTO {
    private String name;
    private String description;
    private List<DietPlanRecipeEntryDTO> recipes;
    private List<DietPlanIngredientEntryDTO> ingredients;

}
