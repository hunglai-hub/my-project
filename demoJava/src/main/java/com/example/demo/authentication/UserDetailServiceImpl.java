package com.example.demo.authentication;

import java.time.LocalDateTime;

import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
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
	
	//アカウントロックの継続時間
	private final int LOCKING_TIME = 1;
	
	//ログイン失敗の上限回数
	private final int LOCKING_BORDER_COUNT = 3;

	@Override
	public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
		var userInfo = repository.findById(loginId)
				.orElseThrow(() -> new UsernameNotFoundException(loginId));
		
		var accountLockedTime = userInfo.getAccountLockedTime();
		var isAccountLocked = accountLockedTime != null && accountLockedTime.plusHours(LOCKING_TIME).isAfter(LocalDateTime.now());
		
		return User.withUsername(userInfo.getLoginId())
				.password(userInfo.getPassword())
				.roles("user")
				.disabled(userInfo.isDisabled())
				.accountLocked(isAccountLocked)
				.build();
	}
	
	/**
	 * ログイン認証失敗時に失敗回数を加算
	 */
	@EventListener
	public void handle(AuthenticationFailureBadCredentialsEvent event) {
		var loginId = event.getAuthentication().getName();
		repository.findById(loginId).ifPresent(userInfo -> {
			repository.save(userInfo.incrementLoginFailureCount());
			var isReachFailureCount = userInfo.getLoginFailureCount() == LOCKING_BORDER_COUNT;
			if(isReachFailureCount) {
				repository.save(userInfo.updateAccountLocked());
			}
		});
		
	}
	
	/**
	 * ログイン成功時のログイン失敗回数をリセット
	 */
	@EventListener
	public void handle(AuthenticationSuccessEvent event) {
		var loginId = event.getAuthentication().getName();
		repository.findById(loginId).ifPresent(userInfo -> {
			repository.save(userInfo.resetLoginFailureInfo());
			
		});
		
	}
	
	
	
	
	
	
	
	
	
}
