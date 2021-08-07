package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.DAO;
import model.Description;
import model.Product;

public class ProductDAO extends DAO{
public ProductDAO() {
	// TODO Auto-generated constructor stub
}
public boolean createProduct(Product product) {
	try {
		PreparedStatement ps = connection.prepareStatement("Insert into tblProduct (productName,calculationUnit,amount,stickerPrice) VALUES(?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, product.getProductName());
		ps.setString(2, product.getCalculationUnit());
		ps.setInt(3, product.getAmount());
		ps.setInt(4,product.getStickerPrice());
		int addRecord = ps.executeUpdate();
		if(addRecord >0) {
		ResultSet rs = ps.getGeneratedKeys();
		if(rs.next()) {
			product.setId(rs.getInt(1));	
			ps = connection.prepareStatement("Insert into tblDescription(origin,supplyCompany,idProduct)VALUES(?,?,?)");
			ps.setString(1, product.getDescription().getOrigin());
			ps.setString(2, product.getDescription().getSupplyCompany());
			ps.setInt(3, product.getId());
			int addedDescription = ps.executeUpdate();
			return addedDescription > 0 ;
		}
	
		}
	
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return false;
	}
	return false;
	
}

public ArrayList<Product> getAllProduct(){
	ArrayList<Product> result = new ArrayList<Product>();
	try {
		PreparedStatement ps= connection.prepareStatement("select p.id as productID,p.productName,p.calculationUnit,p.amount,p.stickerPrice,d.id as descriptionID,d.origin,d.supplyCompany From tblProduct as p LEFT JOIN tblDescription as d ON p.id = d.idProduct\r\n");
		ResultSet rs = ps.executeQuery();
		Product product = null;
		Description description = null;
		while(rs.next()) {
			product = new Product();
			product.setId(rs.getInt("productID"));
			product.setProductName(rs.getString("productName"));
			product.setCalculationUnit(rs.getString("calculationUnit"));
			product.setAmount(rs.getInt("amount"));
			product.setStickerPrice(rs.getInt("stickerPrice"));
			description = new Description();
			description.setId(rs.getInt("descriptionID"));
			description.setOrigin(rs.getString("origin"));
			description.setSupplyCompany(rs.getString("supplyCompany"));
			product.setDescription(description);
			
			result.add(product);
		}
	
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return result;
}

public ArrayList<Product> searchByProductName(String key){
	ArrayList<Product> result = new ArrayList<>();
	PreparedStatement ps;
	try {
		ps = connection.prepareStatement("select p.id as productID,p.productName,p.calculationUnit,p.amount,p.stickerPrice,d.id as descriptionID,d.origin,d.supplyCompany From tblProduct as p LEFT JOIN tblDescription as d ON p.id = d.idProduct Where p.productName LIKE ? \r\n");
		ps.setString(1, "%"+key+"%");
		ResultSet rs = ps.executeQuery();
		
		Product product = null;
		Description description = null;
		while(rs.next()) {
			product = new Product();
			product.setId(rs.getInt("productID"));
			product.setProductName(rs.getString("productName"));
			product.setCalculationUnit(rs.getString("calculationUnit"));
			product.setStickerPrice(rs.getInt("stickerPrice"));
			product.setAmount(rs.getInt("amount"));
			description = new Description();
			description.setId(rs.getInt("descriptionID"));
			description.setOrigin(rs.getString("origin"));
			description.setSupplyCompany(rs.getString("supplyCompany"));
			product.setDescription(description);
			result.add(product);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
	}
		return result;
}

public boolean updateProduct(Product product) {
	try {
		PreparedStatement ps = connection.prepareStatement("Update tblProduct Set productName = ?,calculationUnit= ?,amount = ?,stickerPrice = ? Where id = ?" );
		ps.setString(1, product.getProductName());
		ps.setString(2, product.getCalculationUnit());
		ps.setInt(3, product.getAmount());
		ps.setInt(4, product.getStickerPrice());
		ps.setInt(5, product.getId());
		int rowCount = ps.executeUpdate();
		if(rowCount>0) {
			ps = connection.prepareStatement("Update tblDescription set origin = ?,supplyCompany = ? Where id = ?");
			ps.setString(1, product.getDescription().getOrigin());
			ps.setString(2, product.getDescription().getSupplyCompany());
			ps.setInt(3, product.getDescription().getId());
			rowCount = ps.executeUpdate();
			return rowCount > 0;
		}
		else {
			return false;
			
		} 
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return false;
	}
	


}

public boolean deleteProduct(int idProduct) {
	PreparedStatement ps;
	try {
		ps = connection.prepareStatement("Delete from tblProduct where id = ?");
		ps.setInt(1, idProduct);
		int rowCount = ps.executeUpdate();
		return rowCount > 0 ;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return false;
	}
	
	
	
}
}
