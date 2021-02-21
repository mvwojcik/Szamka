package pl.mvwojcik.recipe;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import pl.mvwojcik.model.BaseModel;
import pl.mvwojcik.plan.DietPlanRecipeEntry;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "RECIPES")
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
public class Recipe extends BaseModel {

    private String name;

    private String shortDescription;

    private String description;

    private String imageUrl;

    private Double rating;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
    private Set<RecipeIngredient> recipeIngredients;

    @JsonIgnore
    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
    private Set<DietPlanRecipeEntry> dietPlanRecipes;
}
