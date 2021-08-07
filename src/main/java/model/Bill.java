package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Bill implements Serializable {
	private int id ;
	private int shipmentFee;
	private int deposit;
	private int totalAmount;
	private int discount;
	private int oweAmount;
	private LocalDate writeDate;
	private String status;
	private Client client;
	private ArrayList<OrderLine> orderLines;
	private User user;
	public Bill() {
		// TODO Auto-generated constructor stub
	}
	public Bill(int id, int shipmentFee, int deposit, int totalAmount, int discount, int oweAmount, LocalDate writeDate,
			String status, Client client,  ArrayList<OrderLine> orderLines, User user) {
		super();
		this.id = id;
		this.shipmentFee = shipmentFee;
		this.deposit = deposit;
		this.totalAmount = totalAmount;
		this.discount = discount;
		this.oweAmount = oweAmount;
		this.writeDate = writeDate;
		this.status = status;
		this.client = client;
		this.orderLines = orderLines;
		this.user = user;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getShipmentFee() {
		return shipmentFee;
	}
	public void setShipmentFee(int shipmentFee) {
		this.shipmentFee = shipmentFee;
	}
	public int getDeposit() {
		return deposit;
	}
	public void setDeposit(int deposit) {
		this.deposit = deposit;
	}
	public int getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}
	public int getDiscount() {
		return discount;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	public int getOweAmount() {
		return oweAmount;
	}
	public void setOweAmount(int oweAmount) {
		this.oweAmount = oweAmount;
	}
	public LocalDate getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(LocalDate writeDate) {
		this.writeDate = writeDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public  ArrayList<OrderLine> getOrderLines() {
		return orderLines;
	}
	public void setOrderLines( ArrayList<OrderLine> orderLines) {
		this.orderLines = orderLines;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public int calculateTotalAmount() {
		int sum = 0;
		for(OrderLine orderLine : this.orderLines) {
			sum+=orderLine.getTotal();
		}
		sum+=this.getShipmentFee();
		sum-=this.getDiscount();
		
		return sum;
	}
	
	
}
