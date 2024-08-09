package org.big.prj.mapper;

import org.apache.ibatis.annotations.*;
import org.big.prj.dto.InquiryDTO;
import java.util.List;

@Mapper
public interface InquiryMapper {

	 void insertInquiry(InquiryDTO inquiry);

    InquiryDTO selectInquiryById(Long inquiryId);

    List<InquiryDTO> selectAllInquiries();

    List<InquiryDTO> selectInquiriesWithPaging(@Param("offset") int offset, @Param("limit") int limit);

    void deleteInquiry(Long inquiryId);

    void updateInquiry(InquiryDTO inquiry);

    void updateInquiryWithComment(InquiryDTO inquiry);

    String selectPasswordByInquiryId(Long inquiryId);// inquiryId를 받아 비밀번호를 반환
    
    int selectTotalCount();
    
    List<InquiryDTO> getAllInquiries(@Param("search") String search, @Param("limit") int limit, @Param("offset") int offset);
    int countInquiries(@Param("search") String search);
}
