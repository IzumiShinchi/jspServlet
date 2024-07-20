package org.big.service;

import org.big.dto.UsersDTO;

import jakarta.servlet.http.HttpSession;

public interface UsersService {
	
	void insertUsers(UsersDTO users) throws Exception;
	
	UsersDTO usersDetail(String userId) throws Exception;
	
	UsersDTO getUsersById(String userId) throws Exception;
	
	void updateUsers(UsersDTO users) throws Exception;
	
	void deleteUsers(UsersDTO users) throws Exception;
	
	UsersDTO loginUsers(String userId, String userPw, HttpSession session) throws Exception;
	
	void logout(HttpSession session);
}
