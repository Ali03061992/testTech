package com.example.carsalesapi.service.impl;

import com.example.carsalesapi.domaine.Car;
import com.example.carsalesapi.domaine.enumuration.FuelType;
import com.example.carsalesapi.dto.CarDto;
import com.example.carsalesapi.repository.CarRepository;
import com.example.carsalesapi.service.CarService;
import com.example.carsalesapi.service.mapper.CarMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CarServiceImpl implements CarService {
    @Autowired
    private CarRepository carRepository;

    @Override
    public CarDto addCar(CarDto car) {
        LocalDate minRegistrationDate = LocalDate.of(2015, 1, 1);
        if (car.getRegistrationDate().isBefore(minRegistrationDate)) {
            throw new IllegalArgumentException("Seules les voitures enregistrées après 2015 sont autorisées.");
        }
        return CarMapper.INSTANCE.carToCarDto(carRepository.save( CarMapper.INSTANCE.carDtoToCar(car)));
    }

    @Override
    public List<CarDto> getCarsByFuelTypeAndMaxPrice(FuelType fuelType, Double maxPrice) {
        return CarMapper.INSTANCE.carsToCarDtos(carRepository.findByFuelTypeAndPriceLessThanEqual(fuelType, maxPrice));
    }

    @Override
    public List<String> getAllMakes() {
        return carRepository.findDistinctMake();
    }

    @Override
    public CarDto updateCarPicture(Long carId, byte[] picture) {
        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new NoSuchElementException("Voiture non trouvée avec l'ID spécifié."));
        car.setPicture(picture);
        return CarMapper.INSTANCE.carToCarDto(carRepository.save(car)) ;
    }
}
