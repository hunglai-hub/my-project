package com.example.demo.controller;

import org.springframework.context.MessageSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.constant.MessageConst;
import com.example.demo.form.LoginForm;
import com.example.demo.service.LoginService;
import com.example.demo.util.AppUtil;

import lombok.RequiredArgsConstructor;

/**
 * ログイン画面 Controller
 */

@Controller
@RequiredArgsConstructor
public class LoginController {
	
	// ログイン画面 Service
	private final LoginService service;
	
	//PassWordEncoder
	private final PasswordEncoder passwordEncoder;
	
	//MessageSource
	private final MessageSource messageSource;
	
	//初期表示
	@GetMapping("/login")
	public String view(Model model, LoginForm form) {
		
		return "login";
		
	}
	
	//ログイン処理
	@PostMapping("/login")
	public String login(Model model, LoginForm form) {
		var userInfo = service.searchUserByID(form.getLoginID());
		var isCorrectAccount = userInfo.isPresent() 
				&& passwordEncoder.matches(form.getPassword(), userInfo.get().getPassword());
		if(isCorrectAccount) {
			return "redirect:/menu";
		}else {
			var errorMsg = AppUtil.getMessage(messageSource, MessageConst.LOGIN_WRONG_INPUT);
			model.addAttribute("errorMsg", errorMsg);
			return "/login";
		}
		
	}

}
