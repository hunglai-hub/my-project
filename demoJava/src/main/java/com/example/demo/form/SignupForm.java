package com.example.demo.form;

import org.hibernate.validator.constraints.Length;

import lombok.Data;
/**
 * ユーザ登録画面 Form
 */
@Data
public class SignupForm {
	
	@Length(min = 3, max = 20)
	private String loginId;

	@Length(min = 3, max = 20)
	private String password;
	
	//@Length(min = 10, max = 40)
	private String email;
	
	private String role;
	
	private String createdAt;
	
	private int isDisabled = 0;

}
