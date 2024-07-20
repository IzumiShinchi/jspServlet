package org.big.prj.controller;

import java.util.List;

import org.big.prj.dto.BoardDTO;
import org.big.prj.dto.FmovieDTO;
import org.big.prj.service.FmovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MovieController {
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired // Controller 안에는 기본적으로 Service가 와있어야 한다!
	private FmovieService fmovieService;


	@RequestMapping("/board/tani.do")
	public String tani() throws Exception {
		return "thymeleaf/movie/taniList";
	}

	@RequestMapping("/board/kmovie.do")
	public ModelAndView kmovie() throws Exception {
	    // Model==DTO | View==구현한 Page(.html 등)
	    ModelAndView mv = new ModelAndView("thymeleaf/movie/kmovieList");
//	    List<FmovieDTO> list = fmovieService.selectFmovieList();
//	    mv.addObject("list", list);
	    return mv;
	}
	
	@RequestMapping("/board/fmovie.do")
	public ModelAndView fmovie() throws Exception {
	    // Model==DTO | View==구현한 Page(.html 등)
	    ModelAndView mv = new ModelAndView("thymeleaf/movie/fmovieList");
	    List<FmovieDTO> list = fmovieService.selectFmovieList();
	    mv.addObject("list", list);
	    
	    return mv;
	}
	
	@RequestMapping("/board/openFmovieDetail.do")
	public ModelAndView openFmovieDetail(@RequestParam("contentsCode") String contentsCode) throws Exception {
		ModelAndView mv = new ModelAndView("thymeleaf/movie/fmovieDetail");
		
		FmovieDTO fmovie = fmovieService.selectFmovieDetail(contentsCode);
		mv.addObject("fmovie", fmovie);
		
		return mv;
	}


}
