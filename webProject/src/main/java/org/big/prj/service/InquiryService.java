package org.big.prj.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.big.prj.dto.InquiryDTO;
import org.springframework.data.domain.Page;

public interface InquiryService {

    void insertInquiry(InquiryDTO inquiry);

    InquiryDTO selectInquiryById(Long inquiryId);

    List<InquiryDTO> selectAllInquiries();

    Page<InquiryDTO> findInquiries(int page);

    int selectTotalCount();

    void deleteInquiry(Long inquiryId);

    void updateInquiry(InquiryDTO inquiry);

    void updateInquiryWithComment(InquiryDTO inquiry);

    boolean checkPassword(Long inquiryId, String inputPassword);// inquiryId와 inputPassword를 받아 비밀번호 확인
    
    List<InquiryDTO> getAllInquiries(@Param("search") String search, @Param("limit") int limit, @Param("offset") int offset);
    int countInquiries(@Param("search") String search);
}
