package com.mervy.root.tsp_app.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mervy.root.tsp_app.distances.DistanceFormat;

import java.io.*;
import java.util.*;

public class JsonTo2D {

    public int[][] convertJsonTo2D(String fileJSON_Name) throws FileNotFoundException {

        List<String> listCities = this.readJsonTSP_FileSorted(fileJSON_Name);

        List<DistanceFormat> distanceFormats = getDistanceFormats(fileJSON_Name);
        int numberCities = listCities.size();

        //create an 2D array and fill it
        int[][] distancesArray = new int[numberCities][numberCities];
        for(DistanceFormat distance : distanceFormats) {
            int i = listCities.indexOf(distance.getVille1());
            int j = listCities.indexOf(distance.getVille2());
            distancesArray[i][j] = distance.getDistance_km();
        }
        return distancesArray;
    }

    public List<String> readJsonTSP_FileSorted(String fileJSON_Name) throws FileNotFoundException {
        List<DistanceFormat> distanceFormats = getDistanceFormats(fileJSON_Name);

        //get all cities
        Set<String> setCities = new HashSet<>();
        for (DistanceFormat distanceFormat : distanceFormats) {
            setCities.add(distanceFormat.getVille1());
            setCities.add(distanceFormat.getVille2());
        }

        List<String> listCities = new ArrayList<>(setCities);
        Collections.sort(listCities);
        return listCities;
    }

    public List<DistanceFormat> getDistanceFormats(String fileJSON_Name) throws FileNotFoundException {
        Gson gson = new Gson();
        //read the file
        Reader reader = new FileReader(fileJSON_Name);

        //parser in to the list of distance objet
        return  gson.fromJson(reader, new TypeToken<List<DistanceFormat>>(){}.getType());
    }

    public static void main(String[] args) throws FileNotFoundException {
        JsonTo2D jsonTo2D = new JsonTo2D();
        int[][]essai = jsonTo2D.convertJsonTo2D("src/main/resources/distances_villes.json");
        System.out.println("Tableau des distances (non symétriques) :");
        for (int[] ints : essai) {
            //System.out.print(villes.get(i) + " : ");
            for (int j = 0; j < essai[0].length; j++) {
                System.out.printf("%d \t ", ints[j]);
            }
            System.out.println();
        }
    }
}

