package com.company.view.controller;

import java.util.HashMap;
import java.util.Map;	//클래스가 아닌 인터페이스이다.

import com.company.view.board.DeleteBoardController;
import com.company.view.board.GetBoardController;
import com.company.view.board.GetBoardListController;
import com.company.view.board.InsertBoardController;
import com.company.view.board.UpdateBoardController;
import com.company.view.user.LoginController;
import com.company.view.user.LogoutController;

/*
 * 자바 자료구조 개념이 있어야함!!
 * Map 인터페이스를 구현(implements)받은 클래스에 HashMap 클래스 (중요)
 */

public class HandlerMapping {
	//제네릭 기능(조금 더 정확한 타입의 객체를 받아들이기 위함)
	private Map<String, Controller> mappings;
	
	//생성자 생성
	public HandlerMapping() {
		//해시맵 객체 생성
		mappings = new HashMap<String, Controller>();
		
		//map 계열 이므로 put으로 추가
		mappings.put("/login.do", new LoginController());
		mappings.put("/getBoardList.do", new GetBoardListController());
		mappings.put("/getBoard.do", new GetBoardController());
		mappings.put("/insertBoard.do", new InsertBoardController());
		mappings.put("/updateBoard.do", new UpdateBoardController());
		mappings.put("/deleteBoard.do", new DeleteBoardController());
		mappings.put("/logout.do", new LogoutController());
	}
	
	//개발자가 필요한 메소드 구현
	public Controller getController(String path) {
		return mappings.get(path);
	}
}
