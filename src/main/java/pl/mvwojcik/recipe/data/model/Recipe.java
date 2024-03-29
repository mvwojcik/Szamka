package pl.mvwojcik.recipe.data.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedSubgraph;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "RECIPES")
@NoArgsConstructor
@Getter
@NamedEntityGraph(
        name = "graph.recipe",
        attributeNodes = {
                @NamedAttributeNode(value = "recipeIngredients", subgraph = "subgraph.recipeIngredient")},
        subgraphs = {
                @NamedSubgraph(name = "subgraph.recipeIngredient", attributeNodes = {
                        @NamedAttributeNode(value = "ingredient", subgraph = "subgraph.ingredient")}),
                @NamedSubgraph(name = "subgraph.ingredient", attributeNodes = {
                        @NamedAttributeNode(value = "allergens"),
                        @NamedAttributeNode(value = "vitamins", subgraph = "subgraph.vitamins")}),
                @NamedSubgraph(name = "subgraph.vitamins", attributeNodes =
                @NamedAttributeNode(value = "vitamin"))
        }
)
public class Recipe {
    @Id
    @GeneratedValue(generator = "recipe_generator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "recipe_generator", sequenceName = "recipe_seq", allocationSize = 5, initialValue = 20)
    @Column(name = "id", updatable = false, unique = true)
    private Long id;

    private String name;

    private String shortDescription;

    private String description;

    private String imageUrl;

    private Double rating;

    @OneToMany(mappedBy = "recipe", orphanRemoval = true)
    private Set<RecipeIngredient> recipeIngredients;



    public Recipe(String name, String shortDescription, String description,
                  String imageUrl, Double rating, Set<RecipeIngredient> recipeIngredients) {
        this.name = name;
        this.shortDescription = shortDescription;
        this.description = description;
        this.imageUrl = imageUrl;
        this.rating = rating;
        this.recipeIngredients = recipeIngredients.stream()
                .map(recipeIngredient -> new RecipeIngredient(recipeIngredient.getIngredient(), this, recipeIngredient.getAmount()))
                .collect(Collectors.toSet());
    }
    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String name;
        private String shortDescription;
        private String description;
        private String imageUrl;
        private Double rating;
        private Set<RecipeIngredient> recipeIngredients;

        public Builder() {
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder shortDescription(String shortDescription) {
            this.shortDescription = shortDescription;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder imageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }

        public Builder rating(Double rating) {
            this.rating = rating;
            return this;
        }

        public Builder recipeIngredients(Set<RecipeIngredient> recipeIngredients) {
            this.recipeIngredients = recipeIngredients;
            return this;
        }

        public Recipe build() {
            return new Recipe(name, shortDescription, description, imageUrl, rating, recipeIngredients);
        }
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", shortDescription='" + shortDescription + '\'' +
                ", description='" + description + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", rating=" + rating +
                ", recipeIngredients=" + recipeIngredients +
                '}';
    }
}
