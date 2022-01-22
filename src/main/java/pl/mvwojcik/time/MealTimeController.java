package pl.mvwojcik.time;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.mvwojcik.plan.MealTime;

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
