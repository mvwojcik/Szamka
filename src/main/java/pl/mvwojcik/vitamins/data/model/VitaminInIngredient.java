package pl.mvwojcik.vitamins.data.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pl.mvwojcik.ingredient.data.model.Ingredient;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import java.io.Serializable;

@Entity
@NoArgsConstructor
@Getter
@EqualsAndHashCode
@ToString(exclude = {"ingredient"})
public class VitaminInIngredient implements Serializable {

    @EmbeddedId
    private VitaminIngredientId id = new VitaminIngredientId();

    @ManyToOne
    @MapsId("vitaminId")
    private Vitamin vitamin;

    @ManyToOne
    @MapsId("ingredientId")
    @JsonIgnore
    private Ingredient ingredient;

    private Double amount;

    public VitaminInIngredient(Vitamin vitamin, Double amount) {
        this.vitamin = vitamin;
        this.amount = amount;
    }

    public void setIngredient(Ingredient ingredient) {
        System.out.println("SETTING INGREDIENT");
        this.ingredient = ingredient;
    }
}