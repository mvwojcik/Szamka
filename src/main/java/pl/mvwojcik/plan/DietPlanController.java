package pl.mvwojcik.plan;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.mvwojcik.plan.data.dto.DietPlanDTO;
import pl.mvwojcik.plan.data.model.DietPlanProjection;

@RestController
@RequestMapping("/diets")
public class DietPlanController {

    private final DietPlanService dietPlanService;

    public DietPlanController(DietPlanService dietPlanService) {
        this.dietPlanService = dietPlanService;
    }


    @GetMapping
    public ResponseEntity<Page<DietPlanProjection>> getAllDietPlans(@RequestParam int page) {
        return ResponseEntity.ok(dietPlanService.getAll(page));
    }

    @GetMapping("/{id}")
    public ResponseEntity getOneDiet(@PathVariable Long id) {
        return dietPlanService.findById(id).toResponseEntity();
    }


    @PostMapping
    public ResponseEntity createDietPlan(@RequestBody DietPlanDTO dietPlanDTO) {
        return dietPlanService.create(dietPlanDTO).toResponseEntity();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteDietPlan(@PathVariable Long id) {
        return dietPlanService.delete(id).toResponseEntity();
    }

}
