package org.big.prj.dto;

import java.util.Date;
import lombok.Data;

@Data
public class InquiryDTO {
    private Long inquiryId;         // 문의 ID
    private String inquiryName;     // 문의자명
    private String inquiryTitle;    // 문의글 제목
    private String inquiryContent;  // 문의글 내용
    private Date createdDate;       // 작성 날짜
    private String commentTitle;    // 답글 제목
    private String commentContent;  // 답글 내용
    private Date commentDate;       // 답글 날짜
    private String inquiryPasswd;   // 확인 비밀번호
	}
	
    