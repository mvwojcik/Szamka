package pl.mvwojcik.allergen.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pl.mvwojcik.ingredient.data.model.Ingredient;
import pl.mvwojcik.user.User;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.PreRemove;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "ALLERGENS")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public final class Allergen {

    @Id
    @GeneratedValue(generator = "allergen_generator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "allergen_generator", sequenceName = "allergen_seq", allocationSize = 5, initialValue = 20)
    @Column(name = "id", updatable = false, unique = true)
    private Long id;

    private String name;

    @ToString.Exclude
    @JsonIgnore
    @ManyToMany(mappedBy = "allergens")
    private Set<Ingredient> ingredients;

    @ToString.Exclude
    @JsonIgnore
    @ManyToMany(mappedBy = "allergens")
    private Set<User> users;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Allergen allergen = (Allergen) o;
        return Objects.equals(id, allergen.id) && Objects.equals(name, allergen.name);
    }

    public void removeIngredient(Ingredient ingredient) {
        ingredients.remove(ingredient);
    }

    @PreRemove
    private void removeAllergensFromIngredients() {
        ingredients.forEach(ingredient -> ingredient.removeAllergen(this));
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Allergen{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
