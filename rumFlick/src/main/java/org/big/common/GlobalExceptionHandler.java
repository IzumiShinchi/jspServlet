package org.big.common;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(Exception.class)
    public String handleException(Exception e, RedirectAttributes redirectAttributes) {
        e.printStackTrace();
        redirectAttributes.addAttribute("msg", -1); // 예외 메시지 전달
        return "redirect:/error"; // 예외 처리 페이지로 리다이렉트 등
    }
	
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handlesException(Exception e, Model model) {
        model.addAttribute("errorMessage", e.getMessage());
        return "errorPage"; // 오류 페이지로 리다이렉트
    }
}