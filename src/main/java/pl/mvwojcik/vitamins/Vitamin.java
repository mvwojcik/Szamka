package pl.mvwojcik.vitamins;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.mvwojcik.model.BaseModel;
import pl.mvwojcik.model.Unit;

import javax.persistence.Entity;
import javax.persistence.Table;

//@Entity
//@Table(name = "VITAMIN")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Vitamin {
    private String name;
    private VitaminType type;
    private Unit scale;
    private Double dailyRequirement;
}
