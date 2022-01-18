package pl.mvwojcik.plan.data;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.mvwojcik.plan.data.model.DietPlan;
import pl.mvwojcik.plan.data.model.DietPlanProjection;

import java.util.Optional;

public interface DietPlanRepository extends JpaRepository<DietPlan, Long> {

    Boolean existsByName(String name);

    Page<DietPlanProjection> findAllBy(Pageable pageable);


    @EntityGraph(value = "graph.dietplan")
    Optional<DietPlan> findById(Long id);
}
