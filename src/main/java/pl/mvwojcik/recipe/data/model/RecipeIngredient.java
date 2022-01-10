package pl.mvwojcik.recipe.data.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.mvwojcik.ingredient.data.model.Ingredient;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class RecipeIngredient implements Serializable {

    @EmbeddedId
    private RecipeIngredientId id = new RecipeIngredientId();

    @ManyToOne(cascade = CascadeType.ALL)
    @MapsId("ingredientId")
    private Ingredient ingredient;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @MapsId("recipeId")
    private Recipe recipe;

    private Double amount;

    public RecipeIngredient(Ingredient ingredient, Recipe recipe, Double amount) {
        this.ingredient = ingredient;
        this.recipe = recipe;
        this.amount = amount;
    }

    public RecipeIngredient(Double amount, Ingredient ingredient) {
        this.ingredient = ingredient;
        this.amount = amount;
    }

    @PreRemove
    public void cleanup() {
        recipe.getRecipeIngredients().remove(this);
        ingredient.getRecipes().remove(this);
    }


    public String getIngredientName() {
        return ingredient.getName();
    }

    @Override
    public String toString() {
        return "RecipeIngredient{" +
                "id=" + id +
                ", ingredient=" + ingredient +
                ", amount=" + amount +
                '}';
    }
}