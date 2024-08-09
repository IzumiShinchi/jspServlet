package org.big.prj.controller;

import org.big.prj.dto.InquiryDTO;
import org.big.prj.service.InquiryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/inquiry")
public class InquiryController {

    @Autowired
    private InquiryService inquiryService;

 // 모든 문의글 조회
    @GetMapping("/list")
    public String listInquiries(Model model,
                                @RequestParam(value = "search", required = false, defaultValue = "") String search,
                                @RequestParam(value = "page", defaultValue = "0") int page,
                                @RequestParam(value = "size", defaultValue = "10") int size) {
        int offset = page * size;
        List<InquiryDTO> inquiries = inquiryService.getAllInquiries(search, size, offset);
        model.addAttribute("inquiries", inquiries);
        model.addAttribute("search", search);
        model.addAttribute("page", page);
        model.addAttribute("size", size);

        // For pagination
        long totalInquiries = inquiryService.countInquiries(search);
        int totalPages = (int) Math.ceil((double) totalInquiries / size);
        model.addAttribute("totalPages", totalPages);

        return "thymeleaf/inquiry/inquiry_list";
    }

    // 비밀번호 입력 페이지로 이동
    @GetMapping("/view/{id}")
    public String viewInquiryPasswordPage(@PathVariable("id") Long id, Model model) {
        model.addAttribute("inquiryId", id);
        return "thymeleaf/inquiry/inquiry_password";
    }

    // 특정 문의글 조회 (비밀번호 확인)
    @PostMapping("/view/{id}")
    public String viewInquiryWithPassword(@PathVariable("id") Long id, @RequestParam("password") String password, Model model, RedirectAttributes redirectAttributes) {
        if (inquiryService.checkPassword(id, password)) {
            InquiryDTO inquiry = inquiryService.selectInquiryById(id);
            model.addAttribute("inquiry", inquiry);
            return "thymeleaf/inquiry/inquiry_detail";
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "비밀번호가 일치하지 않습니다.");
            return "redirect:/inquiry/view/" + id;
        }
    }
    
 // 문의글 작성 폼
    @GetMapping("/new")
    public ModelAndView newInquiryForm(HttpSession session) {
        ModelAndView mv = new ModelAndView("thymeleaf/inquiry/inquiry_write");
        try {
            String userId = (String) session.getAttribute("userId");
            InquiryDTO inquiryDTO = new InquiryDTO();
            if (userId != null) {
                inquiryDTO.setInquiryName(userId);
            }
            mv.addObject("inquiry", inquiryDTO);
        } catch (Exception e) {
            e.printStackTrace();
            mv.addObject("errorMessage", "문의글 작성 페이지를 불러오는 중 에러가 발생했습니다.");
        }
        return mv;
    }

    // 문의글 저장
    @PostMapping("/save")
    public ResponseEntity<String> saveInquiry(@RequestBody InquiryDTO inquiry) {
        try {
            inquiryService.insertInquiry(inquiry);
            return ResponseEntity.ok("문의글이 성공적으로 등록되었습니다.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("문의글 등록 중 오류가 발생했습니다.");
        }
    }

    
    // 문의글 수정 폼
    @GetMapping("/edit/{id}")
    public String editInquiryForm(@PathVariable("id") Long id, Model model) {
        InquiryDTO inquiry = inquiryService.selectInquiryById(id);
        model.addAttribute("inquiry", inquiry);
        return "thymeleaf/inquiry/inquiry_edit";
    }

    // 문의글 수정 처리
    @PostMapping("/update/{id}")
    public String updateInquiry(@PathVariable("id") Long id, @ModelAttribute InquiryDTO inquiry, RedirectAttributes redirectAttributes) {
        inquiry.setInquiryId(id); // ID를 DTO에 설정
        inquiryService.updateInquiry(inquiry);
        redirectAttributes.addFlashAttribute("successMessage", "문의글이 성공적으로 수정되었습니다.");
        return "redirect:/inquiry/view/" + id; // 수정 후 해당 문의글 상세보기 페이지로 리다이렉트
    }

    // 문의글 삭제 처리
    @PostMapping("/delete/{id}")
    public String deleteInquiry(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        inquiryService.deleteInquiry(id);
        redirectAttributes.addFlashAttribute("successMessage", "문의글이 성공적으로 삭제되었습니다.");
        return "redirect:/inquiry/list";
    }
}