package org.big.service;

import java.util.List;

import org.big.dto.CommunityDTO;

public interface CommunityService {
	
	List<CommunityDTO> CommuList() throws Exception;
	
	void insertCommu(CommunityDTO commu) throws Exception;
	
	CommunityDTO CommuDetail(int commuCode) throws Exception;
	
	void updateCommu(CommunityDTO commu) throws Exception;
	
	void deleteCommu(int commuCode) throws Exception;
}
