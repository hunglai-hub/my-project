package com.example.demo.util;

import java.util.Locale;

import org.springframework.context.MessageSource;

/**
 * アプリケーション共通クラス
 */
public class AppUtil {
	
	/**
	 * キーでメッセージ取得
	 * @return message
	 */
	public static String getMessage(MessageSource messageSourse, String key, Object...params) {
		
		return messageSourse.getMessage(key, params, Locale.JAPAN);
		
	}

}
