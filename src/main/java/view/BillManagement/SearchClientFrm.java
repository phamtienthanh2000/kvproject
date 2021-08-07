package view.BillManagement;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controller.ClientDAO;
import model.Bill;
import model.Client;
import view.ClientManagement.EditClientFrm;

public class SearchClientFrm extends JFrame{
	private JTextField txtSearchKey;
	private JTable tblClient;
	private JButton btnSearch ;
	private JButton btnAddClient;
	private Bill bill;
	private ArrayList<Client> clients;
	private SearchClientFrm mainFrm ;
	public SearchClientFrm(Bill b) {
		this.mainFrm = this;
		this.bill = b;
		getContentPane().setLayout(null);
		this.setSize(600,400);
		JLabel lblNewLabel = new JLabel("Nh\u1EADp t\u00EAn kh\u00E1ch h\u00E0ng");
		lblNewLabel.setBounds(21, 11, 111, 14);
		getContentPane().add(lblNewLabel);
		
		txtSearchKey = new JTextField();
		txtSearchKey.setBounds(133, 8, 297, 20);
		getContentPane().add(txtSearchKey);
		txtSearchKey.setColumns(10);
		
		btnSearch = new JButton("Search");
		btnSearch.setBounds(452, 7, 89, 23);
		getContentPane().add(btnSearch);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(31, 36, 510, 187);
		getContentPane().add(scrollPane);
		
		tblClient = new JTable();
		tblClient.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"No.", "Tên khách hàng", "Số điện thoại", "Địa chỉ"
			}
		));
		scrollPane.setViewportView(tblClient);
		
		btnAddClient = new JButton("Tạo mới khách hàng");
		btnAddClient.setBounds(216, 234, 137, 23);
		getContentPane().add(btnAddClient);
		btnSearch.addActionListener(e->{
			String key = txtSearchKey.getText();
			if(key.isBlank()||key.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Vui lòng nhập từ khóa !");
				return;
			}
			ClientDAO clientDAO = new ClientDAO();
			clients = clientDAO.searchClient(key);
			String[][]data= new String[clients.size()][4];
			for(int i = 0 ; i < clients.size();i++) {
				Client client =  clients.get(i);
				data[i][0] = i+1+"";
				data[i][1] = client.getName();
				data[i][2] = client.getPhoneNumber();
				data[i][3] = client.getAddress();
				
			}
			
			DefaultTableModel defaultTableModel = new DefaultTableModel(data,new String[] {"No.","Tên khách hàng","số điện thoại","Địa chỉ"});
			tblClient.setModel(defaultTableModel);
			
		});
		
		tblClient.addMouseListener(new MouseAdapter() {
		
			@Override
			public void mouseClicked(MouseEvent e) {
				int column = tblClient.getColumnModel().getColumnIndexAtX(e.getX());
				int row = e.getY() / tblClient.getRowHeight();
				if (row < tblClient.getRowCount() && row >= 0 && column < tblClient.getColumnCount() && column >= 0) {
					Client client = clients.get(row);
					bill.setClient(client);
					ConfirmFrm confirmFrm = new ConfirmFrm(bill);
					confirmFrm.setVisible(true);
					mainFrm.dispose();
				}
				
			}
		
		});
		btnAddClient.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				CreateClientFrm createClientFrm = new CreateClientFrm(bill);
				createClientFrm.setVisible(true);
				mainFrm.dispose();
			}
		});
		
	}

}
