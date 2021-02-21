package pl.mvwojcik.allergen;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public final class AllergenDTO {
    private String name;
    private Integer id;
}
