package pl.mvwojcik.plan;


import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.Tuple3;
import io.vavr.control.Either;
import io.vavr.control.Option;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import pl.mvwojcik.comunication.ContentResponse;
import pl.mvwojcik.comunication.EmptyResponse;
import pl.mvwojcik.comunication.ServiceResponse;
import pl.mvwojcik.error.ErrorConstants;
import pl.mvwojcik.error.ErrorResponse;
import pl.mvwojcik.ingredient.IngredientsService;
import pl.mvwojcik.ingredient.data.model.Ingredient;
import pl.mvwojcik.plan.data.DietPlanMapper;
import pl.mvwojcik.plan.data.DietPlanRepository;
import pl.mvwojcik.plan.data.dto.DietPlanDTO;
import pl.mvwojcik.plan.data.model.DietPlan;
import pl.mvwojcik.plan.data.model.DietPlanProjection;
import pl.mvwojcik.recipe.RecipesService;
import pl.mvwojcik.recipe.data.model.Recipe;
import pl.mvwojcik.vitamins.VitaminsService;

import java.util.List;
import java.util.function.Function;

@Service
public class DietPlanService {

    private final DietPlanRepository repository;
    private final DietPlanValidator validator;
    private final IngredientsService ingredientsService;
    private final RecipesService recipesService;
    private final VitaminsService vitaminsService;

    public DietPlanService(DietPlanRepository repository, DietPlanValidator validator, IngredientsService ingredientsService, RecipesService recipesService, VitaminsService vitaminsService) {
        this.repository = repository;
        this.validator = validator;
        this.ingredientsService = ingredientsService;
        this.recipesService = recipesService;
        this.vitaminsService = vitaminsService;
    }

    public Page<DietPlanProjection> getAll(int page) {
        return repository.findAllBy(PageRequest.of(page,10));
    }

    public ServiceResponse findById(Long id) {
        return Option.ofOptional(repository.findById(id))
                .toEither(ErrorConstants.dietPlanNotFound(id))
                .map(d -> DietPlanMapper.mapToDTO(d, vitaminsService.getAll()))
                .map(diet -> new ContentResponse(HttpStatus.OK, diet))
                .fold(Function.identity(), Function.identity());
    }


    public ServiceResponse create(DietPlanDTO dietPlan) {
        return validator.runAllergenValidation(dietPlan)
                .toEither()
                .flatMap(this::applyIngredientsAndRecipes)
                .map(tuple -> DietPlanMapper.map(tuple._1, tuple._2))
                .map(this::save)
                .map(d -> DietPlanMapper.mapToDTO(d, vitaminsService.getAll()))
                .fold(Function.identity(), diet -> new ContentResponse(HttpStatus.CREATED, diet));
    }

    private DietPlan save(DietPlan dietPlan) {
        System.out.println(dietPlan);
        return repository.save(dietPlan);
    }


    public ServiceResponse delete(Long id) {
        return Option.ofOptional(repository.findById(id))
                .toEither(ErrorConstants.dietPlanNotFound(id))
                .map(this::deleteDietPlan)
                .fold(Function.identity(), dietPlan -> new EmptyResponse(HttpStatus.NO_CONTENT));
    }

    public DietPlan deleteDietPlan(DietPlan dietPlan) {
        repository.delete(dietPlan);
        return dietPlan;
    }

    private Either<ErrorResponse, Tuple2<DietPlanDTO, List<Ingredient>>> applyIngredientsAndRecipes(DietPlanDTO dietPlanDTO) {
        List<Ingredient> allIngredientsIn = ingredientsService.findAllIngredientsIn(dietPlanDTO.getIngredientsName());
        Tuple2<DietPlanDTO, List<Ingredient>> recipeTuple = Tuple.of(dietPlanDTO, allIngredientsIn);
        return (recipeTuple._2.size() != dietPlanDTO.getIngredientsName().size())?
                Either.left(ErrorConstants.ingredientAmountNotEquals()) :
                Either.right(recipeTuple);
    }

}
