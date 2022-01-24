package pl.mvwojcik.ingredient.data;

import org.hibernate.annotations.WhereJoinTable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import pl.mvwojcik.ingredient.data.model.Ingredient;
import pl.mvwojcik.ingredient.data.model.IngredientProjection;

import java.util.List;
import java.util.Optional;

@Repository
public interface IngredientsRepository extends PagingAndSortingRepository<Ingredient, Long> {

    @EntityGraph(value = "graph.ingredientRecipesVitamins", type = EntityGraph.EntityGraphType.FETCH)
    Optional<Ingredient> findById(Long id);


    @EntityGraph(value = "graph.ingredientVitamins", type = EntityGraph.EntityGraphType.FETCH)
    List<Ingredient> findByNameInIgnoreCase(List<String> ingredients);

    @EntityGraph(value = "graph.ingredientAllergens", type = EntityGraph.EntityGraphType.FETCH)
    @Query(value = "SELECT distinct i from  Ingredient i where UPPER(i.name) LIKE CONCAT('%',UPPER(?2),'%') and not exists (SELECT 1 FROM Allergen a WHERE i MEMBER OF a.ingredients and a.name in ?1)")
    List<Ingredient> customSelect(List<String> allergens, String word);


//    @Override
//    @EntityGraph(value = "graph.ingredientVitamins", type = EntityGraph.EntityGraphType.FETCH)
//    List<Ingredient> findAll();

    Page<IngredientProjection> findAllBy(Pageable pageable);

    Boolean existsByName(String name);

}
