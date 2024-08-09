package org.big.service;

import java.util.List;

import org.big.dto.NoticeDTO;

public interface NoticeService {
	
	List<NoticeDTO> NoticeList() throws Exception;
	
	void insertNotice(NoticeDTO notice) throws Exception;
	
	NoticeDTO NoticeDetail(int noticeCode) throws Exception;
	
	void updateNotice(NoticeDTO notice) throws Exception;
	
	void deleteNotice(int noticeCode) throws Exception;
}
