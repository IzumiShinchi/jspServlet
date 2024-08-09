package org.big.controller;

import java.util.List;

import org.big.dto.ContentsDTO;
import org.big.dto.ContentsFileDTO;
import org.big.service.ContentsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MainController {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	

	@RequestMapping("/rumflick/openIndex.do")
	public String openIndex(HttpSession session, Model model, @RequestParam(value = "sessionId", required = false) String sessionId) throws Exception {
        // 세션에서 세션 ID 가져오기
		String loginId = session.getId();

        // 전달된 sessionId와 실제 세션 ID 비교
        if (sessionId != null && sessionId.equals(loginId)) {
            // 세션 ID가 일치하는 경우
            model.addAttribute("sessionId", loginId);
        } else {
            // 세션 ID가 일치하지 않는 경우, 에러 처리 또는 다른 로직 수행
        }
        
		return "thymeleaf/index/index";
	}
	
}
