package org.big.prj.service;

import java.util.List;

import org.big.prj.dto.FmovieDTO;
import org.big.prj.mapper.FmovieMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FmovieServiceImpl implements FmovieService {

	@Autowired	//Mapper랑 Service랑 연결!
	private FmovieMapper fmovieMapper;
	
	
	@Override	//Service와 같은 메소드 이름을 가지고 실행!
	public List<FmovieDTO> selectFmovieList() throws Exception {
		// TODO Auto-generated method stub
		return fmovieMapper.selectFmovieList();
	}


	@Override
	public FmovieDTO selectFmovieDetail(String contentsCode) throws Exception {
		// TODO Auto-generated method stub
		return fmovieMapper.selectFmovieDetail(contentsCode);
	}

}
