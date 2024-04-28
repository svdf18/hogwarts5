package dk.kea.dat3js.hogwarts5.ghosts;

import dk.kea.dat3js.hogwarts5.house.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/ghosts")
public class GhostController {

    private List<Ghost> ghosts = new ArrayList<>();
    private final HouseService houseService;

    @Autowired  // Inject HouseService
    public GhostController(HouseService houseService) {
        this.houseService = houseService;
    }

    @GetMapping
    public List<Ghost> getAllGhosts() {
        initGhosts();  // Initialize ghosts if not already done
        return ghosts;
    }

    @GetMapping("/{name}")
    public ResponseEntity<Ghost> getGhost(@PathVariable String name) {
        initGhosts();  // Initialize ghosts if not already done
        return ResponseEntity.of(ghosts.stream().filter(ghost -> ghost.getName().contains(name)).findFirst());
    }

    // Initialize ghosts if not already done
    private void initGhosts() {
        if (ghosts.isEmpty()) {
            ghosts.add(new Ghost(1, "Nearly Headless Nick", "Sir Nicholas de Mimsy-Porpington", houseService.findById("Gryffindor").get()));
            ghosts.add(new Ghost(2,"The Bloody Baron", "Baron", houseService.findById("Slytherin").get()));
            ghosts.add(new Ghost(3,"The Grey Lady", "Helena Ravenclaw", houseService.findById("Ravenclaw").get()));
            ghosts.add(new Ghost(4, "The Fat Friar", "Friar", houseService.findById("Hufflepuff").get()));
        }
    }
}
