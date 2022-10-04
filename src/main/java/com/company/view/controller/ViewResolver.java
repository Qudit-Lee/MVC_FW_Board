package com.company.view.controller;

public class ViewResolver {
	//멤버 필드 선언
	public String prefix;		//접두사 => "./"초기화
	public String suffix;		//접미사=> ".jsp"초기화
	
	//각 멤버 필드의 setter 메소드만 구현
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	
	//개발자가 필요한 메소드 구현
	public String getView(String viewName) {
		return prefix + viewName + suffix;	
	}
}
