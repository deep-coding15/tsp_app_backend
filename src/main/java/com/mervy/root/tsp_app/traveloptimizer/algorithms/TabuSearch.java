package com.mervy.root.tsp_app.traveloptimizer.algorithms;

import com.mervy.root.tsp_app.traveloptimizer.algorithms.utils.DistanceCitiesList;
import com.mervy.root.tsp_app.traveloptimizer.model.city.City;
import com.mervy.root.tsp_app.traveloptimizer.algorithms.utils.Distance;

import java.util.LinkedList;
import java.util.List;

public class TabuSearch {
    private static final int MAX_ITERATIONS = 100;
    public static LinkedList<City> solveTspTabuSearchA(List<City> cities) {
        LinkedList<City> currentSolution = NearestNeighbors.solveTspNearestNeighbor(cities);
        LinkedList<City> bestSolution = new LinkedList<>(currentSolution);

        double bestDistance = DistanceCitiesList.CalculateDistance(bestSolution);

        LinkedList<City> tabuList = new LinkedList<>();

        for (int iter = 0; iter < MAX_ITERATIONS; iter++) {

        }

            return null;
    }
}