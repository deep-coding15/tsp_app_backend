package com.mervy.root.tsp_app.traveloptimizer.algorithms;

import com.mervy.root.tsp_app.traveloptimizer.algorithms.utils.Distance;
import com.mervy.root.tsp_app.traveloptimizer.model.city.City;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class TwoOpt {

    /**
     * Cet algorithme prend une liste de villes et calcule premierement une solution initiale avec le plus proche voisin et
     * ensuite applique le 2-opt*/
    public static LinkedList<City> solveTspTwoOpt(List<City> citiesList){
        LinkedList<City> improveSolution = NearestNeighbors.solveTspNearestNeighbor(citiesList);
        return get2OptMethod(improveSolution);
    }

    /**
     * Ici on considere que il y'a deja une solution initiale qui produit la premiere solution
     * et le 2-opt vient optimiser la solution*/
    public static LinkedList<City> solveTspTwoOptLinkedList(LinkedList<City> improveSolution){
        return get2OptMethod(improveSolution);
    }

    private static LinkedList<City> get2OptMethod(LinkedList<City> improveSolution) {
        boolean improved = true;
        while (improved) {
            improved = false;
            for (int i = 0; i < improveSolution.size() - 2; i++) {
                for(int j=i+2; j < improveSolution.size() - 1; j++){
                    double Xi_Xi1 = Distance.haversineDistance(improveSolution.get(i).getLatitude(), improveSolution.get(i).getLongitude(), improveSolution.get(i + 1).getLatitude(), improveSolution.get(i + 1).getLongitude());
                    double Xj_Xj1 = Distance.haversineDistance(improveSolution.get(j).getLatitude(), improveSolution.get(j).getLongitude(), improveSolution.get(j + 1).getLatitude(), improveSolution.get(j + 1).getLongitude());
                    double Xi_Xj = Distance.haversineDistance(improveSolution.get(i).getLatitude(), improveSolution.get(i).getLongitude(), improveSolution.get(j).getLatitude(), improveSolution.get(j).getLongitude());
                    double Xi1_Xj1 = Distance.haversineDistance(improveSolution.get(i + 1).getLatitude(), improveSolution.get(i + 1).getLongitude(), improveSolution.get(j + 1).getLatitude(), improveSolution.get(j + 1).getLongitude());

                    if ((Xi_Xi1 + Xj_Xj1) > (Xi1_Xj1 + Xi_Xj)) {
                        improved = true;
                        Collections.swap(improveSolution, i + 1, j);
                        Collections.swap(improveSolution, j, i + 1);
                    }
                }
            }
        }
        return improveSolution;
    }

}
