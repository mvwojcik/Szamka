package pl.mvwojcik.recipe;

import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.control.Either;
import io.vavr.control.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import pl.mvwojcik.comunication.ContentResponse;
import pl.mvwojcik.comunication.EmptyResponse;
import pl.mvwojcik.comunication.ServiceResponse;
import pl.mvwojcik.error.ErrorConstants;
import pl.mvwojcik.error.ErrorResponse;
import pl.mvwojcik.ingredient.data.model.Ingredient;
import pl.mvwojcik.ingredient.IngredientsService;
import pl.mvwojcik.recipe.data.RecipeMapper;
import pl.mvwojcik.recipe.data.RecipeRepository;
import pl.mvwojcik.recipe.data.dto.RecipeDTO;
import pl.mvwojcik.recipe.data.model.Recipe;
import pl.mvwojcik.vitamins.VitaminsService;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public final class RecipesService {
    private final RecipeRepository recipeRepository;
    private final IngredientsService ingredientsService;
    private final RecipeValidator recipeValidator;
    private final VitaminsService vitaminsService;

    @Autowired
    public RecipesService(RecipeRepository recipeRepository, IngredientsService ingredientsService, RecipeValidator recipeValidator, VitaminsService vitaminsService) {
        this.recipeRepository = recipeRepository;
        this.ingredientsService = ingredientsService;
        this.recipeValidator = recipeValidator;
        this.vitaminsService = vitaminsService;
    }

    public final Page<RecipeDTO> findAll(int page) {
        return recipeRepository.findAll(PageRequest.of(page,10))
                .map(r -> RecipeMapper.mapRecipeToRecipeDTO(r, vitaminsService.getAll()));
    }

    public ServiceResponse create(RecipeDTO recipeDTO) {
        return recipeValidator.runAllergenValidation(recipeDTO)
                .toEither()
                .flatMap(this::applyIngredients)
                .map(tuple -> RecipeMapper.mapRecipeDTOToRecipe(tuple._1, tuple._2))
                .map(recipeRepository::save)
                .map(r -> RecipeMapper.mapRecipeToRecipeDTO(r, vitaminsService.getAll()))
                .fold(Function.identity(), recipe -> new ContentResponse(HttpStatus.CREATED, recipe));

    }

    private Either<ErrorResponse, Tuple2<RecipeDTO, List<Ingredient>>> applyIngredients(RecipeDTO recipeDTO) {
        Tuple2<RecipeDTO, List<Ingredient>> ingredientsForRecipe = findIngredientsForRecipe(recipeDTO);
        return ingredientsForRecipe._2.size() != recipeDTO.getRecipeIngredients().size() ?
                Either.left(ErrorConstants.ingredientAmountNotEquals()) :
                Either.right(ingredientsForRecipe);
    }

    private Tuple2<RecipeDTO, List<Ingredient>> findIngredientsForRecipe(RecipeDTO recipeDTO) {
        return Tuple.of(recipeDTO, ingredientsService.findAllIngredientsIn(recipeDTO.getRecipesNames()));
    }

    public ServiceResponse delete(Long id) {
        return Option.ofOptional(recipeRepository.findById(id))
                .toEither(ErrorConstants.recipeNotFound(id))
                .map(this::deleteRecipe)
                .fold(Function.identity(), recipe -> new EmptyResponse(HttpStatus.CREATED));
    }

    public final Recipe deleteRecipe(Recipe recipe) {
        recipeRepository.delete(recipe);
        return recipe;
    }

    public final List<Recipe> findAllRecipesIn(List<String> recipes) {
        return recipeRepository.findByNameInIgnoreCase(recipes);
    }
}
