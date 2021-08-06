package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import controller.UserDAO;
import model.User;

public class LoginView extends JFrame implements ActionListener{
	private JTextField txtUserName;
	private JTextField txtPassword;
	JButton btnLogin;
	public LoginView() {
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("T\u00EAn \u0111\u0103ng nh\u1EADp");
		lblNewLabel.setBounds(59, 39, 89, 14);
		getContentPane().add(lblNewLabel);
		
		txtUserName = new JTextField();
		txtUserName.setBounds(59, 64, 195, 20);
		getContentPane().add(txtUserName);
		txtUserName.setColumns(10);
		
		JLabel lblMtKhu = new JLabel("M\u1EADt kh\u1EA9u");
		lblMtKhu.setBounds(59, 114, 46, 14);
		getContentPane().add(lblMtKhu);
		
		txtPassword = new JTextField();
		txtPassword.setColumns(10);
		txtPassword.setBounds(59, 139, 195, 20);
		getContentPane().add(txtPassword);
		this.setSize(500,500);
		
		btnLogin = new JButton("\u0110\u0103ng nh\u1EADp");
		btnLogin.setBounds(59, 198, 89, 23);
		getContentPane().add(btnLogin);
		btnLogin.addActionListener(this);
		
	}
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String userName = this.txtUserName.getText();
		String password = this.txtPassword.getText();
		User user = new User();
		user.setUserName(userName);
		user.setPassword(password);
		UserDAO userDAO = new UserDAO();
		if(userDAO.checkLogin(user)) {
			HomeView homeView = new HomeView(user);
			homeView.setVisible(true);
			this.dispose();
			
		}
		else {
			JOptionPane.showMessageDialog(null, "Nhập Sai thông tin !");	
		}
		
		
	}
	public static void main(String[] args) {
		LoginView loginView = new LoginView();
		loginView.setVisible(true);
	}
}
