package org.big.dto;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class BoardDTO {

	private int boardIdx;                             
	private String title;                                   
	private String content;                                  
	private int hitCnt;
	private Date createdDatetime;
	private String creatorId;
	private Date updatedDatetime;              
	private String updatorId; 
	private List<BoardFileDTO> fileList;
}
