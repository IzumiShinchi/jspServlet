package org.big.prj.mapper;

import org.apache.ibatis.annotations.*;
import org.big.prj.dto.UsersDTO;

import java.util.List;
import java.util.Map;

@Mapper
public interface UsersMapper {
    // 사용자를 데이터베이스에 삽입
    void insertUser(UsersDTO usersDTO);

    // 주어진 아이디로 사용자 수를 확인하여 아이디 중복 여부를 확인
    int countByUserId(@Param("userId") String userId);

    // 주어진 이메일로 사용자 수를 확인하여 이메일 중복 여부를 확인
    int countByEmail(@Param("userEmail") String userEmail);

    // 주어진 휴대폰 번호로 사용자 수를 확인하여 휴대폰 번호 중복 여부를 확인
    int countByUserMobile(@Param("userMobile") String userMobile);

    // 사용자 아이디와 비밀번호를 확인하여 사용자가 존재하는지 검증
    UsersDTO validateUser(@Param("userId") String userId, @Param("userPasswd") String userPasswd);

    // 이름과 생년월일로 아이디 찾기
    String findUserId(@Param("userName") String userName, @Param("userBirth") String userBirth);

    // 아이디와 이메일로 비밀번호 찾기
    String findUserPassword(@Param("userId") String userId, @Param("userEmail") String userEmail);

    // 주어진 아이디로 사용자 정보 찾기
    UsersDTO getUserById(@Param("userId") String userId);

    // 사용자 정보 업데이트
    void updateUser(UsersDTO user);

    // 사용자 탈퇴
    void deleteUser(@Param("userId") String userId);

    // 사용자 비밀번호 검증
    int countValidUserPassword(Map<String, String> params);
    
 // 모든 사용자 조회
    List<UsersDTO> getAllUsers(@Param("offset") int offset, @Param("limit") int limit, @Param("search") String search);
    
    int countUsers(@Param("search") String search);

    // 사용자 삭제 (관리자)
    void deleteUserByAdmin(@Param("userId") String userId);
}
