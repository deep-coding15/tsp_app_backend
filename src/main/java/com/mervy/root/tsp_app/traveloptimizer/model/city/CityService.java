package com.mervy.root.tsp_app.traveloptimizer.model.city;

import com.mervy.root.tsp_app.traveloptimizer.model.CityRepository;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class CityService{

    private final CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    /**return a map that contains the name of the cities in the key and all the properties in the values*/
    public Map<String, City> getCityMap() {
        Map<String, City> cityMap = new HashMap<>();
        for (City city : cityRepository.findAll()) {
            cityMap.put(city.getName(), city);
        }
        return cityMap;
    }

}

