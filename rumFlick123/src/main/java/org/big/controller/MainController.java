package org.big.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	private Logger log = LoggerFactory.getLogger(this.getClass());
//	메인 페이지 및 관리자 입장
//	@Autowired
//	private MainService mainService;
//	
	@RequestMapping("/rum/main.do")
	public String mainPage() throws Exception {
		return "thymeleaf/main/index";
	}
	
	

	
}
