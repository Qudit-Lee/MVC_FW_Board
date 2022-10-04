package com.company.view.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.company.MVC_FW_Board.board.BoardDAO;
import com.company.MVC_FW_Board.board.BoardDO;
import com.company.view.controller.Controller;

public class GetBoardListController implements Controller {

	@Override
	public String handlerRequest(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("전체 게시글 검색 처리 완료!!");
		
		//게시글 검색 조건이 발생한 경우 필요한 소스
		String searchField = ""; //사용자가 선택한 작성자 or 제목 선택 항목
		String searchText = "";	//사용자가 입력한 검색 입력값

		if(request.getParameter("searchCondition") != "" 
				&& request.getParameter("searchKeyword") != ""){
			searchField = request.getParameter("searchCondition");
			searchText = request.getParameter("searchKeyword");
			}
		
		BoardDAO boardDAO = new BoardDAO();
		List<BoardDO> boardList = boardDAO.getBoardList(searchField, searchText);
		
		HttpSession session = request.getSession();
		session.setAttribute("boardList", boardList);
		
		return "getBoardList";
		
		
	}
	

}
