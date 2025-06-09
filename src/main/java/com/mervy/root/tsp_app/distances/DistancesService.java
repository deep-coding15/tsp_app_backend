package com.mervy.root.tsp_app.distances;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
@Slf4j
@Getter
@Setter
public class DistancesService {
    private final DistancesRepository distancesRepository;

    public void create(DistanceFormat distance) {
        this.distancesRepository.save(distance);
    }

    public List<DistanceFormat> search() {
        return this.distancesRepository.findAll();
    }


    public DistanceFormat read(Long id) {
        Optional<DistanceFormat>  optional = this.distancesRepository.findById(id);

        return optional.orElse(null);
    }

    public DistanceFormat update(Long id, DistanceFormat distance) {
        DistanceFormat distanceInDatabase = this.read(id);
        distanceInDatabase.setVille1(distance.getVille1());
        distanceInDatabase.setVille2(distance.getVille2());
        distanceInDatabase.setDistance_km(distance.getDistance_km());
        distanceInDatabase.setTime(distance.getTime());
        return this.distancesRepository.save(distanceInDatabase);
    }

    public void delete(Long id) {
        DistanceFormat distance = this.read(id);
        this.distancesRepository.delete(distance);
    }
}
