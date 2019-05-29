package com.saeyan.javabean;

public class MemberBeans {
	private String name;
	private String userid;

	public MemberBeans() {
	}
	
	public MemberBeans(String name, String userid) {
		this.name = name;
		this.userid = userid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	@Override
	public String toString() {
		return "MemberBeans [name=" + name + ", userid=" + userid + "]";
	}
	
}
