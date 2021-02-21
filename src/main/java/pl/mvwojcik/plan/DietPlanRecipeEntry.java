package pl.mvwojcik.plan;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import pl.mvwojcik.recipe.Recipe;

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
public class DietPlanRecipeEntry implements Serializable {

    @Enumerated(EnumType.STRING)
    private MealTime mealTime;

    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private Recipe recipe;

    private Double amount;

    @JsonIgnore
    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private DietPlan dietPlan;

}
