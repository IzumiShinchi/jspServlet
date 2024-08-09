package org.big.prj.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.big.prj.dto.CarBaseDTO;

import java.util.List;
import java.util.Map;

@Mapper
public interface CarBaseMapper {
    List<CarBaseDTO> getAllCarBases();
    List<CarBaseDTO> getAllCarBasesByType(@Param("type") String type);
    void insertCarBase(CarBaseDTO carBaseDTO);
    void updateCarBase(CarBaseDTO carBaseDTO);
    void deleteCarBase(String baseId);
    CarBaseDTO getCarBaseById(String baseId);
    CarBaseDTO getCarBaseByNameAndType(Map<String, Object> params);
}
