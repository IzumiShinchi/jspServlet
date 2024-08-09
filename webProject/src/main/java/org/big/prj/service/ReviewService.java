package org.big.prj.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.big.prj.dto.ReviewDTO;

public interface ReviewService{

    /**
     * 리뷰를 삽입합니다.
     * @param review 삽입할 리뷰의 데이터 전송 객체 (DTO)
     */
    void insertReview(ReviewDTO review);

    /**
     * 모든 리뷰를 조회합니다.
     * @return 모든 리뷰의 리스트
     */
    List<ReviewDTO> getAllReviews();

    /**
     * 리뷰 ID를 기반으로 특정 리뷰를 조회합니다.
     * @param reviewId 조회할 리뷰의 ID
     * @return 조회된 리뷰의 데이터 전송 객체 (DTO)
     */
    ReviewDTO getReviewById(Long reviewId);

    /**
     * 특정 리뷰를 업데이트합니다.
     * @param review 업데이트할 리뷰의 데이터 전송 객체 (DTO)
     */
    void updateReview(ReviewDTO review);

 

    /**
     * 댓글을 삽입합니다.
     * @param comment 삽입할 댓글의 데이터 전송 객체 (DTO)
     */
    void insertComment(ReviewDTO comment);

    /**
     * 특정 댓글을 업데이트합니다.
     * @param comment 업데이트할 댓글의 데이터 전송 객체 (DTO)
     */
    void updateComment(ReviewDTO comment);

    /**
     * 댓글 ID와 사용자 ID를 기반으로 특정 댓글을 삭제합니다.
     * @param commentId 삭제할 댓글의 ID
     * @param commentUserId 댓글을 삭제하려는 사용자의 ID
     */
    void  deleteComment(Long commentId, String commentUserId);

    /**
     * 리뷰 ID를 기반으로 해당 리뷰에 대한 모든 댓글을 조회합니다.
     * @param reviewId 댓글을 조회할 리뷰의 ID
     * @return 해당 리뷰에 대한 모든 댓글의 리스트
     */
    List<ReviewDTO> getCommentsByReviewId(Long reviewId);

    /**
     * 댓글 ID를 기반으로 특정 댓글을 조회합니다.
     * @param commentId 조회할 댓글의 ID
     * @return 조회된 댓글의 데이터 전송 객체 (DTO)
     */
    public ReviewDTO getCommentById(Long commentId);
    
    public Integer getCommentLevel(Long commentReview);
    
    List<ReviewDTO> selectReviewAndCommentsById(Long reviewId); // Add this method

    void deleteReview(Map<String, Object> params);
    void adminDeleteReview(Long reviewId);
    void adminDeleteComment(Long commentId);
    
    List<ReviewDTO> getAllReviews(@Param("search") String search, @Param("limit") int limit, @Param("offset") int offset);
    int countReviews(@Param("search") String search);
    
    
}