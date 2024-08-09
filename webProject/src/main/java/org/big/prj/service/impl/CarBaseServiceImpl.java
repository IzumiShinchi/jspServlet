package org.big.prj.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.big.prj.dto.CarBaseDTO;
import org.big.prj.mapper.CarBaseMapper;
import org.big.prj.service.CarBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarBaseServiceImpl implements CarBaseService {

    @Autowired
    private CarBaseMapper carBaseMapper;

    @Override
    public List<CarBaseDTO> getAllCarBases() {
        return carBaseMapper.getAllCarBases();
    }

    @Override
    public void insertCarBase(CarBaseDTO carBaseDTO) {
        carBaseMapper.insertCarBase(carBaseDTO);
    }

    @Override
    public CarBaseDTO getCarBaseById(String baseId) {
        return carBaseMapper.getCarBaseById(baseId);
    }

    @Override
    public void updateCarBase(CarBaseDTO carBaseDTO) {
        carBaseMapper.updateCarBase(carBaseDTO);
    }

    @Override
    public void deleteCarBase(String baseId) {
        carBaseMapper.deleteCarBase(baseId);
    }

    @Override
    public List<CarBaseDTO> getAllCarBasesByType(String type) {
        return carBaseMapper.getAllCarBasesByType(type.trim().toUpperCase());
    }

    @Override
    public CarBaseDTO getCarBaseByNameAndType(String name, String type) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", name.trim().toUpperCase());
        params.put("type", type.trim().toUpperCase());
        System.out.println("Parameters: " + params); // Log parameters to verify they are correct
        return carBaseMapper.getCarBaseByNameAndType(params);
    }
    
    @Override
    public Map<String, List<CarBaseDTO>> getAllCarBasesMap(){
    	Map<String, List<CarBaseDTO>> map = new HashMap<>();
    	List<CarBaseDTO> kindList = new ArrayList<>();
    	List<CarBaseDTO> colorList = new ArrayList<>();
    	List<CarBaseDTO> optionList = new ArrayList<>();
    	
    	List<CarBaseDTO> list = carBaseMapper.getAllCarBases();
    	
    	for(CarBaseDTO dto : list) {
    		if(dto.getType().equals("KIND")) {
    			kindList.add(dto);
    		} else if(dto.getType().equals("COLOR")) {
    			colorList.add(dto);
    		} else {
    			optionList.add(dto);
    		}
    	}
    	
    	map.put("KIND", kindList);
    	map.put("COLOR", colorList);
    	map.put("OPTION", optionList);
    	
    	return map;
    }
}
