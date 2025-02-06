package com.example.demo.constant;

/**
 * URL定数クラス
 */
public class UrlConst {

	//ログイン画面
	public static final String LOGIN = "/login";

	//ユーザ登録画面
	public static final String SIGNUP = "/signup";

	//トップメニュー画面
	public static final String MENU = "/menu";

	//認証不要画面
	public static final String[] NO_AUTHENTICATION = {LOGIN, SIGNUP, "/webjars/**"};

}
