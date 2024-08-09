package org.big.prj.service.impl;

import org.big.prj.dto.CarDTO;
import org.big.prj.mapper.CarMapper;
import org.big.prj.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarMapper carMapper;

    @Override
    public void saveCar(CarDTO car) {
        carMapper.insertCar(car);
        car.setCarId(carMapper.getLastInsertedCarId()); // 이 부분을 추가
    }

    @Override
    public CarDTO getCarById(String carId) {
        return carMapper.getCarById(carId);
    }
}
	