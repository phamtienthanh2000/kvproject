package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Client;
import model.DAO;

public class ClientDAO extends DAO{
	public ClientDAO() {
		// TODO Auto-generated constructor stub
	}
	public boolean createClient(Client client) {
		boolean result = false;
		try {
			PreparedStatement ps = this.connection.prepareStatement("Insert into tblClient(`name`,address,phoneNumber) VALUES(?,?,?)",Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, client.getName());
			ps.setString(2, client.getAddress());
			ps.setString(3, client.getPhoneNumber());
			int addSuccess =ps.executeUpdate();
//			ResultSet rs =ps.getGeneratedKeys();
//			if(rs.next()) {
//				System.out.println(rs.getInt(1));
//				
//			}
			return addSuccess > 0 ? true : false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}

	public ArrayList<Client> searchClient(String key){
		ArrayList<Client> result = new ArrayList<>();
		try {
			PreparedStatement ps = connection.prepareStatement("Select * from tblClient Where `name` LIKE ?");
			ps.setString(1, "%"+key+"%");
			ResultSet rs = ps.executeQuery();
			Client client = null;
			while(rs.next()) {
				client = new Client();
				client.setId(rs.getInt("id"));
				client.setName(rs.getString("name"));
				client.setAddress(rs.getString("address"));
				client.setPhoneNumber(rs.getString("phoneNumber"));
				result.add(client);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}

	public boolean editClient(Client client ) {
		boolean result = false;
		
		try {
			PreparedStatement ps = connection.prepareStatement("Update tblClient Set `name` = ? ,address = ? ,phoneNumber = ? Where id = ? ");
			ps.setString(1, client.getName());
			ps.setString(2, client.getAddress());
			ps.setString(3, client.getPhoneNumber());
			ps.setInt(4, client.getId());
			int count = ps.executeUpdate();
			return count > 0 ? true : false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return result;

		}
		
	}

	public ArrayList<Client> getAllClients(){
		ArrayList<Client> result  = new ArrayList<Client>();
 		try {
			PreparedStatement ps= connection.prepareStatement("Select * from tblClient");
			
			ResultSet rs = ps.executeQuery();
			Client client = null;
			while(rs.next()) {
				client  = new Client();
				client.setId(rs.getInt("id"));
				client.setName(rs.getString("name"));
				client.setAddress(rs.getString("address"));
				client.setPhoneNumber(rs.getString("phoneNumber"));
				result.add(client);
				
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return result;
		
	}
	
	
	public boolean deleteClient(int idClient) {
		boolean result = false;
		try {
			PreparedStatement ps = connection.prepareStatement("Delete From tblClient Where id = ?");
			ps.setInt(1, idClient);
			int deletedRecords = ps.executeUpdate();
			return deletedRecords > 0 ? true : false;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return result;
		}
		
		
		
	}
}


