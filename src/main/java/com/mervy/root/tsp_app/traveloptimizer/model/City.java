package com.mervy.root.tsp_app.traveloptimizer.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "cities")
public class City {
    @Id
    private Long id;
    private String name;

    private double latitude;
    private double longitude;


    public City(String name, double lat, double lng) {
        this.name = name;
        this.latitude = lat;
        this.longitude = lng;
    }

    public City() {

    }


}
