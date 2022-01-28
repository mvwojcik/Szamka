package pl.mvwojcik.plan.data.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import pl.mvwojcik.plan.MealTime;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class DietPlanIngredientId implements Serializable {

    private Long dietPlanId;
    private Long ingredientId;


}
