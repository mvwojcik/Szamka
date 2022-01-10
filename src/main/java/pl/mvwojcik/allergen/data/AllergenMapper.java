package pl.mvwojcik.allergen.data;

public class AllergenMapper {

    public static AllergenDTO mapAllergenToAllergenDTO(Allergen allergen) {
        return AllergenDTO.builder()
                          .id(allergen.getId())
                          .name(allergen.getName())
                          .build();
    }

    public static Allergen mapAllergenDTOToAllergen(AllergenDTO allergen) {
        return Allergen.builder()
                       .id(allergen.getId())
                       .name(allergen.getName())
                       .build();
    }
}
