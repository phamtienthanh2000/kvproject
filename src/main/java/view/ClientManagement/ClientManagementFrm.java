package view.ClientManagement;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.ClientDAO;
import model.Client;
import model.User;
import view.HomeView;

public class ClientManagementFrm extends JFrame implements ActionListener{
	private User user ;
	private JButton btnAdd ;
	private JButton btnSearch;
	private ClientManagementFrm mainFrame ;
	private JTable tblClient;
	private JButton btnDelete;
	ArrayList<Client> clients;
	private Client chosenClient ;
	public ClientManagementFrm(User u) {
		mainFrame = this;
		this.user = u;
		getContentPane().setLayout(null);
		this.setSize(554,500);
	
		
		btnAdd = new JButton("Th\u00EAm Kh\u00E1ch h\u00E0ng");
		btnAdd.setBounds(33, 280, 146, 23);
		getContentPane().add(btnAdd);
		
		btnSearch = new JButton("T\u00ECm kh\u00E1ch h\u00E0ng");
		btnSearch.setBounds(211, 280, 146, 23);
		getContentPane().add(btnSearch);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 36, 518, 208);
		getContentPane().add(scrollPane);
		
		ClientDAO clientDAO  = new ClientDAO ();
		clients = clientDAO.getAllClients();
		String[][] data = new String[clients.size()][4];
		for(int i = 0 ; i < clients.size();i++) {
			Client client = clients.get(i);
			data[i][0] = i+1+"";
			data[i][1] = client.getName();
			data[i][2] = client.getAddress();
			data[i][3] = client.getPhoneNumber();
			
		}
		tblClient = new JTable();
		tblClient.setModel(new DefaultTableModel(
			data,
			new String[] {
				"No.", "Tên khách hàng", "Số điện thoại", "Địa chỉ"
			}
		));
		scrollPane.setViewportView(tblClient);
		
		btnDelete = new JButton("Xóa Khách hàng");
		btnDelete.setBounds(370, 280, 109, 23);
		getContentPane().add(btnDelete);

		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			CreateClientFrm createClientFrm = new  CreateClientFrm(user);
			createClientFrm.setVisible(true);
				mainFrame.dispose();
			}
		});
		btnSearch.addActionListener(e -> {
			SearchClientFrm searchClientFrm = new SearchClientFrm(user);
			searchClientFrm.setVisible(true);
			mainFrame.dispose();
			
		});
		tblClient.addMouseListener(new MouseAdapter() {
		
		@Override
		public void mouseClicked(MouseEvent e) {
			int column = tblClient.getColumnModel().getColumnIndexAtX(e.getX());
			int row = e.getY() / tblClient.getRowHeight();
			if (row < tblClient.getRowCount() && row >= 0 && column < tblClient.getColumnCount() && column >= 0) {
				chosenClient = clients.get(row);
			}
		}
		
		});
		
		btnDelete.addActionListener(this);
		
	}
	public void actionPerformed(ActionEvent e) {
		ClientDAO clientDAO = new ClientDAO();
		if(chosenClient == null) {
			JOptionPane.showMessageDialog(null, "Xin hãy chọn một dòng trong bảng khách hàng");
			return;
		}
		
		if(clientDAO.deleteClient(chosenClient.getId())) {
			JOptionPane.showMessageDialog(null, "Xóa khách hàng thành công !");
			
		}
		else {
			JOptionPane.showMessageDialog(null, "Xóa khách hàng thất bại");
		}
		
		HomeView homeView = new HomeView(user);
		homeView.setVisible(true);
		mainFrame.dispose();
		
	}

}
