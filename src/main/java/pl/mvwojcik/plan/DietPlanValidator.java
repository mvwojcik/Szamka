package pl.mvwojcik.plan;

import io.vavr.control.Validation;
import org.springframework.stereotype.Service;
import pl.mvwojcik.error.ErrorConstants;
import pl.mvwojcik.error.ErrorResponse;
import pl.mvwojcik.plan.data.DietPlanRepository;
import pl.mvwojcik.plan.data.dto.DietPlanDTO;
import pl.mvwojcik.utils.ValidationUtils;

import java.util.Objects;

@Service
public class DietPlanValidator {

    private final DietPlanRepository repository;

    public DietPlanValidator(DietPlanRepository repository) {
        this.repository = repository;
    }

    public Validation<ErrorResponse, DietPlanDTO> checkIfDietPlanExists(DietPlanDTO dietPlan) {
        return this.repository.existsByName(dietPlan.getName()) ?
                Validation.invalid(ErrorConstants.dietPlanExists(dietPlan.getName())) :
                Validation.valid(dietPlan);
    }


    public Validation<ErrorResponse, DietPlanDTO> checkIfDietPlanIsValid(DietPlanDTO dietPlan) {
        return dietPlan.getName().isEmpty() ?
                Validation.invalid(ErrorConstants.dietPlanNotValid(dietPlan.getName())) :
                Validation.valid(dietPlan);
    }

    public Validation<ErrorResponse, DietPlanDTO> checkIfDietPlanIsNotNull(DietPlanDTO dietPlan) {
        return Objects.isNull(dietPlan) ?
                Validation.invalid(ErrorConstants.dietPlanNotValid(null)) :
                Validation.valid(dietPlan);
    }

    public Validation<ErrorResponse, DietPlanDTO> checkIfDietPlanHasIngredients(DietPlanDTO dietPlan) {
        return dietPlan.getIngredients().size() < 1 && dietPlan.getRecipes().size() < 1 ?
                Validation.invalid(ErrorConstants.dietPlanNotValid("DietPlan should have ingredients or recipes inside")) :
                Validation.valid(dietPlan);
    }

    public Validation<ErrorResponse, DietPlanDTO> runAllergenValidation(DietPlanDTO dietPlan) {
        return Validation.combine(checkIfDietPlanIsNotNull(dietPlan), checkIfDietPlanIsValid(dietPlan), checkIfDietPlanExists(dietPlan), checkIfDietPlanHasIngredients(dietPlan)).
                ap((a, b, c, d) -> dietPlan)
                .mapError(ValidationUtils.mapErrors);
    }

}
