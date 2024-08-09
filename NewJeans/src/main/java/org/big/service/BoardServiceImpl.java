package org.big.service;

import java.util.Iterator;
import java.util.List;

import org.big.common.FileUtils;
import org.big.dto.BoardDTO;
import org.big.dto.BoardFileDTO;
import org.big.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private FileUtils fileUtils;

	@Autowired
	private BoardMapper boardMapper;
	
	@Override
	public List<BoardDTO> selectBoardList() throws Exception {
		// TODO Auto-generated method stub
		return boardMapper.selectBoardList();
	}
	
	@Override
	public void insertBoard(BoardDTO board, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception {
		boardMapper.insertBoard(board);
		List<BoardFileDTO> list = fileUtils.parseFileInfo(board.getBoardIdx(), multipartHttpServletRequest);
		if(CollectionUtils.isEmpty(list) == false){
			boardMapper.insertBoardFileList(list);
		}
		
		  if(ObjectUtils.isEmpty(multipartHttpServletRequest)==false) {
		  Iterator<String> iterator=multipartHttpServletRequest.getFileNames(); 
		  String name; 
		  
		  	while (iterator.hasNext()) { 
			  name=iterator.next();
			  log.debug("file tag name :" + name); 
			  List<MultipartFile> list1=multipartHttpServletRequest.getFiles(name); 
			  for(MultipartFile multipartFile : list1) { 
				  log.debug("start file information");
				  log.debug("file name : " + multipartFile.getOriginalFilename());
				  log.debug("file size : " + multipartFile.getSize());
				  log.debug("file content type : " + multipartFile.getContentType());
				  log.debug("end file information. \n"); 
			  } 
			} 
		  }
		 
	}
	
	@Override
	public BoardDTO selectBoardDetail(int boardIdx) throws Exception {
		// TODO Auto-generated method stub
		boardMapper.updateHitCount(boardIdx);
		BoardDTO board=boardMapper.selectBoardDetail(boardIdx);
		
		List<BoardFileDTO> fileList=boardMapper.selectBoardFileList(boardIdx);
		board.setFileList(fileList);
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
	
	@Override
	public BoardFileDTO selectBoardFileInformation(int idx, int boardIdx) throws Exception {
		// TODO Auto-generated method stub
		return boardMapper.selectBoardFileInformation(idx, boardIdx);
	}
	
}
