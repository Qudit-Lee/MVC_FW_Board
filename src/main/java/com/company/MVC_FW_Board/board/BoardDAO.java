package com.company.MVC_FW_Board.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.company.MVC_FW_Board.common.JDBCUtil;

public class BoardDAO {
	//H2 DB���� ���� ����
		private Connection 		  conn = null;
		private PreparedStatement stmt = null;
		private ResultSet		  rs = null;
		
		String where = "";
		//[�߿�] ��ü �Խñ� ��� ��ȸ ó�� �޼ҵ�
		public List<BoardDO> getBoardList(String searchField , String searchText){
			System.out.println("===> JDBC�� getBoardList() ��� ó�� �Ϸ�!");
			
			//ArrayList ���� �迭 ��ü ���� => �⺻ 10���� ��ü�� ������ ���� ����
			//�����ϸ� �ڵ����� 10���� ������ ��� �������
		
			List<BoardDO> boardList = new ArrayList<BoardDO>();
			
			try {
				conn = JDBCUtil.getConnection();
				
				if(searchField != null && searchText != null) {
					where = "where "+ searchField +" like '%"+ searchText
							+"%'";
				}
				String BOARD_CONDITION_LiST = "select * from board "+where+" order by seq desc";
				stmt = conn.prepareStatement(BOARD_CONDITION_LiST);
				rs = stmt.executeQuery();
				
				while(rs.next()) {
					BoardDO board = new BoardDO();
					
					board.setSeq(rs.getInt("SEQ"));
					board.setTitle(rs.getString("TITLE"));
					board.setWriter(rs.getString("WRITER"));
					board.setContent(rs.getString("CONTENT"));
					board.setRegdate(rs.getDate("REGDATE"));
					board.setCnt(rs.getInt("CNT"));
					
					boardList.add(board);
				}
			
			}catch(Exception e) {
				System.out.println("getBoardList() " + e);
			}finally {
				JDBCUtil.close(rs, stmt, conn);
			}
			return boardList;
		}
		/*End getBoardList() ����ڰ� [����]�� Ŭ�� �� ������ �Խñ� �󼼺��� �޼ҵ� ����*/
		public BoardDO getBoard(BoardDO boardDO) {
			System.out.println("===> JDBC�� getBoardList() ��� ó�� �Ϸ�!");
			
			BoardDO board= null;
			
			try {
				conn = JDBCUtil.getConnection();
				
				// 1. ��� �Խñ��� [����]�� Ŭ�� �� ��ȸ�� 1���� �޼ҵ� ����
				String UPDATE_CNT= "update board set cnt= cnt+1 where seq=?";
				stmt= conn.prepareStatement(UPDATE_CNT);
				stmt.setInt(1, boardDO.getSeq());
				stmt.executeUpdate(); //DML �۾��ÿ���  executeUpdate(); ȣ��
				
				// 2. �ش� �Խñ� ��������
				String BOARD_GET= "select * from board where seq=?";
				stmt= conn.prepareStatement(BOARD_GET);
				stmt.setInt(1,  boardDO.getSeq());
				rs= stmt.executeQuery(); //select �۾��̱⿡ executeQuery(); ȣ��
				
				if(rs.next()) {
					board= new BoardDO();
					board.setSeq(rs.getInt("SEQ"));
					board.setTitle(rs.getString("TITLE"));
					board.setWriter(rs.getString("WRITER"));
					board.setContent(rs.getString("CONTENT"));
					board.setRegdate(rs.getDate("REGDATE"));
					board.setCnt(rs.getInt("CNT"));
				}
				
			}catch(Exception e) {
				System.out.println("getBoard() "+ e);
			}finally {
				JDBCUtil.close(rs, stmt, conn);
			}
			return board;
		}
		
		// �Խù� ���� �޼ҵ�
		public void updateBoard(BoardDO boardDO) {
			System.out.println(">>> JDBC�� updateBoard() ��� ó��");
			//BoardDO board =null;
			try {
				conn = JDBCUtil.getConnection();
				
				String UPDATE_BOARD = "update board set title=?, content=? where seq=?";
				stmt = conn.prepareStatement(UPDATE_BOARD);
				stmt.setString(1, boardDO.getTitle());
				stmt.setString(2, boardDO.getContent());
				stmt.setInt(3, boardDO.getSeq());
				stmt.executeUpdate();

			} catch(Exception e) {
				System.out.println("updateBoard()" + e);
				//e.printStackTrace();
			} finally {
				JDBCUtil.close(stmt, conn);
			}//return board;
		}
		
		// �Խù� ����
		public void deleteBoard(BoardDO boardDO) {
			System.out.println(">>> JDBC�� deleteBoard() ��� ó��");

			try {
				conn = JDBCUtil.getConnection();
				
				String DELETE_BOARD = "delete from board where seq=?";
				stmt = conn.prepareStatement(DELETE_BOARD);
				stmt.setInt(1, boardDO.getSeq());
				stmt.executeUpdate();

			} catch(Exception e) {
				System.out.println("deleteBoard()" + e);
			} finally {
				JDBCUtil.close(stmt, conn);
			}
		}
}
