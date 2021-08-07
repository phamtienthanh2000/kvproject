package model;

import java.io.Serializable;

public class Product implements Serializable{
private int id;
private String productName;
private String calculationUnit;
private int amount;
private int stickerPrice;
private Description description;
	public Product() {
		// TODO Auto-generated constructor stub
	}
	public Product(int id, String productName, String calculationUnit, int amount, int stickerPrice,
			Description description) {
		super();
		this.id = id;
		this.productName = productName;
		this.calculationUnit = calculationUnit;
		this.amount = amount;
		this.stickerPrice = stickerPrice;
		this.description = description;
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getCalculationUnit() {
		return calculationUnit;
	}
	public void setCalculationUnit(String calculationUnit) {
		this.calculationUnit = calculationUnit;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getStickerPrice() {
		return stickerPrice;
	}
	public void setStickerPrice(int stickerPrice) {
		this.stickerPrice = stickerPrice;
	}
	public Description getDescription() {
		return description;
	}
	public void setDescription(Description description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "Product [id=" + id + ", productName=" + productName + ", calculationUnit=" + calculationUnit
				+ ", amount=" + amount + ", stickerPrice=" + stickerPrice + ", description=" + description + "]";
	}
	
}
