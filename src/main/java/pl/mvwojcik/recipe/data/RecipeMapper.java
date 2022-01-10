package pl.mvwojcik.recipe.data;

import pl.mvwojcik.ingredient.data.IngredientMapper;
import pl.mvwojcik.ingredient.data.model.Ingredient;
import pl.mvwojcik.recipe.data.dto.RecipeDTO;
import pl.mvwojcik.recipe.data.dto.RecipeIngredientDTO;
import pl.mvwojcik.recipe.data.model.Recipe;
import pl.mvwojcik.recipe.data.model.RecipeIngredient;
import pl.mvwojcik.vitamins.data.model.Vitamin;

import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class RecipeMapper {
    public static Recipe mapRecipeDTOToRecipe(RecipeDTO recipeDTO, List<Ingredient> ingredients) {
        Function<RecipeIngredientDTO, Ingredient> getIngredientForName
                = recipeIngredient -> ingredients.stream()
                .filter(ingredient1 -> ingredient1.getName()
                        .equalsIgnoreCase(recipeIngredient.getIngredientName()))
                .findFirst()
                .get();

        Set<RecipeIngredient> collect = recipeDTO.getRecipeIngredients()
                .stream()
                .map(ingredient -> new RecipeIngredient(ingredient.getAmount(),
                        getIngredientForName.apply(ingredient)))
                .collect(Collectors.toSet());
        return Recipe.builder()
                .name(recipeDTO.getName())
                .description(recipeDTO.getDescription())
                .shortDescription(recipeDTO.getShortDescription())
                .imageUrl(recipeDTO.getImageUrl())
                .rating(recipeDTO.getRating())
                .recipeIngredients(collect)
                .build();
    }

    public static RecipeDTO mapRecipeToRecipeDTO(Recipe recipe, Set<Vitamin> vitamins) {
        return RecipeDTO.builder()
                .name(recipe.getName())
                .description(recipe.getDescription())
                .shortDescription(recipe.getShortDescription())
                .imageUrl(recipe.getImageUrl())
                .rating(recipe.getRating())
                .id(recipe.getId())
                .recipeIngredients(recipe.getRecipeIngredients()
                        .stream()
                        .map(r -> RecipeMapper.mapToDTO(r, vitamins))
                        .collect(Collectors.toSet()))
                .build();
    }

    public static RecipeIngredientDTO mapToDTO(RecipeIngredient recipeIngredient, Set<Vitamin> vitamins) {
        return RecipeIngredientDTO
                .builder()
                .ingredient(IngredientMapper.mapIngredientToIngredientDTO(recipeIngredient.getIngredient(), vitamins))
                .amount(recipeIngredient.getAmount())
                .build();
    }
}
