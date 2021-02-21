package pl.mvwojcik.label;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import pl.mvwojcik.model.BaseModel;

//@Entity
//@Table(name = "LABELS")
@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Label extends BaseModel {
    private String label;

}
