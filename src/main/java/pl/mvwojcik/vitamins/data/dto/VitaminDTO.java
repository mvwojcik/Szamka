package pl.mvwojcik.vitamins.data.dto;

import lombok.*;
import pl.mvwojcik.model.Unit;
import pl.mvwojcik.vitamins.data.VitaminType;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class VitaminDTO {
    private String name;
    private VitaminType type;
    private Unit scale;
    private Double dailyRequirement;
}
