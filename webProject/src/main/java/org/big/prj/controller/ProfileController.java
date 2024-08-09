package org.big.prj.controller;

import org.big.prj.service.UsersService;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import org.big.prj.dto.UsersDTO;
import org.big.prj.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    private final ProfileService profileService;
    private final UsersService usersService; // UsersService를 ProfileController에 추가

    @Autowired
    public ProfileController(ProfileService profileService, UsersService usersService) {
        this.profileService = profileService;
        this.usersService = usersService; // UsersService를 주입받음
    }

    @GetMapping("")
    public String viewProfile(HttpSession session, Model model,RedirectAttributes redirectAttributes) {
        String userId = (String) session.getAttribute("userId");
        if (userId == null) {
        	redirectAttributes.addFlashAttribute("errorMessage", "로그인이 필요합니다.");
            return "redirect:/login?error=" + URLEncoder.encode("로그인이 필요합니다.", StandardCharsets.UTF_8);
        }
        UsersDTO usersDTO = profileService.getUserById(userId);
        model.addAttribute("usersDTO", usersDTO);
        return "thymeleaf/users/profile";
    }

    @PostMapping("/update")
    public String updateProfile(@ModelAttribute UsersDTO usersDTO, @RequestParam("userPasswd") String userPasswd, HttpSession session, RedirectAttributes redirectAttributes) {
        String userId = usersDTO.getUserId();
        if (profileService.validateUserPassword(userId, userPasswd)) {
            profileService.updateUser(usersDTO);
            session.invalidate(); // 세션 무효화
            return "redirect:/login?update=true"; // 업데이트 후 로그인 페이지로 리다이렉트
        } else {
            redirectAttributes.addFlashAttribute("error", "비밀번호가 틀렸습니다.");
            return "redirect:/profile";
        }
    }

    @PostMapping("/delete")
    public String deleteProfile(@RequestParam("userPasswd") String userPasswd, HttpSession session, RedirectAttributes redirectAttributes) {
        String userId = (String) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/login";
        }
        if (profileService.deleteUser(userId, userPasswd)) {
            session.invalidate();
            return "redirect:/login?deleted=true";
        } else {
            redirectAttributes.addFlashAttribute("error", "비밀번호가 일치하지 않습니다.");
            return "redirect:/profile";
        }
    }

    @GetMapping("/checkUserMobile")
    @ResponseBody
    public Map<String, String> checkUserMobile(@RequestParam("userMobile") String userMobile) {
        boolean isAvailable = usersService.isUserMobileAvailable(userMobile);
        Map<String, String> response = new HashMap<>();
        response.put("message", isAvailable ? "사용 가능한 휴대폰 번호입니다." : "이미 사용 중인 휴대폰 번호입니다.");
        return response;
    }

    @GetMapping("/checkUserEmail")
    @ResponseBody
    public Map<String, String> checkUserEmail(@RequestParam("email") String userEmail) {
        boolean isAvailable = usersService.isUserEmailAvailable(userEmail);
        Map<String, String> response = new HashMap<>();
        response.put("message", isAvailable ? "사용 가능한 이메일입니다." : "이미 사용 중인 이메일입니다.");
        return response;
    }
}
