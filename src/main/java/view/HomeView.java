package view;

import javax.swing.JFrame;

import model.User;
import view.ClientManagement.ClientManagementFrm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class HomeView extends JFrame implements ActionListener{
	private User user;
	JButton btnManageClient;
	private HomeView mainFrame;
	public HomeView(User u) {
		this.mainFrame = this;
		this.user = u;
		getContentPane().setLayout(null);
		
		btnManageClient = new JButton("Qu\u1EA3n l\u00FD kh\u00E1ch h\u00E0ng");
		btnManageClient.setBounds(122, 45, 149, 23);
		getContentPane().add(btnManageClient);
		this.setSize(500,500);
		
		btnManageClient.addActionListener(e ->{
			ClientManagementFrm clientManagementFrm = new ClientManagementFrm(user);
			clientManagementFrm.setVisible(true);
			mainFrame.dispose();
			
			
		});
		
	}
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
