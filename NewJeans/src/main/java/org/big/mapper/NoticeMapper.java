package org.big.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.big.dto.NoticeDTO;

@Mapper
public interface NoticeMapper {
	
	List<NoticeDTO> NoticeList() throws Exception;
	
	void insertNotice(NoticeDTO notice) throws Exception;
	
	void updateReadCnt(int noticeCode) throws Exception;
	
	NoticeDTO NoticeDetail(int noticeCode) throws Exception;
	
	void updateNotice(NoticeDTO notice) throws Exception;
	
	void deleteNotice(int noticeCode) throws Exception;
}
