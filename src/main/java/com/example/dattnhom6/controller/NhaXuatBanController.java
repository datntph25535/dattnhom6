package com.example.dattnhom6.controller;

import com.example.dattnhom6.entity.NhaXuatBan;
import com.example.dattnhom6.repository.NhaXuatBanRepository;
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
@RequestMapping("NhaXuatBan")
public class NhaXuatBanController {

    @Autowired
    private NhaXuatBanRepository nhaXuatBanRepository;

    @Autowired
    private ServletContext context;

    @GetMapping("list")
    public String viewNXB(Model model,
                          @RequestParam(name = "pageNumber", required = false, defaultValue = "1") Integer pageNumber,
                          @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize)
    {
        List<NhaXuatBan> listNXB = nhaXuatBanRepository.findAll();
        model.addAttribute("listNXB", listNXB);

        Pageable pageable = PageRequest.of(pageNumber-1, pageSize);
        Page<NhaXuatBan> page = nhaXuatBanRepository.findAll(pageable);

        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("listPage", page.getContent());

        return "/NhaXuatBan/list";
    }

    @GetMapping("create")
    public String createPage(Model model){
        model.addAttribute("nhaXuatBan", new NhaXuatBan());
        return "/NhaXuatBan/create";
    }

    @PostMapping("create")
    public String create(Model model, @ModelAttribute("nhaXuatBan") NhaXuatBan nhaXuatBan, BindingResult result, RedirectAttributes attributes){
        if (result.hasErrors()){
            return "/NhaXuatBan/create";
        }

        if (nhaXuatBan.getTen() == null || nhaXuatBan.getTen().isEmpty() || nhaXuatBan.getTen().trim().length() == 0){
            model.addAttribute("checkNXBNotNull", "Tên nhà xuất bản không được để trống!");
            return "/NhaXuatBan/create";
        }

        if (nhaXuatBan.getTen().matches("^\\d.*") || !nhaXuatBan.getTen().matches(".*[a-zA-Z].*")){
            model.addAttribute("checkNXBHopLe", "Tên nhà xuất bản hợp lệ!");
            return "/NhaXuatBan/create";
        }

        nhaXuatBanRepository.save(nhaXuatBan);
        attributes.addFlashAttribute("message", "Thêm thành công!");
        return "redirect:/NhaXuatBan/list";
    }

    @GetMapping("edit/{id}")
    public String editPage(Model model, @PathVariable Integer id){
        NhaXuatBan nhaXuatBan = nhaXuatBanRepository.findById(id).orElse(null);
        if (nhaXuatBan == null){
            model.addAttribute("messageFind", "Không tìm thấy id có mã: " +id);
            return "/NhaXuatBan/list";
        }
        model.addAttribute("nhaXuatBan", nhaXuatBanRepository.findById(id).orElse(null));
        return "/NhaXuatBan/edit";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") NhaXuatBan nhaXuatBan){
        nhaXuatBanRepository.delete(nhaXuatBan);
        return "redirect:/NhaXuatBan/list";
    }
}
