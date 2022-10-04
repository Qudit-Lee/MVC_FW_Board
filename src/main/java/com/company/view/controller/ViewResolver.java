package com.company.view.controller;

public class ViewResolver {
	//��� �ʵ� ����
	public String prefix;		//���λ� => "./"�ʱ�ȭ
	public String suffix;		//���̻�=> ".jsp"�ʱ�ȭ
	
	//�� ��� �ʵ��� setter �޼ҵ常 ����
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	
	//�����ڰ� �ʿ��� �޼ҵ� ����
	public String getView(String viewName) {
		return prefix + viewName + suffix;	
	}
}
