package pl.mvwojcik.utils;

import pl.mvwojcik.allergen.Allergen;
import pl.mvwojcik.allergen.AllergenDTO;
import pl.mvwojcik.ingredient.Ingredient;
import pl.mvwojcik.ingredient.IngredientDTO;
import pl.mvwojcik.plan.DietPlan;
import pl.mvwojcik.plan.DietPlanDTO;
import pl.mvwojcik.plan.DietPlanIngredientEntry;
import pl.mvwojcik.plan.DietPlanIngredientEntryDTO;
import pl.mvwojcik.plan.DietPlanRecipeEntry;
import pl.mvwojcik.plan.DietPlanRecipeEntryDTO;
import pl.mvwojcik.recipe.Recipe;
import pl.mvwojcik.recipe.RecipeDTO;
import pl.mvwojcik.recipe.RecipeIngredient;
import pl.mvwojcik.recipe.RecipeIngredientDTO;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Mapper {
    public static Ingredient mapIngredientDTOToIngredient(IngredientDTO ingredientDTO, List<Allergen> allergens) {
        return Ingredient.builder()
                         .id(ingredientDTO.getId())
                         .name(ingredientDTO.getName())
                         .kcal(ingredientDTO.getKcal())
                         .proteins(ingredientDTO.getProteins())
                         .carbohydrates(ingredientDTO.getCarbohydrates())
                         .fat(ingredientDTO.getFat())
                         .allergens(allergens)
                         .build();
    }

    public static IngredientDTO mapIngredientToIngredientDTO(Ingredient ingredient) {
        return IngredientDTO.builder()
                            .id(ingredient.getId())
                            .name(ingredient.getName())
                            .kcal(ingredient.getKcal())
                            .proteins(ingredient.getProteins())
                            .carbohydrates(ingredient.getCarbohydrates())
                            .fat(ingredient.getFat())
                            .allergens(ingredient.getAllergens()
                                                 .stream()
                                                 .map(Mapper::mapAllergenToAllergenDTO)
                                                 .collect(Collectors.toList()))
                            .build();
    }

    public static AllergenDTO mapAllergenToAllergenDTO(Allergen allergen) {
        return AllergenDTO.builder()
                          .id(allergen.getId())
                          .name(allergen.getName())
                          .build();
    }

    public static Allergen mapAllergenDTOToAllergen(AllergenDTO allergen) {
        return Allergen.builder()
                       .id(allergen.getId())
                       .name(allergen.getName())
                       .build();
    }


    public static Recipe mapRecipeDTOToRecipe(RecipeDTO recipeDTO, Set<RecipeIngredient> recipeIngredients) {
        return Recipe.builder()
                     .name(recipeDTO.getName())
                     .description(recipeDTO.getDescription())
                     .shortDescription(recipeDTO.getShortDescription())
                     .imageUrl(recipeDTO.getImageUrl())
                     .rating(recipeDTO.getRating())
                     .id(recipeDTO.getId())
                     .recipeIngredients(recipeIngredients)
                     .build();
    }

    public static RecipeDTO mapRecipeToRecipeDTO(Recipe recipe) {
        return RecipeDTO.builder()
                        .name(recipe.getName())
                        .description(recipe.getDescription())
                        .shortDescription(recipe.getShortDescription())
                        .imageUrl(recipe.getImageUrl())
                        .rating(recipe.getRating())
                        .id(recipe.getId())
                        .recipeIngredients(recipe.getRecipeIngredients()
                                                 .stream()
                                                 .map(Mapper::mapToDTO)
                                                 .collect(Collectors.toSet()))
                        .build();
    }

    public static RecipeIngredientDTO mapToDTO(RecipeIngredient recipeIngredient) {
        return RecipeIngredientDTO
                .builder()
                .ingredient(Mapper.mapIngredientToIngredientDTO(recipeIngredient.getIngredient()))
                .amount(recipeIngredient.getAmount())
                .build();
    }

    public static DietPlanIngredientEntryDTO mapToDTO(DietPlanIngredientEntry dietPlanIngredientEntry) {
        return DietPlanIngredientEntryDTO.builder()
                                         .amount(dietPlanIngredientEntry.getAmount())
                                         .ingredient(Mapper.mapIngredientToIngredientDTO(dietPlanIngredientEntry.getIngredient()))
                                         .mealTime(dietPlanIngredientEntry.getMealTime())
                                         .build();
    }

    public static DietPlanRecipeEntryDTO mapToDTO(DietPlanRecipeEntry dietPlanRecipeEntry) {
        return DietPlanRecipeEntryDTO.builder()
                                     .amount(dietPlanRecipeEntry.getAmount())
                                     .recipe(Mapper.mapRecipeToRecipeDTO(dietPlanRecipeEntry.getRecipe()))
                                     .mealTime(dietPlanRecipeEntry.getMealTime())
                                     .build();
    }

    public static DietPlanDTO mapToDTO(DietPlan dietPlan) {
        return DietPlanDTO.builder()
                          .name(dietPlan.getName())
                          .description(dietPlan.getDescription())
                          .ingredients(dietPlan.getIngredients()
                                               .stream()
                                               .map(Mapper::mapToDTO)
                                               .collect(Collectors.toList()))
                          .recipes(dietPlan.getRecipes()
                                           .stream()
                                           .map(Mapper::mapToDTO)
                                           .collect(Collectors.toList()))
                          .build();
    }

    public static DietPlan map(DietPlanDTO dietPlanDTO, List<DietPlanIngredientEntry> ingredients,
                                    List<DietPlanRecipeEntry> recipes) {
        return DietPlan.builder()
                       .name(dietPlanDTO.getName())
                       .description(dietPlanDTO.getDescription())
                       .ingredients(ingredients)
                       .recipes(recipes)
                       .build();
    }

}
