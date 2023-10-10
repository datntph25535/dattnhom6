package com.example.dattnhom6.controller;

import com.example.dattnhom6.entity.ChucVu;
import com.example.dattnhom6.repository.ChucVuRepository;
import jakarta.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("ChucVu")
public class ChucVuController {

    @Autowired
    private ChucVuRepository repository;

    @Autowired
    private ServletContext context;

    @GetMapping("list")
    public String viewTG(Model model,
                         @RequestParam(name = "pageNumber", required = false, defaultValue = "1") Integer pageNumber,
                         @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize) {
        List<ChucVu> listCV = repository.findAll();
        model.addAttribute("listCV", listCV);

        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
        Page<ChucVu> page = repository.findAll(pageable);

        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("listPage", page.getContent());

        return "/ChucVu/list";
    }

    @GetMapping("create")
    public String createPage(Model model) {
        model.addAttribute("chucVu", new ChucVu());
        return "/ChucVu/create";
    }

    @PostMapping("create")
    public String create(Model model, @ModelAttribute("chucVu") ChucVu chucVu, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            return "/ChucVu/create";
        }

        if (chucVu.getTen() == null || chucVu.getTen().isEmpty() || chucVu.getTen().trim().length() == 0) {
            model.addAttribute("checkCVNotNull", "Tên chức vụ không được để trống!");
            return "/ChucVu/create";
        }

        if (chucVu.getTen().matches("^\\d.*") || !chucVu.getTen().matches(".*[a-zA-Z].*")) {
            model.addAttribute("checkCVHopLe", "Tên chức vụ hợp lệ!");
            return "/ChucVu/create";
        }

        repository.save(chucVu);
        attributes.addFlashAttribute("message", "Thêm thành công!");
        return "redirect:/ChucVu/list";
    }

    @GetMapping("edit/{id}")
    public String editPage(Model model, @PathVariable Integer id) {
        ChucVu chucVu = repository.findById(id).orElse(null);
        if (chucVu == null) {
            model.addAttribute("messageFind", "Không tìm thấy id có mã: " + id);
            return "/ChucVu/list";
        }
        model.addAttribute("chucVu", repository.findById(id).orElse(null));
        return "/ChucVu/edit";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") ChucVu chucVu) {
        repository.delete(chucVu);
        return "redirect:/ChucVu/list";
    }
}
