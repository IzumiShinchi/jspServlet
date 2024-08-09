package org.big.service;

import java.util.List;

import org.big.dto.BoardDTO;
import org.big.dto.BoardFileDTO;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public interface BoardService {

	List<BoardDTO> selectBoardList() throws Exception;
	
	void insertBoard(BoardDTO board, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception;
	
	BoardDTO selectBoardDetail(int boardIdx) throws Exception;
	
	void updateBoard(BoardDTO board) throws Exception;
	
	void deleteBoard(int boardIdx) throws Exception;
	
	BoardFileDTO selectBoardFileInformation(int idx, int boardIdx) throws Exception;
}
