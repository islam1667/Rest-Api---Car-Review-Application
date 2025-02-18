package com.company.api.services;

import com.company.api.dto.CarDto;
import com.company.api.dto.CarResponse;

import java.util.List;

public interface CarService {
    CarDto createCar(CarDto dto);

    CarResponse getAllCars(Integer pageNo, Integer pageSize);

    CarDto getCarById(Integer id);

    CarDto updateCar(CarDto dto, Integer id);

    void deleteCar(Integer id);
}
