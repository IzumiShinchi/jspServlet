package org.big.prj.controller;

import java.io.File;
import java.net.URLEncoder;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.big.prj.dto.BoardDTO;
import org.big.prj.dto.BoardFileDTO;
import org.big.prj.service.BoardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class BoardController {
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired	//Controller 안에는 기본적으로 Service가 와있어야 한다!
	private BoardService boardService;
	
	@RequestMapping("/board/gaga.do")
	public String mainpage() throws Exception {
		return "thymeleaf/board/index";
	}
	
	@RequestMapping("/board/openBoardList.do")
	public ModelAndView openBoardList() throws Exception {					//	Model==DTO | View==구현한 Page(.html 등)
		log.info("openBoardList");
		ModelAndView mv = new ModelAndView("thymeleaf/board/boardList");
		List<BoardDTO> list = boardService.selectBoardList();
		mv.addObject("list", list);
		
		return mv;
	}

	@RequestMapping("/board/openBoardWrite.do")
	public String openBoardWrite() throws Exception {
		return "thymeleaf/board/boardWrite";
	}
	
	@RequestMapping("/board/insertBoard.do")
	public String insertBoard(BoardDTO board, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception {
		boardService.insertBoard(board, multipartHttpServletRequest);
		return "redirect:/board/openBoardList.do";
	}
	
	@RequestMapping("/board/openBoardDetail.do")
	public ModelAndView openBoardDetail(@RequestParam("boardIdx") int boardIdx) throws Exception {
		ModelAndView mv = new ModelAndView("thymeleaf/board/boardDetail");
		
		BoardDTO board = boardService.selectBoardDetail(boardIdx);
		mv.addObject("board", board);
		
		return mv;
	}
	
	@RequestMapping("/board/updateBoard.do")
	public String updateBoard(BoardDTO board) throws Exception {
		boardService.updateBoard(board);
		return "redirect:/board/openBoardList.do";
	}
	
	@RequestMapping("/board/deleteBoard.do")			//자료형이 명시된 변수는 RequestParam 입력.
	public String deleteBoard(@RequestParam("boardIdx") int boardIdx) throws Exception {
		boardService.deleteBoard(boardIdx);
		return "redirect:/board/openBoardList.do";
	}
	
	@RequestMapping("/board/test.do")
	public String test() throws Exception {
		return "/test";
	}
	

	@RequestMapping("/board/downloadBoardFile.do")
	public void downloadBoardFile(@RequestParam("idx") int idx, @RequestParam("boardIdx") int boardIdx, HttpServletResponse response) throws Exception {
		try {
				BoardFileDTO boardFile = boardService.selectBoardFileInformation(idx, boardIdx);
				
			if(ObjectUtils.isEmpty(boardFile) == false) {
				String fileName = boardFile.getOriginalFileName();
				
				byte[] files = FileUtils.readFileToByteArray(new File(boardFile.getStoredFilePath()));
				
				response.setContentType("application/octet-stream");
				response.setContentLength(files.length);
				response.setHeader("Content-Disposition", "attachment; fileName=\"" + URLEncoder.encode(fileName, "UTF-8") + "\";");
				response.setHeader("Content-Transfer-Encoding", "binary");
				
				response.getOutputStream().write(files);
				response.getOutputStream().flush();
				response.getOutputStream().close();
			}
		} catch(Exception e) {
			log.error(e.getMessage());
			e.getStackTrace();
			e.printStackTrace();
		}
	}
	
}