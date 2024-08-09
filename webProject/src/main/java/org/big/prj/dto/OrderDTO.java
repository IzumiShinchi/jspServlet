package org.big.prj.dto;

import java.util.Date;

import lombok.Data;
@Data
public class OrderDTO {
	private String orderNo;
	private String userId;
    private String carId;
    private String cartNo;
    private String orderDate;
    private String totalPrice;
    private int quantity;
    
    //고객정보
    private String userPost;
    private String userAd;
    private String userAdd;
    
    private String kind;
    private String color;
    private String options;

}
