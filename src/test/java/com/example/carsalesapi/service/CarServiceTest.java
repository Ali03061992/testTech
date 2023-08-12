package com.example.carsalesapi.service;

import com.example.carsalesapi.domaine.Car;
import com.example.carsalesapi.domaine.enumuration.FuelType;
import com.example.carsalesapi.dto.CarDto;
import com.example.carsalesapi.repository.CarRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class CarServiceTest {

    @Autowired
    private CarService carService;

    @MockBean
    private CarRepository carRepository;

    @Test
    public void testAddCar() {
        CarDto carDto = new CarDto();
        carDto.setMake("Toyota");
        carDto.setModel("Camry");
        carDto.setRegistrationDate(LocalDate.of(2022, 6, 20));
        carDto.setPrice(30000.0);
        carDto.setFuelType(FuelType.DIESEL);

        Car car = new Car();
        car.setId(1L);
        car.setMake(carDto.getMake());
        car.setModel(carDto.getModel());
        car.setRegistrationDate(carDto.getRegistrationDate());
        car.setPrice(carDto.getPrice());
        car.setFuelType(carDto.getFuelType());

        when(carRepository.save(any())).thenReturn(car);

        // When
        CarDto result = carService.addCar(carDto);

        // Then
        assertNotNull(result);
        assertEquals(carDto.getMake(), result.getMake());
        assertEquals(carDto.getModel(), result.getModel());
        assertEquals(carDto.getRegistrationDate(), result.getRegistrationDate());
        assertEquals(carDto.getPrice(), result.getPrice());
        assertEquals(carDto.getFuelType(), result.getFuelType());
    }

    @Test
    public void testGetCarsByFuelTypeAndMaxPrice() {
        FuelType fuelType = FuelType.DIESEL;
        Double maxPrice = 10000.0;
        List<Car> cars = new ArrayList<>(); // Initialize cars
        when(carRepository.findByFuelTypeAndPriceLessThanEqual(fuelType, maxPrice)).thenReturn(cars);

        List<CarDto> result = carService.getCarsByFuelTypeAndMaxPrice(fuelType, maxPrice);

        assertNotNull(result);
        // Add more assertions based on your logic
    }

    @Test
    public void testGetAllMakes() {
        List<String> makes = Arrays.asList("Make1", "Make2");
        when(carRepository.findDistinctMake()).thenReturn(makes);

        List<String> result = carService.getAllMakes();

        assertNotNull(result);
        // Add more assertions based on your logic
    }

    @Test
    public void testUpdateCarPicture() {
        // Given
        Long carId = 1L;
        byte[] newPicture = new byte[]{0x01, 0x02, 0x03}; // Replace with your actual picture data
        Car car = new Car();
        car.setId(carId);
        // Initialize other properties of the car

        when(carRepository.findById(carId)).thenReturn(Optional.of(car));
        when(carRepository.save(any())).thenReturn(car);

        // When
        CarDto result = carService.updateCarPicture(carId, newPicture);

        // Then
        assertNotNull(result);
        assertArrayEquals(newPicture, car.getPicture());
        // Add more assertions based on your logic
    }

}