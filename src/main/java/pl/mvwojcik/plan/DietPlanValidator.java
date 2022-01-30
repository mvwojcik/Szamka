package pl.mvwojcik.plan;

import io.vavr.control.Validation;
import org.springframework.stereotype.Service;
import pl.mvwojcik.error.ErrorConstants;
import pl.mvwojcik.error.ErrorResponse;
import pl.mvwojcik.plan.data.DietPlanRepository;
import pl.mvwojcik.plan.data.dto.DietPlanDTO;
import pl.mvwojcik.plan.data.dto.DietPlanInputDTO;
import pl.mvwojcik.utils.ValidationUtils;

import java.util.Objects;

@Service
public class DietPlanValidator {

    private final DietPlanRepository repository;

    public DietPlanValidator(DietPlanRepository repository) {
        this.repository = repository;
    }

    public Validation<ErrorResponse, DietPlanInputDTO> checkIfDietPlanExists(DietPlanInputDTO dietPlan) {
        return this.repository.existsByName(dietPlan.getName()) ?
                Validation.invalid(ErrorConstants.dietPlanExists(dietPlan.getName())) :
                Validation.valid(dietPlan);
    }


    public Validation<ErrorResponse, DietPlanInputDTO> checkIfDietPlanIsValid(DietPlanInputDTO dietPlan) {
        return dietPlan.getName().isEmpty() ?
                Validation.invalid(ErrorConstants.dietPlanNotValid(dietPlan.getName())) :
                Validation.valid(dietPlan);
    }

    public Validation<ErrorResponse, DietPlanInputDTO> checkIfDietPlanIsNotNull(DietPlanInputDTO dietPlan) {
        return Objects.isNull(dietPlan) ?
                Validation.invalid(ErrorConstants.dietPlanNotValid(null)) :
                Validation.valid(dietPlan);
    }

    public Validation<ErrorResponse, DietPlanInputDTO> checkIfDietPlanHasIngredients(DietPlanInputDTO dietPlan) {
        return dietPlan.getMealTimes().size() < 1 ?
                Validation.invalid(ErrorConstants.dietPlanNotValid("DietPlan should have ingredients inside")) :
                Validation.valid(dietPlan);
    }

    public Validation<ErrorResponse, DietPlanInputDTO> runDietPlanValidation(DietPlanInputDTO dietPlan) {
        return Validation.combine(checkIfDietPlanIsNotNull(dietPlan), checkIfDietPlanIsValid(dietPlan), checkIfDietPlanExists(dietPlan), checkIfDietPlanHasIngredients(dietPlan)).
                ap((a, b, c, d) -> dietPlan)
                .mapError(ValidationUtils.mapErrors);
    }

}
