package pl.mvwojcik.vitamins;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/vitamins")
public class VitaminController {

    private final VitaminsService vitaminsService;

    public VitaminController(VitaminsService vitaminsService) {
        this.vitaminsService = vitaminsService;
    }

//    @GetMapping
//    public final ResponseEntity<List<Vitamin>> getAll() {
//      return ResponseEntity.ok(this.vitaminsService.getAll());
//    }

}
