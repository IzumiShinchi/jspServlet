package org.big.prj.service;

import org.big.prj.dto.CarDTO;

public interface CarService {
    void saveCar(CarDTO carDTO);
    CarDTO getCarById(String carId);
}
