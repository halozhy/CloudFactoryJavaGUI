package com.cloudfactory.frames;

import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.cloudfactory.controllers.ProductController;
import com.cloudfactory.entity.Product;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class ModifyProductFrame extends MyFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8138519264282898916L;
	private JPanel contentPane;
	private JTextField nameTextField;
	private JTextField detailTextField;
	private JTextField guigeTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// 给构造方法传入的参数为1，表示关闭后回到登录界面
					ModifyProductFrame frame = new ModifyProductFrame(null);
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static void mainForModify(Product oProduct) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModifyProductFrame frame = new ModifyProductFrame(oProduct);
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * 
	 */
	public ModifyProductFrame(Product oProduct) {
		ImageIcon frameIcon = new ImageIcon("./img/card.png");
		setIconImage(frameIcon.getImage());

		setTitle("修改产品");

		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {

					ProductManagerPanel.getInstance().updateTable();
				}
		});
		setBounds(100, 100, 455, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		nameTextField = new JTextField();
		nameTextField.setBounds(108, 35, 278, 30);
		contentPane.add(nameTextField);
		nameTextField.setText(oProduct.getName());
		nameTextField.setColumns(10);

		JLabel idLabel = new JLabel("产品名称：");
		idLabel.setHorizontalAlignment(SwingConstants.CENTER);
		idLabel.setBounds(15, 36, 90, 24);
		contentPane.add(idLabel);

		JLabel pwdLabel = new JLabel("产品规格：");
		pwdLabel.setHorizontalAlignment(SwingConstants.CENTER);
		pwdLabel.setBounds(15, 75, 90, 24);
		contentPane.add(pwdLabel);

		detailTextField = new JTextField();
		detailTextField.setColumns(10);
		detailTextField.setBounds(108, 114, 278, 30);
		detailTextField.setText(oProduct.getDetail());
		contentPane.add(detailTextField);

		JLabel nameLabel = new JLabel("产品描述：");
		nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		nameLabel.setBounds(15, 115, 90, 24);
		contentPane.add(nameLabel);


		JButton btnRegister = new JButton("确认");
		btnRegister.setBounds(155, 200, 117, 34);
		contentPane.add(btnRegister);
		
		guigeTextField = new JTextField();
		guigeTextField.setColumns(10);
		guigeTextField.setBounds(108, 75, 278, 30);
		guigeTextField.setText(oProduct.getGuige());
		contentPane.add(guigeTextField);

		btnRegister.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String name = nameTextField.getText();
				String guige = guigeTextField.getText();
				String detail = detailTextField.getText();
//				String type = (String) comboBox.getSelectedItem();
				if (name.equals("")) {
					JOptionPane.showMessageDialog(null, "名称不能为空", "错误", JOptionPane.ERROR_MESSAGE);
					nameTextField.requestFocus();
				} else if (guige.equals("")) {
					JOptionPane.showMessageDialog(null, "规格不能为空", "错误", JOptionPane.ERROR_MESSAGE);
					guigeTextField.requestFocus();
				}
				else {
					ProductController pController = ProductController.getInstance("Product");
					Product product = new Product(name, oProduct.getType(), guige, detail);
					product.setId(oProduct.getId());
					product.setUid(oProduct.getUid());
					boolean re = pController.modifyProduct(oProduct.getId(),product);
					if (re) {

						JOptionPane.showMessageDialog(null, "修改成功", "提示",
								JOptionPane.INFORMATION_MESSAGE);
						dispose();
						ProductManagerPanel.getInstance().updateTable();
						
					} else {
						JOptionPane.showMessageDialog(null, "修改失败", "错误", JOptionPane.ERROR_MESSAGE);
					}

				}

			}	
		});

		
	
	}
}
