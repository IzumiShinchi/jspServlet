package org.big.prj.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.big.prj.dto.UsersDTO;
import org.big.prj.mapper.UsersMapper;
import org.big.prj.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;

@Service
public class ProfileServiceImpl implements ProfileService {

    private final UsersMapper usersMapper;

    @Autowired
    public ProfileServiceImpl(UsersMapper usersMapper) {
        this.usersMapper = usersMapper;
    }

    @Override
    public UsersDTO getUserById(String userId) {
        UsersDTO user = usersMapper.getUserById(userId);
        if (user != null) {
            String[] mobileParts = user.getUserMobile().split("-");
            if (mobileParts.length == 3) {
                user.setUserMobilePart1(mobileParts[0]);
                user.setUserMobilePart2(mobileParts[1]);
                user.setUserMobilePart3(mobileParts[2]);
            }

            String[] emailParts = user.getUserEmail().split("@");
            if (emailParts.length == 2) {
                user.setUserEmailPart1(emailParts[0]);
                if (emailParts[1].equals("naver.com") || emailParts[1].equals("gmail.com") || emailParts[1].equals("daum.net")) {
                    user.setEmailDomain(emailParts[1]);
                } else {
                    user.setEmailDomainOther(emailParts[1]);
                }
            }
        }
        return user;
    }

    public boolean validateUserPassword(String userId, String userPasswd) {
        Map<String, String> params = new HashMap<>();
        params.put("userId", userId);
        params.put("userPasswd", userPasswd);
        int count = usersMapper.countValidUserPassword(params);
        return count > 0;
    }

    @Override
    public void updateUser(UsersDTO usersDTO) {
        // 휴대폰 번호를 하나의 문자열로 합치기
        String userMobile = usersDTO.getUserMobilePart1() + "-" +
                            usersDTO.getUserMobilePart2() + "-" +
                            usersDTO.getUserMobilePart3();
        usersDTO.setUserMobile(userMobile);

        // 이메일 주소를 하나의 문자열로 합치기
        String emailDomain = usersDTO.getEmailDomain();
        if ("other".equals(emailDomain)) {
            emailDomain = usersDTO.getEmailDomainOther();
        }
        String userEmail = usersDTO.getUserEmailPart1() + "@" + emailDomain;
        usersDTO.setUserEmail(userEmail);

        usersMapper.updateUser(usersDTO);
    }

   
    @Override
    public boolean checkCurrentPassword(String userId, String currentPassword) {
        return validateUserPassword(userId, currentPassword);
    }
    

    @Override
    public boolean deleteUser(String userId, String password) {
        if (validateUserPassword(userId, password)) {
            usersMapper.deleteUser(userId);
            return true;
        }
        return false;
    }
}