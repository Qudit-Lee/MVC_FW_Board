package com.company.view.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Controller {
	//�߻�޼ҵ�
	String handlerRequest(HttpServletRequest request, HttpServletResponse response);

}
