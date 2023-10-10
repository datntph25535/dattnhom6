package com.example.dattnhom6.controller;

import com.example.dattnhom6.entity.TacGia;
import com.example.dattnhom6.repository.TacGiaRepository;
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
@RequestMapping("TacGia")
public class TacGiaController {

    @Autowired
    private TacGiaRepository repository;

    @Autowired
    private ServletContext context;

    @GetMapping("list")
    public String viewTG(Model model,
                         @RequestParam(name = "pageNumber", required = false, defaultValue = "1") Integer pageNumber,
                         @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize) {
        List<TacGia> listTG = repository.findAll();
        model.addAttribute("listTG", listTG);

        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
        Page<TacGia> page = repository.findAll(pageable);

        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("listPage", page.getContent());

        return "/TacGia/list";
    }

    @GetMapping("create")
    public String createPage(Model model) {
        model.addAttribute("tacGia", new TacGia());
        return "/TacGia/create";
    }

    @PostMapping("create")
    public String create(Model model, @ModelAttribute("tacGia") TacGia tacGia, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            return "/TacGia/create";
        }

        if (tacGia.getTen() == null || tacGia.getTen().isEmpty() || tacGia.getTen().trim().length() == 0) {
            model.addAttribute("checkNXBNotNull", "Tên tác giả không được để trống!");
            return "/TacGia/create";
        }

        if (tacGia.getTen().matches("^\\d.*") || !tacGia.getTen().matches(".*[a-zA-Z].*")) {
            model.addAttribute("checkTGHopLe", "Tên tác giả hợp lệ!");
            return "/TacGia/create";
        }

        repository.save(tacGia);
        attributes.addFlashAttribute("message", "Thêm thành công!");
        return "redirect:/TacGia/list";
    }

    @GetMapping("edit/{id}")
    public String editPage(Model model, @PathVariable Integer id) {
        TacGia tacGia = repository.findById(id).orElse(null);
        if (tacGia == null) {
            model.addAttribute("messageFind", "Không tìm thấy id có mã: " + id);
            return "/TacGia/list";
        }
        model.addAttribute("tacGia", repository.findById(id).orElse(null));
        return "/TacGia/edit";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") TacGia tacGia) {
        repository.delete(tacGia);
        return "redirect:/TacGia/list";
    }


}
