package org.big.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.big.dto.BoardDTO;
import org.big.dto.BoardFileDTO;

@Mapper
public interface BoardMapper {

	List<BoardDTO> selectBoardList() throws Exception;
	
	void insertBoard(BoardDTO board) throws Exception;
	
	void insertBoardFileList(List<BoardFileDTO> list) throws Exception;
	
	void updateHitCount(int boardIdx) throws Exception;
	
	BoardDTO selectBoardDetail(int boardIdx) throws Exception;
	
	void updateBoard(BoardDTO board) throws Exception;
	
	void deleteBoard(int boardIdx) throws Exception;
	
	List<BoardFileDTO> selectBoardFileList(int boardIdx) throws Exception;
	
	BoardFileDTO selectBoardFileInformation(@Param("idx")int idx, @Param("boardIdx")int boardIdx) throws Exception;
}
