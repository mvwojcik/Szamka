package pl.mvwojcik.ingredient.data.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.mvwojcik.allergen.data.Allergen;
import pl.mvwojcik.model.Unit;
import pl.mvwojcik.plan.data.model.DietPlanIngredient;
import pl.mvwojcik.recipe.data.model.RecipeIngredient;
import pl.mvwojcik.vitamins.data.model.VitaminInIngredient;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedSubgraph;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "INGREDIENTS")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@NamedEntityGraph(
        name = "graph.ingredientAllergens",
        attributeNodes = @NamedAttributeNode(value = "allergens"))
@NamedEntityGraph(
        name = "graph.ingredientVitamins",
        attributeNodes = {@NamedAttributeNode(value = "allergens"), @NamedAttributeNode(value = "vitamins", subgraph = "subgraph.vitamin")},
        subgraphs = @NamedSubgraph(name = "subgraph.vitamin", attributeNodes = @NamedAttributeNode(value = "vitamin")))
@NamedEntityGraph(
        name = "graph.ingredientRecipesVitamins",
        attributeNodes = {
                @NamedAttributeNode(value = "allergens"),
                @NamedAttributeNode(value = "vitamins", subgraph = "subgraph.vitamin"),
                @NamedAttributeNode(value = "recipes", subgraph = "subgraph.recipe")},
        subgraphs = {
                @NamedSubgraph(name = "subgraph.vitamin", attributeNodes = @NamedAttributeNode(value = "vitamin")),
                @NamedSubgraph(name = "subgraph.recipe", attributeNodes = @NamedAttributeNode(value = "recipe"))}
)
public class Ingredient {

    @Id
    @GeneratedValue(generator = "ingredient_generator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "ingredient_generator", sequenceName = "ingredient_seq", allocationSize = 5, initialValue = 50)
    @Column(name = "id", updatable = false, unique = true)
    private Long id;

    private String name;

    private Integer kcal;

    private Double proteins;

    private Double carbohydrates;

    private Double fat;

    @Enumerated(EnumType.STRING)
    private Unit unit;


    @ManyToMany
    @JoinTable(
            name = "ingredient_allergen",
            joinColumns = @JoinColumn(name = "ingredient_id"),
            inverseJoinColumns = @JoinColumn(name = "allergen_id")
    )
    private Set<Allergen> allergens;

    @OneToMany(mappedBy = "ingredient", orphanRemoval = true)
    private Set<VitaminInIngredient> vitamins;

    @OneToMany(mappedBy = "ingredient", orphanRemoval = true)
    private Set<RecipeIngredient> recipes;


    @OneToMany(mappedBy = "ingredient", orphanRemoval = true)
    private Set<DietPlanIngredient> dietPlans;


    public Ingredient(String name, Integer kcal, Double proteins, Double carbohydrates,
                      Double fat, Unit unit, Set<Allergen> allergens,
                      Set<VitaminInIngredient> vitamins) {
        this.name = name;
        this.kcal = kcal;
        this.proteins = proteins;
        this.carbohydrates = carbohydrates;
        this.fat = fat;
        this.unit = unit;
        this.allergens = allergens;
        vitamins.forEach(vitaminInIngredient -> vitaminInIngredient.setIngredient(this));
        this.vitamins = vitamins;
        this.dietPlans = new HashSet<>();
    }

    public void addToDietPlans(DietPlanIngredient dietPlanIngredient) {
        if (dietPlans == null) {
            dietPlans = new HashSet<>();
        }
        this.dietPlans.add(dietPlanIngredient);
    }


    public void removeAllergen(Allergen allergen) {
        this.allergens.remove(allergen);
    }

    @PreRemove
    public void clean() {
        allergens.forEach(allergen -> allergen.removeIngredient(this));
//        vitamins.removeAll(vitamins);
//        recipes.removeAll(recipes);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingredient that = (Ingredient) o;
        return Objects.equals(id, that.id)
                && Objects.equals(name, that.name)
                && Objects.equals(kcal, that.kcal)
                && Objects.equals(proteins, that.proteins)
                && Objects.equals(carbohydrates, that.carbohydrates)
                && Objects.equals(fat, that.fat)
                && unit == that.unit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, kcal, proteins, carbohydrates, fat, unit);
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", kcal=" + kcal +
                ", proteins=" + proteins +
                ", carbohydrates=" + carbohydrates +
                ", fat=" + fat +
                ", unit=" + unit +
                ", allergens=" + allergens +
                ", vitamins=" + vitamins +
                '}';
    }
}
