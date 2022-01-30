package pl.mvwojcik.plan.data.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.mvwojcik.plan.MealTime;
import pl.mvwojcik.user.User;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedSubgraph;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "DIETPLANS")
@Getter
@Builder
@NoArgsConstructor
@NamedEntityGraph(
        name = "graph.dietplan",
        attributeNodes = {
                @NamedAttributeNode(value = "ingredients", subgraph = "subgraph.dietPlanIngredients")
        },
        subgraphs = {
                @NamedSubgraph(name = "subgraph.ingredient", attributeNodes = {
                        @NamedAttributeNode(value = "allergens"),
                        @NamedAttributeNode(value = "vitamins", subgraph = "subgraph.vitamins")}),
                @NamedSubgraph(name = "subgraph.vitamins", attributeNodes = {
                        @NamedAttributeNode(value = "vitamin")}),
                @NamedSubgraph(name = "subgraph.dietPlanIngredients", attributeNodes = {
                        @NamedAttributeNode(value = "ingredient", subgraph = "subgraph.ingredient")
                })
        })
public class DietPlan {

    @Id
    @GeneratedValue(generator = "dietplan_generator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "dietplan_generator", sequenceName = "dietplan_seq", allocationSize = 5, initialValue = 20)
    @Column(name = "id", updatable = false, unique = true)
    private Long id;

    private String name;

    private String description;

    @OneToMany(mappedBy = "dietPlan", orphanRemoval = true, cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    private Set<DietPlanIngredient> ingredients;

    private DietAccessLevel accessType;

    @ManyToMany(mappedBy = "dietPlans")
    private Set<User> users;


    public DietPlan(Long id, String name, String description, Set<DietPlanIngredient> ingredients, DietAccessLevel dietAccessLevel, Set<User> users) {
        System.out.println("Use me");
        this.id = id;
        this.name = name;
        this.description = description;
        this.users = users;
        this.accessType = dietAccessLevel;
        this.ingredients = ingredients.stream()
                .map(i -> i.setDietPlan(this))
                .collect(Collectors.toSet());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DietPlan dietPlan = (DietPlan) o;
        return Objects.equals(id, dietPlan.id) && Objects.equals(name, dietPlan.name) && Objects.equals(description, dietPlan.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description);
    }

    @Override
    public String toString() {
        return "DietPlan{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", ingredients=" + ingredients +
                '}';
    }

}

//    @ManyToMany()
//    @JoinTable(name = "dietPlan_user",
//    joinColumns = { @JoinColumn(name = "fk_dietplan")},
//    inverseJoinColumns = { @JoinColumn(name = "fk_user")})
//    private Set<User> users;
