package org.big.dto;

import java.util.Date;

import lombok.Data;

@Data
public class ReplyDTO {
	private int replyCode;
	private String replyContent;
	private String userId;
	private String contentsCode;
	private Date replyCreated;
}
