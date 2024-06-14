package org.big.prj.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.big.prj.dto.BoardDTO;

@Mapper
public interface BoardMapper {

	List<BoardDTO> selectBoardList() throws Exception;			//목록 검색
	
	void insertBoard(BoardDTO board) throws Exception;			//글 작성
	
	void updateHitCount(int boardIdx) throws Exception;			//조회수
	
	BoardDTO selectBoardDetail(int boardIdx) throws Exception;	//글 상세 내용
	
	void updateBoard(BoardDTO board) throws Exception;
	
	void deleteBoard(int boardIdx) throws Exception;
}
