package org.big.service;

import java.util.List;

import org.big.common.FileUtils;
import org.big.dto.ContentsDTO;
import org.big.dto.ContentsFileDTO;
import org.big.mapper.ContentsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ContentsServiceImpl implements ContentsService {

	@Autowired
	private ContentsMapper contentsMapper;
	
	@Autowired
	private FileUtils fileUtils;
	
	@Override
	public List<ContentsDTO> contentsList() throws Exception {
		List<ContentsDTO> list = contentsMapper.contentsList();
		log.info("Retrieved contents list: {}", list); // 리스트 내용을 로그로 출력
		return list;
	}
	
	@Override
	public List<ContentsDTO> movieList() throws Exception {
		return contentsMapper.movieList();
	}
	
	@Override
	public List<ContentsDTO> kMovieList() throws Exception {
		return contentsMapper.kMovieList();
	}
	
	@Override
	public List<ContentsDTO> fMovieList() throws Exception {
		return contentsMapper.fMovieList();
	}
	
	@Override
	public List<ContentsDTO> tAniList() throws Exception {
		return contentsMapper.tAniList();
	}
	
	@Override
	public List<ContentsDTO> dramaList() throws Exception {
		return contentsMapper.dramaList();
	};
	
	@Override
	public List<ContentsDTO> kDramaList() throws Exception {
		return contentsMapper.kDramaList();
	}
	
	@Override
	public List<ContentsDTO> fDramaList() throws Exception {
		return contentsMapper.fDramaList();
	}
	
	@Override
	public List<ContentsDTO> aniList() throws Exception {
		return contentsMapper.aniList();
	}
	
	@Override
	public void registContent(ContentsDTO contents, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception {
		contentsMapper.registContent(contents);
		List<ContentsFileDTO> list = fileUtils.parseFileInfo(contents.getCCode(), multipartHttpServletRequest);
		if(org.springframework.util.CollectionUtils.isEmpty(list) == false) {
			contentsMapper.registContentFileList(list);
		}
	}
	
	@Override
	public void updateContent(ContentsDTO contents) throws Exception {
		contentsMapper.updateContent(contents);
	}
	
	@Override
	public void deleteContent(String contentsCode) throws Exception {
		contentsMapper.deleteContent(contentsCode);
	}
	
	@Override
	public ContentsDTO contentDetail(String contentsCode) throws Exception {
		ContentsDTO contents = contentsMapper.contentDetail(contentsCode);
		ContentsFileDTO fileInfo = contentsMapper.contentsFileInformation(contentsCode);
		contents.setFileInfo(fileInfo);
		return contents;
	}
	
	@Override
	public List<ContentsFileDTO> contentsFileList() throws Exception {
		return contentsMapper.contentsFileList();
	}
	
	@Override
	public List<ContentsFileDTO> movieContentsFileList() throws Exception {
		return contentsMapper.movieContentsFileList();
	};
	
	@Override
	public List<ContentsFileDTO> dramaContentsFileList() throws Exception {
		return contentsMapper.dramaContentsFileList();
	};
	
	@Override
	public List<ContentsFileDTO> aniContentsFileList() throws Exception {
		return contentsMapper.aniContentsFileList();
	};

}
