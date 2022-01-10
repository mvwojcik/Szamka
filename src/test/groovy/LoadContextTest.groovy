import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import pl.mvwojcik.ingredient.IngredientsController
import spock.lang.Specification

@SpringBootTest
class LoadContextTest extends Specification {

    @Autowired
    IngredientsController ingredientsController

    def "when context is loaded then all expected beans are created"() {
        expect: "the WebController is created"
        ingredientsController
        println "HELLO"
    }
}

