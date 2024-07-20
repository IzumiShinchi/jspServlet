package org.big.prj.service;

import java.util.List;

import org.big.prj.dto.FmovieDTO;

public interface FmovieService {

	List<FmovieDTO> selectFmovieList() throws Exception;			//리스트만 갖고 오는 거
	
	FmovieDTO selectFmovieDetail(String contentsCode) throws Exception;
}