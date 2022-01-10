package pl.mvwojcik.plan.data.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.mvwojcik.plan.MealTime;
import pl.mvwojcik.recipe.data.model.Recipe;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor
public class DietPlanRecipe implements Serializable {

    @EmbeddedId
    private DietPlanRecipeId id = new DietPlanRecipeId();

    @Enumerated(EnumType.STRING)
    private MealTime mealTime;

    private Double amount;

    @ManyToOne(cascade = CascadeType.ALL)
    @MapsId("recipeId")
    private Recipe recipe;


    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @MapsId("dietPlanId")
    private DietPlan dietPlan;


    public DietPlanRecipe(MealTime mealTime, Double amount, Recipe recipe) {
        this.mealTime = mealTime;
        this.amount = amount;
        this.recipe = recipe;
        recipe.addToDietPlans(this);
    }

    public DietPlanRecipe(MealTime mealTime, Double amount, Recipe recipe, DietPlan dietPlan) {
        this.mealTime = mealTime;
        this.amount = amount;
        this.recipe = recipe;
        this.dietPlan = dietPlan;
        recipe.addToDietPlans(this);
    }

    public void setDietPlan(DietPlan dietPlan) {
        this.dietPlan = dietPlan;
    }
//
//    @PreRemove
//    public void cleanup() {
//        dietPlan.getRecipes().remove(this);
//    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DietPlanRecipe that = (DietPlanRecipe) o;
        return Objects.equals(id, that.id) && mealTime == that.mealTime && Objects.equals(amount, that.amount) && Objects.equals(recipe, that.recipe) && Objects.equals(dietPlan, that.dietPlan);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, mealTime, amount, recipe, dietPlan);
    }

    @Override
    public String toString() {
        return "DietPlanRecipe{" +
                "id=" + id +
                ", mealTime=" + mealTime +
                ", amount=" + amount +
                ", recipe=" + recipe +
                '}';
    }
}
