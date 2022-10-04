<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>

<!-- �ڹ� Ŭ���� import  -->
<%@ page import ="com.company.MVC_FW_Board.board.BoardDAO" %>    
<%@ page import ="com.company.MVC_FW_Board.board.BoardDO" %>  
<%@ page import ="java.util.List" %>  

<!-- JSTL �����ϱ� ���ؼ�  -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%//��ũ��Ʈ�� => �ڹ� �ڵ� ����Ѵ�.
	request.setCharacterEncoding("euc-kr"); //�ѱ� ������ ����

	String searchField = ""; //����ڰ� ������ �ۼ��� or ���� ���� �׸�
	String searchText = "";	//����ڰ� �Է��� �˻� �Է°�

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
<title>��ü �Խñ� ��� ���� ������ => getBoardList.jsp</title>
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
		<h1>��ü �Խñ� ��� ����</h1>
		<h3>${idkey}�� ȯ���մϴ�.&nbsp;&nbsp;&nbsp;<a href="logout.do">�α׾ƿ�</a></h3>	
		<form name="boardListForm" method="post" action="getBoardList.jsp">
			<p>�� �Խñ� : ${totalList}��</p>
			<table border="1">
				<tr>
					<td align="center">
						<select name="searchCondition">
							<option value="TITLE"> ���� </option>
							<option value="WRITER"> �ۼ��� </option>
						</select>
						<input type="text" name="searchKeyword" />
						<input type="submit" value="�˻�" />
					</td>
				</tr>
			</table>
		</form>
			<table border="1">
				<tr>
					<th bgcolor="orange" width="100">��ȣ</th>
					<th bgcolor="orange" width="200">����</th>
					<th bgcolor="orange" width="150">�ۼ���</th>
					<th bgcolor="orange" width="150">�ۼ�����</th>
					<th bgcolor="orange" width="100">��ȸ��</th>
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
			<a href="insertBoard.jsp">�� �Խñ� ���</a>&nbsp;&nbsp;&nbsp;
			<a href="getBoardList.do">��ü �Խñ� ��� ����</a>
	</div>
</body>
</html>