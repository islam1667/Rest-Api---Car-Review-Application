package com.company.api.services.impl;

import com.company.api.dto.CarDto;
import com.company.api.dto.CarResponse;
import com.company.api.exceptions.CarNotFoundException;
import com.company.api.models.Car;
import com.company.api.repositories.CarRepository;
import com.company.api.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    @Autowired
    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public CarDto createCar(CarDto dto) {
        Car car = new Car();
        car.setName(dto.getName());
        car.setType(dto.getType());

        Car newCar = carRepository.save(car);

        CarDto responseDto = new CarDto();
        responseDto.setId(newCar.getId());
        responseDto.setName(newCar.getName());
        responseDto.setType(newCar.getType());

        return responseDto;
    }

    @Override
    public CarResponse getAllCars(Integer pageNo, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Car> cars = carRepository.findAll(pageable);
        List<Car> listOfCars = cars.getContent();
        List<CarDto> content = listOfCars.stream().map(car -> mapToDto(car)).toList();

        CarResponse carResponse = new CarResponse();
        carResponse.setContent(content);
        carResponse.setPageNo(cars.getNumber());
        carResponse.setPageSize(cars.getSize());
        carResponse.setTotalElements(cars.getTotalElements());
        carResponse.setTotalPages(cars.getTotalPages());
        carResponse.setLast(cars.isLast());

        return carResponse;
    }

    @Override
    public CarDto getCarById(Integer id) {
        Car car = carRepository.findById(id).orElseThrow(() -> new CarNotFoundException("Car could not be found."));
        return mapToDto(car);
    }

    @Override
    public CarDto updateCar(CarDto dto, Integer id) {
        Car car = carRepository.findById(id).orElseThrow(() -> new CarNotFoundException("Car could not be updated."));

        car.setName(dto.getName());
        car.setType(dto.getType());

        Car updatedCar = carRepository.save(car);
        return mapToDto(updatedCar);
    }

    @Override
    public void deleteCar(Integer id) {
        Car car = carRepository.findById(id).orElseThrow(() -> new CarNotFoundException("Car could not be deleted."));
        carRepository.delete(car);
    }

    private CarDto mapToDto(Car car){
        CarDto dto = new CarDto();
        dto.setId(car.getId());
        dto.setName(car.getName());
        dto.setType(car.getType());
        return dto;
    }

    private Car mapToEntity(CarDto dto){
        Car car = new Car();
        car.setName(dto.getName());
        car.setType(dto.getType());
        return car;
    }
}
