package pl.mvwojcik.recipe.data;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.mvwojcik.recipe.data.model.Recipe;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    @Override
    @EntityGraph(value = "graph.recipe")
    Optional<Recipe> findById(Long integer);

    Boolean existsByName(String name);

    @Override
    @EntityGraph(value = "graph.recipe")
    Page<Recipe> findAll(Pageable pageable);

    @EntityGraph(value = "graph.recipe")
    List<Recipe> findByNameInIgnoreCase(List<String> recipes);
}
