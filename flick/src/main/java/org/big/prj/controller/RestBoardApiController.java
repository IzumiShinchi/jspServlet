package org.big.prj.controller;

import java.util.List;

import org.big.prj.dto.BoardDTO;
import org.big.prj.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@RestController						//Rest 는 value, method 지정 필수!
public class RestBoardApiController {

	@Autowired	//Controller 안에는 기본적으로 Service가 와있어야 한다!
	private BoardService boardService;
	
	@RequestMapping(value="/api/board", method=RequestMethod.GET)
	public List<BoardDTO> openBoardList() throws Exception {					//	Model==DTO | View==구현한 Page(.html 등)
		return boardService.selectBoardList();
	}
	
	@RequestMapping(value="/api/board/write", method=RequestMethod.POST)
	public void insertBoard(@RequestBody BoardDTO board, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception {
		boardService.insertBoard(board, multipartHttpServletRequest);
	}
		
	@RequestMapping(value="/api/board/{boardIdx}", method=RequestMethod.GET)
	public BoardDTO openBoardDetail(@PathVariable("boardIdx") int boardIdx) throws Exception {
		return boardService.selectBoardDetail(boardIdx);
	}
	
	@RequestMapping(value="/api/board/{boardIdx}", method=RequestMethod.PUT)
	public String updateBoard(@RequestBody BoardDTO board) throws Exception {
		boardService.updateBoard(board);
		return "redirect:/board";
	}
	
	@RequestMapping(value="/api/board/{boardIdx}", method=RequestMethod.DELETE)			//자료형이 명시된 변수는 RequestParam 입력.
	public String deleteBoard(@PathVariable("boardIdx") int boardIdx) throws Exception {
		boardService.deleteBoard(boardIdx);
		return "redirect:/board";
	}
}
