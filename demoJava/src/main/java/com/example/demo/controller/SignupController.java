package com.example.demo.controller;

import java.util.Optional;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.constant.MessageConst;
import com.example.demo.entity.UserInfo;
import com.example.demo.form.SignupForm;
import com.example.demo.service.SignupService;
import com.example.demo.util.AppUtil;

import lombok.RequiredArgsConstructor;

/**
 * ユーザ登録画面 Controller
 */

@Controller
@RequiredArgsConstructor
public class SignupController {
	
	// ユーザ登録画面 Service
	private final SignupService service;
	

	//MessageSource
	private final MessageSource messageSource;
	
	//初期表示
	@GetMapping("/signup")
	public String view(Model model, SignupForm form) {
		
		return "signup";
		
	}
	
	//ユーザ登録処理
	@PostMapping("/signup")
	public void signup(Model model, SignupForm form) {
		var userInfoOpt = service.registUserInfo(form);
			var message = AppUtil.getMessage(messageSource, judgeMessageKey(userInfoOpt));
			model.addAttribute("message", message);
		
	}
	
	/**
	 * ユーザ登録結果のメッセージキーを判断
	 * @param userInfo
	 * @return
	 */
	private String judgeMessageKey(Optional<UserInfo> userInfo) {
		
		if(userInfo.isEmpty()) {
			return MessageConst.SIGNUP_EXISTED_LOGIN_ID;
		}else {
			return MessageConst.SIGNUP_REGIST_SUCCEED;
		}
		
	}
	

}
