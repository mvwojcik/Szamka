package pl.mvwojcik.vitamins;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.mvwojcik.model.Unit;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VitaminDTO {
    private String name;
    private VitaminType type;
    private Unit scale;
    private Double dailyRequirement;
}
