package org.big.controller;

import java.util.List;

import org.big.dto.CommunityDTO;
import org.big.service.CommunityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CommunityController {
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CommunityService communityService;
	
	@RequestMapping("/rum/openCommunity.do")
	public ModelAndView openCommunity() throws Exception {
		log.info("openCommunity");
		ModelAndView mv = new ModelAndView("thymeleaf/community/commuList");
		List<CommunityDTO> list = communityService.CommuList();
		mv.addObject("list", list);
		return mv;
	}
	
	@RequestMapping("/rum/commuWrite.do")
	public String CommuWrite() throws Exception {
		return "thymeleaf/community/CommuWrite";
	}
	
	@RequestMapping("/rum/insertCommu.do")
	public String insertCommu(CommunityDTO commu) throws Exception {
		communityService.insertCommu(commu);
		return "redirect:/rum/openCommunity.do";
	}
	
	@RequestMapping("/rum/commuDetail.do")
	public ModelAndView openCommuDetail(@RequestParam("commuCode") int commuCode) throws Exception {
		ModelAndView mv = new ModelAndView("thymeleaf/community/commuDetail");
		CommunityDTO commu = communityService.CommuDetail(commuCode);
		mv.addObject("commu", commu);
		return mv;
	}
	
	@RequestMapping("/rum/updateCommu.do")
	public String updateCommu(CommunityDTO commu) throws Exception {
		communityService.updateCommu(commu);
		return "redirect:/rum/openCommunity.do";
	}
	
	@RequestMapping("/rum/deleteCommu.do")
	public String deleteCommu(@RequestParam("commuCode") int commuCode) throws Exception {
		communityService.deleteCommu(commuCode);
		return "redirect:/rum/openCommunity.do";
	}
}
