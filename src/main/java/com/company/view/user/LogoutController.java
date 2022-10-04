package com.company.view.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.company.view.controller.Controller;

public class LogoutController implements Controller {

	@Override
	public String handlerRequest(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("로그아웃 처리 완료!");
		
		HttpSession session= request.getSession();
		session.invalidate(); // 현재 세션 객체 무효화
		
		return "login";
	}
	

}
