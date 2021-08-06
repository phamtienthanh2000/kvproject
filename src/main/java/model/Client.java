package model;

import java.io.Serializable;

public class Client implements Serializable{
 private int id;
 private String name;
 private String phoneNumber;
 private String address;
 public Client() {
	// TODO Auto-generated constructor stub
}
public Client(int id, String name, String phoneNumber, String address) {
	super();
	this.id = id;
	this.name = name;
	this.phoneNumber = phoneNumber;
	this.address = address;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getPhoneNumber() {
	return phoneNumber;
}
public void setPhoneNumber(String phoneNumber) {
	this.phoneNumber = phoneNumber;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
 
}
