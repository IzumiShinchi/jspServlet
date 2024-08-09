package org.big.prj.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class ReviewDTO {
    private Long reviewId;
    private String userId;
    private String orderNo;
    private String reviewTitle;
    private String reviewContent;
    private Date createdAt;
    private String reviewLevel;
    private String parentReview;
    
    // 주문정보
    private String kind;  // 추가
    private String color;  // 추가
    private String options;  // 추가
    private String totalPrice;  // 추가
    
 // 댓글 정보
    private String commentUserId;
    private String commentContent;
    private Date commentDate;
    private Long commentReview;
    private Integer commentLevel;
    
    // 기본 생성자에서 기본 값 설정
    public ReviewDTO() {
        this.reviewTitle = "Comment Title";
        this.reviewContent = "Comment Content";
        this.createdAt = new Date(); // 현재 날짜로 설정
    }
    
    private List<ReviewDTO> replies;
    
 
}
 