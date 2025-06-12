package com.mervy.root.tsp_app.traveloptimizer.service;

import com.mervy.root.tsp_app.traveloptimizer.model.CityRepository;
import com.mervy.root.tsp_app.traveloptimizer.model.RouteResponse;
import com.mervy.root.tsp_app.traveloptimizer.model.city.City;

import com.mervy.root.tsp_app.traveloptimizer.model.city.CityService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.*;

@Getter
@Setter
@Service
public class TspService extends CityService{

    private final CityRepository cityRepository;

    /**super is the class CityService because TspServices herites of the CityServices class*/
    public TspService(CityRepository cityRepository) {
        super(cityRepository);
        this.cityRepository = cityRepository;
    }

    private final Map<String, City> cityMap = super.getCityMap();


    public Map<String, City> getCities() {
        return super.getCityMap();
    }

    public RouteResponse optimizeRoute(List<String> selectedNames) {
        List<City> selected = new ArrayList<>();
        for (String name : selectedNames) {
            if (cityMap.containsKey(name)) {
                selected.add(cityMap.get(name));
            }
        }
        List<City> optimized = solveTspNearestNeighbor(selected);
        return new RouteResponse(optimized);
    }

    private List<City> solveTspNearestNeighbor(List<City> cities) {
        if (cities.isEmpty()) return cities;

        List<City> result = new ArrayList<>();
        Set<City> remaining = new HashSet<>(cities);
        City current = cities.get(0);
        result.add(current);
        remaining.remove(current);

        while (!remaining.isEmpty()) {
            City finalCurrent = current;
            City next = remaining.stream()
                    .min(Comparator.comparingDouble(c -> distance(finalCurrent, c)))
                    .orElse(null);
            result.add(next);
            remaining.remove(next);
            current = next;
        }

        return result;
    }

    private double distance(City a, City b) {
        double dx = a.getLatitude() - b.getLatitude();
        double dy = a.getLongitude() - b.getLongitude();
        return Math.sqrt(dx * dx + dy * dy);
    }
}