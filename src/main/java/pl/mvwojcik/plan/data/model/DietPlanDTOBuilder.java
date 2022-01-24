package pl.mvwojcik.plan.data.model;

import lombok.Setter;
import pl.mvwojcik.plan.data.dto.DietPlanDTO;
import pl.mvwojcik.plan.data.dto.DietPlanIngredientDTO;

import java.util.List;

@Setter
public class DietPlanDTOBuilder {
    DietPlanDTO dietPlanDTO;
    List<DietPlanIngredientDTO> dietPlanIngredientDTOS;

}
