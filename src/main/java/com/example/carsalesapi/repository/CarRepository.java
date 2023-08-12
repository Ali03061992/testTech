package com.example.carsalesapi.repository;

import com.example.carsalesapi.domaine.Car;
import com.example.carsalesapi.domaine.enumuration.FuelType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findByFuelTypeAndPriceLessThanEqual(FuelType fuelType, Double maxPrice);
    @Query(value = "select distinct(c.make) from Car c")
    List<String> findDistinctMake();
}

