package pl.mvwojcik.vitamins.data.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@ToString
public class VitaminInIngredientDTO {

    private VitaminDTO vitamin;

    private Double amount;

    @JsonIgnore
    public final String getName() {
        return vitamin.getName();
    }

}
