package com.company.MVC_FW_Board.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.company.MVC_FW_Board.common.JDBCUtil;

public class BoardDAO {
	//H2 DB관련 변수 선언
		private Connection 		  conn = null;
		private PreparedStatement stmt = null;
		private ResultSet		  rs = null;
		
		String where = "";
		//[중요] 전체 게시글 목록 조회 처리 메소드
		public List<BoardDO> getBoardList(String searchField , String searchText){
			System.out.println("===> JDBC로 getBoardList() 기능 처리 완료!");
			
			//ArrayList 가변 배열 객체 생성 => 기본 10개의 객체를 저장할 공간 마련
			//부족하면 자동으로 10개의 공간을 계속 만들어줌
		
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
		/*End getBoardList() 사용자가 [제목]을 클릭 시 선텍한 게시글 상세보기 메소드 구현*/
		public BoardDO getBoard(BoardDO boardDO) {
			System.out.println("===> JDBC로 getBoardList() 기능 처리 완료!");
			
			BoardDO board= null;
			
			try {
				conn = JDBCUtil.getConnection();
				
				// 1. 어느 게시글의 [제목]을 클릭 시 조회수 1증가 메소드 구현
				String UPDATE_CNT= "update board set cnt= cnt+1 where seq=?";
				stmt= conn.prepareStatement(UPDATE_CNT);
				stmt.setInt(1, boardDO.getSeq());
				stmt.executeUpdate(); //DML 작업시에는  executeUpdate(); 호출
				
				// 2. 해당 게시글 가져오기
				String BOARD_GET= "select * from board where seq=?";
				stmt= conn.prepareStatement(BOARD_GET);
				stmt.setInt(1,  boardDO.getSeq());
				rs= stmt.executeQuery(); //select 작업이기에 executeQuery(); 호출
				
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
		
		// 게시물 수정 메소드
		public void updateBoard(BoardDO boardDO) {
			System.out.println(">>> JDBC로 updateBoard() 기능 처리");
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
		
		// 게시물 삭제
		public void deleteBoard(BoardDO boardDO) {
			System.out.println(">>> JDBC로 deleteBoard() 기능 처리");

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
