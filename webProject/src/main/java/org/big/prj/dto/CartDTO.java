package org.big.prj.dto;

import java.util.Date;

import lombok.Data;

@Data
public class CartDTO {
	private String cartNo;
	private String userId;  // 수정된 부분
    private String carId;
    private String createdDate;
    private int quantity;
    private String totalPrice;
    
    private String kind; // 이 부분 추가
    private String color; // 이 부분 추가
    private String options; // 이 부분 추가
    
    private String status;
}
