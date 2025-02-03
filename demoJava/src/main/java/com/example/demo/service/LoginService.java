package com.example.demo.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.entity.UserInfo;
import com.example.demo.repository.UserInfoRepository;

import lombok.RequiredArgsConstructor;


/**
 * ログイン画面 Service
 */

@Service
@RequiredArgsConstructor
public class LoginService {
	
	//ユーザ情報テーブルDAO
	private final UserInfoRepository repository;
	
	//ユーザ情報テーブルキー検索
	public Optional<UserInfo> searchUserByID(String loginId){
		return repository.findById(loginId);
		
	}

}
