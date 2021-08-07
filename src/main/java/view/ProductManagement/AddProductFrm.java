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
import model.Description;
import model.Product;
import model.User;
import view.HomeView;

public class AddProductFrm extends JFrame implements ActionListener{
	private JTextField txtProductName;
	private JTextField txtCalculationUnit;
	private JTextField txtAmount;
	private JTextField txtStickerPrice;
	private JTextField txtOrigin;
	private JTextField txtSupplyCompany;
	private JButton btnAdd;
	private User user;
	
	public AddProductFrm(User u) {
		this.user = u;
		getContentPane().setLayout(null);
		this.setSize(600,500);
		JLabel lblNewLabel = new JLabel("T\u00EAn S\u1EA3n ph\u1EA9m");
		lblNewLabel.setBounds(31, 21, 89, 14);
		getContentPane().add(lblNewLabel);
		
		txtProductName = new JTextField();
		txtProductName.setBounds(155, 18, 242, 20);
		getContentPane().add(txtProductName);
		txtProductName.setColumns(10);
		
		JLabel lblnVTnh = new JLabel("\u0110\u01A1n v\u1ECB t\u00EDnh");
		lblnVTnh.setBounds(31, 61, 89, 14);
		getContentPane().add(lblnVTnh);
		
		txtCalculationUnit = new JTextField();
		txtCalculationUnit.setColumns(10);
		txtCalculationUnit.setBounds(155, 58, 242, 20);
		getContentPane().add(txtCalculationUnit);
		
		JLabel lblSLng = new JLabel("S\u1ED1 l\u01B0\u1EE3ng");
		lblSLng.setBounds(31, 102, 46, 14);
		getContentPane().add(lblSLng);
		
		txtAmount = new JTextField();
		txtAmount.setColumns(10);
		txtAmount.setBounds(155, 99, 242, 20);
		getContentPane().add(txtAmount);
		
		JLabel lblGiNimYt = new JLabel("Gi\u00E1 Ni\u00EAm Y\u1EBFt");
		lblGiNimYt.setBounds(31, 147, 89, 14);
		getContentPane().add(lblGiNimYt);
		
		txtStickerPrice = new JTextField();
		txtStickerPrice.setColumns(10);
		txtStickerPrice.setBounds(155, 144, 242, 20);
		getContentPane().add(txtStickerPrice);
		
		JLabel lblNgunGc = new JLabel("Ngu\u1ED3n g\u1ED1c");
		lblNgunGc.setBounds(31, 190, 70, 14);
		getContentPane().add(lblNgunGc);
		
		txtOrigin = new JTextField();
		txtOrigin.setColumns(10);
		txtOrigin.setBounds(155, 187, 242, 20);
		getContentPane().add(txtOrigin);
		
		JLabel lblCngTyCung = new JLabel("C\u00F4ng ty cung c\u1EA5p");
		lblCngTyCung.setBounds(31, 233, 89, 14);
		getContentPane().add(lblCngTyCung);
		
		txtSupplyCompany = new JTextField();
		txtSupplyCompany.setColumns(10);
		txtSupplyCompany.setBounds(155, 230, 242, 20);
		getContentPane().add(txtSupplyCompany);
		
		btnAdd = new JButton("Th\u00EAm s\u1EA3n ph\u1EA9m");
		btnAdd.setBounds(217, 286, 112, 23);
		getContentPane().add(btnAdd);
		btnAdd.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String productName = this.txtProductName.getText();
		String calculationUnit = this.txtCalculationUnit.getText();
		
		if(!Pattern.matches("^\\d{1,}$", this.txtAmount.getText())) {
			JOptionPane.showMessageDialog(null, "Vui lòng nhập đúng số lượng");
			return;
			
		}
		if(!Pattern.matches("^\\d{3,}$", this.txtStickerPrice.getText())) {
			
			JOptionPane.showMessageDialog(null, "Vui lòng nhập đúng giá bán");
			return;
		}
		int amount = Integer.parseInt(this.txtAmount.getText());
		int stickerPrice = Integer.parseInt(this.txtStickerPrice.getText());
		String origin = this.txtOrigin.getText();
		String supplyCompany = this.txtSupplyCompany.getText();
		
		if(productName.isBlank()||productName.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Vui lòng không để trống tên sản phẩm");
			return;
		}
		else if(calculationUnit.isBlank()||calculationUnit.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Vui lòng không để trống đơn vị tính");
			return;
		}
		
		Product product = new Product();
		product.setProductName(productName);
		product.setCalculationUnit(calculationUnit);
		product.setAmount(amount);
		product.setStickerPrice(stickerPrice);
		Description description = new Description();
		description.setOrigin(origin);
		description.setSupplyCompany(supplyCompany);
		
		product.setDescription(description);
		
		ProductDAO productDAO = new ProductDAO();
		if(productDAO.createProduct(product)) {
			JOptionPane.showMessageDialog(null, "Thêm sản phẩm thành công");
			
			
		}
		else {
			JOptionPane.showMessageDialog(null, "Thêm sản phẩm thất bại");

			
		};
		
		HomeView homeView = new HomeView(user);
		homeView.setVisible(true);
		this.dispose();
		
		
	}
}
