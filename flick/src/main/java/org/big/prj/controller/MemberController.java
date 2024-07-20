package org.big.prj.controller;

import java.io.File;
import java.net.URLEncoder;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.big.prj.dto.BoardDTO;
import org.big.prj.dto.BoardFileDTO;
import org.big.prj.service.BoardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class MemberController {
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired // Controller 안에는 기본적으로 Service가 와있어야 한다!
	private BoardService boardService;

	@RequestMapping("/board/login.do")
	public String loginpage() throws Exception {
		return "thymeleaf/member/login";
	}

	@RequestMapping("/board/logout.do")
	public String logoutpage() throws Exception {
		return "thymeleaf/member/logout";
	}

	@RequestMapping("/board/signup.do")
	public String signUppage() throws Exception {
		return "thymeleaf/member/signUp";
	}

	@RequestMapping("/board/loginuser.do")
	public ModelAndView loginuser() throws Exception {
	    // Model==DTO | View==구현한 Page(.html 등)
	    ModelAndView mv = new ModelAndView("thymeleaf/board/loginuser");
//	    List<BoardDTO> list = boardService.selectBoardList();
//	    mv.addObject("user", user);
	    
	    return mv;
	}


}
