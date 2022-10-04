package com.company.view.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//DispatcherServlet 클래스는 서블릿 클래스의 성격을 가진 클래스이다.
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private HandlerMapping handlerMapping;
	private ViewResolver viewResolver;
		
	@Override //재정의
	public void init() throws ServletException {
		//객체 생성 후 
		handlerMapping = new HandlerMapping();
		viewResolver = new ViewResolver();
		
		viewResolver.setPrefix("./"); // => prefix가 "./"로 초기화 된다
		viewResolver.setSuffix(".jsp"); // => suffix가 ".jsp"로 초기화 된다
	
	}
	//생성자
	public DispatcherServlet() {
		super();
	}
	
	@Override //재정의
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
				throws ServletException, IOException {
		process(request,response);
	}
	@Override //재정의
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
		request.setCharacterEncoding("euc-kr");
		process(request,response);
	}
	//개발자가 필요한 메소드 구현
	private void process(HttpServletRequest request, HttpServletResponse response) 
			throws IOException{
		//1. 클라이언트의 요청 path 정보 추출한다.
		//
		//ex) "http://localhost:8087/MVC_FW_Board/login.do" 로 url요청이
		//들어왓다고 가정하자. 밑에 getrequestURI가 MVC_FW_Board/login.do를 갖고와
		// 준다. [아주 중요함!]
 		String uri = request.getRequestURI(); //마지막"/" 인덱스 값을 int로 리턴
 		int lastposition =  uri.lastIndexOf("/");//13을 리턴해준다.
 		String path = uri.substring(lastposition); // "/login.do" 얻어냄.
	
 		//2. HandlerMapping을 통해서 path에 해당하는 XxxController를 검색한다.
 		Controller ctrl = handlerMapping.getController(path);
 		
 		//3. 검색된 XxxController를 실행한다.
 		String viewName = ctrl.handlerRequest(request, response);
 		
 		//4. ViewResolver를 통해서 viewName에 해당하는 페이지 또는 XxxController 처리해라.
 		String view = null;
 		if(viewName.contains(".do")) {
 			view = viewName;
 		}else {
 			view = viewResolver.getView(viewName);
 		}
 		//5. view가 가리키는 문자열은 getBoard.do 또는 ./login.jsp
 		response.sendRedirect(view);
	}
}
