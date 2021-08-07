package model;

import java.io.Serializable;

public class Description implements Serializable{
private int id;
private String origin;
private String supplyCompany ;

public Description() {
	// TODO Auto-generated constructor stub
}

public Description(int id, String origin, String supplyCompany) {
	super();
	this.id = id;
	this.origin = origin;
	this.supplyCompany = supplyCompany;
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getOrigin() {
	return origin;
}

public void setOrigin(String origin) {
	this.origin = origin;
}

public String getSupplyCompany() {
	return supplyCompany;
}

public void setSupplyCompany(String supplyCompany) {
	this.supplyCompany = supplyCompany;
}

}
