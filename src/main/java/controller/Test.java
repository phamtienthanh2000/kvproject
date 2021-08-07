package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.DAO;
public class Test extends DAO{
	public void getString() {
		try {
			PreparedStatement ps = connection.prepareStatement("Select * from tblTest where id =3 ");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				System.out.println("getfrmdb: " +rs.getString("txtColumn"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
public static void main(String[] args) {
	Test test = new Test();
	test.getString();

}
}
