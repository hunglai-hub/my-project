package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.constant.UrlConst;

/**
 * メニュー画面 Controller
 */
@Controller
public class MenuController {
	
	//初期表示
	@GetMapping(UrlConst.MENU)
	public String view() {
		
		return "menu";
		
	}

}
