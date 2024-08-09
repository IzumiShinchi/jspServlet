package org.big.prj.service;

import java.util.List;

import org.big.prj.dto.UsersDTO;

public interface UsersService {
    // 사용자 등록
    List<String> registerUser(UsersDTO usersDTO);

    // 아이디 중복 확인
    boolean checkUserId(String userId);

    // 아이디 사용 가능 여부 확인
    boolean isUserIdAvailable(String userId);

    // 이메일 사용 가능 여부 확인
    boolean isUserEmailAvailable(String userEmail);

    // 휴대폰 번호 사용 가능 여부 확인
    boolean isUserMobileAvailable(String userMobile);

    // 아이디 찾기
    String findUserId(String userName, String userBirth);

    // 비밀번호 찾기
    String findUserPassword(String userId, String userEmail);

    // 로그인 확인
    boolean loginUser(String userId, String userPasswd);
    
    // 모든 사용자 조회
    List<UsersDTO> getAllUsers(int page, int size, String search);
    
    int countUsers(String search);

    // 사용자 삭제 (관리자)
    void deleteUserByAdmin(String userId);
    // 사용자 정보 조회
    UsersDTO getUserById(String userId);
}
