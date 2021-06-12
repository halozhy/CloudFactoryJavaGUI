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

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class NewProductFrame extends MyFrame {

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
					NewProductFrame frame = new NewProductFrame();
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
	public NewProductFrame() {
		ImageIcon frameIcon = new ImageIcon("./img/card.png");
		setIconImage(frameIcon.getImage());

		setTitle("新建产品");

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
		contentPane.add(detailTextField);

		JLabel nameLabel = new JLabel("产品描述：");
		nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		nameLabel.setBounds(15, 115, 90, 24);
		contentPane.add(nameLabel);

		JLabel contactLabel = new JLabel("产品类别：");
		contactLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contactLabel.setBounds(15, 154, 90, 24);
		contentPane.add(contactLabel);


		JButton btnRegister = new JButton("确认");
		btnRegister.setBounds(155, 200, 117, 34);
		contentPane.add(btnRegister);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		comboBox.setBounds(108, 153, 278, 27);
		comboBox.addItem("手机壳");
		comboBox.addItem("普通零件");
		contentPane.add(comboBox);
		
		guigeTextField = new JTextField();
		guigeTextField.setColumns(10);
		guigeTextField.setBounds(108, 75, 278, 30);
		contentPane.add(guigeTextField);

		btnRegister.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String name = nameTextField.getText();
				String guige = guigeTextField.getText();
				String detail = detailTextField.getText();
				String type = (String) comboBox.getSelectedItem();
				if (name.equals("")) {
					JOptionPane.showMessageDialog(null, "名称不能为空", "错误", JOptionPane.ERROR_MESSAGE);
					nameTextField.requestFocus();
				} else if (guige.equals("")) {
					JOptionPane.showMessageDialog(null, "规格不能为空", "错误", JOptionPane.ERROR_MESSAGE);
					guigeTextField.requestFocus();
				}
				else {
					ProductController pController = ProductController.getInstance("Product");
					Product product = new Product(name, type, guige, detail);
					boolean re = pController.addProduct(product);
					if (re) {

						JOptionPane.showMessageDialog(null, "添加成功", "提示",
								JOptionPane.INFORMATION_MESSAGE);
						dispose();
						ProductManagerPanel.getInstance().updateTable();
						
					} else {
						JOptionPane.showMessageDialog(null, "账号重复，注册失败", "错误", JOptionPane.ERROR_MESSAGE);
					}

				}

			}	
		});

		
	
	}
}
