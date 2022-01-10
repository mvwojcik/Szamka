package pl.mvwojcik.recipe.data.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class RecipeIngredientId implements Serializable {

    private Long recipeId;
    private Long ingredientId;

}
