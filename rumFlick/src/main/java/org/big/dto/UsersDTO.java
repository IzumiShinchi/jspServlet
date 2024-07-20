package org.big.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class UsersDTO {
	private String userId;
	private String userPw;
	private String userName;
	private String userBirth;
	private String userGender;
	private String userPhone;
	private String userNick;
	private String userAddr;
	private String userAddrDetail;
	private LocalDateTime userSignUp;
}
