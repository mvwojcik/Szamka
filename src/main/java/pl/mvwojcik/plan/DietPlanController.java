package pl.mvwojcik.plan;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/diets")
public class DietPlanController {

    private final DietPlanService dietPlanService;

    public DietPlanController(DietPlanService dietPlanService) {
        this.dietPlanService = dietPlanService;
    }


    @GetMapping
    public ResponseEntity<List<DietPlanDTO>> getAllDietPlans() {
        return ResponseEntity.ok(dietPlanService.getAll());
    }
}
