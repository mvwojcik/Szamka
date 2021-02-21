package pl.mvwojcik.allergen;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import pl.mvwojcik.model.BaseModel;
import pl.mvwojcik.ingredient.Ingredient;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "ALLERGENS")
@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public final class Allergen extends BaseModel {
    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "allergens")
    private Set<Ingredient> ingredients;
}
