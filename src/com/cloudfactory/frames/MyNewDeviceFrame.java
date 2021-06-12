package com.cloudfactory.frames;

import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.cloudfactory.controllers.DeviceController;
import com.cloudfactory.entity.Device;
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

public class MyNewDeviceFrame extends MyFrame {

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
	public static void run(String userID) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					MyNewDeviceFrame frame = new MyNewDeviceFrame(userID);
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
	public MyNewDeviceFrame(String userID) {
		ImageIcon frameIcon = new ImageIcon("./img/card.png");
		setIconImage(frameIcon.getImage());

		setTitle("新建设备");

		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {

					DeviceManagerPanel.getInstance().updateTable();
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

		JLabel idLabel = new JLabel("设备名称：");
		idLabel.setHorizontalAlignment(SwingConstants.CENTER);
		idLabel.setBounds(15, 36, 90, 24);
		contentPane.add(idLabel);

		JLabel pwdLabel = new JLabel("设备规格：");
		pwdLabel.setHorizontalAlignment(SwingConstants.CENTER);
		pwdLabel.setBounds(15, 121, 90, 24);
		contentPane.add(pwdLabel);

		detailTextField = new JTextField();
		detailTextField.setColumns(10);
		detailTextField.setBounds(108, 160, 278, 30);
		contentPane.add(detailTextField);

		JLabel nameLabel = new JLabel("设备描述：");
		nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		nameLabel.setBounds(15, 161, 90, 24);
		contentPane.add(nameLabel);

		JLabel contactLabel = new JLabel("设备类型：");
		contactLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contactLabel.setBounds(15, 81, 90, 24);
		contentPane.add(contactLabel);


		JButton btnRegister = new JButton("确认");
		btnRegister.setBounds(148, 205, 117, 34);
		contentPane.add(btnRegister);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		comboBox.setBounds(108, 80, 278, 27);
		comboBox.addItem("车床");
		comboBox.addItem("镗床");
		contentPane.add(comboBox);
		
		guigeTextField = new JTextField();
		guigeTextField.setColumns(10);
		guigeTextField.setBounds(108, 121, 278, 30);
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
					DeviceController dController = DeviceController.getInstance("Device");
					Device device = new Device(name, type, guige, detail,0,1);
					boolean re = dController.addMyDevice(device,userID);
					if (re) {

						JOptionPane.showMessageDialog(null, "添加成功", "提示",
								JOptionPane.INFORMATION_MESSAGE);
						dispose();
						MyFacDeviceManagerPanel.getInstance(userID).updateTable();
						
					} else {
						JOptionPane.showMessageDialog(null, "账号重复，注册失败", "错误", JOptionPane.ERROR_MESSAGE);
					}

				}

			}	
		});

		
	
	}
}
