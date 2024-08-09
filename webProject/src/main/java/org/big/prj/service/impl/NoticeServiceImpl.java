package org.big.prj.service.impl;

import org.big.prj.dto.NoticeDTO;
import org.big.prj.mapper.NoticeMapper;
import org.big.prj.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeMapper noticeMapper;

    @Override
    public void insertNotice(NoticeDTO notice) throws Exception {
        noticeMapper.insertNotice(notice);
    }

    @Override
    public List<NoticeDTO> getAllNotices() throws Exception {
        return noticeMapper.selectAllNotices();
    }

    @Override
    public List<NoticeDTO> getNoticesWithPaging(int offset, int limit) throws Exception {
        return noticeMapper.selectNoticesWithPaging(offset, limit);
    }

    @Override
    public int getTotalCount() throws Exception {
        return noticeMapper.selectTotalCount();
    }

    @Override
    public void deleteNotice(Long noticeId) throws Exception {
        noticeMapper.deleteNotice(noticeId);
    }

    @Override
    public void updateNotice(NoticeDTO notice) throws Exception {
        noticeMapper.updateNotice(notice);
    }

    @Override
    public NoticeDTO selectNoticeById(Long id) throws Exception {
        return noticeMapper.selectNoticeById(id); // Add this method
    }
    
    @Override
    public List<NoticeDTO> getAllNotices(String search, int limit, int offset) {
        return noticeMapper.getAllNotices(search, limit, offset);
    }

    @Override
    public int countNotices(String search) {
        return noticeMapper.countNotices(search);
    }
}
