package pl.mvwojcik.plan.data.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.mvwojcik.user.User;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedSubgraph;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
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
                @NamedAttributeNode(value = "recipes", subgraph = "subgraph.dietPlanRecipes"),
                @NamedAttributeNode(value = "ingredients", subgraph = "subgraph.dietPlanIngredients")
        },
        subgraphs = {
                @NamedSubgraph(name = "subgraph.dietPlanRecipes", attributeNodes = {
                        @NamedAttributeNode(value = "recipe", subgraph = "subgraph.recipes")}),
                @NamedSubgraph(name = "subgraph.recipes", attributeNodes = {
                        @NamedAttributeNode(value = "recipeIngredients", subgraph = "subgraph.recipeIngredient")
                }),
                @NamedSubgraph(name = "subgraph.recipeIngredient", attributeNodes = {
                        @NamedAttributeNode(value = "ingredient", subgraph = "subgraph.ingredient")}),
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

    @OneToMany(mappedBy = "dietPlan", orphanRemoval = true, cascade = CascadeType.ALL)
    private Set<DietPlanRecipe> recipes;

    @OneToMany(mappedBy = "dietPlan", orphanRemoval = true, cascade = CascadeType.ALL)
    private Set<DietPlanIngredient> ingredients;

    private DietAccessLevel accessType;

    @ManyToOne
    private User user;


    public DietPlan(Long id, String name, String description, Set<DietPlanRecipe> recipes, Set<DietPlanIngredient> ingredients) {
        this.id = id;
        this.name = name;
        this.description = description;

        this.recipes = recipes
                .stream()
                .map(r -> new DietPlanRecipe(r.getMealTime(), r.getAmount(), r.getRecipe(), this))
                .collect(Collectors.toSet());
        this.ingredients = ingredients.stream()
                .map(i -> new DietPlanIngredient(i.getMealTime(), i.getAmount(), i.getIngredient(), this))
                .collect(Collectors.toSet());
    }

    public DietPlan(Long id, String name, String description, Set<DietPlanRecipe> recipes, Set<DietPlanIngredient> ingredients, DietAccessLevel dietAccessLevel, User user) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.user = user;
        this.accessType = dietAccessLevel;
        this.recipes = recipes
                .stream()
                .map(r -> new DietPlanRecipe(r.getMealTime(), r.getAmount(), r.getRecipe(), this))
                .collect(Collectors.toSet());
        this.ingredients = ingredients.stream()
                .map(i -> new DietPlanIngredient(i.getMealTime(), i.getAmount(), i.getIngredient(), this))
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
                ", recipes=" + recipes +
                ", ingredients=" + ingredients +
                '}';
    }

}

//    @ManyToMany()
//    @JoinTable(name = "dietPlan_user",
//    joinColumns = { @JoinColumn(name = "fk_dietplan")},
//    inverseJoinColumns = { @JoinColumn(name = "fk_user")})
//    private Set<User> users;
