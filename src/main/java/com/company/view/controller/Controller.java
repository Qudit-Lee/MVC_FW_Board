package com.company.view.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Controller {
	//추상메소드
	String handlerRequest(HttpServletRequest request, HttpServletResponse response);

}
