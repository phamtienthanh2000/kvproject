package view.ProductManagement;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controller.ProductDAO;
import model.Product;
import model.User;
import view.HomeView;

public class ProductManagementFrm extends JFrame {
	private User user;
	private JButton btnAddProduct;
	private ProductManagementFrm mainFrame;
	private JTable tblProduct;
	private JTextField txtSearchKey;
	private JButton btnSearch;
	private JButton btnDelete;
	private ArrayList<Product> products ;
	private Product chosenProduct;
	public ProductManagementFrm(User u) {
		mainFrame = this;
		this.user = u;
		getContentPane().setLayout(null);
		this.setSize(611,419);
		btnAddProduct = new JButton("Th\u00EAm s\u1EA3n ph\u1EA9m");
		btnAddProduct.setBounds(79, 327, 116, 23);
		getContentPane().add(btnAddProduct);
		
		JButton btnEdit = new JButton("S\u1EEDa th\u00F4ng tin");
		btnEdit.setBounds(249, 327, 116, 23);
		getContentPane().add(btnEdit);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 52, 564, 264);
		getContentPane().add(scrollPane);
		
		ProductDAO productDAO = new ProductDAO();
		products = productDAO.getAllProduct();
		String[][]data = new String[products.size()][7];
		for(int i = 0 ; i < products.size();i++) {
			Product product = products.get(i);
			data[i][0] = i + 1 +"";
			data[i][1] = product.getProductName();
			data[i][2] = product.getCalculationUnit();
			data[i][3] = product.getAmount()+"";
			data[i][4] = product.getStickerPrice()+"";
			data[i][5] = product.getDescription().getOrigin();
			data[i][6] = product.getDescription().getSupplyCompany();
		}
		tblProduct = new JTable();
		tblProduct.setModel(new DefaultTableModel(
			data,
			new String[] {
				"No.", "Tên sản phẩm", "Đơn vị tính", "Số lượng", "Giá bán", "Nguồn gốc", "Công ty sản xuất"
			}
		));
		scrollPane.setViewportView(tblProduct);
		
		JLabel lblNewLabel = new JLabel("T\u00ECm ki\u1EBFm");
		lblNewLabel.setBounds(10, 26, 66, 14);
		getContentPane().add(lblNewLabel);
		
		txtSearchKey = new JTextField();
		txtSearchKey.setBounds(57, 23, 371, 20);
		getContentPane().add(txtSearchKey);
		txtSearchKey.setColumns(10);
		
		btnSearch = new JButton("Search");
		btnSearch.setBounds(460, 22, 89, 23);
		getContentPane().add(btnSearch);
		
		 btnDelete = new JButton("Xóa sản phẩm");
		btnDelete.setBounds(405, 327, 127, 23);
		getContentPane().add(btnDelete);
		
		btnAddProduct.addActionListener(e->{
			AddProductFrm addProductFrm = new AddProductFrm(user);
			addProductFrm.setVisible(true);
			mainFrame.dispose();
			
			
		});
		
		btnSearch.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String key = txtSearchKey.getText();
				
				if(key.isEmpty()) {
					return;			
				}
				
				ProductDAO	 productDAO = new ProductDAO();
				products = productDAO.searchByProductName(key);
				String[][]data = new String[products.size()][7];
				for(int i = 0 ; i < products.size();i++) {
					Product product = products.get(i);
					data[i][0] = i + 1 +"";
					data[i][1] = product.getProductName();
					data[i][2] = product.getCalculationUnit();
					data[i][3] = product.getAmount()+"";
					data[i][4] = product.getStickerPrice()+"";
					data[i][5] = product.getDescription().getOrigin();
					data[i][6] = product.getDescription().getSupplyCompany();
				}
				DefaultTableModel defaultTableModel = new DefaultTableModel(data, new String[] {"No.","Tên sản phẩm","đơn vị tính","số lượng","giá niêm yết","nguồn gốc","công ty cung cấp"});
				tblProduct.setModel(defaultTableModel);
			}
		});
		
		tblProduct.addMouseListener(new MouseAdapter() {
		
		@Override
		public void mouseClicked(MouseEvent e) {
			int column = tblProduct.getColumnModel().getColumnIndexAtX(e.getX());
			int row = e.getY() / tblProduct.getRowHeight();
			if (row < tblProduct.getRowCount() && row >= 0 && column < tblProduct.getColumnCount() && column >= 0) {
				chosenProduct = products.get(row);
		}
		}
		});
		
		btnEdit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				EditProductFrm editProductFrm = new EditProductFrm(u, chosenProduct) ;
				editProductFrm.setVisible(true);
				mainFrame.dispose();
			}
		});
		
		btnDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(chosenProduct == null) {
					JOptionPane.showMessageDialog(null, "Xin hãy chọn một dòng trong bảng ");
					return;

				}
				
			int confirm= 	JOptionPane.showConfirmDialog(null,"Xác nhận xóa sản phẩm ?","Xóa sản phẩm",JOptionPane.YES_NO_OPTION);
			if(confirm == JOptionPane.YES_OPTION) {
				ProductDAO productDAO = new ProductDAO();
				if(productDAO.deleteProduct(chosenProduct.getId())) {
					JOptionPane.showMessageDialog(null, "Xóa sản phẩm : "+chosenProduct.getProductName()+" thành công");
					
				}
				else {
					
					JOptionPane.showMessageDialog(null, "Xóa sản phẩm : "+chosenProduct.getProductName()+" thất bại");

				}
			}
			
			HomeView homeView = new HomeView(user);
			homeView.setVisible(true);
			mainFrame.dispose();
			}
		});
		
	}
}
