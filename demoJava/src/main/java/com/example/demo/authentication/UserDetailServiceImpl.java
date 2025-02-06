package com.example.demo.authentication;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.demo.repository.UserInfoRepository;

import lombok.RequiredArgsConstructor;


/**
 * ユーザ情報生成
 */
@Component
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {
	
	//ユーザ情報Repository
	private final UserInfoRepository repository;

	@Override
	public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
		var userInfo = repository.findById(loginId).orElseThrow(() -> new UsernameNotFoundException(loginId));
		
		return User.withUsername(userInfo.getLoginId())
				.password(userInfo.getPassword())
				.roles("user")
				.build();
	}

}
