package com.example.carsalesapi.service;

import com.example.carsalesapi.domaine.Car;
import com.example.carsalesapi.domaine.enumuration.FuelType;
import com.example.carsalesapi.dto.CarDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CarService {

    CarDto addCar(CarDto car);
    List<CarDto>  getCarsByFuelTypeAndMaxPrice(FuelType fuelType, Double maxPrice);

    List<String> getAllMakes();

    CarDto updateCarPicture(Long carId, byte[] picture);
}
