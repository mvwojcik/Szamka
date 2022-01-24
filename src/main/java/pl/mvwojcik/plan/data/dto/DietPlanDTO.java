package pl.mvwojcik.plan.data.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;


@AllArgsConstructor
@Builder
@Getter
public class DietPlanDTO {
    private Long id;
    private String name;
    private String description;
    private List<DietPlanIngredientDTO> ingredients;


    @JsonIgnore
    public List<String> getIngredientsName() {
        return ingredients.stream().map(DietPlanIngredientDTO::getIngredientName).collect(Collectors.toList());
    }


}
