package org.big.prj.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.big.prj.dto.CarDTO;

@Mapper
public interface CarMapper {
    void insertCar(CarDTO carDTO);
    CarDTO getCarById(String carId);
    String getLastInsertedCarId(); // 이 메서드를 추가
}
