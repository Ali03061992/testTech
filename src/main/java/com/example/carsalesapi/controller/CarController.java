package com.example.carsalesapi.controller;

import com.example.carsalesapi.domaine.Car;
import com.example.carsalesapi.domaine.enumuration.FuelType;
import com.example.carsalesapi.dto.CarDto;
import com.example.carsalesapi.service.CarService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "Cars", description = "Cars management APIs")
@RestController
@RequestMapping("/api/cars")
public class CarController {
    @Autowired
    CarService carService;

    @Operation(summary = "Create a new Cars", tags = {"cars", "post"})
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = {
                    @Content(schema = @Schema(implementation = CarDto.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
    @PostMapping
    public ResponseEntity<CarDto> createTutorial(@RequestBody CarDto carDto) {
        try {
          CarDto _Dto = carService.addCar(carDto);
            return new ResponseEntity<>(_Dto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @Operation(summary = "get all Cars", tags = {"cars", "get"})
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = {
                    @Content(schema = @Schema(implementation = CarDto.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
    @GetMapping
    public ResponseEntity<List<CarDto>> getCarsByFuelTypeAndMaxPrice(@RequestParam FuelType fuelType, @RequestParam Double maxPrice) {
        List<CarDto> cars = carService.getCarsByFuelTypeAndMaxPrice(fuelType, maxPrice);
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

    @Operation(summary = "get  Cars by makes", tags = {"cars", "get"})
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = {
                    @Content(schema = @Schema(implementation = CarDto.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
    @GetMapping("/makes")
    public ResponseEntity<List<String>> getAllMakes() {
        List<String> makes = carService.getAllMakes();
        return new ResponseEntity<>(makes, HttpStatus.OK);
    }

    @Operation(summary = "Create a picture to Cars", tags = {"cars", "put"})
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = {
                    @Content(schema = @Schema(implementation = CarDto.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
    @PutMapping("/{carId}/picture")
    public ResponseEntity<CarDto> updateCarPicture(@PathVariable Long carId, @RequestBody byte[] picture) {
        CarDto updatedCar = carService.updateCarPicture(carId, picture);
        return new ResponseEntity<>(updatedCar, HttpStatus.OK);
    }

}
