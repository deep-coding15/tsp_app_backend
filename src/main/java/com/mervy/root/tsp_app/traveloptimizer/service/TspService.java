package com.mervy.root.tsp_app.traveloptimizer.service;

import com.mervy.root.tsp_app.traveloptimizer.model.City;
import com.mervy.root.tsp_app.traveloptimizer.model.RouteResponse;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TspService {
    private final Map<String, City> cityMap = new HashMap<>();

    public TspService() {
        cityMap.put("Casablanca", new City("Casablanca", 33.5731, -7.5898));
        cityMap.put("Rabat", new City("Rabat", 34.0209, -6.8416));
        cityMap.put("Marrakech", new City("Marrakech", 31.6295, -7.9811));
        cityMap.put("Fès", new City("Fès", 34.0331, -5.0003));
        cityMap.put("Tanger", new City("Tanger", 35.7595, -5.8339));
        cityMap.put("Agadir", new City("Agadir", 30.4278, -9.5981));
        cityMap.put("Oujda", new City("Oujda", 34.6833, -1.9167));
        cityMap.put("Tétouan", new City("Tétouan", 35.5720, -5.3626));
        cityMap.put("Nador", new City("Nador", 35.1688, -2.9335));
        cityMap.put("El Jadida", new City("El Jadida", 33.2333, -8.5000));
        cityMap.put("Essaouira", new City("Essaouira", 31.5085, -9.7595));
        cityMap.put("Meknès", new City("Meknès", 33.8950, -5.5547));
        cityMap.put("Beni Mellal", new City("Beni Mellal", 32.3394, -6.3606));
        cityMap.put("Errachidia", new City("Errachidia", 31.9311, -4.4241));
        cityMap.put("Laâyoune", new City("Laâyoune", 27.1253, -13.1625));
    }

    public List<String> getCityNames() {
        return new ArrayList<>(cityMap.keySet());
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