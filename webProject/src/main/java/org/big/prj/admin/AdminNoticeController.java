package org.big.prj.admin;

import org.big.prj.dto.NoticeDTO;
import org.big.prj.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin/notice")
public class AdminNoticeController {

    @Autowired
    private NoticeService noticeService;

    @GetMapping("/list")
    public String noticeList(Model model,
                             @RequestParam(value = "search", required = false, defaultValue = "") String search,
                             @RequestParam(value = "page", defaultValue = "0") int page,
                             @RequestParam(value = "size", defaultValue = "10") int size) {
        try {
            int offset = page * size;
            List<NoticeDTO> notices = noticeService.getAllNotices(search, size, offset);
            model.addAttribute("notices", notices);
            model.addAttribute("search", search);
            model.addAttribute("page", page);
            model.addAttribute("size", size);

            // For pagination
            long totalNotices = noticeService.countNotices(search);
            int totalPages = (int) Math.ceil((double) totalNotices / size);
            model.addAttribute("totalPages", totalPages);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", "공지사항 목록을 불러오는 중 에러가 발생했습니다.");
        }
        return "thymeleaf/notice/notice_list_admin";
    }

    @GetMapping("/new")
    public String newNoticeForm() {
        return "thymeleaf/notice/notice_write_admin";
    }

    @PostMapping("/save")
    public String saveNotice(NoticeDTO notice, RedirectAttributes redirectAttributes) {
        try {
            noticeService.insertNotice(notice);
            redirectAttributes.addFlashAttribute("successMessage", "공지사항이 성공적으로 작성되었습니다.");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("errorMessage", "공지사항 작성 중 에러가 발생했습니다.");
            return "redirect:/admin/notice/new";
        }
        return "redirect:/admin/notice/list";
    }

    @GetMapping("/view/{id}")
    public String noticeDetail(@PathVariable("id") Long id, Model model) {
        try {
            NoticeDTO notice = noticeService.selectNoticeById(id);
            model.addAttribute("notice", notice);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", "공지사항을 불러오는 중 에러가 발생했습니다.");
        }
        return "thymeleaf/notice/notice_detail_admin";
    }

    @PostMapping("/delete/{id}")
    public String deleteNotice(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        try {
            noticeService.deleteNotice(id);
            redirectAttributes.addFlashAttribute("successMessage", "공지사항이 성공적으로 삭제되었습니다.");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("errorMessage", "공지사항 삭제 중 오류가 발생했습니다.");
        }
        return "redirect:/admin/notice/list";
    }

    @GetMapping("/edit/{id}")
    public String editNoticeForm(@PathVariable("id") Long id, Model model) {
        try {
            NoticeDTO notice = noticeService.selectNoticeById(id);
            model.addAttribute("notice", notice);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", "공지사항을 불러오는 중 에러가 발생했습니다.");
        }
        return "thymeleaf/notice/notice_edit";
    }

    @PostMapping("/update/{id}")
    public String updateNotice(@PathVariable("id") Long id, NoticeDTO notice, RedirectAttributes redirectAttributes) {
        try {
            notice.setNoticeId(id);
            noticeService.updateNotice(notice);
            redirectAttributes.addFlashAttribute("successMessage", "공지사항이 성공적으로 수정되었습니다.");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("errorMessage", "공지사항 수정 중 오류가 발생했습니다.");
        }
        return "redirect:/admin/notice/view/" + id;
    }
}
