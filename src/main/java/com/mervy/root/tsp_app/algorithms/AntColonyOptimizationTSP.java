package com.mervy.root.tsp_app.algorithms;

import java.util.Arrays;

import java.util.*;

public class AntColonyOptimizationTSP {
    static final int NUM_CITIES = 5;
    static final int NUM_ANTS = 10;
    static final int MAX_ITERATIONS = 100;
    static final double ALPHA = 1.0;  // importance de la phéromone
    static final double BETA = 5.0;   // importance de la distance
    static final double EVAPORATION = 0.5;
    static final double Q = 500;

    static double[][] distances = {
            { 0, 2, 9, 10, 7 },
            { 1, 0, 6, 4, 3 },
            { 15, 7, 0, 8, 3 },
            { 6, 3, 12, 0, 2 },
            { 9, 7, 5, 6, 0 }
    };

    static double[][] pheromones = new double[NUM_CITIES][NUM_CITIES];

    public static void main(String[] args) {
        initializePheromones();

        int[] bestTour = null;
        double bestTourLength = Double.MAX_VALUE;

        for (int iteration = 0; iteration < MAX_ITERATIONS; iteration++) {
            List<int[]> allTours = new ArrayList<>();
            List<Double> allLengths = new ArrayList<>();

            for (int ant = 0; ant < NUM_ANTS; ant++) {
                int[] tour = generateTour();
                double length = calculateTourLength(tour);
                allTours.add(tour);
                allLengths.add(length);

                if (length < bestTourLength) {
                    bestTourLength = length;
                    bestTour = tour.clone();
                }
            }

            evaporatePheromones();
            updatePheromones(allTours, allLengths);

            System.out.println("Iter " + iteration + " - Best length: " + bestTourLength);
        }

        System.out.println("\nBest tour found:");
        System.out.println(Arrays.toString(bestTour));
        System.out.println("Length: " + bestTourLength);
    }

    static void initializePheromones() {
        for (int i = 0; i < NUM_CITIES; i++) {
            for (int j = 0; j < NUM_CITIES; j++) {
                pheromones[i][j] = 1.0;
            }
        }
    }

    static int[] generateTour() {
        boolean[] visited = new boolean[NUM_CITIES];
        int[] tour = new int[NUM_CITIES];
        Random rand = new Random();
        int current = rand.nextInt(NUM_CITIES);
        tour[0] = current;
        visited[current] = true;

        for (int step = 1; step < NUM_CITIES; step++) {
            int next = selectNextCity(current, visited);
            tour[step] = next;
            visited[next] = true;
            current = next;
        }

        return tour;
    }

    static int selectNextCity(int current, boolean[] visited) {
        double[] probabilities = new double[NUM_CITIES];
        double sum = 0.0;

        for (int i = 0; i < NUM_CITIES; i++) {
            if (!visited[i]) {
                double pheromone = Math.pow(pheromones[current][i], ALPHA);
                double heuristic = Math.pow(1.0 / distances[current][i], BETA);
                probabilities[i] = pheromone * heuristic;
                sum += probabilities[i];
            } else {
                probabilities[i] = 0.0;
            }
        }

        double rand = Math.random() * sum;
        double cumulative = 0.0;
        for (int i = 0; i < NUM_CITIES; i++) {
            cumulative += probabilities[i];
            if (rand <= cumulative) return i;
        }

        // fallback (should rarely happen)
        for (int i = 0; i < NUM_CITIES; i++) {
            if (!visited[i]) return i;
        }
        return -1;
    }

    static double calculateTourLength(int[] tour) {
        double total = 0.0;
        for (int i = 0; i < NUM_CITIES - 1; i++) {
            total += distances[tour[i]][tour[i + 1]];
        }
        //total += distances[tour[NUM_CITIES - 1]][tour[0]]; // return to start
        return total;
    }

    static void evaporatePheromones() {
        for (int i = 0; i < NUM_CITIES; i++) {
            for (int j = 0; j < NUM_CITIES; j++) {
                pheromones[i][j] *= (1 - EVAPORATION);
            }
        }
    }

    static void updatePheromones(List<int[]> tours, List<Double> lengths) {
        for (int k = 0; k < tours.size(); k++) {
            int[] tour = tours.get(k);
            double length = lengths.get(k);
            double delta = Q / length;

            for (int i = 0; i < NUM_CITIES - 1; i++) {
                int from = tour[i];
                int to = tour[i + 1];
                pheromones[from][to] += delta;
                pheromones[to][from] += delta;
            }

            // Return to start
            int last = tour[NUM_CITIES - 1];
            int first = tour[0];
            pheromones[last][first] += delta;
            pheromones[first][last] += delta;
        }
    }
}
