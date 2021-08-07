package view.BillManagement;
import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import model.Bill;
import model.Client;
public class CreateClientFrm extends view.ClientManagement.CreateClientFrm{
	private Bill bill;
	
	public CreateClientFrm(Bill b) {
		
		super(b.getUser());
		this.bill = b;
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String clientName = getTxtName().getText();
		String clientPhoneNumber = getTxtPhoneNumber().getText();
		String clientAddress = getTxtAddress().getText();
		
		if(clientName.isBlank()||clientName.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Vui lòng nhập tên khách hàng");
			return;
		}else if(clientPhoneNumber.isBlank()||clientPhoneNumber.isEmpty()) {
			
			JOptionPane.showMessageDialog(null, "Vui lòng nhập số điện thoại khách hàng");
			return;
		}
		else if(clientAddress.isBlank()||clientAddress.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Vui lòng nhập địa chỉ khách hàng");
			return;
		}
		Client client = new Client();
		client.setName(clientName);
		client.setAddress(clientAddress);
		client.setPhoneNumber(clientPhoneNumber);
		bill.setClient(client);
		ConfirmFrm confirmFrm = new ConfirmFrm(bill);
		confirmFrm.setVisible(true);
		this.dispose();
		
		
	}

}
