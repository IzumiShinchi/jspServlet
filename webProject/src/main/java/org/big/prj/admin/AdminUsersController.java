package org.big.prj.admin;

import java.util.List;

import org.big.prj.dto.UsersDTO;
import org.big.prj.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/users")
public class AdminUsersController {

    @Autowired
    private UsersService usersService;

    // 사용자 목록 조회
    @GetMapping("/list")
    public String listUsers(Model model,
                            @RequestParam(value = "page", defaultValue = "1") int page,
                            @RequestParam(value = "size", defaultValue = "15") int size,
                            @RequestParam(value = "search", defaultValue = "") String search) {
        List<UsersDTO> users = usersService.getAllUsers(page, size, search);
        int totalUsers = usersService.countUsers(search);
        int totalPages = (int) Math.ceil((double) totalUsers / size);

        model.addAttribute("users", users);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("search", search);
        return "thymeleaf/users/user_list";
    }

    // 사용자 상세 조회
    @GetMapping("/view/{userId}")
    public String viewUser(@PathVariable("userId") String userId, Model model) {
        UsersDTO user = usersService.getUserById(userId);
        model.addAttribute("user", user);
        return "thymeleaf/users/user_detail";
    }

    // 사용자 삭제 처리
    @PostMapping("/delete/{userId}")
    public String deleteUser(@PathVariable("userId") String userId, RedirectAttributes redirectAttributes) {
        usersService.deleteUserByAdmin(userId);
        redirectAttributes.addFlashAttribute("successMessage", "사용자가 성공적으로 삭제되었습니다.");
        return "redirect:/admin/users/list";
    }
}