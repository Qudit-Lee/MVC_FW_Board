package com.company.view.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.company.MVC_FW_Board.user.UserDAO;
import com.company.MVC_FW_Board.user.UserDO;
import com.company.view.controller.Controller;

public class LoginController implements Controller{

	@Override //������
	public String handlerRequest(HttpServletRequest request, HttpServletResponse response) {
		// 1. login.jsp ���������� �Ѿ�� ����� �Է� ���� ����
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		
		//2.�Ѿ�� ������ UserDO ��ü�� ����ʵ忡 �ʱ�ȭ
		UserDO userDO = new UserDO();
		userDO.setId(id);
		userDO.setPassword(password);
		
		//3. UserDAO ��ü �����ϱ�
		UserDAO userDAO = new UserDAO();
		UserDO user = userDAO.getUser(userDO);
		
		if(user != null) {
			//�α��� ���� = ���� ����
			HttpSession session = request.getSession();
			session.setAttribute("idkey", id);
			System.out.println("�α��� ����!!");
			return "getBoardList.do";
		}else {
			//�α��� ���� ��, ���� ����
			return "login";
		}
		
	}

}
