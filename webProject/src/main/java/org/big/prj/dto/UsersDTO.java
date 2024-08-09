package org.big.prj.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UsersDTO {
    private String userNo;

  
    private String userId;
    private String userName;
    private String userPasswd;
    private String confirmPassword;
    private String userAd;
    private String userAdd;
    private String userPost;
    private String userMobile;
    private String userEmail;
    private String userG;
    private String userBirth;

    // 다중 필드
    private String userMobilePart1;
    private String userMobilePart2;
    private String userMobilePart3;
    private String userEmailPart1;
    private String emailDomain;
    private String emailDomainOther;

    // 추가: 비밀번호 확인 로직
    public boolean isPasswordConfirmed() {
        return this.userPasswd.equals(this.confirmPassword);
    }

    // 추가: 이메일 및 전화번호 조합 로직
    public void combineMobile() {
        this.userMobile = String.format("%s-%s-%s", userMobilePart1, userMobilePart2, userMobilePart3);
    }

    public void combineEmail() {
        if ("기타".equals(emailDomain)) {
            this.userEmail = String.format("%s@%s", userEmailPart1, emailDomainOther);
        } else {
            this.userEmail = String.format("%s@%s", userEmailPart1, emailDomain);
        }
    }
    
    public void splitMobile() {
        String[] mobileParts = this.userMobile.split("-");
        if (mobileParts.length == 3) {
            this.userMobilePart1 = mobileParts[0];
            this.userMobilePart2 = mobileParts[1];
            this.userMobilePart3 = mobileParts[2];
        }
    }

    public void splitEmail() {
        String[] emailParts = this.userEmail.split("@");
        if (emailParts.length == 2) {
            this.userEmailPart1 = emailParts[0];
            if (emailParts[1].equals("naver.com") || emailParts[1].equals("gmail.com") || emailParts[1].equals("daum.net")) {
                this.emailDomain = emailParts[1];
            } else {
                this.emailDomainOther = emailParts[1];
            }
        }
    }

}
