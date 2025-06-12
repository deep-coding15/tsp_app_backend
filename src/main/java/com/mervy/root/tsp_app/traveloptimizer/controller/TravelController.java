package com.mervy.root.tsp_app.traveloptimizer.controller;

import com.mervy.root.tsp_app.traveloptimizer.model.RouteRequest;
import com.mervy.root.tsp_app.traveloptimizer.model.RouteResponse;
import com.mervy.root.tsp_app.traveloptimizer.model.city.City;

import com.mervy.root.tsp_app.traveloptimizer.service.TspService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = "http://localhost:3000") // adapte selon port React
public class TravelController {

    private final TspService tspService;

    @Autowired
    public TravelController(TspService tspService) {
        this.tspService = tspService;
    }

    @GetMapping("/cities")
    public Map<String, City> getCities() {
        return tspService.getCities();
    }

    @PostMapping(value = "/optimize-trip", consumes = MediaType.APPLICATION_JSON_VALUE)
    public RouteResponse optimize(@RequestBody RouteRequest request) {
        return tspService.optimizeRoute(request.getCities());
    }

   /*@PostMapping("/chemin-min")
    public RouteResponse cheminMin(@RequestBody RouteRequest request) {

   }*/
}