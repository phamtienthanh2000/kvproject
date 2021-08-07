package view.ProductManagement;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import controller.ProductDAO;
import model.Product;
import model.User;
import view.HomeView;

public class EditProductFrm extends JFrame implements ActionListener{
	private JTextField txtProductName;
	private JTextField txtCalculationUnit;
	private JTextField txtAmount;
	private JTextField txtStickerPrice;
	private JTextField txtOrigin;
	private JTextField txtSupplyCompany;
	private JButton btnSave;
	private User user;
	private Product product;

	public EditProductFrm(User u, Product p) {
		this.user = u;
		this.product = p;
		getContentPane().setLayout(null);
		this.setSize(600,500);
		JLabel lblNewLabel = new JLabel("T\u00EAn S\u1EA3n Ph\u1EA9m");
		lblNewLabel.setBounds(10, 30, 96, 14);
		getContentPane().add(lblNewLabel);
		
		txtProductName = new JTextField();
		txtProductName.setText(product.getProductName());
		txtProductName.setBounds(155, 27, 261, 20);
		getContentPane().add(txtProductName);
		txtProductName.setColumns(10);
		
		JLabel lblnVTnh = new JLabel("\u0110\u01A1n v\u1ECB t\u00EDnh");
		lblnVTnh.setBounds(10, 58, 96, 14);
		getContentPane().add(lblnVTnh);
		
		txtCalculationUnit = new JTextField();
		txtCalculationUnit.setText(product.getCalculationUnit());
		
		txtCalculationUnit.setColumns(10);
		txtCalculationUnit.setBounds(155, 55, 261, 20);
		getContentPane().add(txtCalculationUnit);
		
		JLabel lblSLng = new JLabel("S\u1ED1 l\u01B0\u1EE3ng");
		lblSLng.setBounds(10, 86, 96, 14);
		getContentPane().add(lblSLng);
		
		txtAmount = new JTextField();
		txtAmount.setText(product.getAmount()+"");
		txtAmount.setColumns(10);
		txtAmount.setBounds(155, 83, 261, 20);
		getContentPane().add(txtAmount);
		
		JLabel lblGiNimYt = new JLabel("Gi\u00E1 ni\u00EAm y\u1EBFt");
		lblGiNimYt.setBounds(10, 114, 96, 14);
		getContentPane().add(lblGiNimYt);
		
		txtStickerPrice = new JTextField();
		txtStickerPrice.setText(product.getStickerPrice()+"");
		txtStickerPrice.setColumns(10);
		txtStickerPrice.setBounds(155, 111, 261, 20);
		getContentPane().add(txtStickerPrice);
		
		JLabel lblNgunGc = new JLabel("Ngu\u1ED3n g\u1ED1c");
		lblNgunGc.setBounds(10, 142, 96, 14);
		getContentPane().add(lblNgunGc);
		
		txtOrigin = new JTextField();
		txtOrigin.setText(product.getDescription().getOrigin());
		txtOrigin.setColumns(10);
		txtOrigin.setBounds(155, 139, 261, 20);
		getContentPane().add(txtOrigin);
		
		JLabel lblCngTyCung = new JLabel("C\u00F4ng ty cung c\u1EA5p");
		lblCngTyCung.setBounds(10, 170, 96, 14);
		getContentPane().add(lblCngTyCung);
		
		txtSupplyCompany = new JTextField();
		txtSupplyCompany.setText(product.getDescription().getSupplyCompany());
		txtSupplyCompany.setColumns(10);
		txtSupplyCompany.setBounds(155, 167, 261, 20);
		getContentPane().add(txtSupplyCompany);
		
		btnSave = new JButton("L\u01B0u");
		btnSave.setBounds(220, 212, 89, 23);
		getContentPane().add(btnSave);
		btnSave.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if( txtProductName.getText().isEmpty()||txtProductName.getText().isBlank()) {
			JOptionPane.showMessageDialog(null, "Vui lòng không bỏ trống thông tin mặt hàng");
			return;
		}
		if( txtCalculationUnit.getText().isEmpty()||txtCalculationUnit.getText().isBlank()) {
			JOptionPane.showMessageDialog(null, "Vui lòng không bỏ trống thông tin mặt hàng");
			return;
		}
		if(!Pattern.matches("^\\d{1,}$", txtAmount.getText())) {
			JOptionPane.showMessageDialog(null, "Vui lòng điền số lượng hợp lệ");
			return;
		};
		if(!Pattern.matches("^\\d{1,}$", txtStickerPrice.getText())) {
			JOptionPane.showMessageDialog(null, "Vui lòng điền giá niêm yết hợp lệ");
			return;
		}
		
		String productName = txtProductName.getText();
		String calculationUnit = txtCalculationUnit.getText();
		int amount = Integer.parseInt(txtAmount.getText());
		int stickerPrice = Integer.parseInt(txtStickerPrice.getText());
		String origin = txtOrigin.getText();
		String supplyCompany = txtSupplyCompany.getText();
		
		product.setProductName(productName);
		product.setCalculationUnit(calculationUnit);
		product.setAmount(amount);
		product.setStickerPrice(stickerPrice);
		product.getDescription().setOrigin(origin);
		product.getDescription().setSupplyCompany(supplyCompany);
		
		ProductDAO productDAO = new ProductDAO();
		if(productDAO.updateProduct(product)) {
			JOptionPane.showMessageDialog(null, "Lưu thành công");
		}else {
			
			JOptionPane.showMessageDialog(null, "Lưu thất bại");

		}
		
		HomeView homeView = new HomeView(user);
		homeView.setVisible(true);
		this.dispose();
		
		
	}

	
	
}
