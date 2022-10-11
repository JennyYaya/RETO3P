package Controller;

import Model.Quadbike;
import Service.QuadbikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/Quadbike")
@CrossOrigin(origins = "*", methods ={RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
public class QuadbikeController {

    @Autowired
    private QuadbikeService quadbikeService;

    @GetMapping("/all")
    public List<Quadbike> getAll() {
        return quadbikeService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Quadbike> getquadbike(@PathVariable("id") int id) {
        return quadbikeService.getQuadbike(id);
    }

    @PostMapping("/save")
    public Quadbike save(@RequestBody Quadbike quadbike) {
        return quadbikeService.save(quadbike);
    }

}
