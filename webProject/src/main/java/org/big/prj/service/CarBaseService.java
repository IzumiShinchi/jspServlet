package org.big.prj.service;

import java.util.List;
import java.util.Map;

import org.big.prj.dto.CarBaseDTO;

public interface CarBaseService {
    List<CarBaseDTO> getAllCarBases();
    List<CarBaseDTO> getAllCarBasesByType(String type);
    void insertCarBase(CarBaseDTO carBase);
    void updateCarBase(CarBaseDTO carBase);
    void deleteCarBase(String baseId);
    CarBaseDTO getCarBaseById(String baseId);
    CarBaseDTO getCarBaseByNameAndType(String name, String type);
    Map<String, List<CarBaseDTO>> getAllCarBasesMap();
}
