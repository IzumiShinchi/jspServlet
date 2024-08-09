package org.big.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

	
	@RequestMapping("/rum/member.do")
	public String member() {
		return "/member";
	}
	
	@RequestMapping("/rum/idCheck.do")
	public String idCheck() throws Exception {
		return "/idCheck";
	}
	
	@RequestMapping("/rum/idSearch.do")
	public String idSearch() throws Exception {
		return "/idSearch";
	}	
	
	@RequestMapping("/rum/jusopopup.do")
	public String jusoPopup() {
		return "/jusoPopup";
	}
	
	@RequestMapping("/rum/memberok.do")
	public String memberok() {
		return "/member_ok";
	}
	
	@RequestMapping("/rum/list.do")
	public String list() {
		return "/list";
	}

	@RequestMapping("/rum/loginok.do")
	public String loginok() {
		return "/login_ok";
	}
	
	@RequestMapping("/rum/admin.do")
	public String admin() {
		return "/admin";
	}
	
	@RequestMapping("/rum/login.do")
	public String login() {
		return "/login";
	}
	
	@RequestMapping("/rum/logout.do")
	public String logout() {
		return "/logout";
	}
	
	@RequestMapping("/rum/edit.do")
	public String edit() {
		return "/edit";
	}

	@RequestMapping("/rum/editok.do")
	public String editok() {
		return "/edit_ok";
	}
	
	@RequestMapping("/rum/delete.do")
	public String delete() {
		return "/delete";
	}

	@RequestMapping("/rum/deleteok.do")
	public String deleteok() {
		return "/delete_ok";
	}
}
