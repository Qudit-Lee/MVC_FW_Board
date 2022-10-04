package com.company.view.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.company.MVC_FW_Board.user.UserDAO;
import com.company.MVC_FW_Board.user.UserDO;
import com.company.view.controller.Controller;

public class LoginController implements Controller{

	@Override //재정의
	public String handlerRequest(HttpServletRequest request, HttpServletResponse response) {
		// 1. login.jsp 페이지에서 넘어온 사용자 입력 정보 추출
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		
		//2.넘어온 값들을 UserDO 객체의 멤버필드에 초기화
		UserDO userDO = new UserDO();
		userDO.setId(id);
		userDO.setPassword(password);
		
		//3. UserDAO 객체 생성하기
		UserDAO userDAO = new UserDAO();
		UserDO user = userDAO.getUser(userDO);
		
		if(user != null) {
			//로그인 성공 = 인증 성공
			HttpSession session = request.getSession();
			session.setAttribute("idkey", id);
			System.out.println("로그인 성공!!");
			return "getBoardList.do";
		}else {
			//로그인 실패 즉, 인증 실패
			return "login";
		}
		
	}

}
