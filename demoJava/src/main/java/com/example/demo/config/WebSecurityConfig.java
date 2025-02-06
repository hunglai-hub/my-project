package com.example.demo.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.demo.constant.UrlConst;

import lombok.RequiredArgsConstructor;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class WebSecurityConfig {

	//ユーザ名の属性
	private final String USERNAME_PARAMETER = "loginId";

	//PasswordEncoder
	private final PasswordEncoder passwordEncoder;

	//ユーザ情報取得Service
	private final UserDetailsService userDetailService;

	//Message取得
	private final MessageSource messageSource;

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				.authorizeHttpRequests(
						authorize -> authorize.requestMatchers(UrlConst.NO_AUTHENTICATION).permitAll()
								.anyRequest().authenticated())
				.formLogin(
						login -> login.loginPage(UrlConst.LOGIN) //自作のログイン画面を使う
								.usernameParameter(USERNAME_PARAMETER) //ユーザ名の属性
								.defaultSuccessUrl(UrlConst.MENU)); //ログイン成功時のリダイレクトURL
		return http.build();
	}
	
	
	/**
	 * Provider 定義
	 * @return
	 */
	@Bean
	AuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailService);
		provider.setPasswordEncoder(passwordEncoder);
		provider.setMessageSource(messageSource);
		return provider;
	}

}
