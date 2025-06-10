package com.mervy.root.tsp_app.traveloptimizer.model;


import lombok.Getter;

import java.util.List;

@Getter
public class RouteResponse {
    private final List<City> route;

    public RouteResponse(List<City> route) {
        this.route = route;
    }

}