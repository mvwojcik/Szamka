package pl.mvwojcik.ingredient.data;

import pl.mvwojcik.allergen.data.Allergen;
import pl.mvwojcik.allergen.data.AllergenMapper;
import pl.mvwojcik.ingredient.data.dto.IngredientDTO;
import pl.mvwojcik.ingredient.data.model.Ingredient;
import pl.mvwojcik.vitamins.data.VitaminMapper;
import pl.mvwojcik.vitamins.data.dto.VitaminInIngredientDTO;
import pl.mvwojcik.vitamins.data.model.Vitamin;
import pl.mvwojcik.vitamins.data.model.VitaminInIngredient;

import java.util.List;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public class IngredientMapper {
    public static Ingredient mapIngredientDTOToIngredient(IngredientDTO ingredientDTO, Set<Allergen> allergens, Set<VitaminInIngredient> vitamins) {
        return Ingredient.builder()
                .name(ingredientDTO.getName())
                .kcal(ingredientDTO.getKcal())
                .proteins(ingredientDTO.getProteins())
                .carbohydrates(ingredientDTO.getCarbohydrates())
                .fat(ingredientDTO.getFat())
                .allergens(allergens)
                .vitamins(vitamins)
                .build();
    }

    public static IngredientDTO mapIngredientToIngredientDTO(Ingredient ingredient) {
        return mapBaseIngredient(ingredient)
                .vitamins(ingredient.getVitamins().stream()
                        .map(VitaminMapper::mapToDTO)
                        .collect(Collectors.toList()))
                .build();
    }

    public static IngredientDTO mapIngredientToIngredientDTOWithoutVitamins(Ingredient ingredient) {
        return mapBaseIngredient(ingredient)
                .vitamins(List.of())
                .build();
    }


    static BiFunction<VitaminInIngredientDTO, VitaminInIngredientDTO, Boolean> compareVitaminNames = (v, vv) -> v.getVitamin().getName().equalsIgnoreCase(vv.getVitamin().getName());
    static BiFunction<Set<VitaminInIngredient>, VitaminInIngredientDTO, VitaminInIngredientDTO> findMissingVitamins = (vitaminList, vitamin) -> vitaminList
            .stream()
            .map(VitaminMapper::mapToDTO)
            .filter(v -> compareVitaminNames.apply(vitamin, v))
            .findAny()
            .orElse(vitamin);

    public static IngredientDTO mapIngredientToIngredientDTO(Ingredient ingredient, Set<Vitamin> vitaminList) {
        return mapBaseIngredient(ingredient)
                .vitamins(vitaminList
                        .stream()
                        .map(VitaminMapper::mapEmptyVitamin)
                        .map(v -> findMissingVitamins.apply(ingredient.getVitamins(), v))
                        .collect(Collectors.toList()))
                .build();
    }

    private static IngredientDTO.IngredientDTOBuilder mapBaseIngredient(Ingredient ingredient) {
        return IngredientDTO.builder()
                .id(ingredient.getId())
                .name(ingredient.getName())
                .kcal(ingredient.getKcal())
                .proteins(ingredient.getProteins())
                .carbohydrates(ingredient.getCarbohydrates())
                .fat(ingredient.getFat())
                .allergens(ingredient.getAllergens()
                        .stream()
                        .map(AllergenMapper::mapAllergenToAllergenDTO)
                        .collect(Collectors.toList()));
    }


    static BiFunction<Set<Vitamin>, String, Vitamin> findVitaminByName = (set, name) ->
            set.stream()
                    .filter(vitamin -> vitamin.getName().equalsIgnoreCase(name))
                    .findFirst()
                    .get();

    public static Set<VitaminInIngredient> mapVitaminsDTO(IngredientDTO ingredientDTO, Set<Vitamin> vitamins) {
        return ingredientDTO
                .getVitamins()
                .stream()
                .map(v -> new VitaminInIngredient(findVitaminByName.apply(vitamins, v.getVitamin().getName()), v.getAmount()))
                .collect(Collectors.toSet());
    }
}
