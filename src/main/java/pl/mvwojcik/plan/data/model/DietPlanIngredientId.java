package pl.mvwojcik.plan.data.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class DietPlanIngredientId implements Serializable {

    private Long dietPlanId;
    private Long ingredientId;


}
