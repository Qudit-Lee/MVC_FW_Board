package com.company.MVC_FW_Board.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.company.MVC_FW_Board.common.JDBCUtil;

public class UserDAO {
	//H2 DB관련 변수 선언
	private Connection 		  conn = null;
	private PreparedStatement stmt = null;
	private ResultSet		  rs = null;
	
	//로그인 인증 처리 SQL 문장
	private final String USER_GET =
			"select id, password from users where id=? and password =?"; 

	public UserDO getUser(UserDO userDO) {
		UserDO user = null;
		
		//자바의 예외처리!!
		try {
			System.out.println("===> JDBC로 getUser() 기능 처리");
			
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(USER_GET);
			stmt.setString(1, userDO.getId());
			stmt.setString(2, userDO.getPassword());
			rs = stmt.executeQuery();
			
			if(rs.next()) {//인증 성공
				user = new UserDO();
				user.setId(rs.getString("ID"));
				user.setPassword(rs.getString("PASSWORD"));
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		return user;
	}
}
