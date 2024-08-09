package org.big.prj.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.big.prj.dto.ReviewDTO;

@Mapper
public interface ReviewMapper  {
	 void insertReview(ReviewDTO review);
	    List<ReviewDTO> getAllReviews();
	    ReviewDTO getReviewById(Long reviewId);
	    void updateReview(ReviewDTO review);
	    void deleteReview(Map<String, Object> params);

	    void insertComment(ReviewDTO comment);
	    void updateComment(ReviewDTO comment);
	    void deleteComment(Map<String, Object> params);
	    List<ReviewDTO> getCommentsByReviewId(Long reviewId);
	    public ReviewDTO getCommentById(Long commentId);
	    public Integer getCommentLevel(Long commentReview);
	    
	    
	    //관리자용
	    List<ReviewDTO> selectReviewAndCommentsById(Long reviewId);
	    void adminDeleteReview(Long reviewId); // 관리자용 리뷰 삭제
	    void adminDeleteComment(Long commentId); // 관리자용 댓글 삭제
	    
	    List<ReviewDTO> getAllReviews(@Param("search") String search, @Param("limit") int limit, @Param("offset") int offset);
	    int countReviews(@Param("search") String search);

}
