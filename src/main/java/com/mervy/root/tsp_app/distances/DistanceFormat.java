package com.mervy.root.tsp_app.distances;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DistanceFormat {
    private String ville1;
    private String ville2;
    private int distance_km;
}
