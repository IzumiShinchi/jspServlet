package org.big.prj.service.impl;

import org.big.prj.dto.InquiryDTO;
import org.big.prj.mapper.InquiryMapper;
import org.big.prj.service.InquiryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import java.util.List;

@Service
public class InquiryServiceImpl implements InquiryService {

    @Autowired
    private InquiryMapper inquiryMapper;

    @Override
    @Transactional
    public void insertInquiry(InquiryDTO inquiry) {
        inquiryMapper.insertInquiry(inquiry);
    }

    @Override
    public InquiryDTO selectInquiryById(Long inquiryId) {
        return inquiryMapper.selectInquiryById(inquiryId);
    }

    @Override
    public List<InquiryDTO> selectAllInquiries() {
        return inquiryMapper.selectAllInquiries();
    }

    @Override
    public Page<InquiryDTO> findInquiries(int page) {
        int pageSize = 10;
        int offset = (page - 1) * pageSize;

        List<InquiryDTO> inquiries = inquiryMapper.selectInquiriesWithPaging(offset, pageSize);
        int total = inquiryMapper.selectTotalCount();
        return new PageImpl<>(inquiries, PageRequest.of(page - 1, pageSize, Sort.by(Sort.Direction.DESC, "createdDate")), total);
    }

    @Override
    public int selectTotalCount() {
        return inquiryMapper.selectTotalCount();
    }

    @Override
    public void deleteInquiry(Long inquiryId) {
        inquiryMapper.deleteInquiry(inquiryId);
    }

    @Override
    public void updateInquiry(InquiryDTO inquiry) {
        inquiryMapper.updateInquiry(inquiry);
    }

    @Override
    public void updateInquiryWithComment(InquiryDTO inquiry) {
        inquiryMapper.updateInquiryWithComment(inquiry); // 수정한 부분
    }

    @Override
    public boolean checkPassword(Long inquiryId, String inputPassword) {
        String storedPassword = inquiryMapper.selectPasswordByInquiryId(inquiryId);
        return storedPassword != null && storedPassword.equals(inputPassword);
    }

    @Override
    public List<InquiryDTO> getAllInquiries(String search, int limit, int offset) {
        return inquiryMapper.getAllInquiries(search, limit, offset);
    }

    @Override
    public int countInquiries(String search) {
        return inquiryMapper.countInquiries(search);
    }

}

