package org.big.controller;

import org.big.service.MainService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class MainController {
	private Logger log = LoggerFactory.getLogger(this.getClass());
//	메인 페이지 및 관리자 입장
	@Autowired
	private MainService mainService;
	
	@RequestMapping("/rum/main.do")
	public String mainPage() throws Exception {
		return "thymeleaf/main/index";
	}
	
	@RequestMapping("/rum/requireLogin.do")
	public String requireLogin() throws Exception {
		return "thymeleaf/users/requireLogin";
	}
	
	@RequestMapping("/rum/verifyyAge.do")
	public String verifyAge() throws Exception {
		return "thymeleaf/users/vefifyAge";
	}
	
	@RequestMapping("/rum/login.do")
    public String pageLogin(HttpSession session, Model model) {
        String sessionId = (String) session.getAttribute("sessionId");
        model.addAttribute("sessionId", sessionId);
        return "example"; // Thymeleaf 템플릿 이름
    }
//	
//	@RequestMapping("/openIndex.do")
//	public ModelAndView openBoardList() throws Exception {					//	Model==DTO | View==구현한 Page(.html 등)
//		log.info("openBoardList");
//		ModelAndView mv = new ModelAndView("thymeleaf/main/index");
//		
//		return mv;
//	}
//	
//	public ModelAndView openMain() throws Exception {
//		log.info("openMain");
//		ModelAndView mv = new ModelAndView("thymeleaf/main/rumFlickMain");
//		
//		return mv;
//	}
	
}
