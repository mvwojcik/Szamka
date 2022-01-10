package pl.mvwojcik.ingredient;

import io.vavr.control.Validation;
import org.springframework.stereotype.Service;
import pl.mvwojcik.error.ErrorConstants;
import pl.mvwojcik.error.ErrorResponse;
import pl.mvwojcik.ingredient.data.IngredientsRepository;
import pl.mvwojcik.ingredient.data.dto.IngredientDTO;
import pl.mvwojcik.utils.ValidationUtils;

import java.util.Objects;
import java.util.function.Predicate;

@Service
public class IngredientsValidator {
    private final IngredientsRepository ingredientsRepository;

    private final Predicate<Double> isInBound = aDouble -> aDouble != null && aDouble <= 100.0;
    private final Predicate<Object> isNotNull = Predicate.not(Objects::isNull);

    public IngredientsValidator(IngredientsRepository ingredientsRepository) {
        this.ingredientsRepository = ingredientsRepository;
    }

    public Validation<ErrorResponse, IngredientDTO> checkIfIngredientExists(IngredientDTO ingredient) {
        return this.ingredientsRepository.existsByName(ingredient.getName()) ?
                Validation.invalid(ErrorConstants.ingredientExists(ingredient.getName())) :
                Validation.valid(ingredient);
    }

    private Validation<ErrorResponse, IngredientDTO> checkIfValuesAreValid(IngredientDTO ingredient) {
        return isInBound.test(ingredient.getFat()) && isInBound.test(ingredient.getProteins())
                && isInBound.test(ingredient.getCarbohydrates()) ?
                Validation.valid(ingredient) :
                Validation.invalid(ErrorConstants.ValuesAreNotInBounds());
    }


    public Validation<ErrorResponse, IngredientDTO> checkIfIngredientIsNotNull(IngredientDTO ingredient) {
        return Objects.isNull(ingredient) ?
                Validation.invalid(ErrorConstants.ingredientNotValid(null)) :
                Validation.valid(ingredient);
    }

    public Validation<ErrorResponse, IngredientDTO> runIngredientValidation(IngredientDTO ingredient) {
        return Validation.combine(
                checkIfIngredientIsNotNull(ingredient),
                checkIfValuesAreValid(ingredient),
                checkIfIngredientExists(ingredient))
                .ap((a, b, c) -> ingredient)
                .mapError(ValidationUtils.mapErrors);
    }
}
