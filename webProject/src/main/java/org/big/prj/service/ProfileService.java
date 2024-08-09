package org.big.prj.service;

import org.apache.ibatis.annotations.Param;
import org.big.prj.dto.UsersDTO;

public interface ProfileService {
    // 사용자 정보 가져오기
    UsersDTO getUserById(String userId);

    // 비밀번호 검증
    boolean validateUserPassword(String userId, String password);

    // 사용자 정보 업데이트
    void updateUser(UsersDTO usersDTO);

    // 사용자 탈퇴
    boolean deleteUser(String userId, String password); 

    // 현재 비밀번호 검증 메소드
    boolean checkCurrentPassword(String userId, String currentPassword);
}