package com.mervy.root.tsp_app.algorithms;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;

public class TabuSearchTSP {
    private static final int NUM_CITIES = 5;
    private static final int TABU_TENURE = 5;
    private static final int MAX_ITERATIONS = 100;

    //Matrice des distances entre villes
    private static final int[][] distanceMatrix = {
            { 0, 2, 9, 10, 7 },
            { 2, 0, 6, 4, 3 },
            { 9, 6, 0, 8, 5 },
            { 10, 4, 8, 0, 6 },
            { 7, 3, 5, 6, 0 }
    };

    public static void main(String[] args) {
        List<Integer> currentSolution  = generateInitialSolution();
        List<Integer> bestSolution = new ArrayList<>(currentSolution);
        int bestDistance = calculateDistance(bestSolution);

        LinkedList<String> tabuList = new LinkedList<>();

        for (int iter = 0; iter < MAX_ITERATIONS; iter++) {
            List<List<Integer>> neighbors = generateNeighbors(currentSolution);
            List<Integer> bestNeighbor = null;
            int bestNeighborDistance = Integer.MAX_VALUE;
            String bestMove = "";

            for (List<Integer> neighbor : neighbors) {
                String move = encodeMove(currentSolution, neighbor);
                int distance = calculateDistance(neighbor);

                if((!tabuList.contains(move) || distance < bestDistance) && distance < bestNeighborDistance) {
                    bestNeighbor = neighbor;
                    bestNeighborDistance = distance;
                    bestMove = move;
                }
            }

            currentSolution = bestNeighbor;
            if (bestNeighborDistance < bestDistance){
                bestSolution = new ArrayList<>(bestNeighbor);
                bestDistance = bestNeighborDistance;
            }

            tabuList.add(bestMove);
            if(tabuList.size() > TABU_TENURE) {
                tabuList.removeFirst();
            }

            System.out.println("Iter: " + iter + " Cost: " + bestNeighborDistance + " Middle Solution: " + bestNeighbor + " Best Distance: " + bestDistance);
        }

        System.out.println("Best Solution: " + toStringSolution(bestSolution) + " Best Distance: " + bestDistance);
    }

    private static String toStringSolution(List<Integer> solution){
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("[");
        for(Integer i : solution){
            stringBuilder.append(i).append(" ");
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    /**This method generate an initial random solution of all the cities in considering the number of the cities*/
    private static List<Integer> generateInitialSolution() {
        List<Integer> cities = new ArrayList<>(NUM_CITIES);
        for (int i = 0; i < NUM_CITIES; i++) {
            cities.add(i);
        }
        Collections.shuffle(cities);
        return cities;
    }

    /**
     * This method generate a list of neighbors according to the actual solution
     *
     * @param solution : contain an initial solution or a solution that has the lows total distance than the initial solution
     * The neighbors is find were we swap between two element of the existing solution
     */
    private  static List<List<Integer>> generateNeighbors(List<Integer> solution){
        List<List<Integer>> neighbors = new ArrayList<List<Integer>>();
        for (int i = 0; i < (solution.size() - 1); i++) {
            for (int j = i + 1; j < (solution.size()); j++) {
                List<Integer> neighbor = new ArrayList<>(solution);
                Collections.swap(neighbor, i, j);
                neighbors.add(neighbor);
            }
        }
        return neighbors;
    }

    /**This method calculates the total distance for a tsp_app for a specific solution
     * @param solution can be an initial solution or a better solution than the initial one
     * */
    private static int calculateDistance(List<Integer> solution) {
        int distance = 0;
        for(int i = 0; i < (solution.size() - 1); i ++){
            distance += distanceMatrix[solution.get(i)][solution.get(i+1)];
        }

        //retour à la ville de départ
        //distance += distanceMatrix[solution.get(solution.size() - 1)][solution.get(0)];
        return distance;
    }

    /**this method is useful to detect the first modification between the @param current and @param neighbor
     * useful to refuse to go to a solution back
     * compare modifications*/
    private static String encodeMove(List<Integer> current, List<Integer> neighbor){
        for(int i = 0; i < current.size(); i++){
            if(!current.get(i).equals(neighbor.get(i))){
                return current.get(i) + " - " + neighbor.get(i);
            }
        }
        return "";
    }

}
