package org.big.prj.admin;

import org.big.prj.dto.OrderDTO;
import org.big.prj.dto.ReviewDTO;
import org.big.prj.service.OrderService;
import org.big.prj.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin/review")
public class AdminReviewController {

    @Autowired
    private ReviewService reviewService;
    
    @Autowired
    private OrderService orderService;

    // 모든 후기 조회 (관리자)
    @GetMapping("/list")
    public String listReviews(Model model,
                              @RequestParam(value = "search", required = false, defaultValue = "") String search,
                              @RequestParam(value = "page", defaultValue = "0") int page,
                              @RequestParam(value = "size", defaultValue = "10") int size) {
        try {
            int offset = page * size;
            List<ReviewDTO> reviews = reviewService.getAllReviews(search, size, offset);
            model.addAttribute("reviews", reviews);
            model.addAttribute("search", search);
            model.addAttribute("page", page);
            model.addAttribute("size", size);

            // For pagination
            long totalReviews = reviewService.countReviews(search);
            int totalPages = (int) Math.ceil((double) totalReviews / size);
            model.addAttribute("totalPages", totalPages);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", "후기 목록을 불러오는 중 에러가 발생했습니다.");
        }
        return "thymeleaf/review/review_list_admin";
    }

    // 특정 후기 조회 (관리자)
    @GetMapping("/view/{id}")
    public String reviewDetail(@PathVariable("id") Long id, Model model, HttpSession session) {
        try {
            // 리뷰 가져오기
            ReviewDTO review = reviewService.getReviewById(id);
            if (review == null) {
                model.addAttribute("errorMessage", "리뷰를 찾을 수 없습니다.");
                return "thymeleaf/review/review_detail";
            }

            // 주문 정보 가져오기
            OrderDTO order = orderService.getOrderDetails(review.getOrderNo());
            review.setKind(order.getKind());
            review.setColor(order.getColor());
            review.setOptions(order.getOptions());
            review.setTotalPrice(order.getTotalPrice());

            // 댓글 가져오기
            List<ReviewDTO> comments = reviewService.getCommentsByReviewId(id);
            Map<Long, List<ReviewDTO>> commentsMap = new HashMap<>();

            for (ReviewDTO comment : comments) {
                commentsMap.computeIfAbsent(comment.getCommentReview(), k -> new ArrayList<>()).add(comment);
            }

            // 모델에 데이터 추가
            model.addAttribute("review", review);
            model.addAttribute("comments", comments);

            // 세션에서 사용자 ID 가져와서 모델에 추가
            String userId = (String) session.getAttribute("userId");
            model.addAttribute("userId", userId);

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", "후기를 불러오는 중 에러가 발생했습니다.");
            model.addAttribute("review", new ReviewDTO());
        }
        return "thymeleaf/review/review_detail";
    }

    // 후기 삭제 (관리자)
    @PostMapping("/delete/{id}")
    public String deleteReview(@PathVariable("id") Long id, HttpSession session, RedirectAttributes redirectAttributes) {
        String userId = (String) session.getAttribute("userId");
        if (userId == null || !userId.equals("admin")) {
            redirectAttributes.addFlashAttribute("errorMessage", "삭제 권한이 없습니다.");
            return "redirect:/admin/review/list";
        }

        try {
            reviewService.adminDeleteReview(id);
            redirectAttributes.addFlashAttribute("successMessage", "후기가 성공적으로 삭제되었습니다.");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("errorMessage", "후기 삭제 중 에러가 발생했습니다.");
        }
        return "redirect:/admin/review/list";
    }

    // 댓글 삭제 (관리자)
    @PostMapping("/comment/delete/{commentId}")
    public String deleteComment(@PathVariable("commentId") Long commentId, HttpSession session, RedirectAttributes redirectAttributes) {
        String userId = (String) session.getAttribute("userId");
        if (userId == null || !userId.equals("admin")) {
            redirectAttributes.addFlashAttribute("errorMessage", "삭제 권한이 없습니다.");
            return "redirect:/admin/review/list";
        }

        try {
            reviewService.adminDeleteComment(commentId);
            redirectAttributes.addFlashAttribute("successMessage", "댓글이 성공적으로 삭제되었습니다.");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("errorMessage", "댓글 삭제 중 에러가 발생했습니다.");
        }
        return "redirect:/admin/review/list";
    }
    
    // 댓글 작성
    @PostMapping("/comment/save")
    public String saveComment(@ModelAttribute ReviewDTO review, HttpSession session, RedirectAttributes redirectAttributes) {
        String userId = (String) session.getAttribute("userId");
        if (userId == null) {
        	redirectAttributes.addFlashAttribute("errorMessage", "로그인이 필요합니다.");
            return "redirect:/login?error=" + URLEncoder.encode("로그인이 필요합니다.", StandardCharsets.UTF_8);
        }

        review.setCommentUserId(userId);
        review.setUserId(userId); // 추가: userId를 설정합니다.

        if (review.getReviewId() == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "리뷰 ID가 필요합니다.");
            return "redirect:/reviews/list";
        }

        // 댓글 작성 시 기본 제목과 내용, 생성일자 설정
        if (review.getReviewTitle() == null || review.getReviewTitle().isEmpty()) {
            review.setReviewTitle("Comment Title");
        }
        if (review.getReviewContent() == null || review.getReviewContent().isEmpty()) {
            review.setReviewContent("Comment Content");
        }
        review.setCreatedAt(new Date()); // 기본 생성일자 설정

        // comment_review가 설정되었는지 확인
        if (review.getCommentReview() == null) {
            review.setCommentReview(review.getReviewId());
        }

        // 현재 comment_review의 comment_level을 가져와서 1 증가시킵니다
        Integer currentLevel = reviewService.getCommentLevel(review.getCommentReview());
        if (currentLevel == null) {
            currentLevel = 0;
        }
        review.setCommentLevel(currentLevel + 1);

        reviewService.insertComment(review);
        redirectAttributes.addFlashAttribute("successMessage", "댓글이 성공적으로 작성되었습니다.");
        return "redirect:/reviews/view/" + review.getReviewId();
    }
}