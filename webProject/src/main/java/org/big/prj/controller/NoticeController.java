package org.big.prj.controller;

import java.util.List;

import org.big.prj.dto.NoticeDTO;
import org.big.prj.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    // 공지사항 목록 조회
    @GetMapping("/notice/list")
    public String noticeList(Model model,
                             @RequestParam(value = "search", required = false, defaultValue = "") String search,
                             @RequestParam(value = "page", defaultValue = "0") int page,
                             @RequestParam(value = "size", defaultValue = "10") int size) {
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

        return "thymeleaf/notice/notice_list";
    }

    @GetMapping("/notice/view/{id}")
    public String noticeDetail(@PathVariable("id") Long id, Model model) {
        try {
            NoticeDTO notice = noticeService.selectNoticeById(id);
            model.addAttribute("notice", notice);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", "공지사항을 불러오는 중 에러가 발생했습니다.");
        }
        return "thymeleaf/notice/notice_detail";
    }
}
