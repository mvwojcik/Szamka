package pl.mvwojcik.recipe;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Builder
@Getter
public class RecipeDTO {

    private Integer id;

    private String name;

    private String shortDescription;

    private String description;

    private String imageUrl;

    private Double rating;

    private Set<RecipeIngredientDTO> recipeIngredients;


}
