package com.jutjoy.controller.profile; // ★ パッケージに profile を追加

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jutjoy.domain.form.profile.ProfileCreateForm;
import com.jutjoy.service.profile.ProfileService;

@Controller
@RequestMapping("/profile") 
public class ProfileController {
	
	@Autowired
    private ProfileService profileService;

    // URL: http://localhost:8080/profile/create
    @GetMapping("/create") 
    public String create(@ModelAttribute("profileCreateForm") ProfileCreateForm form, Model model) {
        // Modelに属性を追加することで、create.htmlのth:object="${profileCreateForm}"と紐づく
        return "profile/create"; 
    }

    @PostMapping("/create")
    public String save(@Validated @ModelAttribute("profileCreateForm") ProfileCreateForm form,
                       BindingResult bindingResult,
                       RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return "profile/create";
        }

        profileService.save(form);

        // ニュース機能の完了画面を流用
        redirectAttributes.addFlashAttribute("message", "プロフィールが正常に登録されました。");
        return "redirect:/news/create/complete"; 
    }
}