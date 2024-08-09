package org.big.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class UsersDTO {
	private String uId;
	private String uPw;
	private String uName;
	private String uBirth;
	private String uGender;
	private String uPhone;
	private String uNick;
	private String uAddr;
	private String uAddrDetail;
	private Date uSignUp;
}
