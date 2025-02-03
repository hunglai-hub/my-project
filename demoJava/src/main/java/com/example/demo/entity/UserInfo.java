package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;


/**
 * ユーザ情報テーブル
 */

@Entity
@Table(name ="0010_UserInfo")
@Data
public class UserInfo {
	@Id
	@Column(name = "LOGIN_ID")
	private String loginID;
	
	@Column(name = "PASSWORD")
	private String password;
	
	@Column(name = "EMAIL")
	private String email;
	
}
