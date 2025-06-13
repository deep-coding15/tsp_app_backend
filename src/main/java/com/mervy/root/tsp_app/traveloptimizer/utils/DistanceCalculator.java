package com.mervy.root.tsp_app.traveloptimizer.utils;

public class DistanceCalculator {

    // Rayon de la Terre en kilomètres
    private static final double EARTH_RADIUS_KM = 6371.0;

    /**
     * Calcule la distance entre deux points donnés par latitude et longitude.
     * Elle retourne une bonne approximation de la distance entre deux points sur une sphère (la terre)
     *
     * @param lat1 latitude du point 1 en degrés
     * @param lon1 longitude du point 1 en degrés
     * @param lat2 latitude du point 2 en degrés
     * @param lon2 longitude du point 2 en degrés
     * @return distance en kilomètres
     */
    public static double haversineDistance(double lat1, double lon1, double lat2, double lon2) {
        // Conversion des degrés en radians
        double lat1Rad = Math.toRadians(lat1);
        double lon1Rad = Math.toRadians(lon1);
        double lat2Rad = Math.toRadians(lat2);
        double lon2Rad = Math.toRadians(lon2);

        // Différences
        double dLat = lat2Rad - lat1Rad;
        double dLon = lon2Rad - lon1Rad;

        // Formule de Haversine
        double a = Math.pow(Math.sin(dLat / 2), 2)
                + Math.cos(lat1Rad) * Math.cos(lat2Rad) * Math.pow(Math.sin(dLon / 2), 2);

        double c = 2 * Math.asin(Math.sqrt(a));

        return EARTH_RADIUS_KM * c;
    }

    public static void main(String[] args) {
        // Exemple : Paris à New York
        double parisLat = 48.8566;
        double parisLon = 2.3522;
        double newYorkLat = 40.7128;
        double newYorkLon = -74.0060;

        double distance = haversineDistance(parisLat, parisLon, newYorkLat, newYorkLon);

        System.out.printf("Distance entre Paris et New York : %.2f km%n", distance);
    }
}