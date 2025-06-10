package com.mervy.root.tsp_app.distances;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Locale;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity //entite qui est mappe dans la base de donnee
@Table(name = "distances")
public class DistanceFormat {
    /*ce n'est pas le programmeur qui va gerer les cles primaires mais la base de donnees qui va generer les cles primaires*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ville1;
    private String ville2;
    private int distance_km;
    //private LocalDateTime time;
}
