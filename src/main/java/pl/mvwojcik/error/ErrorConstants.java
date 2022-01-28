package pl.mvwojcik.error;

import org.springframework.http.HttpStatus;

public interface ErrorConstants {

    static ErrorResponse allergenNotFound(String name) {
        return ErrorResponse.builder()
                .status(HttpStatus.NOT_FOUND)
                .message("Allergen does not exist" + name)
                .build();
    }

    static ErrorResponse allergenExists(String name) {
        return ErrorResponse.builder()
                .status(HttpStatus.CONFLICT)
                .message("Allergen already exists: " + name)
                .build();
    }

    static ErrorResponse allergenNotValid(String name) {
        return ErrorResponse.builder()
                .status(HttpStatus.NOT_ACCEPTABLE)
                .message(String.format("Allergen with name %s could not be created", name))
                .build();
    }

    static ErrorResponse ingredientExists(String name) {
        return ErrorResponse.builder()
                .status(HttpStatus.CONFLICT)
                .message("Ingredient already exists: " + name)
                .build();
    }

    static ErrorResponse ingredientHasId() {
        return ErrorResponse.builder()
                .status(HttpStatus.NOT_ACCEPTABLE)
                .message("Ingredient has id inside ")
                .build();
    }

    static ErrorResponse ingredientNotValid(String name) {
        return ErrorResponse.builder()
                .status(HttpStatus.NOT_ACCEPTABLE)
                .message(String.format("Ingredient with name %s could not be created", name))
                .build();
    }

    static ErrorResponse ValuesAreNotInBounds() {
        return ErrorResponse.builder()
                .status(HttpStatus.NOT_ACCEPTABLE)
                .message("Values are not in bounds")
                .build();
    }

    static ErrorResponse VITAMIN_NOT_FOUND(String name) {
        return ErrorResponse.builder()
                .status(HttpStatus.NOT_FOUND)
                .message("Vitamin not found: " + name)
                .build();
    }


    static ErrorResponse ingredientNotFound(Long id) {
        return ErrorResponse.builder()
                .status(HttpStatus.NOT_FOUND)
                .message("Ingredient not found: " + id)
                .build();
    }

    static ErrorResponse recipeExists(String name) {
        return ErrorResponse.builder()
                .status(HttpStatus.CONFLICT)
                .message("Recipe already exists: " + name)
                .build();
    }

    static ErrorResponse recipeNotValid(String name) {
        return ErrorResponse.builder()
                .status(HttpStatus.NOT_ACCEPTABLE)
                .message(String.format("Recipe with name %s could not be created", name))
                .build();
    }

    static ErrorResponse recipeNotFound(Long id) {
        return ErrorResponse.builder()
                .status(HttpStatus.NOT_FOUND)
                .message("Recipe not found with id: " + id)
                .build();
    }

    static ErrorResponse ingredientAmountNotEquals() {
        return ErrorResponse.builder()
                .status(HttpStatus.NOT_ACCEPTABLE)
                .message("Recipe cannot be created, ingredient not found")
                .build();
    }

    static ErrorResponse dietPlanExists(String name) {
        return ErrorResponse.builder()
                .status(HttpStatus.CONFLICT)
                .message("DietPlan already exists: " + name)
                .build();
    }

    static ErrorResponse dietPlanNotValid(String name) {
        return ErrorResponse.builder()
                .status(HttpStatus.NOT_ACCEPTABLE)
                .message(String.format("DietPlan with name %s could not be created", name))
                .build();
    }

    static ErrorResponse dietPlanNotFound(Long id) {
        return ErrorResponse.builder()
                .status(HttpStatus.NOT_FOUND)
                .message("DietPlan not found with id: " + id)
                .build();
    }


    static ErrorResponse userNotFound(String username) {
        return ErrorResponse.builder()
                .status(HttpStatus.NOT_FOUND)
                .message("User not found with username: " + username)
                .build();
    }

    static ErrorResponse userAttemptedAccess(String username) {
        return ErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST)
                .message("You can not access data of user: " + username)
                .build();
    }

    static ErrorResponse userUsernameNotValid(String username) {
        return ErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST)
                .message("Username value not valid: " + username)
                .build();
    }

    static ErrorResponse userWeightNotValid(Double weight) {
        return ErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST)
                .message("Weight value not valid: " + weight)
                .build();
    }

    static ErrorResponse userHeightNotValid(Integer weight) {
        return ErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST)
                .message("Height value not valid: " + weight)
                .build();
    }

    static ErrorResponse userKcalNotValid(Integer weight) {
        return ErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST)
                .message("Kcal value not valid: " + weight)
                .build();
    }

    static ErrorResponse userExists(String username) {
        return ErrorResponse.builder()
                .status(HttpStatus.CONFLICT)
                .message("User already exists: " + username)
                .build();
    }


    static ErrorResponse userNotCreated(String username) {
        return ErrorResponse.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .message("User could not be created: " + username)
                .build();
    }

}
