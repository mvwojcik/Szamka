package pl.mvwojcik.plan.data.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pl.mvwojcik.ingredient.data.model.Ingredient;
import pl.mvwojcik.plan.MealTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor
@IdClass(DietPlanIngredientId.class)
@EqualsAndHashCode
public class DietPlanIngredient implements Serializable {


    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    @Id
    private Ingredient ingredient;

    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    @Id
    private DietPlan dietPlan;

    private Double amount;
    @Id
    private String mealTime;

    public DietPlanIngredient(MealTime mealTime, Double amount, Ingredient ingredient) {
        this.mealTime = mealTime.name();
        this.ingredient = ingredient;
        this.amount = amount;
    }

    public DietPlanIngredient(MealTime mealTime, Double amount, Ingredient ingredient, DietPlan dietPlan) {
        this.mealTime = mealTime.name();
        this.ingredient = ingredient;
        this.dietPlan = dietPlan;
        this.amount = amount;
    }

    public DietPlanIngredient setDietPlan(DietPlan dietPlan) {
        this.dietPlan = dietPlan;
        return this;
    }
}
