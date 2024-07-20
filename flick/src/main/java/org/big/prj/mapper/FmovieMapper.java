package org.big.prj.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.big.prj.dto.FmovieDTO;

@Mapper
public interface FmovieMapper {

	List<FmovieDTO> selectFmovieList() throws Exception; // 목록 검색

	FmovieDTO selectFmovieDetail(String contentsCode) throws Exception;

}
