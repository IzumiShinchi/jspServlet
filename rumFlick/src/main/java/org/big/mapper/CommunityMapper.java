package org.big.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.big.dto.CommunityDTO;

@Mapper
public interface CommunityMapper {
	List<CommunityDTO> CommuList() throws Exception;
	
	void insertCommu(CommunityDTO commu) throws Exception;
	
	void updateReadCnt(int commuCode) throws Exception;
	
	CommunityDTO CommuDetail(int commuCode) throws Exception;
	
	void updateCommu(CommunityDTO commu) throws Exception;
	
	void deleteCommu(int commuCode) throws Exception;
}
