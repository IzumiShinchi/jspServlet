package org.big.prj.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.big.prj.dto.NoticeDTO;

public interface NoticeService {
    void insertNotice(NoticeDTO notice) throws Exception;
    List<NoticeDTO> getAllNotices() throws Exception;
    List<NoticeDTO> getNoticesWithPaging(int offset, int limit) throws Exception;
    int getTotalCount() throws Exception;
    void deleteNotice(Long noticeId) throws Exception;
    void updateNotice(NoticeDTO notice) throws Exception;
    NoticeDTO selectNoticeById(Long id) throws Exception; 
    List<NoticeDTO> getAllNotices(@Param("search") String search, @Param("limit") int limit, @Param("offset") int offset);
    int countNotices(@Param("search") String search);
}
