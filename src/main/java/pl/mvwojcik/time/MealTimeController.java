package pl.mvwojcik.time;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.mvwojcik.plan.MealTime;
import pl.mvwojcik.plan.data.model.DietPlanProjection;

import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/time")
public class MealTimeController {

    @GetMapping
    public ResponseEntity<List<MealTime>> getAllDietPlans() {
        return ResponseEntity.ok(Arrays.asList(MealTime.values()));
    }
}
