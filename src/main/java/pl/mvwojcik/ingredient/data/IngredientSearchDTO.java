package pl.mvwojcik.ingredient.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class IngredientSearchDTO {
    List<String> skipAllergens;
}
