package org.big.prj.service;

import java.util.List;

import org.big.prj.dto.BoardDTO;
import org.big.prj.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired	//Mapper랑 Service랑 연결!
	private BoardMapper boardMapper;
	
	@Override	//Service와 같은 메소드 이름을 가지고 실행!
	public List<BoardDTO> selectBoardList() throws Exception {
		// TODO Auto-generated method stub
		return boardMapper.selectBoardList();
	}
	@Override
	public void insertBoard(BoardDTO board) throws Exception {
		// TODO Auto-generated method stub
		boardMapper.insertBoard(board);
	}
	@Override
	public BoardDTO selectBoardDetail(int boardIdx) throws Exception {
		// TODO Auto-generated method stub
		boardMapper.updateHitCount(boardIdx);
		
		BoardDTO board = boardMapper.selectBoardDetail(boardIdx);
		
		return board;
	}
	@Override
	public void updateBoard(BoardDTO board) throws Exception {
		// TODO Auto-generated method stub
		boardMapper.updateBoard(board);
		
	}
	@Override
	public void deleteBoard(int boardIdx) throws Exception {
		// TODO Auto-generated method stub
		boardMapper.deleteBoard(boardIdx);
		
	}
}
