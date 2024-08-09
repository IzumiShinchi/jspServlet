package org.big.prj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import ch.qos.logback.core.model.Model;

@Controller
public class MainController {

	@GetMapping("/")
	public String main() {
		return "thymeleaf/main/main.html";
	}
	
	@GetMapping("/admin.do")
	public String admin_main() {
		return "thymeleaf/main/admin_main.html";
	}

	
}
