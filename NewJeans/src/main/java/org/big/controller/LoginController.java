package org.big.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
	
	@RequestMapping("/rumflick/login.do")
	public String openLogin() throws Exception {
		return "/rumflick/login";
	}

	@RequestMapping("/rumflick/login_ok.do")
	public String login_Ok() throws Exception {
		return "/rumflick/login_ok";
	}
	
	@RequestMapping("/rumflick/member.do")
	public String member() throws Exception {
		return "/rumflick/member";
	}
	
	@RequestMapping("/rumflick/idcheck.do")
	public String idCheck() throws Exception {
		return "/rumflick/member_ok";
	}
		
	@RequestMapping("/rumflick/idsearch.do")
	public String idSearch() throws Exception {
		return "/rumflick/member_ok";
	}
		
	@RequestMapping("/rumflick/jusopopup.do")
	public String jusoPopup() throws Exception {
		return "/rumflick/member_ok";
	}
	
	@RequestMapping("/rumflick/memberok.do")
	public String memberok() throws Exception {
		return "/rumflick/member_ok";
	}
	
	@RequestMapping("/rumflick/logout.do")
	public String logout() throws Exception {
		return "/rumflick/logout";
	}
	
	@RequestMapping("/rumflick/list.do")
	public String list() throws Exception {
		return "/rumflick/list";
	}
	
	@RequestMapping("/rumflick/edit.do")
	public String edit() throws Exception {
		return "/rumflick/edit";
	}
	
	@RequestMapping("/rumflick/editok.do")
	public String ecitok() throws Exception {
		return "/rumflick/edit_ok";
	}
	
	@RequestMapping("/rumflick/delete.do")
	public String delete() throws Exception {
		return "/rumflick/delete";
	}
		
	@RequestMapping("/rumflick/deleteok.do")
	public String deleteok() throws Exception {
		return "/rumflick/delete_ok";
	}
	
}
