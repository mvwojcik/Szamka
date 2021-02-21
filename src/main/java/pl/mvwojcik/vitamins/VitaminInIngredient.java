package pl.mvwojcik.vitamins;

import com.fasterxml.jackson.annotation.JsonIgnore;
import pl.mvwojcik.ingredient.Ingredient;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

//@Entity
public class VitaminInIngredient {

//    @Id
//    @ManyToOne(cascade = CascadeType.ALL)//{CascadeType.MERGE,CascadeType.REFRESH})//
//    @JoinColumn
    private Vitamin vitamin;

//    @JsonIgnore
//    @Id
//    @ManyToOne(cascade = CascadeType.ALL)//{CascadeType.MERGE,CascadeType.REFRESH})//
//    @JoinColumn
    private Ingredient ingredient;

    private Double amount;
}
