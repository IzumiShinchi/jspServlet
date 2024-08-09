package org.big.prj.dto;

import lombok.Data;

@Data
public class CarDTO {
	private String carId;
    private String kindId;
    private String kindName;
    private String colorId;
    private String colorName;
    private String optionIds;  // DB에는 이 필드가 저장됨
    private String optionNames;
    private String totalPrice;
    
    @Override
    public String toString() {
        return "CarDTO{" +
                "carId='" + carId + '\'' +
                ", kindId='" + kindId + '\'' +
                ", colorId='" + colorId + '\'' +
                ", optionIds='" + optionIds + '\'' +
                ", totalPrice='" + totalPrice + '\'' +
                '}';
    }
}
