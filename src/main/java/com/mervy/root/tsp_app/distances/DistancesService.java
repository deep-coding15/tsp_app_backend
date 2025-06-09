package com.mervy.root.tsp_app.distances;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

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


}
