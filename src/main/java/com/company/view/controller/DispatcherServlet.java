package com.company.view.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//DispatcherServlet Ŭ������ ���� Ŭ������ ������ ���� Ŭ�����̴�.
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private HandlerMapping handlerMapping;
	private ViewResolver viewResolver;
		
	@Override //������
	public void init() throws ServletException {
		//��ü ���� �� 
		handlerMapping = new HandlerMapping();
		viewResolver = new ViewResolver();
		
		viewResolver.setPrefix("./"); // => prefix�� "./"�� �ʱ�ȭ �ȴ�
		viewResolver.setSuffix(".jsp"); // => suffix�� ".jsp"�� �ʱ�ȭ �ȴ�
	
	}
	//������
	public DispatcherServlet() {
		super();
	}
	
	@Override //������
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
				throws ServletException, IOException {
		process(request,response);
	}
	@Override //������
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
		request.setCharacterEncoding("euc-kr");
		process(request,response);
	}
	//�����ڰ� �ʿ��� �޼ҵ� ����
	private void process(HttpServletRequest request, HttpServletResponse response) 
			throws IOException{
		//1. Ŭ���̾�Ʈ�� ��û path ���� �����Ѵ�.
		//
		//ex) "http://localhost:8087/MVC_FW_Board/login.do" �� url��û��
		//���Ӵٰ� ��������. �ؿ� getrequestURI�� MVC_FW_Board/login.do�� �����
		// �ش�. [���� �߿���!]
 		String uri = request.getRequestURI(); //������"/" �ε��� ���� int�� ����
 		int lastposition =  uri.lastIndexOf("/");//13�� �������ش�.
 		String path = uri.substring(lastposition); // "/login.do" ��.
	
 		//2. HandlerMapping�� ���ؼ� path�� �ش��ϴ� XxxController�� �˻��Ѵ�.
 		Controller ctrl = handlerMapping.getController(path);
 		
 		//3. �˻��� XxxController�� �����Ѵ�.
 		String viewName = ctrl.handlerRequest(request, response);
 		
 		//4. ViewResolver�� ���ؼ� viewName�� �ش��ϴ� ������ �Ǵ� XxxController ó���ض�.
 		String view = null;
 		if(viewName.contains(".do")) {
 			view = viewName;
 		}else {
 			view = viewResolver.getView(viewName);
 		}
 		//5. view�� ����Ű�� ���ڿ��� getBoard.do �Ǵ� ./login.jsp
 		response.sendRedirect(view);
	}
}
