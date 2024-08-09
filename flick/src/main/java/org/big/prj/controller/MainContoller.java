package org.big.prj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainContoller {

	@RequestMapping("/main.do")
	public String mainPage() throws Exception {
		return "templates/thymeleaf/main/index";
	}
}
