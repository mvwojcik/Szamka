package pl.mvwojcik.vitamins.data;

import pl.mvwojcik.vitamins.data.dto.VitaminDTO;
import pl.mvwojcik.vitamins.data.dto.VitaminInIngredientDTO;
import pl.mvwojcik.vitamins.data.model.Vitamin;
import pl.mvwojcik.vitamins.data.model.VitaminInIngredient;

public class VitaminMapper {
    public static VitaminInIngredientDTO mapToDTO(VitaminInIngredient vitaminInIngredient) {
        return VitaminInIngredientDTO
                .builder()
                .vitamin(mapToDTO(vitaminInIngredient.getVitamin()))
                .amount(vitaminInIngredient.getAmount())
                .build();
    }

    public static VitaminDTO mapToDTO(Vitamin vitamin) {
        return VitaminDTO
                .builder()
                .name(vitamin.getName())
                .dailyRequirement(vitamin.getDailyRequirement())
                .scale(vitamin.getScale())
                .type(vitamin.getType())
                .build();
    }


    public static VitaminInIngredientDTO mapEmptyVitamin(Vitamin vitamin) {
        return new VitaminInIngredientDTO(VitaminMapper.mapToDTO(vitamin), 0D);
    }
}
