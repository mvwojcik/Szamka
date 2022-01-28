package pl.mvwojcik.plan.data;

import pl.mvwojcik.allergen.data.Allergen;
import pl.mvwojcik.ingredient.data.IngredientMapper;
import pl.mvwojcik.ingredient.data.model.Ingredient;
import pl.mvwojcik.plan.MealTime;
import pl.mvwojcik.plan.data.dto.DietPlanDTO;
import pl.mvwojcik.plan.data.dto.DietPlanIngredientDTO;
import pl.mvwojcik.plan.data.dto.DietPlanOutputDTO;
import pl.mvwojcik.plan.data.dto.IngredientAmount;
import pl.mvwojcik.plan.data.dto.IngredientsMealTime;
import pl.mvwojcik.plan.data.dto.VitaminValue;
import pl.mvwojcik.plan.data.model.DietPlan;
import pl.mvwojcik.plan.data.model.DietPlanIngredient;
import pl.mvwojcik.recipe.data.RecipeMapper;
import pl.mvwojcik.recipe.data.model.Recipe;
import pl.mvwojcik.vitamins.data.dto.VitaminInIngredientDTO;
import pl.mvwojcik.vitamins.data.model.Vitamin;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
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

    public static VitaminValue mapVitamin(Vitamin vitamin) {
        return VitaminValue.builder().amount(0d).name(vitamin.getName()).type(vitamin.getType())
                .dailyRequirement(vitamin.getDailyRequirement()).scale(vitamin.getScale())
                .build();
    }

    public static DietPlanOutputDTO mapToDTO2(DietPlan dietPlan, Set<Vitamin> vitamins) {
        Map<String, VitaminValue> vitaminsValues = vitamins.stream().map(DietPlanMapper::mapVitamin)
                .collect(Collectors.toMap(VitaminValue::getName, Function.identity()));
        List<IngredientsMealTime> collect = dietPlan.getIngredients().stream()
                .collect(Collectors.groupingBy(DietPlanIngredient::getMealTime))
                .entrySet()
                .stream().map(DietPlanMapper::map).collect(Collectors.toList());
        int kcal = 0;
        double protein = 0.0;
        double carbs = 0.0;
        double fat = 0.0;
        Set<String> allergens = new HashSet<>();
        for (IngredientsMealTime ingredientsMealTime : collect) {
            kcal += ingredientsMealTime.getKcal();
            protein += ingredientsMealTime.getProtein();
            carbs += ingredientsMealTime.getCarbs();
            fat += ingredientsMealTime.getFat();
            allergens.addAll(ingredientsMealTime.getAllergens());
            for (IngredientAmount ingredientAmount : ingredientsMealTime.getIngredientAmounts()) {
                for (VitaminInIngredientDTO vitamin : ingredientAmount.getIngredient().getVitamins()) {
                    VitaminValue vitaminValue = vitaminsValues.get(vitamin.getName());
                    vitaminValue.increaseAmount(vitamin.getAmount());
                }
            }
        }
        return DietPlanOutputDTO.builder()
                .id(dietPlan.getId())
                .name(dietPlan.getName())
                .description(dietPlan.getDescription())
                .mealTimes(collect)
                .fat(fat)
                .kcal(kcal)
                .protein(protein)
                .carbs(carbs)
                .allergens(allergens)
                .vitaminValues(new ArrayList<>(vitaminsValues.values()))
                .build();
    }

    //TODO: this should be better
    public static IngredientsMealTime map(Map.Entry<MealTime, List<DietPlanIngredient>> entry) {
        int kcal = 0;
        double protein = 0.0;
        double carbs = 0.0;
        double fat = 0.0;
        List<String> allergens = new ArrayList<>();
        List<IngredientAmount> ingredientAmounts = new ArrayList<>();
        for (DietPlanIngredient dietPlanIngredient : entry.getValue()) {
            protein += dietPlanIngredient.getIngredient().getProteins() * dietPlanIngredient.getAmount();
            carbs += dietPlanIngredient.getIngredient().getCarbohydrates() * dietPlanIngredient.getAmount();
            fat += dietPlanIngredient.getIngredient().getFat() * dietPlanIngredient.getAmount();
            kcal += dietPlanIngredient.getIngredient().getKcal() * dietPlanIngredient.getAmount();
            ingredientAmounts.add(new IngredientAmount(IngredientMapper.mapIngredientToIngredientDTO(dietPlanIngredient.getIngredient()),
                    dietPlanIngredient.getAmount()));
            allergens.addAll(dietPlanIngredient.getIngredient().getAllergens().stream().map(Allergen::getName).collect(Collectors.toList()));
        }

        return IngredientsMealTime.builder().mealTime(entry.getKey())
                .ingredientAmounts(ingredientAmounts)
                .fat(fat)
                .carbs(carbs)
                .kcal(kcal)
                .protein(protein)
                .allergens(allergens)

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
