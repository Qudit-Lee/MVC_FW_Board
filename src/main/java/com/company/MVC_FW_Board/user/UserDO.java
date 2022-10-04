package com.company.MVC_FW_Board.user;

public class UserDO {
	//멤버필드(프로퍼티, 중간 저장소)
	private String id;		//아이디
	private String password;//암호
	private String name;	//이름
	private String role;	//역할 = 관리자 / 일반 유저
	
	//멤버 필드 하나당 getter, setter 메소드
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	
}
