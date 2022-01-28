package pl.mvwojcik.plan.data.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.mvwojcik.ingredient.data.dto.IngredientDTO;

@AllArgsConstructor
@Getter
public class IngredientAmount {
    private IngredientDTO ingredient;
    private Double amount;
}