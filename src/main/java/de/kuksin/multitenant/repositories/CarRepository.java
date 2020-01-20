package de.kuksin.multitenant.repositories;

import de.kuksin.multitenant.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
}
