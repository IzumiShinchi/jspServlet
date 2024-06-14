package org.big.prj.dto;

import java.util.Date;

import lombok.Data;

@Data
public class BoardDTO {
	private int boardIdx;
	private String title;
	private String contents;
	private int hitCnt;
	private Date createdDatetime;
	private String creatorId;
	private Date updatedDatetime;
	private String updaterId;	
}
