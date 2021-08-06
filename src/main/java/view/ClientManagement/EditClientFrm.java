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

public class EditClientFrm extends JFrame implements ActionListener{
	private JTextField txtName;
	private JTextField txtPhoneNumber;
	private JTextField txtAddress;
	private User user;
	private Client client;
	private JButton btnSave;
	public EditClientFrm(User u,Client client) {
		this.user = u ;
		this.client = client;
		getContentPane().setLayout(null);
		this.setSize(500,500);
		JLabel lblNewLabel = new JLabel("C\u1EADp nh\u1EADt t\u00EAn");
		lblNewLabel.setBounds(22, 25, 83, 14);
		getContentPane().add(lblNewLabel);
		
		txtName = new JTextField();
		txtName.setBounds(133, 22, 224, 20);
		txtName.setText(this.client.getName());
		getContentPane().add(txtName);
		txtName.setColumns(10);
		
		JLabel lblCpNhtS = new JLabel("S\u1ED1 \u0111i\u1EC7n tho\u1EA1i");
		lblCpNhtS.setBounds(22, 71, 66, 17);
		getContentPane().add(lblCpNhtS);
		
		txtPhoneNumber = new JTextField();
		txtPhoneNumber.setText(this.client.getPhoneNumber());
		txtPhoneNumber.setColumns(10);
		txtPhoneNumber.setBounds(133, 69, 224, 20);
		getContentPane().add(txtPhoneNumber);
		
		JLabel lblaCh = new JLabel("\u0110\u1ECBa ch\u1EC9");
		lblaCh.setBounds(22, 123, 46, 14);
		getContentPane().add(lblaCh);
		
		txtAddress = new JTextField();
		txtAddress.setText(this.client.getAddress());
		txtAddress.setColumns(10);
		txtAddress.setBounds(133, 120, 224, 20);
		getContentPane().add(txtAddress);
		
		 btnSave = new JButton("L\u01B0u");
		btnSave.setBounds(186, 171, 89, 23);
		getContentPane().add(btnSave);
		btnSave.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String updateName = this.txtName.getText();
		String updatePhoneNumber = this.txtPhoneNumber.getText();
		String updateAddress = this.txtAddress.getText();
		if(updateName.isBlank()||updateName.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Vui lòng không bỏ trống thông tin");
			
		}else if(!Pattern.matches("^0[9,8,1]{1}\\d{8,9}$", updatePhoneNumber)) {
			JOptionPane.showMessageDialog(null, "Vui lòng Nhập đúng số điện thoại");		
		}else if(updateAddress.isBlank()||updateAddress.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Vui lòng không bỏ trống địa chỉ");		

		}else{
			this.client.setName(updateName);
			this.client.setAddress(updateAddress);
			this.client.setPhoneNumber(updatePhoneNumber);
			ClientDAO clientDAO = new ClientDAO();
			if(clientDAO.editClient(client)) {
				
				JOptionPane.showMessageDialog(null, "Cập nhật khách hàng thành công");
				
			}
			else {
				JOptionPane.showMessageDialog(null, "Cập nhật khách hàng thất bại");
				
			}
			
			HomeView homeView = new HomeView(user);
			homeView.setVisible(true);
			this.dispose();
			
		};
		
}

}
