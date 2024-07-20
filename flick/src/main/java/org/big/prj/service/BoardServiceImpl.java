package org.big.prj.service;

import java.util.List;

import org.big.prj.common.FileUtils;
import org.big.prj.dto.BoardDTO;
import org.big.prj.dto.BoardFileDTO;
import org.big.prj.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BoardServiceImpl implements BoardService {

	@Autowired	//Mapper랑 Service랑 연결!
	private BoardMapper boardMapper;
	
	@Autowired
	private FileUtils fileUtils;
	
	@Override	//Service와 같은 메소드 이름을 가지고 실행!
	public List<BoardDTO> selectBoardList() throws Exception {
		// TODO Auto-generated method stub
		return boardMapper.selectBoardList();
	}
	@Override
	public void insertBoard(BoardDTO board, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception {
		// TODO Auto-generated method stub
		boardMapper.insertBoard(board);
		List<BoardFileDTO> list = fileUtils.parseFileInfo(board.getBoardIdx(), multipartHttpServletRequest);
		if(org.springframework.util.CollectionUtils.isEmpty(list) == false) {
			boardMapper.insertBoardFileList(list);
		}
//		if(ObjectUtils.isEmpty(multipartHttpServletRequest)==false) {
//			Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
//			String name;
//			while(iterator.hasNext()) {
//				name = iterator.next();
//				log.debug("file tag name : " + name);
//				List<MultipartFile> list = multipartHttpServletRequest.getFiles(name);
//				for(MultipartFile multipartFile : list) {
//					log.debug("start file infomation");
//					log.debug("file name : " + multipartFile.getOriginalFilename());
//					log.debug("file size : " + multipartFile.getSize());
//					log.debug("file content type : " + multipartFile.getContentType());
//					log.debug("end file infomation. \n");
//				}
//			}
//		}
	}
	@Override
	public BoardDTO selectBoardDetail(int boardIdx) throws Exception {
		// TODO Auto-generated method stub
		//boardMapper.updateHitCount(boardIdx);
		BoardDTO board = boardMapper.selectBoardDetail(boardIdx);
		List<BoardFileDTO> fileList = boardMapper.selectBoardFileList(boardIdx);
		board.setFileList(fileList);
		boardMapper.updateHitCount(boardIdx);
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
