package pl.mvwojcik.recipe;

import io.vavr.control.Validation;
import org.springframework.stereotype.Service;
import pl.mvwojcik.error.ErrorConstants;
import pl.mvwojcik.error.ErrorResponse;
import pl.mvwojcik.recipe.data.RecipeRepository;
import pl.mvwojcik.recipe.data.dto.RecipeDTO;
import pl.mvwojcik.utils.ValidationUtils;

import java.util.Objects;

@Service
public class RecipeValidator {

    private final RecipeRepository recipeRepository;

    public RecipeValidator(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public Validation<ErrorResponse, RecipeDTO> checkIfRecipeExists(RecipeDTO recipe) {
        return this.recipeRepository.existsByName(recipe.getName()) ?
                Validation.invalid(ErrorConstants.recipeExists(recipe.getName())) :
                Validation.valid(recipe);
    }


    public Validation<ErrorResponse, RecipeDTO> checkIfRecipeIsValid(RecipeDTO recipe) {
        return recipe.getName().isEmpty() ?
                Validation.invalid(ErrorConstants.recipeNotValid(recipe.getName())) :
                Validation.valid(recipe);
    }

    public Validation<ErrorResponse, RecipeDTO> checkIfRecipeIsNotNull(RecipeDTO recipe) {
        return Objects.isNull(recipe) ?
                Validation.invalid(ErrorConstants.recipeNotValid(null)) :
                Validation.valid(recipe);
    }

    public Validation<ErrorResponse, RecipeDTO> checkIfRecipeHasIngredients(RecipeDTO recipe) {
        return recipe.getRecipeIngredients().size() < 1 ?
                Validation.invalid(ErrorConstants.recipeNotValid("Recipe should have recipes")) :
                Validation.valid(recipe);
    }

    public Validation<ErrorResponse, RecipeDTO> runAllergenValidation(RecipeDTO recipe) {
        return Validation.combine(checkIfRecipeIsNotNull(recipe), checkIfRecipeIsValid(recipe), checkIfRecipeExists(recipe), checkIfRecipeHasIngredients(recipe)).
                ap((a, b, c, d) -> recipe)
                .mapError(ValidationUtils.mapErrors);
    }
}
