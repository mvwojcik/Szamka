package pl.mvwojcik.vitamins.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
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
