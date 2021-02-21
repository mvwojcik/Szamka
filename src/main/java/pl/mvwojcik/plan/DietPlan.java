package pl.mvwojcik.plan;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.mvwojcik.model.BaseModel;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "DIETPLANS")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DietPlan extends BaseModel {
    private String name;
    private String description;
    @OneToMany(mappedBy = "dietPlan", cascade = CascadeType.ALL)
    private List<DietPlanRecipeEntry> recipes;
    @OneToMany(mappedBy = "dietPlan", cascade = CascadeType.ALL)
    private List<DietPlanIngredientEntry> ingredients;
}
