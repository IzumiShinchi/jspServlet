package org.big.controller;

import java.util.List;

import org.big.dto.ContentsDTO;
import org.big.dto.ContentsFileDTO;
import org.big.service.ContentsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ContentsController {
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ContentsService contentsService;
	
	@RequestMapping("/rum/contentsList.do")
	public ModelAndView contentsList() throws Exception {
		log.info("contentsList");
		ModelAndView mv = new ModelAndView("thymeleaf/contents/contentsList");
		List<ContentsDTO> list = contentsService.contentsList();
		mv.addObject("list", list);
		return mv;
	}
	
	@RequestMapping("/rum/kmovie.do")
	public ModelAndView openKmovie() throws Exception {
		ModelAndView mv = new ModelAndView("thymeleaf/contents/kMovieList");
		List<ContentsDTO> list = contentsService.kMovieList();
		mv.addObject("list", list);
		return mv;
	}
	
	@RequestMapping("/rum/fmovie.do")
	public ModelAndView openFmovie() throws Exception {
		ModelAndView mv = new ModelAndView("thymeleaf/contents/fMovieList");
		List<ContentsDTO> list = contentsService.fMovieList();
		List<ContentsFileDTO> filelist = contentsService.contentsFileList();
		mv.addObject("list", list);
		mv.addObject("filelist", filelist);
		return mv;
	}

	@RequestMapping("/rum/tani.do")
	public ModelAndView openTani() throws Exception {
		ModelAndView mv = new ModelAndView("thymeleaf/contents/tAniList");
		List<ContentsDTO> list = contentsService.tAniList();
		mv.addObject("list", list);
		return mv;
	}

	@RequestMapping("/rum/kdrama.do")
	public ModelAndView openKdrama() throws Exception {
		ModelAndView mv = new ModelAndView("thymeleaf/contents/kDramaList");
		List<ContentsDTO> list = contentsService.kDramaList();
		mv.addObject("list", list);
		return mv;
	} 

	@RequestMapping("/rum/fdrama.do")
	public ModelAndView openFdrama() throws Exception {
		ModelAndView mv = new ModelAndView("thymeleaf/contents/fDramaList");
		List<ContentsDTO> list = contentsService.fDramaList();
		mv.addObject("list", list);
		return mv;
	}

	@RequestMapping("/rum/ani.do")
	public ModelAndView openAni() throws Exception {
		ModelAndView mv = new ModelAndView("thymeleaf/contents/aniList");
		List<ContentsDTO> list = contentsService.aniList();
		mv.addObject("list", list);
		return mv;
	}
	
	@RequestMapping("/rum/contentdetail.do")
	public ModelAndView contentDetail(@RequestParam("contentsCode") String contentsCode) throws Exception {
		ModelAndView mv = new ModelAndView("thymeleaf/contents/contentDetail");
		ContentsDTO content = contentsService.contentDetail(contentsCode);
		mv.addObject("content", content);
		return mv;
	}
	
	@RequestMapping("/rum/registContent.do")
	public String registContent() throws Exception {
		return "thymeleaf/contents/registContent";
	}
	
	@RequestMapping("/rum/registComplete.do")
	public String registComplete(ContentsDTO content, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception {
		contentsService.registContent(content, multipartHttpServletRequest);
		return "redirect:/rum/login.do";
	}
	
	@RequestMapping("/rum/updateContent.do")
	public String updateContent() throws Exception {
		return "thymeleaf/contents/updateContent";
	}
	
	@RequestMapping("/rum/updateComplete.do")
	public String updateComplete(ContentsDTO content) throws Exception {
		contentsService.updateContent(content);
		return "redirect:/rum/admin.do";
	}
	
	@RequestMapping("/rum/deleteContent.do")
	public String deleteContent() throws Exception {
		return "thymeleaf/contents/deleteContent";
	}
	
	@RequestMapping("/rum/deleteComplete.do")
	public String deleteComplete(@RequestParam("contentsCode") String contentsCode) throws Exception {
		contentsService.deleteContent(contentsCode);
		return "redirect:/rum/admin.do";
	}
}
