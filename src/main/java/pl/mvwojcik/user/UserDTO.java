package pl.mvwojcik.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import pl.mvwojcik.allergen.data.AllergenDTO;

import java.util.Set;

@Builder
@AllArgsConstructor
@Getter
public class UserDTO {
    private String username;

    private Double weight;

    private Integer height;

    private Integer dailyKcal;

    private Integer activityLevel;

    private Set<AllergenDTO> allergens;
}
