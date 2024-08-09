package org.big.controller;

import java.util.List;

import org.big.dto.NoticeDTO;
import org.big.service.NoticeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class NoticeController {
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private NoticeService noticeService;
	
	@RequestMapping("/notice/openNotice.do")
	public ModelAndView openNoticeList(HttpSession session) throws Exception {					//	Model==DTO | View==구현한 Page(.html 등)
		log.info("openNoticeList");
		ModelAndView mv = new ModelAndView("thymeleaf/notice/noticeList");
		List<NoticeDTO> list = noticeService.NoticeList();
		mv.addObject("list", list);
		// 로그인 정보가 있는지 확인
	    String sessionId = session.getId();
	    mv.addObject("sessionId", sessionId);
		
		return mv;
	}

	@RequestMapping("/notice/noticeWrite.do")
	public String NoticeWrite(HttpSession session) throws Exception {
	    String mNo = (String) session.getAttribute("mNo");
	    String mId = (String) session.getAttribute("mId");
	    String sessionId = (String) session.getId();
		session.getMaxInactiveInterval();
		return "thymeleaf/notice/noticeWrite";
	}
	
	@RequestMapping("/notice/insertNotice.do")
	public String insertNotice(NoticeDTO notice, HttpSession session) throws Exception {
	    String mNo = (String) session.getAttribute("mNo");
	    String mId = (String) session.getAttribute("mId");
	    String sessionId = (String) session.getId();
		session.getMaxInactiveInterval();
		noticeService.insertNotice(notice);
		return "redirect:/notice/openNotice.do";
	}
	
	@RequestMapping("/notice/noticeDetail.do")
	public ModelAndView openNoticeDetail(@RequestParam("nCode") int nCode) throws Exception {
		ModelAndView mv = new ModelAndView("thymeleaf/notice/noticeDetail");
		NoticeDTO notice = noticeService.NoticeDetail(nCode);
		mv.addObject("notice", notice);
		return mv;
	}
	
	@RequestMapping("/notice/updateNotice.do")
	public String updateNotice(NoticeDTO notice) throws Exception {
		noticeService.updateNotice(notice);
		return "redirect:/notice/openNotice.do";
	}
	
	@RequestMapping("/notice/deleteNotice.do")
	public String deleteNotice(@RequestParam("noticeCode") int noticeCode) throws Exception {
		noticeService.deleteNotice(noticeCode);
		return "redirect:/notice/openNotice.do";
	}
}
