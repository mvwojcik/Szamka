package pl.mvwojcik.recipe;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import pl.mvwojcik.ingredient.Ingredient;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Entity
@Getter
public class RecipeIngredient implements Serializable {

    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private Ingredient ingredient;

    @JsonIgnore
    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private Recipe recipe;

    private Double amount;
}