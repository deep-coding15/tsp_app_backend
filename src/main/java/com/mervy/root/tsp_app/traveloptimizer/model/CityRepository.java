package com.mervy.root.tsp_app.traveloptimizer.model;

import com.mervy.root.tsp_app.traveloptimizer.model.city.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long> {
}