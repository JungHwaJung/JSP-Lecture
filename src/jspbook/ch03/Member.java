package jspbook.ch03;

import java.util.Objects;

public class Member {
	private int id;
	private String name;
	private String email;
	private String tel;
	
	public int getId() {
		if (tel!=null) {
			id = Objects.hash(name, email, tel);
			System.out.println(toString());
		}
		return id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	@Override
	public String toString() {
		return "Member [id=" + id + ", name=" + name + ", email=" + email + ", tel=" + tel + "]";
	}
}
