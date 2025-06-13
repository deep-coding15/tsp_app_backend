package com.mervy.root.tsp_app.traveloptimizer.model;


import com.mervy.root.tsp_app.traveloptimizer.model.city.City;

import lombok.Getter;

import java.util.LinkedList;
import java.util.List;

@Getter
public class RouteResponse {
    private final LinkedList<City> route;
    private double distanceOptimises = 0;

    public RouteResponse(LinkedList<City> route, double distance) {
        this.route = route;
        this.distanceOptimises = distance;
    }
}