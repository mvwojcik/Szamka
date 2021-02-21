package pl.mvwojcik.ingredient;

import org.springframework.stereotype.Service;
import pl.mvwojcik.allergen.Allergen;
import pl.mvwojcik.allergen.AllergenDTO;
import pl.mvwojcik.allergen.AllergensRepository;
import pl.mvwojcik.utils.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
public final class IngredientsService {
    private final AllergensRepository allergensRepository;
    private final IngredientsRepository ingredientsRepository;

    public IngredientsService(AllergensRepository allergensRepository, IngredientsRepository ingredientsRepository) {
        this.allergensRepository = allergensRepository;
        this.ingredientsRepository = ingredientsRepository;
    }

    public List<Ingredient> getAll() {
        return ingredientsRepository.findAll();
    }

    public Ingredient save(IngredientDTO ingredientDTO) {
        //VALIDATION
        Ingredient ingredient = Mapper.mapIngredientDTOToIngredient(ingredientDTO,
                                                                    findAllergensForIngredient(ingredientDTO));
        if (ingredientDTO.getAllergens()
                         .size() != ingredient.getAllergens()
                                              .size()) {
            throw new RuntimeException("Allergen not found");
        }
        return ingredientsRepository.save(ingredient);
    }

    private List<Allergen> findAllergensForIngredient(IngredientDTO ingredientDTO) {
        return allergensRepository.findByNameInIgnoreCase(ingredientDTO.getAllergens()
                                                                       .stream()
                                                                       .map(AllergenDTO::getName)
                                                                       .collect(Collectors.toSet()));
    }


    public void delete(Integer id) {
        // check if any recipe has this ingredient inside
        ingredientsRepository.deleteById(id);
    }
}
