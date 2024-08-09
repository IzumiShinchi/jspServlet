package org.big.prj.admin;

import org.big.prj.dto.InquiryDTO;
import org.big.prj.service.InquiryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin/inquiry")
public class AdminInquiryController {

    @Autowired
    private InquiryService inquiryService;

 // 모든 문의글 조회 (관리자)
    @GetMapping("/list")
    public String listInquiries(Model model,
                                @RequestParam(value = "search", required = false, defaultValue = "") String search,
                                @RequestParam(value = "page", defaultValue = "0") int page,
                                @RequestParam(value = "size", defaultValue = "10") int size) {
        try {
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
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", "문의 목록을 불러오는 중 에러가 발생했습니다.");
        }
        return "thymeleaf/inquiry/inquiry_list_admin";
    }


    // 특정 문의글 조회 (관리자)
    @GetMapping("/view/{id}")
    public String viewInquiry(@PathVariable("id") Long id, Model model) {
        InquiryDTO inquiry = inquiryService.selectInquiryById(id);
        model.addAttribute("inquiry", inquiry);
        return "thymeleaf/inquiry/inquiry_detail_admin";
    }

    // 답글 작성 처리
    @PostMapping("/reply/{id}")
    public String updateInquiryWithComment(@PathVariable("id") Long id, @ModelAttribute InquiryDTO inquiry, RedirectAttributes redirectAttributes) {
        inquiry.setInquiryId(id); // 수정한 부분
      inquiry.setCommentTitle("[답글] " + inquiry.getInquiryTitle());
        inquiryService.updateInquiryWithComment(inquiry);
        redirectAttributes.addFlashAttribute("successMessage", "답글이 성공적으로 작성/수정되었습니다.");
        return "redirect:/admin/inquiry/view/" + inquiry.getInquiryId();
    }

    // 문의글 삭제 처리
    @PostMapping("/delete/{id}")
    public String deleteInquiry(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        inquiryService.deleteInquiry(id);
        redirectAttributes.addFlashAttribute("successMessage", "문의글이 성공적으로 삭제되었습니다.");
        return "redirect:/inquiry/list";
    }
}
