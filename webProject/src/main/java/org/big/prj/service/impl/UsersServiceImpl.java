package org.big.prj.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.big.prj.dto.UsersDTO;
import org.big.prj.mapper.UsersMapper;
import org.big.prj.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsersServiceImpl implements UsersService {

    private final UsersMapper usersMapper;

    @Autowired
    public UsersServiceImpl(UsersMapper usersMapper) {
        this.usersMapper = usersMapper;
    }

    @Override
    @Transactional
    public List<String> registerUser(UsersDTO usersDTO) {
        List<String> errors = new ArrayList<>();

        if (!isUserIdAvailable(usersDTO.getUserId())) {
        	errors.add("아이디가 이미 사용 중입니다.");
        }
        if (!isUserEmailAvailable(usersDTO.getUserEmail())) {
            errors.add("이메일이 이미 사용 중입니다.");
        }
        
        if (!isUserMobileAvailable(usersDTO.getUserMobile())) {
            errors.add("휴대폰 번호가 이미 사용 중입니다.");
        }

        if (!usersDTO.isPasswordConfirmed()) {
            errors.add("비밀번호와 비밀번호 확인이 일치하지 않습니다.");
        }

        if (!errors.isEmpty()) {
            return errors;
        }

        // 휴대폰 번호를 하나의 문자열로 합치기
        usersDTO.combineMobile();

        // 이메일 주소를 하나의 문자열로 합치기
        usersDTO.combineEmail();

        // 사용자 정보를 DB에 저장
        usersMapper.insertUser(usersDTO);
        return null;
    }

    @Override
    public boolean checkUserId(String userId) {
        return usersMapper.countByUserId(userId) == 0;
    }

    @Override
    public boolean isUserIdAvailable(String userId) {
        return checkUserId(userId);
    }

    @Override
    public boolean isUserEmailAvailable(String userEmail) {
        return usersMapper.countByEmail(userEmail) == 0;
    }

    @Override
    public boolean isUserMobileAvailable(String userMobile) {
        return usersMapper.countByUserMobile(userMobile) == 0;
    }

    @Override
    public String findUserId(String userName, String userBirth) {
        return usersMapper.findUserId(userName, userBirth);
    }

    @Override
    public String findUserPassword(String userId, String userEmail) {
        return usersMapper.findUserPassword(userId, userEmail);
    }

    @Override
    public boolean loginUser(String userId, String userPasswd) {
        UsersDTO user = usersMapper.validateUser(userId, userPasswd);
        return user != null;
    }

    @Override
    public List<UsersDTO> getAllUsers(int page, int size, String search) {
        int offset = (page - 1) * size;
        return usersMapper.getAllUsers(offset, size, "%" + search + "%");
    }

    @Override
    public int countUsers(String search) {
        return usersMapper.countUsers("%" + search + "%");
    }

    @Override
    public void deleteUserByAdmin(String userId) {
        usersMapper.deleteUserByAdmin(userId);
    }

    @Override
    public UsersDTO getUserById(String userId) {
        return usersMapper.getUserById(userId);
    }
}
