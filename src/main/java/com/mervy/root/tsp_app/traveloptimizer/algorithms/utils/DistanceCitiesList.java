package com.mervy.root.tsp_app.traveloptimizer.algorithms.utils;

import com.mervy.root.tsp_app.traveloptimizer.model.city.City;

import java.util.List;

public class DistanceCitiesList {
    public static double CalculateDistance(List<City> cities){
        double distance = 0D;
        for(int i = 0; i < (cities.size() - 1); i++){
            distance += Distance.haversineDistance(cities.get(i).getLatitude(), cities.get(i).getLongitude(), cities.get(i+1).getLatitude(), cities.get(i+1).getLongitude());
        }
        return distance;
    }
}
