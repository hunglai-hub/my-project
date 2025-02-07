package com.example.demo.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.constant.RoleKind;
import com.example.demo.constant.UrlConst;

/**
 * メニュー画面 Controller
 */
@Controller
public class MenuController {
	
	//初期表示
	@GetMapping(UrlConst.MENU)
	public String view(@AuthenticationPrincipal User user, Model model) {
		var hasUserManageRole = user.getAuthorities().stream()
				.allMatch(authority -> authority.getAuthority()
				.equals(RoleKind.ITEM_AND_USER_MANAGER.getRoleKind()));
				model.addAttribute("hasUserManageRole", hasUserManageRole);
		return "menu";
		
	}

}
