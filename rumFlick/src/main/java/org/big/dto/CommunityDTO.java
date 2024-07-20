package org.big.dto;

import java.util.Date;

import lombok.Data;

@Data
public class CommunityDTO {
	private int commuCode;
	private String userId;
	private String commuTitle;
	private String commuContent;
	private int commuReadCnt;
	private Date commuCreated;
}
