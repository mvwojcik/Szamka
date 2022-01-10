package helpers

import pl.mvwojcik.allergen.data.Allergen
import pl.mvwojcik.allergen.data.AllergenDTO
import spock.lang.Specification

class AllergenHelper extends Specification {
    static final String GLUTEN = "GLUTEN",
                        DAIRY = "DAIRY",
                        LACTOSE = "LACTOSE"

    static final Integer GLUTEN_ID = 1,
                         DAIRY_ID = 2,
                         LACTOSE_ID = 3

    static def createSingleAllergenDTO(name = GLUTEN) {
        return AllergenDTO
                .builder()
                .name(name)
                .build()
    }

    static def createSingleAllergenDTOWithId(name = GLUTEN, id = GLUTEN_ID) {
        return AllergenDTO.builder()
                .id(id)
                .name(name)
                .build()
    }

    static def createSingleAllergenDTOWithIdWithoutName(id = GLUTEN_ID) {
        return AllergenDTO.builder()
                .id(id)
                .build()
    }

    static def createListOfAllergenWithIdDTOs() {
        return List.of(createSingleAllergenDTOWithId(),
                createSingleAllergenDTOWithId(DAIRY, DAIRY_ID),
                createSingleAllergenDTOWithId(LACTOSE, LACTOSE_ID))
    }

    static def createListOfAllergenDTOs() {
        return List.of(createSingleAllergenDTO(),
                createSingleAllergenDTO(DAIRY),
                createSingleAllergenDTO(LACTOSE))
    }


    static def createSingleAllergen(name = GLUTEN) {
        return Allergen
                .builder()
                .name(name)
                .build()
    }

    static def createSingleAllergenWithId(name = GLUTEN, id = GLUTEN_ID) {
        return Allergen.builder()
                .id(id)
                .name(name)
                .build()
    }

    static def createSingleAllergenWithoutName() {
        return Allergen.builder()
                .build()
    }

    static def createListOfAllergensWithId() {
        return List.of(createSingleAllergenWithId(),
                createSingleAllergenWithId(DAIRY, DAIRY_ID),
                createSingleAllergenWithId(LACTOSE, LACTOSE_ID))
    }

    static def createListOfAllergens() {
        return List.of(createSingleAllergen(),
                createSingleAllergen(DAIRY),
                createSingleAllergen(LACTOSE))
    }
}

