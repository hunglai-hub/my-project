package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.dozer.Mapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.constant.RoleKind;
import com.example.demo.entity.UserInfo;
import com.example.demo.form.SignupForm;
import com.example.demo.repository.UserInfoRepository;

import lombok.RequiredArgsConstructor;


/**
 * ユーザ登録画面 Service実装クラス
 */

@Service
@RequiredArgsConstructor
public class SignupServiceImpl implements SignupService {
	
	//ユーザ情報テーブルDAO
	private final UserInfoRepository repository;
	
	//Dozer Mapper
	private final Mapper mapper;
	
	//PassWordEncoder
	private final PasswordEncoder passwordEncoder;
	
	//ユーザ情報テーブル 新規登録
	@Override
	public Optional<UserInfo> registUserInfo(SignupForm form){
		var userInfoExistedOpt = repository.findById(form.getLoginId());
		//同じログインIDがすでにある場合
		if(userInfoExistedOpt.isPresent()) {
			return Optional.empty();
		}else {
			var userInfo = mapper.map(form, UserInfo.class);
			userInfo.setPassword(passwordEncoder.encode(form.getPassword()));
			userInfo.setRole(RoleKind.ITEM_WATCHER.getRoleKind());
			userInfo.setCreatedAt(LocalDateTime.now().toString());
			if(userInfo.getEmail() == "") {
				userInfo.setEmail("undefined@gmail.com");
			}
			return Optional.of(repository.save(userInfo)) ;
		}
		
		
	}

}
