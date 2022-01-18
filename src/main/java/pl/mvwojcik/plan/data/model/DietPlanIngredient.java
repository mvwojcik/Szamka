package pl.mvwojcik.plan.data.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.mvwojcik.ingredient.data.model.Ingredient;
import pl.mvwojcik.plan.MealTime;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor
public class DietPlanIngredient implements Serializable {

    @EmbeddedId
    private DietPlanIngredientId id = new DietPlanIngredientId();

    @ManyToOne(cascade = CascadeType.ALL)
    @MapsId("ingredientId")
    private Ingredient ingredient;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @MapsId("dietPlanId")
    private DietPlan dietPlan;

    private Double amount;

    @Enumerated(EnumType.STRING)
    private MealTime mealTime;

    public DietPlanIngredient(MealTime mealTime, Double amount, Ingredient ingredient) {
        this.mealTime = mealTime;
        this.ingredient = ingredient;
        this.amount = amount;
    }

    public DietPlanIngredient(MealTime mealTime, Double amount, Ingredient ingredient, DietPlan dietPlan) {
        this.mealTime = mealTime;
        this.ingredient = ingredient;
        this.dietPlan = dietPlan;
        this.amount = amount;
    }

    public void setDietPlan(DietPlan dietPlan) {
        this.dietPlan = dietPlan;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DietPlanIngredient that = (DietPlanIngredient) o;
        return Objects.equals(id, that.id) && Objects.equals(ingredient, that.ingredient) && Objects.equals(dietPlan, that.dietPlan) && Objects.equals(amount, that.amount) && mealTime == that.mealTime;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ingredient, dietPlan, amount, mealTime);
    }

    @Override
    public String toString() {
        return "DietPlanIngredient{" +
                "id=" + id +
                ", ingredient=" + ingredient +
                ", amount=" + amount +
                ", mealTime=" + mealTime +
                '}';
    }
}
