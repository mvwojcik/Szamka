package pl.mvwojcik.plan;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import pl.mvwojcik.ingredient.Ingredient;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Entity
@Getter
public class DietPlanIngredientEntry implements Serializable {

    @Enumerated(EnumType.STRING)
    private MealTime mealTime;

    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private Ingredient ingredient;

    private Double amount;

    @JsonIgnore
    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private DietPlan dietPlan;
}
