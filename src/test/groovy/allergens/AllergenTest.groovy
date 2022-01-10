package allergens

import pl.mvwojcik.allergen.data.AllergensRepository
import spock.lang.Specification

class AllergenTest extends Specification {
    AllergensRepository allergensRepository = Mock(AllergensRepository)

    def 'When allergen then ok'() {
        given: 'Allergen exists'
        int a = 10
        int z = 21
        when: 'Allergen git'
        int b = a + 10
        then: 'allergen ok'
        z > b
    }


    def 'When allergen then o'() {

    }
}
