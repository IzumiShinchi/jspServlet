package org.big.prj.service;

import java.util.List;

import org.big.prj.dto.BoardDTO;

public interface BoardService {

	List<BoardDTO> selectBoardList() throws Exception;			//리스트만 갖고 오는 거
	
	void insertBoard(BoardDTO board) throws Exception;			//return없이 boardDTO만 사용
	
	BoardDTO selectBoardDetail(int boardIdx) throws Exception;	//boardDTO 이용해서 그 값만 가져옴
	
	void updateBoard(BoardDTO board) throws Exception;
	
	void deleteBoard(int boardIdx) throws Exception;
}