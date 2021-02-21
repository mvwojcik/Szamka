package pl.mvwojcik.ingredient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import pl.mvwojcik.allergen.Allergen;
import pl.mvwojcik.model.BaseModel;
import pl.mvwojcik.plan.DietPlanIngredientEntry;
import pl.mvwojcik.recipe.RecipeIngredient;
import pl.mvwojcik.model.Unit;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "INGERDIENTS")
@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Ingredient extends BaseModel {

    private String name;

    private Integer kcal;

    private Double proteins;

    //TODO div on carbs / sugars
    private Double carbohydrates;


    //TODO div on saturated/unsaturated
    private Double fat;

    @Enumerated(EnumType.STRING)
    private Unit unit;

    @ManyToMany
    @JoinTable(
            name = "ingredient_allergen",
            joinColumns = @JoinColumn(name = "ingredient_id"),
            inverseJoinColumns = @JoinColumn(name = "allergen_id")
    )
    private List<Allergen> allergens;

    @JsonIgnore
    @OneToMany(mappedBy = "ingredient", cascade = CascadeType.ALL)
    private Set<RecipeIngredient> recipeIngredients;

    @JsonIgnore
    @OneToMany(mappedBy = "ingredient", cascade = CascadeType.ALL)
    private Set<DietPlanIngredientEntry> dietPlanIngredientEntries;
}
