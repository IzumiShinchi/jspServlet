package org.big.prj.dto;

import lombok.Data;

@Data
public class CarBaseDTO {
	private String baseId;
    private String type;
    private String name;
    private String description;
    private String price;
    private String baseImage;
}
