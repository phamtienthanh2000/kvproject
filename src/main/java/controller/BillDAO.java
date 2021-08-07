package controller;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import model.Bill;
import model.Client;
import model.DAO;
import model.OrderLine;
public class BillDAO extends DAO{
	private Bill bill;
	public boolean createBill(Bill b) {
		this.bill = b;
		PreparedStatement ps ;
		int rowCount;
		try {
			// check client exist in db ?
			
		Client client = bill.getClient();
		if(client.getId()==0) {
			ps = connection.prepareStatement("Insert into tblClient (`name`,address,phoneNumber)VALUES(?,?,?)",Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, client.getName());
			ps.setString(2, client.getAddress());
			ps.setString(3, client.getPhoneNumber());
			rowCount = ps.executeUpdate();
			if(rowCount > 0) {
				ResultSet rs = ps.getGeneratedKeys();
				if(rs.next()) {
					client.setId(rs.getInt(1));
					
					
				}
				
			}
			else {
				return false;
				
			}
		}
			
		ps = connection.prepareStatement("Insert into tblBill(shipmentFee,deposit,discount,oweAmount,writeDate,idClient,idUser)VALUES(?,?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
		ps.setInt(1, bill.getShipmentFee());
		ps.setInt(2, bill.getDeposit());
		ps.setInt(3, bill.getDiscount());
		ps.setInt(4, bill.getOweAmount());
		ps.setDate(5,java.sql.Date.valueOf(bill.getWriteDate()));
		ps.setInt(6,bill.getClient().getId() );
		ps.setInt(7,bill.getUser().getId());
		rowCount = ps.executeUpdate();
		// neu tao hoa don thanh cong roi
		if(rowCount>0) {
			ResultSet rs = ps.getGeneratedKeys();
			if(rs.next()) {
				bill.setId(rs.getInt(1));	
				String sql = "Insert Into tblOrderline(amount,sellPrice,idBill,idProduct) VALUES";
				int count = 0;
				for(OrderLine orderLine : bill.getOrderLines()) {
					if(count == bill.getOrderLines().size()-1) {
						sql+="("+orderLine.getAmount()+","+orderLine.getSellPrice()+","+bill.getId()+","+orderLine.getProduct().getId() +")";
						break;
						
					}
					sql+="("+orderLine.getAmount()+","+orderLine.getSellPrice()+","+bill.getId()+","+orderLine.getProduct().getId() +"),";
					
					count++;
				}
				
				ps =connection.prepareStatement(sql);
				rowCount = ps.executeUpdate();
				// cac orderLine da dc cap nhat
				if(rowCount>0) {
					String SQL = "Update tblProduct Set amount = ? Where id = ?";
					for(OrderLine orderLine : bill.getOrderLines()) {
						int boughtAmount = orderLine.getAmount();
						ps = connection.prepareStatement(SQL);
						ps.setInt(1, orderLine.getProduct().getAmount()-boughtAmount);
						ps.setInt(2, orderLine.getProduct().getId());
						rowCount = ps.executeUpdate();
						return rowCount > 0;
					}
					
				}else {
					return false;
				}
			}
			
		}
		else {
			return false;
		}
		
		
		}
		catch (Exception e) {
			e.printStackTrace();
		return false;
		}
		
		return false;
		
	}
}
