package pl.mvwojcik.plan.data.model;

import lombok.Setter;
import pl.mvwojcik.plan.data.dto.DietPlanDTO;
import pl.mvwojcik.plan.data.dto.DietPlanIngredientDTO;
import pl.mvwojcik.plan.data.dto.DietPlanRecipeDTO;
import pl.mvwojcik.vitamins.data.VitaminMapper;
import pl.mvwojcik.vitamins.data.dto.VitaminInIngredientDTO;
import pl.mvwojcik.vitamins.data.model.Vitamin;

import java.util.List;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

@Setter
public class DietPlanDTOBuilder {
    DietPlanDTO dietPlanDTO;
    List<DietPlanIngredientDTO> dietPlanIngredientDTOS;
    List<DietPlanRecipeDTO> dietPlanRecipeDTOS;

}
