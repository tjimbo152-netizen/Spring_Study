package com.jutjoy.controller.profile;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jutjoy.domain.entity.profile.Profile;
import com.jutjoy.domain.form.profile.ProfileCreateForm;
import com.jutjoy.domain.form.profile.ProfileEditForm;
import com.jutjoy.service.profile.ProfileService;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    // --- 一覧表示 ---
    @GetMapping("/list")
    public String list(Model model) {
        List<Profile> profileList = profileService.findAll();
        model.addAttribute("profileList", profileList);
        return "profile/list";
    }

    // --- 新規登録画面 ---
    @GetMapping("/create") 
    public String create(@ModelAttribute("profileCreateForm") ProfileCreateForm form) {
        return "profile/create"; 
    }

    // --- 新規保存処理 ---
    @PostMapping("/create")
    public String save(@Validated @ModelAttribute("profileCreateForm") ProfileCreateForm form,
                       BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "profile/create";
        }
        profileService.save(form);
        // 完了画面ではなく一覧へ戻る設定
        return "redirect:/profile/list"; 
    }

    // --- 編集画面の表示 ---
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        Profile profile = profileService.findById(id);
        
        ProfileEditForm form = new ProfileEditForm();
        form.setId(profile.getId());
        form.setName(profile.getName());
        form.setGender(profile.getGender());
        form.setHobby(profile.getHobby());
        form.setIntroduction(profile.getIntroduction());
        
        model.addAttribute("profileEditForm", form);
        return "profile/edit";
    }

    // --- 更新処理 ---
    @PostMapping("/edit")
    public String update(@Validated @ModelAttribute("profileEditForm") ProfileEditForm form,
                         BindingResult result) {
        if (result.hasErrors()) {
            return "profile/edit";
        }
        profileService.update(form);
        return "redirect:/profile/list";
    }

    // --- 削除処理 ---
    @PostMapping("/delete")
    public String delete(@RequestParam Integer id) {
        profileService.delete(id);
        return "redirect:/profile/list";
    }
}