package pl.mvwojcik.ingredient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.mvwojcik.allergen.AllergenDTO;

import java.util.List;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class IngredientDTO {

    private Integer id;

    private String name;

    private Integer kcal;

    private Double proteins;

    //TODO div on carbs / sugars
    private Double carbohydrates;

    //TODO div on saturated/unsaturated
    private Double fat;

    private List<AllergenDTO> allergens;

}
