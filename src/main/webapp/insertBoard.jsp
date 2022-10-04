<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>

<!-- 자바 클래스 import  -->
<%@ page import ="com.company.MVC_FW_Board.board.BoardDAO" %>    
<%@ page import ="com.company.MVC_FW_Board.board.BoardDO" %>  
<%@ page import ="java.util.List" %>  


<% //[중요] GetBoardController 클래스에서 session.setAttribute("board", board); 등록한 속성값을 가져오기
   BoardDO board = (BoardDO)session.getAttribute("board");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>게시글 상세보기 페이지 >> getBoard.jsp</title>
<style>
   #div_box{
      position: absoLute;
      top: 10%;
      left: 20%;
   }
   #a{
   	text-align: center;
   	width: 100px;
    margin: 0 auto;
   }
</style>
</head>
<body>
   <div id = "div_box">
      <h1>게시글 상세보기</h1>
      <a href = "logout.do">로그아웃</a>
      <hr>
      <form name = "getBoardForm" method="post" action="updateBoard.do">
         <input type="hidden" name = "seq" value = "${board.getSeq()}" />
         <table border="1">
            <tr>
               <td bgcolor="orange" width="70">제목</td>
               <td align="Left"><input name="title" type="text" value="${board.title }"></td>
            </tr>
            <tr>
               <td bgcolor="orange" >작성자</td>
               <td align="Left"><textarea name="content" rows="10" cols="40">${board.content}</textarea></td>
            </tr>
            <tr>
               <td bgcolor="orange" >내용</td>
               <td align="Left"><textarea name="content" rows="10" cols="40">${board.content}</textarea></td>
            </tr>
            <tr>
               <td bgcolor="orange" >작성일자</td>
               <td align="Left">${board.regdate}</td>
            </tr>
            <tr>
               <td bgcolor="orange" >조회수</td>
               <td align="Left">${board.cnt}</td>
            </tr>
            <tr>
               <td colspan="2" align="center">
            <input type="submit" value="게시글 등록"/>
            </td>
            </tr>
            </table>
      </form>
   </div>
</body>
</html>