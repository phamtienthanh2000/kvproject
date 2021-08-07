package view.ClientManagement;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import controller.ClientDAO;
import model.Client;
import model.User;
import view.HomeView;

public class CreateClientFrm extends JFrame implements ActionListener{
	private JTextField txtName;
	private JTextField txtPhoneNumber;
	private JTextField txtAddress;
	private User user;
	public CreateClientFrm(User u) {
		getContentPane().setLayout(null);
		this.setSize(500,500);
		JLabel lblNewLabel = new JLabel("T\u00EAn kh\u00E1ch h\u00E0ng");
		lblNewLabel.setBounds(24, 74, 93, 14);
		getContentPane().add(lblNewLabel);
		
		txtName = new JTextField();
		txtName.setBounds(141, 71, 204, 20);
		getContentPane().add(txtName);
		txtName.setColumns(10);
		
		JLabel lblSinThoi = new JLabel("S\u1ED1 \u0111i\u1EC7n tho\u1EA1i");
		lblSinThoi.setBounds(24, 129, 79, 14);
		getContentPane().add(lblSinThoi);
		
		txtPhoneNumber = new JTextField();
		txtPhoneNumber.setColumns(10);
		txtPhoneNumber.setBounds(141, 126, 204, 20);
		getContentPane().add(txtPhoneNumber);
		
		JLabel lblaCh = new JLabel("\u0110\u1ECBa ch\u1EC9");
		lblaCh.setBounds(24, 181, 46, 14);
		getContentPane().add(lblaCh);
		
		txtAddress = new JTextField();
		txtAddress.setColumns(10);
		txtAddress.setBounds(141, 178, 204, 20);
		getContentPane().add(txtAddress);
		
		JButton btnAdd = new JButton("Th\u00EAm");
		btnAdd.setBounds(186, 215, 89, 23);
		getContentPane().add(btnAdd);
		
		btnAdd.addActionListener(this);
	}
	
	
	
	public JTextField getTxtName() {
		return txtName;
	}



	public void setTxtName(JTextField txtName) {
		this.txtName = txtName;
	}



	public JTextField getTxtPhoneNumber() {
		return txtPhoneNumber;
	}



	public void setTxtPhoneNumber(JTextField txtPhoneNumber) {
		this.txtPhoneNumber = txtPhoneNumber;
	}



	public JTextField getTxtAddress() {
		return txtAddress;
	}



	public void setTxtAddress(JTextField txtAddress) {
		this.txtAddress = txtAddress;
	}



	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String clientName = this.txtName.getText();
		String clientPhoneNumber = this.txtPhoneNumber.getText();
		String clientAddress =this.txtAddress.getText();
		if(clientName.isEmpty()||clientName.isBlank()) {
		JOptionPane.showMessageDialog(null, "Vui lòng nhập đúng tên khách hàng");
		}
		else if(!Pattern.matches( "^09\\d{8,9}$",clientPhoneNumber)) {
			JOptionPane.showMessageDialog(null, "Vui lòng nhập đúng số điện thoại");
		}
		else if(clientAddress.isBlank()||clientAddress.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Vui lòng nhập đúng địa chỉ");
			
		}
		else {
			Client client = new Client();
			client.setName(clientName);
			client.setAddress(clientAddress);
			client.setPhoneNumber(clientPhoneNumber);
			
			ClientDAO clientDAO = new ClientDAO();
			if(clientDAO.createClient(client)) {
				JOptionPane.showMessageDialog(null, "Tạo khách hàng thành công");
				HomeView homeView = new HomeView(this.user);
				homeView.setVisible(true);
				this.dispose();
			};
			
		}
						
		
	}

}
