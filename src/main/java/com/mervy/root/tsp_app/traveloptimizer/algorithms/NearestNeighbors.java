package com.mervy.root.tsp_app.traveloptimizer.algorithms;

import com.mervy.root.tsp_app.traveloptimizer.model.city.City;
import com.mervy.root.tsp_app.traveloptimizer.algorithms.utils.Distance;

import java.util.*;

public class NearestNeighbors {
    public static LinkedList<City> solveTspNearestNeighbor(List<City> cities) {
        if (cities.isEmpty()) return new LinkedList<>(cities);

        LinkedList<City> result = new LinkedList<>();
        Set<City> remaining = new HashSet<>(cities);
        City current = cities.get(0);
        result.add(current);
        remaining.remove(current);

        while (!remaining.isEmpty()) {
            City finalCurrent = current;
            City next = remaining.stream()
                    .min(Comparator.comparingDouble(c -> Distance.haversineDistance(finalCurrent.getLatitude(), finalCurrent.getLongitude(), c.getLatitude(), c.getLongitude())))
                    .orElse(null);
            result.add(next);
            remaining.remove(next);
            current = next;
        }

        return result;
    }

   }
