package com.mervy.root.tsp_app.traveloptimizer.model.city;

import com.mervy.root.tsp_app.traveloptimizer.model.CityRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CityDataInitializer {

    @Bean
    CommandLineRunner initDatabase(CityRepository repository) {
        return args -> {
            repository.save(new City(1L, "Casablanca", 33.5731, -7.5898));
            repository.save(new City(2L, "Rabat", 34.0209, -6.8416));
            repository.save(new City(3L, "Marrakech", 31.6295, -7.9811));
            repository.save(new City(4L, "Fès", 34.0331, -5.0003));
            repository.save(new City(5L, "Tanger", 35.7595, -5.8339));
            repository.save(new City(6L, "Agadir", 30.4278, -9.5981));
            repository.save(new City(7L, "Oujda", 34.6833, -1.9167));
            repository.save(new City(8L, "Tétouan", 35.5720, -5.3626));
            repository.save(new City(9L, "Nador", 35.1688, -2.9335));
            repository.save(new City(10L, "El Jadida", 33.2333, -8.5000));
            repository.save(new City(11L, "Essaouira", 31.5085, -9.7595));
            repository.save(new City(12L, "Meknès", 33.8950, -5.5547));
            repository.save(new City(13L, "Beni Mellal", 32.3394, -6.3606));
            repository.save(new City(14L, "Errachidia", 31.9311, -4.4241));
            repository.save(new City(15L, "Laâyoune", 27.1253, -13.1625));
        };
    }
}