package org.big.dto;

import java.util.Date;

import lombok.Data;

@Data
public class NoticeDTO {
	private int noticeCode;
	private String noticeTitle;
	private String noticeContent;
	private int noticeReadCnt;
	private Date noticeCreated;
}
