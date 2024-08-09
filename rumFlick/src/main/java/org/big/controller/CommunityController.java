package org.big.controller;

import java.util.List;

import org.big.dto.CommunityDTO;
import org.big.service.CommunityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpSession;

@Controller
public class CommunityController {
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CommunityService communityService;
	
	@RequestMapping("/rum/openCommunity.do")
	public ModelAndView openBoardList() throws Exception {					//	Model==DTO | View==구현한 Page(.html 등)
		log.info("openBoardList");
		ModelAndView mv = new ModelAndView("thymeleaf/community/commuList");
		List<CommunityDTO> list = communityService.CommuList();
		mv.addObject("list", list);
		
		return mv;
	}
	
//	@RequestMapping("/rum/openCommunity.do")
//	public ModelAndView openCommunity(HttpSession session) throws Exception {
////		log.info("openCommunity");
////		ModelAndView mv = new ModelAndView("thymeleaf/community/commuList");
////		List<CommunityDTO> list = communityService.CommuList();
////		mv.addObject("list", list);
////		return mv;
//		
//		ModelAndView mv = new ModelAndView("thymeleaf/community/commuList");
//		 List<CommunityDTO> list = communityService.CommuList();
//		    mv.addObject("list", list);
//	    log.info("openCommunity");
//
//	    String mNo = (String) session.getAttribute("mNo");
//	    String mId = (String) session.getAttribute("mId");
//	    String sessionId = (String) session.getId();
//
//	   
//	    
////	    if (mNo == null || mId == null) {
////	        // 세션 데이터가 없을 경우 에러 페이지로 리다이렉트
////	        mv.addObject("error", "Session data is missing");
////	        mv.setViewName("errorPage"); // 에러 페이지 이름 설정
////	        return mv;
////	    }
//
//	    mv.addObject("mNo", mNo);
//	    mv.addObject("mId", mId);
//	    mv.addObject(sessionId);
//
//	    // 커뮤니티 목록을 가져오는 로직
//	   
//
//	    return mv;
//	}
	
	@RequestMapping("/rum/commuWrite.do")
	public String CommuWrite(HttpSession session) throws Exception {
	    String mNo = (String) session.getAttribute("mNo");
	    String mId = (String) session.getAttribute("mId");
	    String sessionId = (String) session.getId();
		session.getMaxInactiveInterval();
		return "thymeleaf/community/CommuWrite";
	}
	
	@RequestMapping("/rum/insertCommu.do")
	public String insertCommu(CommunityDTO commu, HttpSession session) throws Exception {
	    String mNo = (String) session.getAttribute("mNo");
	    String mId = (String) session.getAttribute("mId");
	    String sessionId = (String) session.getId();
		session.getMaxInactiveInterval();
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
