package com.company.view.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.company.MVC_FW_Board.board.BoardDAO;
import com.company.MVC_FW_Board.board.BoardDO;
import com.company.view.controller.Controller;

public class GetBoardController implements Controller {

	@Override
	public String handlerRequest(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("게시글 상세보기 처리 완료!");
		
		String seq= request.getParameter("seq");
		
		BoardDO boardDO = new BoardDO();
	    boardDO.setSeq(Integer.parseInt(seq));
	      
	    BoardDAO boardDAO = new BoardDAO();
        //BoardDAO 클래스의 getBoard(boardDO) 메소드 호출
	    BoardDO board= boardDAO.getBoard(boardDO);
	    
	    HttpSession session= request.getSession();
	    session.setAttribute("board", board);
        
	    return "getBoard";
	   }
	}