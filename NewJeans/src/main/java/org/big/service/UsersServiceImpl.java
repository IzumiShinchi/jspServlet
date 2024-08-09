package org.big.service;

import java.util.List;

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
        // The userSignUp date is already set in the UsersDTO by the controller
		usersMapper.insertUsers(users);
	}
	
	// 사용자 ID의 중복 여부를 확인하는 메서드
    public boolean idCheck(String userId) throws Exception {
        // 실제 로직으로 DB에서 사용자 ID의 중복 여부를 확인
        return usersMapper.idSearch(userId) == 0;	// 아이디가 없으면 true, 있으면 false
    }
	
	@Override
	public UsersDTO usersDetail(String userId) throws Exception {
		UsersDTO users = usersMapper.usersDetail(userId);
		return users;
//		return usersMapper.usersDetail(userId);
	}
	
	@Override
    public List<UsersDTO> getUsersDetail() throws Exception {
        return usersMapper.getUsersDetail();
    }
	
	@Override
	public UsersDTO getUsersById(String userId) throws Exception {
		return usersMapper.getUsersById(userId);
	}
	
	@Override
	public boolean updateUsers(UsersDTO users) throws Exception {
		return usersMapper.updateUsers(users) > 0;
	}
	
	@Override
	public boolean deleteUsers(String userId) throws Exception {
		return usersMapper.deleteUsers(userId) > 0;
	}
	
	@Override
	public UsersDTO loginUsers(String userId, String userPw, HttpSession session) throws Exception {
		UsersDTO users = usersMapper.loginUsers(userId, userPw);
		if(users!=null && users.getUId().equals(userId) && users.getUPw().equals(userPw)) {
			session.setAttribute("userId", users.getUId());
			session.setAttribute("userPw", users.getUPw());
			return users;
		} else {
            return null;
        }
		
	}
	
	@Override
	public void logout(HttpSession session) throws Exception {
		session.invalidate();	//세션 초기화
	}

}
