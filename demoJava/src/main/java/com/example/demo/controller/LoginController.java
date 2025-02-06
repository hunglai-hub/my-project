package com.example.demo.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.context.MessageSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.constant.MessageConst;
import com.example.demo.constant.UrlConst;
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
	
	//Session情報
	private final HttpSession session;
	
	//ログインエラー表示
	@GetMapping(UrlConst.LOGIN)
	public String view(Model model, LoginForm form) {
		
		return "login";
		
	}
	
	//初期表示
	@GetMapping(value = UrlConst.LOGIN, params = "error")
	public String viewWithError(Model model, LoginForm form) {
		var errorInfo = (Exception)session.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
		model.addAttribute("errorMsg", errorInfo.getMessage());
		return "login";
		
	}
	
	//ログイン処理
	@PostMapping(UrlConst.LOGIN)
	public String login(Model model, LoginForm form) {
		var userInfo = service.searchUserByID(form.getLoginId());
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
