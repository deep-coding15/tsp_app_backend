package com.mervy.root.tsp_app.traveloptimizer.service;

import com.mervy.root.tsp_app.traveloptimizer.algorithms.NearestNeighbors;
import com.mervy.root.tsp_app.traveloptimizer.algorithms.TwoOpt;
import com.mervy.root.tsp_app.traveloptimizer.algorithms.utils.DistanceCitiesList;
import com.mervy.root.tsp_app.traveloptimizer.model.CityRepository;
import com.mervy.root.tsp_app.traveloptimizer.model.RouteResponse;
import com.mervy.root.tsp_app.traveloptimizer.model.city.City;

import com.mervy.root.tsp_app.traveloptimizer.model.city.CityService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.*;

@Getter
@Setter
@Service
public class TspService extends CityService{

    private final CityRepository cityRepository;

    /**super is the class CityService because TspServices herites of the CityServices class*/
    public TspService(CityRepository cityRepository) {
        super(cityRepository);
        this.cityRepository = cityRepository;
    }

    private final Map<String, City> cityMap = super.getCityMap();


    public Map<String, City> getCities() {
        return super.getCityMap();
    }

    public RouteResponse optimizeRoute(List<String> selectedNames) {
        List<City> selected = new ArrayList<>();
        for (String name : selectedNames) {
            if (cityMap.containsKey(name)) {
                selected.add(cityMap.get(name));
            }
        }
        LinkedList<City> optimized = TwoOpt.solveTspTwoOpt(selected);
        //LinkedList<City> optimized = NearestNeighbors.solveTspNearestNeighbor(selected);
        return new RouteResponse(optimized, DistanceCitiesList.CalculateDistance(optimized));
    }


}

