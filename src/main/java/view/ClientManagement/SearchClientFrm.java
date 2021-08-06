package view.ClientManagement;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controller.ClientDAO;
import model.Client;
import model.User;

public class SearchClientFrm extends JFrame implements ActionListener{
	private JTextField txtSearchKey;
	private JTable tblClient;
	private User user;
	private JButton btnSearch ;
	private ArrayList<Client> clients;
	SearchClientFrm mainFrm ;
	public SearchClientFrm(User u) {
		mainFrm = this;
		this.user = u;
		this.setSize(531,500);
		getContentPane().setLayout(null);
	
		JLabel lblNewLabel = new JLabel("T\u00EAn kh\u00E1ch h\u00E0ng");
		lblNewLabel.setBounds(10, 27, 89, 14);
		getContentPane().add(lblNewLabel);
		
		txtSearchKey = new JTextField();
		txtSearchKey.setBounds(153, 24, 210, 20);
		getContentPane().add(txtSearchKey);
		txtSearchKey.setColumns(10);
		
		btnSearch = new JButton("Search");
		btnSearch.setBounds(394, 23, 89, 23);
		getContentPane().add(btnSearch);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 66, 485, 235);
		getContentPane().add(scrollPane);
		
		tblClient = new JTable();
		tblClient.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"No.", "Tên Khách hàng", "Số điện thoại", "Địa chỉ"
			}
		));
		scrollPane.setViewportView(tblClient);
		btnSearch.addActionListener(this);
		
		tblClient.addMouseListener(new MouseAdapter() {
		
		@Override
		public void mouseClicked(MouseEvent e) {
			int column = tblClient.getColumnModel().getColumnIndexAtX(e.getX());
			int row = e.getY() / tblClient.getRowHeight();
			if (row < tblClient.getRowCount() && row >= 0 && column < tblClient.getColumnCount() && column >= 0) {
				Client client = clients.get(row);
				EditClientFrm editClientFrm = new EditClientFrm(user , client);
				editClientFrm.setVisible(true);
				mainFrm.dispose();
				

			}

		}
		});
	}
	@Override
	public void actionPerformed(ActionEvent e) {
			String key = this.txtSearchKey.getText();
			if((!key.isBlank())&&!key.isEmpty()) {
				ClientDAO clientDAO = new ClientDAO();
				clients= clientDAO.searchClient(key);
				
				String[][]data = new String[clients.size()][4];
				for(int i = 0 ; i < clients.size();i++) {
					Client client = clients.get(i);
					data[i][0] = i+1+"";
					data[i][1] = client.getName();
					data[i][2] = client.getPhoneNumber();
					data[i][3] = client.getAddress();
					
				}
				
				DefaultTableModel defaultTableModel = new DefaultTableModel(data,new String[] {"No.","tên khách hàng","số điện thoại","địa chỉ"});
				this.tblClient.setModel(defaultTableModel);
			
			}
	}
}
