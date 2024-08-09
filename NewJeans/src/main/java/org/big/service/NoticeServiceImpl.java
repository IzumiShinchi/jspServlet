package org.big.service;

import java.util.List;
import java.util.Iterator;
import org.big.dto.NoticeDTO;
import org.big.mapper.NoticeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class NoticeServiceImpl implements NoticeService {

	@Autowired
	private NoticeMapper noticeMapper;
	
	@Override
	public List<NoticeDTO> NoticeList() throws Exception {
		return noticeMapper.NoticeList();
	}
	
	@Override
	public void insertNotice(NoticeDTO notice) throws Exception {
		noticeMapper.insertNotice(notice);
	}
	
	@Override
	public NoticeDTO NoticeDetail(int noticeCode) throws Exception {
		NoticeDTO Notice = noticeMapper.NoticeDetail(noticeCode);
		noticeMapper.updateReadCnt(noticeCode);
		return Notice;
	}
	
	@Override
	public void updateNotice(NoticeDTO notice) throws Exception {
		noticeMapper.updateNotice(notice);
	}
	
	@Override
	public void deleteNotice(int NoticeCode) throws Exception {
		noticeMapper.deleteNotice(NoticeCode);
	}
}
