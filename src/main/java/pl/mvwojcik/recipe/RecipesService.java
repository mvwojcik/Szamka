package pl.mvwojcik.recipe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.mvwojcik.utils.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
public final class RecipesService {
    private final RecipeRepository recipeRepository;

    @Autowired
    public RecipesService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public final List<RecipeDTO> findAll() {
        return recipeRepository.findAll()
                               .stream()
                               .map(Mapper::mapRecipeToRecipeDTO)
                               .collect(Collectors.toList());
    }

    public RecipeDTO create(RecipeDTO recipeDTO) {
//        return recipeRepository.save(recipeDTO);
        return null;
    }
}
