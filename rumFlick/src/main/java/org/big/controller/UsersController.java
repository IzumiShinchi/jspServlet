package org.big.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.big.dto.UsersDTO;
import org.big.service.UsersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;

@Controller
public class UsersController {
	private Logger log = LoggerFactory.getLogger(this.getClass());
//	일반회원 입장	
	@Autowired		//Controller 안에는 기본적으로 Service가 와있어야 한다!
	private UsersService usersService;
	
	@RequestMapping("/rum/signup.do")		//팡
	public String signUp() throws Exception {
		return "thymeleaf/users/signUp";
	}
	
	@RequestMapping("/rum/insertUsers.do")		//행위
	public String insertUser(
            @RequestParam("userId") String userId,
            @RequestParam("userPw") String userPw,
            @RequestParam("userName") String userName,
            @RequestParam("userBirth") String userBirth,
            @RequestParam("userGender") String userGender,
            @RequestParam("userPhone") String userPhone,
            @RequestParam("userNick") String userNick,
            @RequestParam("userAddr") String userAddr,
            @RequestParam("userAddrDetail") String userAddrDetail,
            @RequestParam("userSignUp") String userSignUp,
            RedirectAttributes redirectAttributes) throws Exception {

        UsersDTO users = new UsersDTO();
        users.setUserId(userId);
        users.setUserPw(userPw);
        users.setUserName(userName);
        users.setUserBirth(userGender);
        users.setUserGender(userBirth); // Assume userBirth is already in the correct format
        users.setUserPhone(userPhone);
        users.setUserNick(userNick);
        users.setUserAddr(userAddr);
        users.setUserAddrDetail(userAddrDetail);
        users.setUserSignUp(LocalDateTime.now());

        usersService.insertUsers(users);

        redirectAttributes.addAttribute("msg", 1);
        return "redirect:/rum/resultSignUp";
    }
	
	@RequestMapping("/rum/idCheck.do")
	public String idCheck(@RequestParam(value = "userId", required = false) String userId, Model model) throws Exception {
		model.addAttribute("userId", userId);
        if (userId != null) {
            boolean idCheck = usersService.idCheck(userId);
            model.addAttribute("isAvailable", idCheck);
        }
		return "thymeleaf/users/idCheck";
	}
	
	@RequestMapping("/rum/idSearch.do")
	public String idSearch(@RequestParam("userId") String userId, Model model) throws Exception {
		boolean idCheck = usersService.idCheck(userId);
        model.addAttribute("userId", userId);
        model.addAttribute("isAvailable", idCheck);
		return "thymeleaf/users/idSearch";
	}
	
	@RequestMapping("/rum/jusopopup.do")
	public String jusoPopup() throws Exception {
		return "thymeleaf/users/jusoPopup";
	}
	
	@RequestMapping("/rum/signupComplete.do")
    public String signupComplete(@RequestParam("msg") String msg, HttpSession session, Model model) {
        String loginId = (String) session.getAttribute("sessionId");
        model.addAttribute("msg", msg);
        model.addAttribute("loginId", loginId);
        return "thymeleaf/users/signUpComplete";
    }
	
	@RequestMapping("/rum/login.do")
	public String login(@RequestParam(value = "error", required = false) String error, Model model) throws Exception {
		model.addAttribute("error", error);
		return "thymeleaf/users/login";
	}

	@RequestMapping("/rum/loginusers.do")
	public String loginUsers(@RequestParam("userId") String userId, @RequestParam("userPw") String userPw, HttpSession session, Model model) throws Exception {
		try {
			UsersDTO users = usersService.loginUsers(userId, userPw, session);
            if (users != null) {
                session.setAttribute("sessionId", userId);
                return "redirect:/rum/main.do";
//                return "redirect:/rum/resultMember?msg=2";
            } else {
            	return "redirect:/rum/login.do?error=true";
//                return "redirect:/rum/login?error=1";
            }
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Error occurred during login.");
            return "login";
        }
	}
	
	@GetMapping("/rum/users.do")
    public String getUsers(Model model) throws Exception {
        List<UsersDTO> users = usersService.getUsersDetail();
        model.addAttribute("users", users);
        return "usersDetail";
    }

	@RequestMapping("/rum/userDetail.do")
	public String userDetail(@RequestParam("userId") String userId, Model model) throws Exception {
        UsersDTO users = usersService.usersDetail(userId);
        model.addAttribute("user", users);
        return "thymeleaf/users/userDetail";
    }
	
	
	@GetMapping("/rum/updateUsers.do")
    public String showUpdateUsersForm(HttpSession session, Model model) throws Exception {
        String sessionId = (String) session.getAttribute("sessionId");
        if (sessionId == null) {
            return "redirect:/rum/login.do";
        }
        
        UsersDTO users = usersService.getUsersById(sessionId);
        model.addAttribute("user", users);

        return "updateUsers";
    }

    @PostMapping("/rum/updateUsers.do")
    public String updateUsers(
            @RequestParam("id") String userId,
            @RequestParam("password") String userPw,
            @RequestParam("name") String userName,
            @RequestParam("gender") String userGender,
            @RequestParam("birthyy") String birthYear,
            @RequestParam("birthmm") String birthMonth,
            @RequestParam("birthdd") String birthDay,
            @RequestParam("phone") String userPhone,
    		@RequestParam("nick") String userNick,
            @RequestParam("addr") String userAddr,
            @RequestParam("addrDetail") String userAddrDetail,
            RedirectAttributes redirectAttributes) throws Exception {
    	
    	String userBirth = birthYear + "/" + birthMonth + "/" + birthDay;
    	
    	UsersDTO users = new UsersDTO();
        users.setUserId(userId);
        users.setUserPw(userPw);
        users.setUserName(userName);
        users.setUserBirth(userGender);
        users.setUserGender(userBirth); // Assume userBirth is already in the correct format
        users.setUserPhone(userPhone);
        users.setUserNick(userNick);
        users.setUserAddr(userAddr);
        users.setUserAddrDetail(userAddrDetail);
        
        boolean isUpdated = usersService.updateUsers(users);

        if (isUpdated) {
            redirectAttributes.addFlashAttribute("message", "회원정보가 성공적으로 업데이트되었습니다.");
            return "redirect:/rum/signUpComplete.do?msg=0";
        } else {
            redirectAttributes.addFlashAttribute("error", "회원정보 업데이트에 실패했습니다.");
            return "redirect:/rum/updateUser.do";
        }
    }
	
//	@RequestMapping("/rum/updateUsers.do")
//	public String updateUsers(UsersDTO users) throws Exception {
//		usersService.updateUsers(users);
//		return "redirect:/rum/main.do";
//	}
	
	@RequestMapping("/rum/deleteUsers.do")
	public String deleteUsers(HttpSession session, RedirectAttributes redirectAttributes) throws Exception {
        String sessionId = (String) session.getAttribute("sessionId");

        if (sessionId == null) {
            redirectAttributes.addFlashAttribute("error", "로그인이 필요합니다.");
            return "redirect:/rum/login.do";
        }

        boolean isDeleted = usersService.deleteUsers(sessionId);

        if (isDeleted) {
            session.invalidate(); // 세션 무효화
            redirectAttributes.addFlashAttribute("message", "회원탈퇴가 성공적으로 처리되었습니다.");
            return "redirect:/rum/signUpComplete.do";
        } else {
            redirectAttributes.addFlashAttribute("error", "회원탈퇴에 실패했습니다.");
            return "redirect:/rum/users.do";
        }
	}
	
	@RequestMapping("/rum/logout.do")
	public String logout(HttpSession session) throws Exception{
		session.invalidate();
        return "redirect:/rum/login.do";
	}
}
