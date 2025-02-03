package com.example.demo.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;

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
	
	@Column(name = "ROLE")
	private String role;
	
	@Column(name = "CREATED_AT")
	private LocalDate  createdAt;

}
