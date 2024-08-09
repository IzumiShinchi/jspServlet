package org.big.dto;

import java.util.Date;

import lombok.Data;

@Data
public class NoticeDTO {
	private int nCode;
	private String nTitle;
	private String nContent;
	private int nReadCnt;
	private Date nCreated;
	private String uId;
}
