package pl.mvwojcik.user;

import io.vavr.control.Either;
import io.vavr.control.Validation;
import org.springframework.security.core.Authentication;
import pl.mvwojcik.error.ErrorConstants;
import pl.mvwojcik.error.ErrorResponse;
import pl.mvwojcik.utils.ValidationUtils;

public class UserValidation {


    public static Either<ErrorResponse, User> UsernameValidation(User user, Authentication authentication) {
        return user.getUsername() != null && user.getUsername().isBlank() ?
                Either.right(user) :
                Either.left(ErrorConstants.userAttemptedAccess(user.getUsername()));
    }

    public static Either<ErrorResponse, User> matchingUsernameValidation(User user, Authentication authentication) {
        return user.getUsername().equals(authentication.getName()) ?
                Either.right(user) :
                Either.left(ErrorConstants.userAttemptedAccess(user.getUsername()));
    }


    public static Validation<ErrorResponse, UserRegistrationDTO> validateUserWeight(UserRegistrationDTO user) {
        return user.getWeight() == null || user.getWeight() < 0 || user.getWeight() > 1000 ?
                Validation.invalid(ErrorConstants.userWeightNotValid(user.getWeight())) :
                Validation.valid(user);
    }

    public static Validation<ErrorResponse, UserRegistrationDTO> validateUserHeight(UserRegistrationDTO user) {
        return user.getHeight() == null || user.getHeight() < 0.0 || user.getHeight() > 500 ?
                Validation.invalid(ErrorConstants.userHeightNotValid(user.getHeight())) :
                Validation.valid(user);
    }

    public static Validation<ErrorResponse, UserRegistrationDTO> validateUserKcal(UserRegistrationDTO user) {
        return user.getDailyKcal() == null || user.getDailyKcal() < 0 || user.getDailyKcal() > 10000 ?
                Validation.invalid(ErrorConstants.userKcalNotValid(user.getDailyKcal())) :
                Validation.valid(user);
    }

    public static Validation<ErrorResponse, UserRegistrationDTO> validateUserRegistration(UserRegistrationDTO user) {
        return Validation.combine(validateUserHeight(user), validateUserWeight(user), validateUserKcal(user))
                .ap((a, b, c) -> user)
                .mapError(ValidationUtils.mapErrors);
    }
}
