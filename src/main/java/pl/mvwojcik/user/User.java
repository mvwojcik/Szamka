package pl.mvwojcik.user;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pl.mvwojcik.allergen.data.Allergen;
import pl.mvwojcik.ingredient.data.model.Ingredient;
import pl.mvwojcik.plan.data.model.DietPlan;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "app_users")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(generator = "user_generation", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "user_generation", sequenceName = "user_seq", allocationSize = 5, initialValue = 20)
    @Column(name = "id", updatable = false, unique = true)
    private Long id;

    private String username;

    private Double weight;

    private Integer height;

    private Integer dailyKcal;

    private Integer activityLevel;

    @ManyToMany
    @JoinTable(
            name = "user_diet",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "dietPlan_id")
    )
    private Set<DietPlan> dietPlans;

    @ManyToMany
    @JoinTable(
            name = "users_allergen",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "allergen_id")
    )
    private Set<Allergen> allergens;

    public void addPlan(DietPlan dietPlan) {
        this.dietPlans.add(dietPlan);
    }
}
