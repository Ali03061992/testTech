package com.example.carsalesapi.service.mapper;

import com.example.carsalesapi.domaine.Car;
import com.example.carsalesapi.dto.CarDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CarMapper {
    CarMapper INSTANCE = Mappers.getMapper(CarMapper.class);

    CarDto carToCarDto(Car car);

    Car carDtoToCar(CarDto carDto);

    List<CarDto> carsToCarDtos(List<Car> car);
}
