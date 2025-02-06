package com.example.demo.controller;

import java.util.Optional;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.constant.MessageConst;
import com.example.demo.constant.SignupMessage;
import com.example.demo.constant.UrlConst;
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
	@GetMapping(UrlConst.SIGNUP)
	public String view(Model model, SignupForm form) {
		
		return "signup";
		
	}
	
	//ユーザ登録処理
	@PostMapping(UrlConst.SIGNUP)
	public void signup(Model model,@Validated SignupForm form, BindingResult bdResult) {
		if(bdResult.hasErrors()) {
			editGuideMessage(model,MessageConst.FORM_ERROR,true);
			return;
		}
		var userInfoOpt = service.registUserInfo(form);
		var signupMessage = judgeMessageKey(userInfoOpt);
			editGuideMessage(model,signupMessage.getMessageId(),signupMessage.isError());
	}
	
	/**
	 * 画面に表示するガイドメッセージ
	 * @param model
	 * @param message
	 * @param isError
	 */
	private void editGuideMessage(Model model,String messageId, boolean isError ) {
		var message = AppUtil.getMessage(messageSource, messageId);
		model.addAttribute("message", message);
		model.addAttribute("isError", isError);
	}
	
	/**
	 * ユーザ登録結果のメッセージキーを判断
	 * @param userInfo
	 * @return
	 */
	private SignupMessage judgeMessageKey(Optional<UserInfo> userInfoOpt) {
		
		if(userInfoOpt.isEmpty()) {
			return SignupMessage.EXISTED_LOGIN_ID;
		}else {
			return SignupMessage.SUCCEED;
		}
		
	}
	

}
