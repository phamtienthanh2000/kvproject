package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.DAO;
import model.User;

public class UserDAO extends DAO {
	public UserDAO() {
		// TODO Auto-generated constructor stub
	}
	public boolean checkLogin(User user) {
		try {
			PreparedStatement ps = this.connection.prepareStatement("Select * from tblUser Where userName = ? And password = ?;");
			ps.setString(1, user.getUserName());
			ps.setString(2, user.getPassword());
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				user.setFullName(rs.getString("fullName"));
				return true;
			}
			return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
}
