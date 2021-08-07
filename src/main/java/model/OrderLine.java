package model;

import java.io.Serializable;

public class OrderLine implements Serializable{
	private int id;
	private int amount;
	private int sellPrice;
	private int total;
	private Product product;
	public OrderLine() {
		// TODO Auto-generated constructor stub
	}
	public OrderLine(int id, int amount, int sellPrice, int total, Product product) {
		super();
		this.id = id;
		this.amount = amount;
		this.sellPrice = sellPrice;
		this.total = total;
		this.product = product;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getSellPrice() {
		return sellPrice;
	}
	public void setSellPrice(int sellPrice) {
		this.sellPrice = sellPrice;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	@Override
	public String toString() {
		return "OrderLine [id=" + id + ", amount=" + amount + ", sellPrice=" + sellPrice + ", total=" + total
				+ ", product=" + product + "]";
	}
	
}
