package com.example.demo.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoleKind {
	
	//商品情報の確認が可能
	ITEM_WATCHER("user"),
	
	//商品情報の管理が可能
	ITEM_MANAGER("manager"),
	
	//商品情報およびユーザ情報の管理が可能
	ITEM_AND_USER_MANAGER("admin");
	
	private String RoleKind;

}
