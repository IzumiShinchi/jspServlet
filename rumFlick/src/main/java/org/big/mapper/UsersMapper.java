package org.big.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.big.dto.UsersDTO;

@Mapper
public interface UsersMapper {

	void insertUsers(UsersDTO users) throws Exception;			//회원 가입
	
	int idSearch(@Param("userId") String userId) throws Exception;
	//"isUserIdExists"(idSearch)는 "isUserIdAvailable"(idCheck)을 포함하는 개념
	//즉, 사용자 ID가 존재한다면 사용 가능한 상태보다 더 큰 범위를 포함합니다. 왜냐하면 사용자 ID가 시스템에 이미 존재한다면 그것은 당연히 사용 가능하지 않은 상태
	
	UsersDTO usersDetail(@Param("userId") String userId) throws Exception;		//회원 정보
	
	@Select ("SELECT * FROM USERS")
	List<UsersDTO> getUsersDetail() throws Exception;
	
	UsersDTO getUsersById(@Param("userId") String userId) throws Exception;
	
	int updateUsers(UsersDTO users) throws Exception;			//회원 정보 수정
	
	int deleteUsers(@Param("userId") String userId) throws Exception;			//회원 탈퇴
	
	UsersDTO loginUsers(@Param("userId") String userId, @Param("userPw") String userPw) throws Exception;	 //로그인
}
