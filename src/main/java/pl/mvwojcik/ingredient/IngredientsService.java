package pl.mvwojcik.ingredient;

import io.vavr.control.Either;
import io.vavr.control.Option;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import pl.mvwojcik.allergen.data.Allergen;
import pl.mvwojcik.allergen.data.AllergenDTO;
import pl.mvwojcik.allergen.data.AllergensRepository;
import pl.mvwojcik.comunication.ContentResponse;
import pl.mvwojcik.comunication.EmptyResponse;
import pl.mvwojcik.comunication.ServiceResponse;
import pl.mvwojcik.error.ErrorConstants;
import pl.mvwojcik.error.ErrorResponse;
import pl.mvwojcik.ingredient.data.IngredientBuilder;
import pl.mvwojcik.ingredient.data.IngredientMapper;
import pl.mvwojcik.ingredient.data.dto.IngredientDTO;
import pl.mvwojcik.ingredient.data.model.Ingredient;
import pl.mvwojcik.ingredient.data.IngredientsRepository;
import pl.mvwojcik.ingredient.data.model.IngredientProjection;
import pl.mvwojcik.vitamins.data.model.Vitamin;
import pl.mvwojcik.vitamins.VitaminsService;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public final class IngredientsService {
    private final AllergensRepository allergensRepository;
    private final IngredientsRepository ingredientsRepository;
    private final VitaminsService vitaminsService;
    private final IngredientsValidator ingredientsValidator;

    public IngredientsService(AllergensRepository allergensRepository, IngredientsRepository ingredientsRepository, VitaminsService vitaminsService, IngredientsValidator ingredientsValidator) {
        this.allergensRepository = allergensRepository;
        this.ingredientsRepository = ingredientsRepository;
        this.vitaminsService = vitaminsService;
        this.ingredientsValidator = ingredientsValidator;
    }

    public ServiceResponse createIngredient(IngredientDTO ingredientDTO) {
        return ingredientsValidator.runIngredientValidation(ingredientDTO)
                .toEither()
                .map(IngredientBuilder::new)
                .flatMap(this::findAllergens)
                .flatMap(this::findVitaminsForIngredient)
                .map(IngredientBuilder::build)
                .flatMap(this::save)
                .map(this::prepareResponse)
                .fold(Function.identity(), Function.identity());
    }

    private Either<ErrorResponse, Ingredient> save(Ingredient ingredient) {
        return Either.right(ingredientsRepository.save(ingredient));
    }

    private Either<ErrorResponse, IngredientBuilder> findAllergens(IngredientBuilder ingredientBuilder) {
        Set<Allergen> allergens = allergensRepository.findByNameInIgnoreCase(ingredientBuilder.getAllergensName());
        return ingredientBuilder.getAllergensName().isEmpty() || ingredientBuilder.getAllergensName().size() == allergens.size() ?
                Either.right(ingredientBuilder.withAllergens(allergens)) :
                Either.left(ErrorConstants.allergenNotFound(""));
    }

    private Either<ErrorResponse, IngredientBuilder> findVitaminsForIngredient(IngredientBuilder ingredientBuilder) {
        Set<Vitamin> vitamins = vitaminsService.findByNameInIgnoreCase(ingredientBuilder.getVitaminsName());
        return vitamins.size() == ingredientBuilder.getVitaminsName().size() ?
                Either.right(ingredientBuilder.withVitamins(IngredientMapper.mapVitaminsDTO(ingredientBuilder.getIngredientDTO(), vitamins))) :
                Either.left(ErrorConstants.VITAMIN_NOT_FOUND(""));
    }

    private ContentResponse<IngredientDTO> prepareResponse(Ingredient ingredient) {
        IngredientDTO dto = IngredientMapper.
                mapIngredientToIngredientDTO(ingredient);
        return new ContentResponse<>(HttpStatus.CREATED, dto);
    }

    //todo projection instead
    public ContentResponse<Page<IngredientProjection>> getAll(int page) {
        return new ContentResponse<>(HttpStatus.OK, ingredientsRepository.findAllBy(PageRequest.of(page,10)));
    }

//    public ContentResponse<Page<IngredientProjection>> getAllNotContaining(List<AllergenDTO> allergens) {
//        ingredientsRepository.findAll
//    }

    public List<Ingredient> findAllIngredientsIn(List<String> ingredients) {
        return ingredientsRepository.findByNameInIgnoreCase(ingredients);
    }


    public ServiceResponse findByID(Long id) {
        return Option.ofOptional(ingredientsRepository.findById(id))
                .toEither(ErrorConstants.ingredientNotFound(id))
                .map(i -> IngredientMapper.mapIngredientToIngredientDTO(i, vitaminsService.getAll()))
                .map(ingredient -> new ContentResponse(HttpStatus.OK, ingredient))
                .fold(Function.identity(), Function.identity());
    }

    public ServiceResponse delete(Long id) {
        return Option.ofOptional(ingredientsRepository.findById(id))
                .toEither(ErrorConstants.ingredientNotFound(id))
                .flatMap(this::deleteIngredient)
                .fold(Function.identity(), a -> new EmptyResponse(HttpStatus.NO_CONTENT));
    }

    public final Either<ErrorResponse, Ingredient> deleteIngredient(Ingredient ingredient) {
        ingredientsRepository.delete(ingredient);
        return Either.right(ingredient);
    }

}
