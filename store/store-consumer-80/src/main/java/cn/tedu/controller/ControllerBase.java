package cn.tedu.controller;

import javax.servlet.http.HttpSession;

public class ControllerBase {
	
	private final String URL="http://localhost:8001"; 
	
	public final Integer SUCCEES=200;
	
	public final Integer FAILED=500;
	
	/**
	 * 从Session获取当前登录的用户id
	 * @param session HttpSession对象
	 * @return 当前登录的用户id
	 */
	protected final Integer getUidFromSession(HttpSession session) {
		return Integer.valueOf(session.getAttribute("uid").toString());
	}

	public String getURL() {
		return URL;
	}
}
