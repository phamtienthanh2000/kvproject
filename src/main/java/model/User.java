package model;

import java.io.Serializable;

public class User implements Serializable{
	private int id;
	private String userName;
	private String password;
	private String fullName;
	public User() {
		// TODO Auto-generated constructor stub
	}
	
	public User(int id, String userName, String password, String fullName) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.fullName = fullName;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	
}
