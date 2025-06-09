package com.mervy.root.tsp_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
		exclude = {
				/*desactiver l'acces automatique à la base de donnees
				* desactiver le service de secutite*/
		}
)
public class TspAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(TspAppApplication.class, args);
	}

}