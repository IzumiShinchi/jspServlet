package org.big.prj.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.big.prj.dto.ReviewDTO;
import org.big.prj.mapper.ReviewMapper;
import org.big.prj.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

 
@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewMapper reviewMapper;

    @Override
    public void insertReview(ReviewDTO review) {
        reviewMapper.insertReview(review);
    }

    @Override
    public List<ReviewDTO> getAllReviews() {
        return reviewMapper.getAllReviews();
    }

    @Override
    public ReviewDTO getReviewById(Long reviewId) {
        return reviewMapper.getReviewById(reviewId);
    }

    @Override
    public void updateReview(ReviewDTO review) {
        reviewMapper.updateReview(review);
    }

 
    @Override
    public void insertComment(ReviewDTO comment) {
        reviewMapper.insertComment(comment);
    }

    @Override
    public void updateComment(ReviewDTO comment) {
        reviewMapper.updateComment(comment);
    }

    @Override
    public void deleteComment(Long commentId, String commentUserId) {
        Map<String, Object> params = new HashMap<>();
        params.put("commentId", commentId);
        params.put("commentUserId", commentUserId);
        reviewMapper.deleteComment(params);
    }

    @Override
    public List<ReviewDTO> getCommentsByReviewId(Long reviewId) {
        return reviewMapper.getCommentsByReviewId(reviewId);
    }

    @Override
    public ReviewDTO getCommentById(Long commentId) {
        return reviewMapper.getCommentById(commentId);
    }
    @Override
    public Integer getCommentLevel(Long commentReview) {
        return reviewMapper.getCommentLevel(commentReview);
    }
    
    @Override
    public List<ReviewDTO> selectReviewAndCommentsById(Long reviewId) {
        return reviewMapper.selectReviewAndCommentsById(reviewId); // Add this method implementation
    }
    @Override
    public void deleteReview(Map<String, Object> params) {
        reviewMapper.deleteReview(params);
    }

    @Override
    public void adminDeleteReview(Long reviewId) {
        reviewMapper.adminDeleteReview(reviewId);
    }

    @Override
    public void adminDeleteComment(Long commentId) {
        reviewMapper.adminDeleteComment(commentId);
    }

    @Override
    public List<ReviewDTO> getAllReviews(String search, int limit, int offset) {
        return reviewMapper.getAllReviews(search, limit, offset);
    }

    @Override
    public int countReviews(String search) {
        return reviewMapper.countReviews(search);
    }
}