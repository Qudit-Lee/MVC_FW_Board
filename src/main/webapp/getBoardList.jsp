<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>

<!-- 자바 클래스 import  -->
<%@ page import ="com.company.MVC_FW_Board.board.BoardDAO" %>    
<%@ page import ="com.company.MVC_FW_Board.board.BoardDO" %>  
<%@ page import ="java.util.List" %>  

<!-- JSTL 적용하기 위해서  -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%//스크립트릿 => 자바 코드 기술한다.
	request.setCharacterEncoding("euc-kr"); //한글 깨지지 마라구

	String searchField = ""; //사용자가 선택한 작성자 or 제목 선택 항목
	String searchText = "";	//사용자가 입력한 검색 입력값

	if(request.getParameter("searchCondition") != "" 
			&& request.getParameter("searchKeyword") != ""){
		searchField = request.getParameter("searchCondition");
		searchText = request.getParameter("searchKeyword");
		}
		BoardDAO boardDAO = new BoardDAO();
		List<BoardDO> boardList = boardDAO.getBoardList(searchField, searchText);
		
		request.setAttribute("boardList", boardList);
		
		int totalList = boardList.size();
		request.setAttribute("totalList", totalList);
		
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>전체 게시글 목록 보기 페이지 => getBoardList.jsp</title>
<style>
   #div_box{
      position: absoLute;
      top: 10%;
      left: 40%;
   }
</style>
</head>
<body>
	<div id="div_box">
		<h1>전체 게시글 목록 보기</h1>
		<h3>${idkey}님 환영합니다.&nbsp;&nbsp;&nbsp;<a href="logout.do">로그아웃</a></h3>	
		<form name="boardListForm" method="post" action="getBoardList.jsp">
			<p>총 게시글 : ${totalList}건</p>
			<table border="1">
				<tr>
					<td align="center">
						<select name="searchCondition">
							<option value="TITLE"> 제목 </option>
							<option value="WRITER"> 작성자 </option>
						</select>
						<input type="text" name="searchKeyword" />
						<input type="submit" value="검색" />
					</td>
				</tr>
			</table>
		</form>
			<table border="1">
				<tr>
					<th bgcolor="orange" width="100">번호</th>
					<th bgcolor="orange" width="200">제목</th>
					<th bgcolor="orange" width="150">작성자</th>
					<th bgcolor="orange" width="150">작성일자</th>
					<th bgcolor="orange" width="100">조회수</th>
				</tr>
				<c:forEach var="board" items="${boardList}">
					<tr>
						<td align="center">${board.getSeq()}</td>
						<td align="left"><a href="getBoard.do?seq=${board.getSeq()}">${board.getTitle()}</a></td>
						<td align="center">${board.getWriter()}</td>
						<td align="center">${board.getRegdate()}</td>
						<td align="center">${board.getCnt()}</td>
					</tr>
				</c:forEach>
			</table>
			<br>
			<a href="insertBoard.jsp">새 게시글 등록</a>&nbsp;&nbsp;&nbsp;
			<a href="getBoardList.do">전체 게시글 목록 보기</a>
	</div>
</body>
</html>