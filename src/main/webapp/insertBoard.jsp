<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>

<!-- �ڹ� Ŭ���� import  -->
<%@ page import ="com.company.MVC_FW_Board.board.BoardDAO" %>    
<%@ page import ="com.company.MVC_FW_Board.board.BoardDO" %>  
<%@ page import ="java.util.List" %>  


<% //[�߿�] GetBoardController Ŭ�������� session.setAttribute("board", board); ����� �Ӽ����� ��������
   BoardDO board = (BoardDO)session.getAttribute("board");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>�Խñ� �󼼺��� ������ >> getBoard.jsp</title>
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
      <h1>�Խñ� �󼼺���</h1>
      <a href = "logout.do">�α׾ƿ�</a>
      <hr>
      <form name = "getBoardForm" method="post" action="updateBoard.do">
         <input type="hidden" name = "seq" value = "${board.getSeq()}" />
         <table border="1">
            <tr>
               <td bgcolor="orange" width="70">����</td>
               <td align="Left"><input name="title" type="text" value="${board.title }"></td>
            </tr>
            <tr>
               <td bgcolor="orange" >�ۼ���</td>
               <td align="Left"><textarea name="content" rows="10" cols="40">${board.content}</textarea></td>
            </tr>
            <tr>
               <td bgcolor="orange" >����</td>
               <td align="Left"><textarea name="content" rows="10" cols="40">${board.content}</textarea></td>
            </tr>
            <tr>
               <td bgcolor="orange" >�ۼ�����</td>
               <td align="Left">${board.regdate}</td>
            </tr>
            <tr>
               <td bgcolor="orange" >��ȸ��</td>
               <td align="Left">${board.cnt}</td>
            </tr>
            <tr>
               <td colspan="2" align="center">
            <input type="submit" value="�Խñ� ���"/>
            </td>
            </tr>
            </table>
      </form>
   </div>
</body>
</html>