package org.big.service;

import java.time.LocalDateTime;

import org.big.dto.UsersDTO;
import org.big.mapper.UsersMapper;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UsersServiceImpl implements UsersService {
	
	@Autowired	//Mapper랑 Service랑 연결!
	private UsersMapper usersMapper;
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public void insertUsers(UsersDTO users) throws Exception {
		users.setUserSignUp(LocalDateTime.now());
		usersMapper.insertUsers(users);
	}
	
	@Override
	public UsersDTO usersDetail(String userId) throws Exception {
		UsersDTO users = usersMapper.usersDetail(userId);
		return users;
	}
	
	@Override
	public UsersDTO getUsersById(String userId) throws Exception {
		return usersMapper.getUsersById(userId);
	}
	
	@Override
	public void updateUsers(UsersDTO users) throws Exception {
		usersMapper.updateUsers(users);
	}
	
	@Override
	public void deleteUsers(UsersDTO users) throws Exception {
		usersMapper.deleteUsers(users);
	}
	
	@Override
	public UsersDTO loginUsers(String userId, String userPw, HttpSession session) throws Exception {
		UsersDTO users = usersMapper.loginUsers(userId, userPw);
		if(users!=null && users.getUserId().equals(userId) && users.getUserPw().equals(userPw)) {
			session.setAttribute("userId", users.getUserId());
			session.setAttribute("userPw", users.getUserPw());
			return users;
		} else {
            return null;
        }
		
	}
	
	@Override
	public void logout(HttpSession session) {
		session.invalidate();	//세션 초기화
	}

}
