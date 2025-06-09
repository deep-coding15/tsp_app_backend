package com.mervy.root.tsp_app.distances;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**Controller use to manage informations about Distance between two cities*/
@RestController
@RequestMapping("distances")
@RequiredArgsConstructor
@Slf4j
public class DistancesController {
    private final DistancesService distancesService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void create(@RequestBody DistanceFormat distance){
        this.distancesService.create(distance);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DistanceFormat> search() {
        return this.distancesService.search();
    }

    @GetMapping(path = "{id}")
    public DistanceFormat read(@PathVariable Long id) {
        return this.distancesService.read(id);
    }


    @PutMapping(path = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public DistanceFormat update(@PathVariable Long id,  @RequestBody DistanceFormat distance) {
        return this.distancesService.update(id, distance);
    }

    @DeleteMapping(path = "{id}")
    public void delete(@PathVariable Long id) {
        this.distancesService.delete(id);
    }
}
