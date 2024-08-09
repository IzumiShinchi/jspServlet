package org.big.prj.admin;

import org.big.prj.dto.CarBaseDTO;
import org.big.prj.service.CarBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/admin/carbase")
public class AdminCarBaseController {

    @Autowired
    private CarBaseService carBaseService;

    private final Path rootLocation = Paths.get("src/main/resources/static/image");

    // 구성 목록 페이지
    @GetMapping("/list")
    public String listCarBases(Model model) {
        List<CarBaseDTO> carBases = carBaseService.getAllCarBases();
        model.addAttribute("carBases", carBases);
        return "thymeleaf/carBase/carbase_list";
    }

    // 구성 추가 페이지
    @GetMapping("/new")
    public String newCarBaseForm(Model model) {
        model.addAttribute("carBase", new CarBaseDTO());
        return "thymeleaf/carBase/carbase_form";
    }

 
 // 구성 수정 페이지로 가기
    @GetMapping("/edit/{baseId}")
    public String editCarBaseForm(@PathVariable("baseId") String baseId, Model model) {
        CarBaseDTO carBase = carBaseService.getCarBaseById(baseId);
        model.addAttribute("carBase", carBase);
        return "thymeleaf/carBase/carbase_form";
    }


    // 구성 삭제
    @GetMapping("/delete/{baseId}")
    public String deleteCarBase(@PathVariable("baseId") String baseId, Model model) {
        carBaseService.deleteCarBase(baseId);
        return "redirect:/admin/carbase/list";
    }

    @PostMapping("/save")
    public String saveCarBase(@ModelAttribute CarBaseDTO carBase, @RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        try {
            if (!Files.exists(rootLocation)) {
                Files.createDirectories(rootLocation);
            }

            if (!file.isEmpty()) {
                String filename = file.getOriginalFilename();
                Path filePath = this.rootLocation.resolve(filename);
                Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
                carBase.setBaseImage(filename);
            }

            carBaseService.insertCarBase(carBase);
            redirectAttributes.addFlashAttribute("message", "구성이 성공적으로 저장되었습니다.");
        } catch (IOException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("message", "이미지 저장 중 오류가 발생했습니다.");
        }
        return "redirect:/admin/carbase/list";
    }

    @PostMapping("/update")
    public String updateCarBase(@ModelAttribute CarBaseDTO carBase, @RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        try {
            if (!Files.exists(rootLocation)) {
                Files.createDirectories(rootLocation);
            }

            if (!file.isEmpty()) {
                String filename = file.getOriginalFilename();
                Path filePath = this.rootLocation.resolve(filename);
                Files.copy(file.getInputStream(), filePath);
                carBase.setBaseImage(filename);
            }

            carBaseService.updateCarBase(carBase);
            redirectAttributes.addFlashAttribute("message", "구성이 성공적으로 수정되었습니다.");
        } catch (IOException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("message", "이미지 저장 중 오류가 발생했습니다.");
        }
        return "redirect:/admin/carbase/list";
    }
}

   