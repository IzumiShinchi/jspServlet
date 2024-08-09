package org.big.prj.controller;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.big.prj.dto.OrderDTO;
import org.big.prj.dto.ReviewDTO;
import org.big.prj.service.OrderService;
import org.big.prj.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private OrderService orderService;

 // 후기 리스트 조회 (사용자용)
    @GetMapping("/list")
    public String reviewList(Model model,
                             @RequestParam(value = "search", required = false, defaultValue = "") String search,
                             @RequestParam(value = "page", defaultValue = "0") int page,
                             @RequestParam(value = "size", defaultValue = "10") int size) {
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

        return "thymeleaf/review/review_list";
    }

 // 후기 상세 보기
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
    // 새로운 후기 작성 페이지
    @GetMapping("/new")
    public ModelAndView newReviewForm(HttpSession session, @RequestParam("orderNo") String orderNo) {
        ModelAndView mv = new ModelAndView("thymeleaf/review/review_write");
        try {
            String userId = (String) session.getAttribute("userId");
            ReviewDTO reviewDTO = new ReviewDTO();
            if (userId != null) {
                reviewDTO.setUserId(userId);
            }

            OrderDTO order = orderService.getOrderDetails(orderNo);

            reviewDTO.setOrderNo(orderNo);
            reviewDTO.setKind(order.getKind());
            reviewDTO.setColor(order.getColor());
            reviewDTO.setOptions(order.getOptions());
            reviewDTO.setTotalPrice(order.getTotalPrice());

            mv.addObject("review", reviewDTO);
        } catch (Exception e) {
            e.printStackTrace();
            mv.addObject("errorMessage", "리뷰 작성 페이지를 불러오는 중 에러가 발생했습니다.");
        }
        return mv;
    }

    // 후기 저장
    @PostMapping("/save")
    public String saveReview(@ModelAttribute ReviewDTO review, HttpSession session, RedirectAttributes redirectAttributes) {
        try {
            String userId = (String) session.getAttribute("userId");
            if (userId == null) {
            	redirectAttributes.addFlashAttribute("errorMessage", "로그인이 필요합니다.");
                return "redirect:/login?error=" + URLEncoder.encode("로그인이 필요합니다.", StandardCharsets.UTF_8);
            }
            review.setUserId(userId);
            reviewService.insertReview(review);
            redirectAttributes.addFlashAttribute("successMessage", "후기가 성공적으로 작성되었습니다.");
            return "redirect:/reviews/list";
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("errorMessage", "후기 작성 중 에러가 발생했습니다.");
            return "redirect:/reviews/new?orderNo=" + review.getOrderNo();
        }
    }

 // 후기 삭제
    @DeleteMapping("/delete/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long reviewId, HttpSession session) {
        try {
            String userId = (String) session.getAttribute("userId");
            ReviewDTO review = reviewService.getReviewById(reviewId);
            if (review == null || !review.getUserId().equals(userId)) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("삭제 권한이 없습니다.");
            }
            Map<String, Object> params = new HashMap<>();
            params.put("reviewId", reviewId);
            params.put("userId", userId);
            reviewService.deleteReview(params);
            return ResponseEntity.ok("리뷰가 성공적으로 삭제되었습니다.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("리뷰 삭제 중 오류가 발생했습니다.");
        }
    }


 // 후기 수정
    @PostMapping("/update")
    public String updateReview(@ModelAttribute ReviewDTO review, HttpSession session, @RequestParam(value = "action", required = false) String action, RedirectAttributes redirectAttributes) {
        try {
            String userId = (String) session.getAttribute("userId");
            if (userId == null) {
                redirectAttributes.addFlashAttribute("errorMessage", "로그인이 필요합니다.");
                return "redirect:/login?error=" + URLEncoder.encode("로그인이 필요합니다.", StandardCharsets.UTF_8);
            }
            if (!review.getUserId().equals(userId)) {
                redirectAttributes.addFlashAttribute("errorMessage", "수정 권한이 없습니다.");
                return "redirect:/reviews/view/" + review.getReviewId();
            }

            if ("delete".equals(action)) {
                Map<String, Object> params = new HashMap<>();
                params.put("reviewId", review.getReviewId());
                params.put("userId", userId);
                reviewService.deleteReview(params);
                redirectAttributes.addFlashAttribute("successMessage", "후기가 성공적으로 삭제되었습니다.");
                return "redirect:/reviews";
            } else {
                reviewService.updateReview(review);
                redirectAttributes.addFlashAttribute("successMessage", "후기가 성공적으로 수정되었습니다.");
                return "redirect:/reviews/view/" + review.getReviewId();
            }
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("errorMessage", "후기 수정 중 에러가 발생했습니다.");
            return "redirect:/reviews/edit/" + review.getReviewId();
        }
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
 

    @DeleteMapping("/comment/delete/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable("commentId") Long commentId, HttpSession session) {
        String userId = (String) session.getAttribute("userId");
        ReviewDTO comment = reviewService.getCommentById(commentId);

        if (comment == null || !userId.equals(comment.getCommentUserId())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("삭제 권한이 없습니다.");
        }

        reviewService.deleteComment(commentId, userId);
        return ResponseEntity.ok("댓글이 성공적으로 삭제되었습니다.");
    }


}