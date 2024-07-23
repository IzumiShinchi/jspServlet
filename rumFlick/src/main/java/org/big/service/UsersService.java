package org.big.service;

import java.util.List;

import org.big.dto.UsersDTO;

import jakarta.servlet.http.HttpSession;

public interface UsersService {
	
	void insertUsers(UsersDTO users) throws Exception;
	
	// ID 중복 확인
    boolean idCheck(String userId) throws Exception;
	
	UsersDTO usersDetail(String userId) throws Exception;
	
	List<UsersDTO> getUsersDetail() throws Exception;
	
	UsersDTO getUsersById(String userId) throws Exception;
	
	boolean updateUsers(UsersDTO users) throws Exception;
	
	boolean deleteUsers(String userId) throws Exception;
	
	UsersDTO loginUsers(String userId, String userPw, HttpSession session) throws Exception;
	
	void logout(HttpSession session) throws Exception;
}
