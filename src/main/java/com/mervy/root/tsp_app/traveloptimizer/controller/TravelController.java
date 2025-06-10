package com.mervy.root.tsp_app.traveloptimizer.controller;

import com.mervy.root.tsp_app.traveloptimizer.model.RouteRequest;
import com.mervy.root.tsp_app.traveloptimizer.model.RouteResponse;
import com.mervy.root.tsp_app.traveloptimizer.service.TspService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = "http://localhost:3000") // adapte selon port React
public class TravelController {

    private final TspService tspService;

    public TravelController(TspService tspService) {
        this.tspService = tspService;
    }

    @GetMapping("/cities")
    public List<String> getCities() {
        return tspService.getCityNames();
    }

    @PostMapping(value = "/optimize-trip")
    public RouteResponse optimize(@RequestBody RouteRequest request) {
        return tspService.optimizeRoute(request.getCities());
    }
}