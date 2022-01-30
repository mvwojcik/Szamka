package pl.mvwojcik.plan.data.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pl.mvwojcik.plan.MealTime;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class DietPlanIngredientId implements Serializable {

    private Long dietPlan;
    private Long ingredient;
    private String mealTime;


}
