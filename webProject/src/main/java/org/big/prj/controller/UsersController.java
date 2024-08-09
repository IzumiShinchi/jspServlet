package org.big.prj.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.big.prj.dto.UsersDTO;
import org.big.prj.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class UsersController {

    private final UsersService usersService;

    @Autowired
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @RequestMapping("/jusoPopup")
    public String jusoPopup() {
        return "thymeleaf/users/jusoPopup";
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("usersDTO", new UsersDTO());
        return "thymeleaf/users/register";
    }
    
 

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("usersDTO") UsersDTO usersDTO, Model model) {
    	String userMobile = usersDTO.getUserMobilePart1()+"-"+usersDTO.getUserMobilePart2()+"-"+usersDTO.getUserMobilePart3();
    	String userEmail = usersDTO.getUserEmailPart1()+"@"+usersDTO.getEmailDomain();
    	usersDTO.setUserMobile(userMobile);
    	usersDTO.setUserEmail(userEmail);
        List<String> errors = usersService.registerUser(usersDTO);
        if (errors != null && !errors.isEmpty()) {
            model.addAttribute("error", String.join(", ", errors));
            model.addAttribute("usersDTO", usersDTO); // 기존 입력값 유지
            return "thymeleaf/users/register";
        }
        return "redirect:/registerSuccess";
    }
    
    @GetMapping("/registerSuccess")
    public String registerSuccess() {
        return "thymeleaf/users/registerSuccess";
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("loginDTO", new UsersDTO());
        return "thymeleaf/users/login";
    }

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<Map<String, String>> loginUser(@RequestBody UsersDTO loginDTO, HttpSession session, HttpServletRequest request) {
        if (usersService.loginUser(loginDTO.getUserId(), loginDTO.getUserPasswd())) {
            session.setAttribute("userId", loginDTO.getUserId());
            session.setMaxInactiveInterval(3600); // 1 hour

            String redirectUrl;
            if ("admin".equalsIgnoreCase(loginDTO.getUserId())) {
                redirectUrl = "/admin.do";
            } else {
                redirectUrl = request.getParameter("redirectUrl");
                if (redirectUrl == null || redirectUrl.isEmpty()) {
                    redirectUrl = "/";
                }
            }

            Map<String, String> response = new HashMap<>();
            response.put("redirectUrl", redirectUrl);

            return ResponseEntity.ok(response);
        } else {
            Map<String, String> response = new HashMap<>();
            response.put("error", "아이디 또는 비밀번호가 일치하지 않습니다.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "thymeleaf/users/login";
    }

    @PostMapping("/extendSession")
    @ResponseBody
    public String extendSession(HttpSession session) {
        session.setMaxInactiveInterval(3600); // 세션을 1시간 연장
        return "로그인 시간이 1시간 연장되었습니다.";
    }

    @GetMapping("/findUserId")
    @ResponseBody
    public String findUserId(@RequestParam("userName") String userName, @RequestParam("userBirth") String userBirth) {
        String userId = usersService.findUserId(userName, userBirth);
        return userId != null ? "아이디는 " + userId + "입니다." : "일치하는 아이디가 없습니다.";
    }

    @GetMapping("/findUserPassword")
    @ResponseBody
    public String findUserPassword(@RequestParam("userId") String userId, @RequestParam("userEmail") String userEmail) {
        String userPassword = usersService.findUserPassword(userId, userEmail);
        return userPassword != null ? "비밀번호는 " + userPassword + "입니다." : "일치하는 비밀번호가 없습니다.";
    }

    @GetMapping("/checkUserId")
    @ResponseBody
    public String checkUserId(@RequestParam("userId") String userId) {
        boolean isAvailable = usersService.isUserIdAvailable(userId);
        return isAvailable ? "사용 가능한 아이디입니다." : "이미 사용 중인 아이디입니다.";
    }

    @GetMapping("/checkUserEmail")
    @ResponseBody
    public String checkUserEmail(@RequestParam("email") String userEmail) {
        boolean isAvailable = usersService.isUserEmailAvailable(userEmail);
        return isAvailable ? "사용 가능한 이메일입니다." : "이미 사용 중인 이메일입니다.";
    }

    @GetMapping("/checkUserMobile")
    @ResponseBody
    public String checkUserMobile(@RequestParam("userMobile") String userMobile) {
        boolean isAvailable = usersService.isUserMobileAvailable(userMobile);
        return isAvailable ? "사용 가능한 휴대폰 번호입니다." : "이미 사용 중인 휴대폰 번호입니다.";
    }
}
