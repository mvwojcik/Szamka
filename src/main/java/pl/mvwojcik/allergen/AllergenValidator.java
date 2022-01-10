package pl.mvwojcik.allergen;

import io.vavr.control.Validation;
import org.springframework.stereotype.Service;
import pl.mvwojcik.allergen.data.AllergenDTO;
import pl.mvwojcik.allergen.data.AllergensRepository;
import pl.mvwojcik.error.ErrorConstants;
import pl.mvwojcik.error.ErrorResponse;
import pl.mvwojcik.utils.ValidationUtils;

import java.util.Objects;

@Service
public class AllergenValidator {

    private AllergensRepository allergensRepository;

    public AllergenValidator(AllergensRepository allergensRepository) {
        this.allergensRepository = allergensRepository;
    }

    public Validation<ErrorResponse, AllergenDTO> checkIfAllergenExists(AllergenDTO allergen) {
        return this.allergensRepository.existsByName(allergen.getName()) ?
                Validation.invalid(ErrorConstants.allergenExists(allergen.getName())) :
                Validation.valid(allergen);
    }


    public Validation<ErrorResponse, AllergenDTO> checkIfAllergenIsValid(AllergenDTO allergen) {
        return allergen.getName().isEmpty() ?
                Validation.invalid(ErrorConstants.allergenNotValid(allergen.getName())) :
                Validation.valid(allergen);
    }

    public Validation<ErrorResponse, AllergenDTO> checkIfAllergenIsNotNull(AllergenDTO allergen) {
        return Objects.isNull(allergen) ?
                Validation.invalid(ErrorConstants.allergenNotValid(null)) :
                Validation.valid(allergen);
    }

    public Validation<ErrorResponse, AllergenDTO> runAllergenValidation(AllergenDTO allergen) {
        return Validation.combine(checkIfAllergenIsNotNull(allergen), checkIfAllergenIsValid(allergen), checkIfAllergenExists(allergen)).
                ap((a, b, c) -> allergen)
                .mapError(ValidationUtils.mapErrors);
    }

}
