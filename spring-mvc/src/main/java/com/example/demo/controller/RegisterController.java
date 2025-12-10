package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.domain.model.UserForm;
import com.example.demo.domain.service.UsersRegisterService;

@Controller
public class RegisterController {

    @Autowired
    private UsersRegisterService usersRegisterService;
	
	@GetMapping("/form")
    private String readForm(@ModelAttribute UserForm userForm) {
        return "form";
    }

    @PostMapping("/form")
    private String confirm(@Validated(UserForm.Groups.class) @ModelAttribute UserForm userForm, BindingResult result, Model model) {

        if (result.hasErrors()) {
            // エラーがある場合、form.htmlに戻る
            return "form";
        }

        return "confirm";
    }
    
    // 【新規追加】登録実行メソッド
    @PostMapping("/register")
    public String register(@ModelAttribute UserForm userForm, BindingResult result) { // BindingResultを追加
        
        // サービスのカスタムバリデーションを呼び出し
        if (usersRegisterService.isValid(userForm, result)) {
            // E-Mail重複エラーがある場合、入力フォーム (form.html) に戻る
            // ★このとき、BindingResultに設定されたエラーメッセージが表示されます
            return "form"; 
        }

        // DBにデータを保存
        usersRegisterService.register(userForm);

        // 登録完了後のリダイレクト
        return "redirect:/complete"; 
    }
    
    // 【新規追加】完了画面表示メソッド
    @GetMapping("/complete")
    public String complete() {
        return "complete";
    }
}