package view.BillManagement;

import javax.swing.JFrame;

import model.User;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class BillManagementFrm extends JFrame{
	private User user;
	private JButton btnAdd;
	private BillManagementFrm mainFrame ;
	public BillManagementFrm(User u) {
		mainFrame = this;
		this.user = u;
		getContentPane().setLayout(null);
		this.setSize(500,500);
		btnAdd = new JButton("Vi\u1EBFt h\u00F3a \u0111\u01A1n");
		btnAdd.setBounds(52, 220, 122, 23);
		getContentPane().add(btnAdd);
		
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				CreateBillFrm billFrm = new CreateBillFrm(user);
				billFrm.setVisible(true);
				mainFrame.dispose();
				
			}
		});
	}
}
