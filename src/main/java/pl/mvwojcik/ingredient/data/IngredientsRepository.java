package pl.mvwojcik.ingredient.data;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.mvwojcik.ingredient.data.model.Ingredient;
import pl.mvwojcik.ingredient.data.model.IngredientProjection;

import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import java.util.List;
import java.util.Optional;

@Repository
public interface IngredientsRepository extends CrudRepository<Ingredient, Long> {

    @EntityGraph(value = "graph.ingredientRecipesVitamins", type = EntityGraph.EntityGraphType.FETCH)
    Optional<Ingredient> findById(Long id);


    @EntityGraph(value = "graph.ingredientVitamins", type = EntityGraph.EntityGraphType.FETCH)
    List<Ingredient> findByNameInIgnoreCase(List<String> ingredients);


//    @Override
//    @EntityGraph(value = "graph.ingredientVitamins", type = EntityGraph.EntityGraphType.FETCH)
//    List<Ingredient> findAll();

    Page<IngredientProjection> findAllBy(Pageable pageable);

    Boolean existsByName(String name);

}
