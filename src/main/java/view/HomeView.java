package view;

import javax.swing.JFrame;

import model.User;
import view.BillManagement.BillManagementFrm;
import view.ClientManagement.ClientManagementFrm;
import view.ProductManagement.ProductManagementFrm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class HomeView extends JFrame implements ActionListener{
	private User user;
	private JButton btnManageClient;
	private HomeView mainFrame;
	private JButton btnProductManagement ;
	private JButton btnBillManagement;
	public HomeView(User u) {
		this.mainFrame = this;
		this.user = u;
		getContentPane().setLayout(null);
		
		btnManageClient = new JButton("Qu\u1EA3n l\u00FD kh\u00E1ch h\u00E0ng");
		btnManageClient.setBounds(122, 66, 165, 23);
		getContentPane().add(btnManageClient);
		
		btnProductManagement = new JButton("Qu\u1EA3n l\u00FD s\u1EA3n ph\u1EA9m");
		btnProductManagement.setBounds(122, 121, 165, 23);
		getContentPane().add(btnProductManagement);
		
		btnBillManagement = new JButton("Qu\u1EA3n l\u00FD h\u00F3a \u0111\u01A1n");
		btnBillManagement.setBounds(122, 177, 165, 23);
		getContentPane().add(btnBillManagement);
		this.setSize(500,500);
		
		btnManageClient.addActionListener(e ->{
			ClientManagementFrm clientManagementFrm = new ClientManagementFrm(user);
			clientManagementFrm.setVisible(true);
			mainFrame.dispose();
			
			
		});
		
		btnProductManagement.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ProductManagementFrm productManagementFrm = new ProductManagementFrm(user);
				productManagementFrm.setVisible(true);
				mainFrame.dispose();
			}
		});
		btnBillManagement.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				BillManagementFrm billManagementFrm = new BillManagementFrm(user);
				billManagementFrm.setVisible(true);
				mainFrame.dispose();
			}
		});
	}
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
