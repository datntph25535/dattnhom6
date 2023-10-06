package com.example.dattnhom6.controller;


import com.example.dattnhom6.entity.TheLoaiSach;
import com.example.dattnhom6.repository.TheLoaiSachRepository;
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
@RequestMapping("TheLoaiSach")
public class TheLoaiSachController {

    @Autowired
    private TheLoaiSachRepository theLoaiSachRepository;

    @Autowired
    private ServletContext context;

    @GetMapping("list")
    public String viewNXB(Model model,
                          @RequestParam(name = "pageNumber", required = false, defaultValue = "1") Integer pageNumber,
                          @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize)
    {
        List<TheLoaiSach> listTLS =theLoaiSachRepository.findAll();
        model.addAttribute("listTLS", listTLS);

        Pageable pageable = PageRequest.of(pageNumber-1, pageSize);
        Page<TheLoaiSach> page = theLoaiSachRepository.findAll(pageable);

        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("listPage", page.getContent());

        return "/TheLoaiSach/list";
    }

    @GetMapping("create")
    public String createPage(Model model){
        model.addAttribute("theLoaiSach", new TheLoaiSach());
        return "/TheLoaiSach/create";
    }

    @PostMapping("TheLoaiSach")
    public String create(Model model, @ModelAttribute("theLoaiSach") TheLoaiSach theLoaiSach, BindingResult result, RedirectAttributes attributes){
        if (result.hasErrors()){
            return "/TheLoaiSach/create";
        }

        if (theLoaiSach.getTheLoai() == null || theLoaiSach.getTheLoai().isEmpty() || theLoaiSach.getTheLoai().trim().length() == 0){
            model.addAttribute("checkTLSNotNull", "Thể loại sách không được để trống!");
            return "/TheLoaiSach/create";
        }

        if (theLoaiSach.getTheLoai().matches("^\\d.*") || !theLoaiSach.getTheLoai().matches(".*[a-zA-Z].*")){
            model.addAttribute("checkTLSHopLe", "Tên nhà xuất bản không hợp lệ!");
            return "/TheLoaiSach/create";
        }

        theLoaiSachRepository.save(theLoaiSach);
        attributes.addFlashAttribute("message", "Thêm thành công!");
        return "redirect:/TheLoaiSach/list";
    }

    @GetMapping("edit/{id}")
    public String editPage(Model model, @PathVariable Integer id){
        TheLoaiSach theLoaiSach = theLoaiSachRepository.findById(id).orElse(null);
        if (theLoaiSach == null){
            model.addAttribute("messageFind", "Không tìm thấy id có mã: " +id);
            return "/TheLoaiSach/list";
        }
        model.addAttribute("theLoaiSach", theLoaiSachRepository.findById(id).orElse(null));
        return "/TheLoaiSach/edit";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") TheLoaiSach theLoaiSach){
        theLoaiSachRepository.delete(theLoaiSach);
        return "redirect:/TheLoaiSach/list";
    }
}
