package pl.mvwojcik.vitamins;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.mvwojcik.vitamins.data.model.Vitamin;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/vitamins")
public class VitaminController {

    private final VitaminsService vitaminsService;

    public VitaminController(VitaminsService vitaminsService) {
        this.vitaminsService = vitaminsService;
    }

    @GetMapping
    public final ResponseEntity<Set<Vitamin>> getAll() {
      return ResponseEntity.ok(this.vitaminsService.getAll());
    }

}
