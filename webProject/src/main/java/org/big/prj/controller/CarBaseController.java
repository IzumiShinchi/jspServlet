package org.big.prj.controller;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.big.prj.dto.CarBaseDTO;
import org.big.prj.dto.CarDTO;
import org.big.prj.service.CarBaseService;
import org.big.prj.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/carbase")
public class CarBaseController {

    @Autowired
    private CarBaseService carBaseService;

    @Autowired
    private CarService carService;

    @GetMapping("/list")
    public String listCarBases(Model model) {
        try {
            List<CarBaseDTO> carBases = carBaseService.getAllCarBases();
            model.addAttribute("carBases", carBases);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", "차량 정보를 불러오는 중 에러가 발생했습니다.");
        }
        return "thymeleaf/carbase/list";
    }

    @GetMapping("/new")
    public String newCarBaseForm(Model model) {
        model.addAttribute("carBase", new CarBaseDTO());
        return "thymeleaf/carbase/form";
    }

    @PostMapping("/save")
    public String saveCarBase(@ModelAttribute CarBaseDTO carBase, Model model) {
        try {
            carBaseService.insertCarBase(carBase);
            return "redirect:/carbase/list";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", "차량 정보를 저장하는 중 에러가 발생했습니다.");
            return "thymeleaf/carbase/form";
        }
    }

    @GetMapping("/edit/{id}")
    public String editCarBaseForm(@PathVariable("id") String baseId, Model model) {
        try {
            CarBaseDTO carBase = carBaseService.getCarBaseById(baseId);
            model.addAttribute("carBase", carBase);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", "차량 정보를 불러오는 중 에러가 발생했습니다.");
        }
        return "thymeleaf/carbase/form";
    }

    @PostMapping("/update")
    public String updateCarBase(@ModelAttribute CarBaseDTO carBase, Model model) {
        try {
            carBaseService.updateCarBase(carBase);
            return "redirect:/carbase/list";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", "차량 정보를 업데이트하는 중 에러가 발생했습니다.");
            return "thymeleaf/carbase/form";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteCarBase(@PathVariable("id") String baseId, Model model) {
        try {
            carBaseService.deleteCarBase(baseId);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", "차량 정보를 삭제하는 중 에러가 발생했습니다.");
        }
        return "redirect:/carbase/list";
    }

    @GetMapping("/build-car")
    public String buildCarPage(HttpSession session, Model model, RedirectAttributes redirectAttributes) {
        String userId = (String) session.getAttribute("userId");
        if (userId == null) {
        	redirectAttributes.addFlashAttribute("errorMessage", "로그인이 필요합니다.");
            return "redirect:/login?error=" + URLEncoder.encode("로그인이 필요합니다.", StandardCharsets.UTF_8);
        }

        try {
            List<CarBaseDTO> kinds = carBaseService.getAllCarBasesByType("kind");
            List<CarBaseDTO> colors = carBaseService.getAllCarBasesByType("color");
            List<CarBaseDTO> options = carBaseService.getAllCarBasesByType("option");
            model.addAttribute("kinds", kinds);
            model.addAttribute("colors", colors);
            model.addAttribute("options", options);
            model.addAttribute("carDTO", new CarDTO());
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", "차량 구성 정보를 불러오는 중 에러가 발생했습니다.");
        }
        return "thymeleaf/carbase/build_car";
    }

    @PostMapping("/save-configuration")
    @ResponseBody
    public Map<String, Object> saveConfiguration(@RequestBody CarDTO carDTO) {
        Map<String, Object> response = new HashMap<>();
        try {
            System.out.println("Received CarDTO: " + carDTO);

            // carId는 DB에서 트리거로 생성
            carService.saveCar(carDTO);
            response.put("message", "success");
            response.put("car", carDTO);
        } catch (Exception e) {
            e.printStackTrace();
            String errorMessage = "An error occurred while saving the configuration:" + e.getMessage();
            System.err.println(errorMessage);
            response.put("message", "error");
        }

        return response;
    }

    
    // carBase 정보 가져오기
    @PostMapping("/carSetting")
    @ResponseBody
    public Map<String, Object> carSetting() {
    	Map<String, Object> response = new HashMap<>();
        try {
        	Map<String, List<CarBaseDTO>> map = carBaseService.getAllCarBasesMap();
        	
        	response.put("message", "success");
            response.put("map", map);
        } catch (Exception e) {
            e.printStackTrace();
            String errorMessage ="An error occurred while saving the configuration:" + e.getMessage();
            System.err.println(errorMessage);
            response.put("message", "error");
        }
        
        return response;
    }
    
    @GetMapping("/confirmation") // 이부분 수정됨
    public String confirmationPage(@RequestParam(value="carId") String carId, @RequestParam(value="kind") String kind, @RequestParam(value="color") String color, @RequestParam(value="option") String option, @RequestParam(value="totalPrice") String totalPrice, Model model) {
        try {
            model.addAttribute("carId", carId);
            model.addAttribute("kind", kind);
            model.addAttribute("color", color);
            model.addAttribute("option", option);
            model.addAttribute("totalPrice", totalPrice);
            model.addAttribute("carImageUrl", "/image/" + kind + "_" + color + ".jpg");
        } catch (Exception e) {
            e.printStackTrace();
            String errorMessage = "An error occurred while saving the configuration:" + e.getMessage();
            System.err.println(errorMessage);
        }

        return "thymeleaf/carbase/confirmation";
    }
	/*
	 * @GetMapping("/confirmation") public String
	 * confirmationPage(@RequestParam(value="carId") String
	 * carId, @RequestParam(value="kind") String kind, @RequestParam(value="color")
	 * String color, @RequestParam(value="option") String
	 * option, @RequestParam(value="totalPrice") String totalPrice, Model model) {
	 * try { model.addAttribute("carId", carId); model.addAttribute("kind", kind);
	 * model.addAttribute("color", color); model.addAttribute("option", option);
	 * model.addAttribute("totalPrice", totalPrice);
	 * model.addAttribute("carImageUrl", "/image/" + kind + "_" + color + ".jpg");
	 * 
	 * 
	 * } catch (Exception e) { e.printStackTrace(); String errorMessage
	 * ="An error occurred while saving the configuration:" + e.getMessage();
	 * System.err.println(errorMessage); }
	 * 
	 * return "thymeleaf/carbase/confirmation"; }
	 */
}
 