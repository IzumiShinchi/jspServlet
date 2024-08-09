package org.big.prj.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.big.prj.dto.NoticeDTO;

@Mapper
public interface NoticeMapper {
    void insertNotice(NoticeDTO notice);
    List<NoticeDTO> selectAllNotices();
    List<NoticeDTO> selectNoticesWithPaging(int offset, int limit);
    int selectTotalCount();
    void deleteNotice(Long noticeId);
    void updateNotice(NoticeDTO notice);
    NoticeDTO selectNoticeById(Long id); 
    List<NoticeDTO> getAllNotices(@Param("search") String search, @Param("limit") int limit, @Param("offset") int offset);
    int countNotices(@Param("search") String search);
}
