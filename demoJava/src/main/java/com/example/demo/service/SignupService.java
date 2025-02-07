package com.example.demo.service;

import java.util.Optional;

import com.example.demo.entity.UserInfo;
import com.example.demo.form.SignupForm;

/**
 * ユーザ登録画面 Serviceインターフェースクラス
 */
public interface SignupService {
	
	public Optional<UserInfo> registUserInfo(SignupForm form);

}
