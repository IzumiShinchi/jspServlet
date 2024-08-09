package org.big.service;

import java.util.List;

import org.big.dto.CommunityDTO;
import org.big.mapper.CommunityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommunityServiceImpl implements CommunityService {

	@Autowired
	private CommunityMapper communityMapper;
	
	@Override
	public List<CommunityDTO> CommuList() throws Exception {
		return communityMapper.CommuList();
	}
	
	@Override
	public void insertCommu(CommunityDTO commu) throws Exception {
		communityMapper.insertCommu(commu);
	}
	
	@Override
	public CommunityDTO CommuDetail(int commuCode) throws Exception {
		CommunityDTO commu = communityMapper.CommuDetail(commuCode);
		communityMapper.updateReadCnt(commuCode);
		return commu;
	}
	
	@Override
	public void updateCommu(CommunityDTO commu) throws Exception {
		communityMapper.updateCommu(commu);
	}
	
	@Override
	public void deleteCommu(int commuCode) throws Exception {
		communityMapper.deleteCommu(commuCode);
	}
}
