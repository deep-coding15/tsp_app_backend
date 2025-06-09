package com.mervy.root.tsp_app.distances;

import org.springframework.data.jpa.repository.JpaRepository;

/**Les methodes populaires de l'interface Spring Data JPA :
 * save(entity)          // Enregistre ou met à jour une entité
 * findById(id)          // Recherche une entité par son ID
 * findAll()             // Récupère toutes les entités
 * deleteById(id)        // Supprime une entité par son ID
 * delete(entity)        // Supprime une entité spécifique
 * count()               // Retourne le nombre total d'entités
 * existsById(id)        // Vérifie si une entité existe avec cet ID*/

public interface DistancesRepository extends JpaRepository<DistanceFormat, Long> {
}
