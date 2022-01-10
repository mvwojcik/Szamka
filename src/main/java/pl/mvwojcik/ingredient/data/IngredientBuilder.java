package pl.mvwojcik.ingredient.data;

import pl.mvwojcik.allergen.data.Allergen;
import pl.mvwojcik.ingredient.data.dto.IngredientDTO;
import pl.mvwojcik.ingredient.data.model.Ingredient;
import pl.mvwojcik.vitamins.data.model.VitaminInIngredient;

import java.util.Set;


public class IngredientBuilder {
    private IngredientDTO ingredientDTO;
    private Set<Allergen> allergens;
    private Set<VitaminInIngredient> vitamins;

    public IngredientBuilder(IngredientDTO ingredientDTO) {
        this.ingredientDTO = ingredientDTO;
    }

    public IngredientBuilder withAllergens(Set<Allergen> allergens) {
        this.allergens = allergens;
        return this;
    }

    public IngredientBuilder withVitamins(Set<VitaminInIngredient> vitamins) {
        this.vitamins = vitamins;
        return this;
    }

    public Ingredient build() {
        return IngredientMapper.mapIngredientDTOToIngredient(ingredientDTO, allergens, vitamins);
    }


    public Set<String> getAllergensName() {
        return ingredientDTO.getAllergensNames();
    }


    public Set<String> getVitaminsName() {
        return ingredientDTO.getVitaminsNames();
    }


    public IngredientDTO getIngredientDTO() {
        return ingredientDTO;
    }
}
