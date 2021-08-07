package view.BillManagement;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.BillDAO;
import model.Bill;
import model.OrderLine;
import view.HomeView;

public class ConfirmFrm extends JFrame implements ActionListener{
	private JTable tblOrderLines;
	private JLabel lblClientName;
	private JLabel lblPhoneNumber;
	private JLabel lblClientAddress;
	private JLabel lblShipmentFee;
	private JLabel lblTotal;
	private JLabel lblDiscount;
	private JLabel lblDeposit;
	private JLabel lblOweAmount;
	private JLabel lblBillStatus;
	private JLabel lblWriteDate;
	private JButton btnConfirm;
	private Bill bill;
	public ConfirmFrm(Bill b) {
		this.bill = b;
		getContentPane().setLayout(null);
		this.setSize(700,500);
		JLabel lblNewLabel = new JLabel("T\u00EAn kh\u00E1ch h\u00E0ng");
		lblNewLabel.setBounds(10, 55, 76, 14);
		getContentPane().add(lblNewLabel);
		
		lblClientName = new JLabel("New label");
		lblClientName.setText(bill.getClient().getName());
		lblClientName.setBounds(115, 55, 201, 14);
		getContentPane().add(lblClientName);
		
		JLabel lblNewLabel_2 = new JLabel("S\u1ED1 \u0111i\u1EC7n tho\u1EA1i");
		lblNewLabel_2.setBounds(353, 55, 76, 14);
		getContentPane().add(lblNewLabel_2);
		
		lblPhoneNumber = new JLabel("New label");
		lblPhoneNumber.setText(bill.getClient().getPhoneNumber());
		lblPhoneNumber.setBounds(498, 55, 151, 14);
		getContentPane().add(lblPhoneNumber);
		
		JLabel lblNewLabel_4 = new JLabel("\u0110\u1ECBa ch\u1EC9");
		lblNewLabel_4.setBounds(10, 92, 46, 14);
		getContentPane().add(lblNewLabel_4);
		
		lblClientAddress = new JLabel("New label");
		lblClientAddress.setText(bill.getClient().getAddress());
		lblClientAddress.setBounds(115, 92, 534, 14);
		getContentPane().add(lblClientAddress);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 117, 639, 166);
		getContentPane().add(scrollPane);
		
		ArrayList<OrderLine> orderLines = bill.getOrderLines();
		String[][] data = new String[orderLines.size()+1][5];
		for(int i = 0 ; i<= orderLines.size();i++) {
			if(i==orderLines.size()) {
				data[i][0] = "Tổng tiền hàng";
				data[i][4] = bill.getTotalAmount()+bill.getDiscount()-bill.getShipmentFee()+"";
				break;
			}
			OrderLine orderLine = orderLines.get(i);
			data[i][0] = i+1+"";
			data[i][1] = orderLine.getProduct().getProductName();
			data[i][2] = orderLine.getAmount()+"";
			data[i][3] = orderLine.getSellPrice()+"";
			data[i][4] = orderLine.getTotal()+"";
			
		}
		tblOrderLines = new JTable();
		tblOrderLines.setModel(new DefaultTableModel(
			data,
			new String[] {
				"No.", "Tên mặt hàng", "Số lượng", "Giá bán", "Thành tiền"
			}
		));
		scrollPane.setViewportView(tblOrderLines);
		
		JLabel lblNewLabel_6 = new JLabel("ph\u00ED v\u1EADn chuy\u1EC3n");
		lblNewLabel_6.setBounds(10, 298, 76, 14);
		getContentPane().add(lblNewLabel_6);
		
		lblShipmentFee = new JLabel("New label");
		lblShipmentFee.setText(bill.getShipmentFee()+"");
		lblShipmentFee.setBounds(101, 298, 139, 14);
		getContentPane().add(lblShipmentFee);
		
		JLabel lblNewLabel_8 = new JLabel("Gi\u1EA3m gi\u00E1");
		lblNewLabel_8.setBounds(438, 298, 62, 14);
		getContentPane().add(lblNewLabel_8);
		
		lblTotal = new JLabel("New label");
		lblTotal.setText(bill.getTotalAmount()+"");
		lblTotal.setBounds(296, 298, 145, 14);
		getContentPane().add(lblTotal);
		
		JLabel lblNewLabel_10 = new JLabel("T\u1ED5ng ti\u1EC1n");
		lblNewLabel_10.setBounds(240, 298, 46, 14);
		getContentPane().add(lblNewLabel_10);
		
		lblDiscount = new JLabel("New label");
		lblDiscount.setText(bill.getDiscount()+"");
		lblDiscount.setBounds(551, 298, 98, 14);
		getContentPane().add(lblDiscount);
		
		JLabel lblNewLabel_12 = new JLabel("\u0111\u1EB7t tr\u01B0\u1EDBc");
		lblNewLabel_12.setBounds(10, 332, 76, 14);
		getContentPane().add(lblNewLabel_12);
		
		lblDeposit = new JLabel("New label");
		lblDeposit.setText(bill.getDeposit()+"");
		lblDeposit.setBounds(101, 332, 85, 14);
		getContentPane().add(lblDeposit);
		
		JLabel lblNewLabel_14 = new JLabel("C\u00F2n n\u1EE3");
		lblNewLabel_14.setBounds(240, 332, 46, 14);
		getContentPane().add(lblNewLabel_14);
		
		lblOweAmount = new JLabel("New label");
		lblOweAmount.setText(bill.getOweAmount()+"");
		lblOweAmount.setBounds(296, 332, 133, 14);
		getContentPane().add(lblOweAmount);
		
		JLabel lblNewLabel_16 = new JLabel("Tr\u1EA1ng th\u00E1i h\u00F3a \u0111\u01A1n");
		lblNewLabel_16.setBounds(438, 332, 111, 14);
		getContentPane().add(lblNewLabel_16);
		
		lblBillStatus = new JLabel("New label");
		lblBillStatus.setText(bill.getStatus());
		lblBillStatus.setBounds(551, 332, 98, 14);
		getContentPane().add(lblBillStatus);
		
		JLabel lblNewLabel_18 = new JLabel("Ng\u00E0y vi\u1EBFt");
		lblNewLabel_18.setBounds(498, 25, 76, 14);
		getContentPane().add(lblNewLabel_18);
		
		lblWriteDate = new JLabel("New label");
		lblWriteDate.setText(bill.getWriteDate().toString());
		lblWriteDate.setBounds(584, 25, 65, 14);
		getContentPane().add(lblWriteDate);
		
		btnConfirm = new JButton("X\u00E1c nh\u1EADn");
		btnConfirm.setBounds(252, 386, 89, 23);
		getContentPane().add(btnConfirm);
		
		btnConfirm.addActionListener(this);
		
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		BillDAO billDAO = new BillDAO();
		if(billDAO.createBill(bill)) {
			JOptionPane.showMessageDialog(null, "Tạo hóa đơn thành công");
			
		}else {
			JOptionPane.showMessageDialog(null, "Tạo hóa đơn thất bại");
			
		}
		HomeView homeView=  new HomeView(bill.getUser());
		homeView.setVisible(true);
		this.dispose();
		
	}

}
