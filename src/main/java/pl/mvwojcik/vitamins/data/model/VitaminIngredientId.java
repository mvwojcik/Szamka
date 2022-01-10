package pl.mvwojcik.vitamins.data.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class VitaminIngredientId implements Serializable {
    private Long vitaminId;
    private Long ingredientId;

}