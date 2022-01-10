package pl.mvwojcik.recipe.data.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Builder
@Getter
public class RecipeDTO {

    private Long id;

    private String name;

    private String shortDescription;

    private String description;

    private String imageUrl;

    private Double rating;

    private Set<RecipeIngredientDTO> recipeIngredients;


    @JsonIgnore
    public List<String> getRecipesNames() {
        return this.getRecipeIngredients()
                .stream()
                .map(RecipeIngredientDTO::getIngredientName)
                .collect(Collectors.toList());
    }
}
