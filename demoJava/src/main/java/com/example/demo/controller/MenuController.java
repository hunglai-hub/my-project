package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * メニュー画面 Controller
 */
@Controller
public class MenuController {
	
	//初期表示
	@GetMapping("/menu")
	public String view() {
		
		return "menu";
		
	}

}
