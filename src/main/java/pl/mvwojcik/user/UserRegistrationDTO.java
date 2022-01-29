package pl.mvwojcik.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import pl.mvwojcik.allergen.data.AllergenDTO;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class UserRegistrationDTO {
    private String username;

    private String password;

    private Double weight;

    private Integer height;

    private Integer dailyKcal;

    private Integer activityLevel;

    private Set<AllergenDTO> allergens;

    @JsonIgnore
    public Set<String> getAllergenNames() {
        return allergens.stream().map(AllergenDTO::getName).collect(Collectors.toSet());
    }
}
