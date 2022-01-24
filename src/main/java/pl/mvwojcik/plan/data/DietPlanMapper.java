package pl.mvwojcik.plan.data;

import pl.mvwojcik.ingredient.data.IngredientMapper;
import pl.mvwojcik.ingredient.data.model.Ingredient;
import pl.mvwojcik.plan.data.dto.DietPlanDTO;
import pl.mvwojcik.plan.data.dto.DietPlanIngredientDTO;
import pl.mvwojcik.plan.data.model.DietPlan;
import pl.mvwojcik.plan.data.model.DietPlanIngredient;
import pl.mvwojcik.recipe.data.RecipeMapper;
import pl.mvwojcik.recipe.data.model.Recipe;
import pl.mvwojcik.vitamins.data.model.Vitamin;

import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DietPlanMapper {

    public static DietPlanIngredientDTO mapToDTO(DietPlanIngredient dietPlanIngredient, Set<Vitamin> vitamins) {
        return DietPlanIngredientDTO.builder()
                .amount(dietPlanIngredient.getAmount())
                .ingredient(IngredientMapper.mapIngredientToIngredientDTO(dietPlanIngredient.getIngredient(), vitamins))
                .mealTime(dietPlanIngredient.getMealTime())
                .build();
    }


    public static DietPlanDTO mapToDTO(DietPlan dietPlan, Set<Vitamin> vitamins) {
        return DietPlanDTO.builder()
                .id(dietPlan.getId())
                .name(dietPlan.getName())
                .description(dietPlan.getDescription())
                .ingredients(dietPlan.getIngredients()
                        .stream()
                        .map(d -> DietPlanMapper.mapToDTO(d, vitamins))
                        .collect(Collectors.toList()))
                .build();
    }

    public static DietPlan map(DietPlanDTO dietPlanDTO, List<Ingredient> ingredients) {


        Function<DietPlanIngredientDTO, Ingredient> getIngredientForName = dietPlanIngredient ->
                ingredients.stream()
                        .filter(ingredient -> ingredient.getName().equalsIgnoreCase(dietPlanIngredient.getIngredientName()))
                        .findFirst()
                        .get();



        Set<DietPlanIngredient> ingredientList = dietPlanDTO.getIngredients()
                .stream()
                .map(ingredient -> new DietPlanIngredient(ingredient.getMealTime(), ingredient.getAmount(), getIngredientForName.apply(ingredient)))
                .collect(Collectors.toSet());


        return DietPlan.builder()
                .name(dietPlanDTO.getName())
                .description(dietPlanDTO.getDescription())
                .ingredients(ingredientList)
                .build();
    }

    public static DietPlan map(DietPlanDTO dietPlanDTO, Set<DietPlanIngredient> ingredients) {
        return DietPlan.builder()
                .name(dietPlanDTO.getName())
                .description(dietPlanDTO.getDescription())
                .ingredients(ingredients)
                .build();
    }

}
