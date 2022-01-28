package pl.mvwojcik.plan.data.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import pl.mvwojcik.model.Unit;
import pl.mvwojcik.vitamins.data.VitaminType;

@Builder
@EqualsAndHashCode
@Getter
public class VitaminValue {
    private String name;
    private VitaminType type;
    private Unit scale;
    private Double dailyRequirement;
    @Setter
    private Double amount;


    public void increaseAmount(Double amountAdded) {
        amount+=amountAdded;
    }
}
