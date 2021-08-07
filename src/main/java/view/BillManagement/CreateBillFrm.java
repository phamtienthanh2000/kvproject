package view.BillManagement;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controller.ProductDAO;
import model.Bill;
import model.OrderLine;
import model.Product;
import model.User;

public class CreateBillFrm extends JFrame implements ActionListener{
	private JTextField txtSellPrice;
	private JTextField txtAmount;
	private JTable tblOrderlines;
	private JTextField txtShipmentFee;
	private JTextField txtDeposit;
	private JTextField txtDiscount;
	private JButton btnAddOrderline ;
	private JButton btnSearchClient ;
	private JComboBox cbxProductName;
	private User user;
	private ArrayList<Product> productList;
	private ArrayList<Product> chosenProducts;
	public CreateBillFrm(User u) {
		this.user = u;
		chosenProducts = new ArrayList<>();
		getContentPane().setLayout(null);
		this.setSize(750,500);
		JLabel lblNewLabel = new JLabel("Ch\u1ECDn s\u1EA3n ph\u1EA9m");
		lblNewLabel.setBounds(10, 28, 87, 14);
		getContentPane().add(lblNewLabel);
		
		// get data
		ProductDAO productDAO = new ProductDAO();
		this.productList = productDAO.getAllProduct();
		ArrayList<String> productNames = new ArrayList<>();
		for(Product p : productList) {
			productNames.add(p.getProductName());
			
		}
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
		model.addAll(productNames);
		
		cbxProductName = new JComboBox();
		cbxProductName.setModel(model);
		cbxProductName.setBounds(105, 24, 156, 22);
		getContentPane().add(cbxProductName);
		
		JLabel lblNewLabel_1 = new JLabel("Gi\u00E1 b\u00E1n");
		lblNewLabel_1.setBounds(288, 28, 46, 14);
		getContentPane().add(lblNewLabel_1);
		
		txtSellPrice = new JTextField();
		txtSellPrice.setBounds(342, 25, 115, 20);
		getContentPane().add(txtSellPrice);
		txtSellPrice.setColumns(10);
		
		 btnAddOrderline = new JButton("Th\u00EAm");
		btnAddOrderline.setBounds(620, 24, 89, 23);
		getContentPane().add(btnAddOrderline);
		
		JLabel lblNewLabel_2 = new JLabel("s\u1ED1 l\u01B0\u1EE3ng");
		lblNewLabel_2.setBounds(467, 28, 46, 14);
		getContentPane().add(lblNewLabel_2);
		
		txtAmount = new JTextField();
		txtAmount.setBounds(523, 25, 87, 20);
		getContentPane().add(txtAmount);
		txtAmount.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 57, 590, 130);
		getContentPane().add(scrollPane);
		
		tblOrderlines = new JTable();
		tblOrderlines.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"No. ", "Tên sản phẩm", "Số lượng",  "Giá bán", "Thành tiền"
			}
		));
		scrollPane.setViewportView(tblOrderlines);
		
		JLabel lblNewLabel_3 = new JLabel("Gi\u00E1 v\u1EADn chuy\u1EC3n");
		lblNewLabel_3.setBounds(10, 195, 87, 14);
		getContentPane().add(lblNewLabel_3);
		
		txtShipmentFee = new JTextField();
		txtShipmentFee.setBounds(92, 192, 99, 20);
		getContentPane().add(txtShipmentFee);
		txtShipmentFee.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("\u0110\u1EB7t tr\u01B0\u1EDBc");
		lblNewLabel_4.setBounds(201, 195, 60, 14);
		getContentPane().add(lblNewLabel_4);
		
		txtDeposit = new JTextField();
		txtDeposit.setBounds(264, 192, 104, 20);
		getContentPane().add(txtDeposit);
		txtDeposit.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Gi\u1EA3m gi\u00E1");
		lblNewLabel_5.setBounds(395, 195, 46, 14);
		getContentPane().add(lblNewLabel_5);
		
		txtDiscount = new JTextField();
		txtDiscount.setBounds(451, 192, 99, 20);
		getContentPane().add(txtDiscount);
		txtDiscount.setColumns(10);
		
		btnSearchClient = new JButton("Next");
		btnSearchClient.setBounds(264, 250, 89, 23);
		getContentPane().add(btnSearchClient);
		
		cbxProductName.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			
				txtAmount.setText("");
				String chosenProductName  = cbxProductName.getSelectedItem().toString();
				for(Product p : productList) {
					if(chosenProductName.equals(p.getProductName())) {
						txtSellPrice.setText(p.getStickerPrice()+"");
						break;

					}	
				}
				
			}
		});
		
		btnAddOrderline.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String productName = cbxProductName.getSelectedItem().toString();
				String amount = txtAmount.getText();
				String sellPrice = txtSellPrice.getText();
				if(productName.isEmpty()||productName.isBlank()) {
					
					JOptionPane.showMessageDialog(null, "Vui lòng chọn một sản phẩm");
					return;
					
				}
				
				else if(!Pattern.matches("^\\d{1,}$", amount)) {
					JOptionPane.showMessageDialog(null, "Vui lòng nhập số lượng sản phẩm");
					return ;
					
				}
				else if(!Pattern.matches("^\\d{3,}$", sellPrice)) {
					
					JOptionPane.showMessageDialog(null, "Vui lòng nhập giá bán sản phẩm");
					return;

				}
				
				
				Product chosenProduct = null;
				for(Product p : productList) {
					if(p.getProductName().equals(productName)) {
						chosenProduct = p;
						break;
					}
					
				}
				if(chosenProduct!=null) {
					boolean duplicate = false;
					for(Product p : chosenProducts) {
						if(p.getId() == chosenProduct.getId()) {
							duplicate = true;
							JOptionPane.showMessageDialog(null, "Đã thêm sản phẩm");
							return;
						}
						
					}
					if(!duplicate) {
						chosenProducts.add(chosenProduct);
					}
		
				}
				
				DefaultTableModel defaultTableModel =(DefaultTableModel) tblOrderlines.getModel();
				defaultTableModel.addRow(new Object[] {tblOrderlines.getRowCount()+1,chosenProduct.getProductName(),Integer.parseInt(amount),Integer.parseInt(sellPrice),Integer.parseInt(sellPrice)*Integer.parseInt(amount)});
				

			}
			
			
			
			
			
			
		});
		btnSearchClient.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(!Pattern.matches("^\\d{1,}$", txtShipmentFee.getText())) {
			JOptionPane.showMessageDialog(null, "Vui lòng kiểm tra giá vận chuyển");
			return;
			
		}
		else if(!Pattern.matches("^\\d{1,}$", txtDeposit.getText())){
			JOptionPane.showMessageDialog(null, "Vui lòng kiểm tra mục đặt trước");

			return;
			
		}
		else if(!Pattern.matches("^\\d{1,}", txtDiscount.getText())) {
			JOptionPane.showMessageDialog(null, "Vui lòng kiểm tra mục giảm giá");
			return;
			
			
		}
		
		ArrayList<OrderLine> orderLines = new ArrayList<>();
		OrderLine orderLine = null;
		for(int i = 0 ; i < chosenProducts.size();i++) {
			orderLine = new OrderLine();
			orderLine.setProduct(chosenProducts.get(i));
			orderLine.setAmount(Integer.parseInt(tblOrderlines.getValueAt(i, 2).toString()));
			orderLine.setSellPrice(Integer.parseInt(tblOrderlines.getValueAt(i, 3).toString()));
			orderLine.setTotal(Integer.parseInt(tblOrderlines.getValueAt(i, 4).toString()));
			orderLines.add(orderLine);
		}
		
		Bill bill = new Bill();
		bill.setOrderLines(orderLines);
		bill.setUser(user);
		bill.setShipmentFee(Integer.parseInt(txtShipmentFee.getText()));
		bill.setDeposit(Integer.parseInt(txtDeposit.getText()));
		bill.setDiscount(Integer.parseInt(txtDiscount.getText()));
		bill.setTotalAmount(bill.calculateTotalAmount());
		bill.setOweAmount(bill.getTotalAmount()-bill.getDeposit());
		bill.setWriteDate(LocalDate.now());
		if(bill.getOweAmount()>0) {
			bill.setStatus("Còn nợ");
		}
		else {
			bill.setStatus("Hết nợ");
		}
		
		SearchClientFrm searchClientFrm = new SearchClientFrm(bill);
		searchClientFrm.setVisible(true);
		this.dispose();
		
		
		
		
	}
}
