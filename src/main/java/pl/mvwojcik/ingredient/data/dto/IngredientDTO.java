package pl.mvwojcik.ingredient.data.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pl.mvwojcik.allergen.data.AllergenDTO;
import pl.mvwojcik.vitamins.data.dto.VitaminInIngredientDTO;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@ToString
public class IngredientDTO {

    private Long id;

    private String name;

    private Integer kcal;

    private Double proteins;

    //TODO div on carbs / sugars
    private Double carbohydrates;

    //TODO div on saturated/unsaturated
    private Double fat;

    private List<AllergenDTO> allergens;

    private List<VitaminInIngredientDTO> vitamins;

    public void setVitamins(List<VitaminInIngredientDTO> vitamins) {
        this.vitamins = vitamins;
    }

    @JsonIgnore
    public Set<String> getAllergensNames() {
        return allergens.stream().map(AllergenDTO::getName).collect(Collectors.toSet());
    }

    @JsonIgnore
    public Set<String> getVitaminsNames() {
        return vitamins.stream().map(VitaminInIngredientDTO::getName)
                .collect(Collectors.toSet());

    }
}
