package com.company.api.controllers;

import com.company.api.dto.CarDto;
import com.company.api.dto.CarResponse;
import com.company.api.models.Car;
import com.company.api.repositories.CarRepository;
import com.company.api.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CarController {

    private CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/car")
    public ResponseEntity<CarResponse> getCars(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) Integer pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize
    ){
        return new ResponseEntity<>(carService.getAllCars(pageNo, pageSize), HttpStatus.OK);
    }

    @GetMapping("/car/{id}")
    public ResponseEntity<CarDto> CarDetail(@PathVariable("id") Integer id){
        return ResponseEntity.ok(carService.getCarById(id));
    }

    @PostMapping("/car/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CarDto> createCar(@RequestBody CarDto dto){
        return new ResponseEntity<>(carService.createCar(dto), HttpStatus.CREATED);
    }

    @PutMapping("/car/{id}/update")
    public ResponseEntity<CarDto> updateCar(@RequestBody CarDto dto, @PathVariable("id") Integer id){
        CarDto response = carService.updateCar(dto, id);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/car/{id}/delete")
    public ResponseEntity<String> deleteCar(@PathVariable("id") Integer id){
        carService.deleteCar(id);
        return ResponseEntity.ok("Deleted successfully");
    }
}
