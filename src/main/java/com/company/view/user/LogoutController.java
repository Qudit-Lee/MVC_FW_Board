package com.company.view.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.company.view.controller.Controller;

public class LogoutController implements Controller {

	@Override
	public String handlerRequest(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("�α׾ƿ� ó�� �Ϸ�!");
		
		HttpSession session= request.getSession();
		session.invalidate(); // ���� ���� ��ü ��ȿȭ
		
		return "login";
	}
	

}
