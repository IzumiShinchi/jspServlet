package org.big.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.big.dto.UsersDTO;

@Mapper
public interface UsersMapper {

	void insertUsers(UsersDTO users) throws Exception;			//회원 가입
	
	UsersDTO usersDetail(String userId) throws Exception;		//회원 정보
	
	UsersDTO getUsersById(@Param("userId") String userId) throws Exception;
	
	void updateUsers(UsersDTO users) throws Exception;			//회원 정보 수정
	
	void deleteUsers(UsersDTO users) throws Exception;			//회원 탈퇴
	
	UsersDTO loginUsers(@Param("userId") String userId, @Param("userPw") String userPw) throws Exception;	 //로그인
}
