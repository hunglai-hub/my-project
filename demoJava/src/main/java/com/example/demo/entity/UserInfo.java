package com.example.demo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;


/**
 * ユーザ情報テーブル
 */

@Entity
@Table(name ="0010_UserInfo")
@Data
@AllArgsConstructor
public class UserInfo {
	@Id
	@Column(name = "LOGIN_ID")
	private String loginId;
	
	@Column(name = "PASSWORD")
	private String password;
	
	@Column(name = "EMAIL")
	private String email;

	@Column(name = "ROLE")
	private String role;
	
	@Column(name = "CREATED_AT")
	private String createdAt;
	
	@Column(name = "LOGIN_FAILURE_COUNT")
	private int loginFailureCount = 0;
	
	@Column(name = "ACCOUNT_LOCKED_TIME")
	private LocalDateTime accountLockedTime;
	
	@Column(name = "IS_DISABLED")
	private boolean isDisabled;
	
	public UserInfo() {
	}
	
	//ログイン失敗回数をインクリメント
	public UserInfo incrementLoginFailureCount() {
		return new UserInfo(loginId, password, email, role, createdAt, ++loginFailureCount, accountLockedTime, isDisabled);
	}
	
	//ログイン失敗情報をリセット
	public UserInfo resetLoginFailureInfo() {
		return new UserInfo(loginId, password, email, role, createdAt, 0, null, isDisabled);
	}
	
	//アカウントロックの状態を更新
	public UserInfo updateAccountLocked() {
		return new UserInfo(loginId, password, email, role, createdAt, 0, LocalDateTime.now(), isDisabled);
	}
	
}
