package com.example.demo.service;

import java.util.Optional;

import org.dozer.Mapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.UserInfo;
import com.example.demo.form.SignupForm;
import com.example.demo.repository.UserInfoRepository;

import lombok.RequiredArgsConstructor;


/**
 * ユーザ登録画面 Service
 */

@Service
@RequiredArgsConstructor
public class SignupService {
	
	//ユーザ情報テーブルDAO
	private final UserInfoRepository repository;
	
	//Dozer Mapper
	private final Mapper mapper;
	
	//PassWordEncoder
	private final PasswordEncoder passwordEncoder;
	
	//ユーザ情報テーブル 新規登録
	public Optional<UserInfo> registUserInfo(SignupForm form){
		var userInfoExistedOpt = repository.findById(form.getLoginID());
		//同じログインIDがすでにある場合
		if(userInfoExistedOpt.isPresent()) {
			return Optional.empty();
		}else {
			form.setPassword(passwordEncoder.encode(form.getPassword()));
			var userInfo = mapper.map(form, UserInfo.class);
			return Optional.of(repository.save(userInfo)) ;
		}
		
		
	}

}
