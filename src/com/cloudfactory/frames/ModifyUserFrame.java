package com.cloudfactory.frames;

import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.cloudfactory.controllers.UserController;
import com.cloudfactory.entity.Dealer;
import com.cloudfactory.entity.Device;
import com.cloudfactory.entity.FacAdmin;
import com.cloudfactory.entity.User;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPasswordField;
import javax.swing.JButton;

public class ModifyUserFrame extends MyFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5513507720471930133L;
	private JPanel contentPane;
	private JTextField idTextField;
	private JTextField nameTextField;
	private JTextField contactTextField;
	private JPasswordField pwdField;
	private JTextField facNameTextField;
	private JTextField facDetailsTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModifyUserFrame frame = new ModifyUserFrame(null);
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * 为超级管理员专门设置的启动方法，给构造方法传入的参数为0，表示关闭后不回到登录界面
	 */
	public static void mainForModify(User oUser) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModifyUserFrame frame = new ModifyUserFrame(oUser);
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * @param id 修改信息用，用于传入原始id
	 */
	public ModifyUserFrame(User oUser) {
		ImageIcon frameIcon = new ImageIcon("./img/id.png");
		setIconImage(frameIcon.getImage());

		setTitle("修改信息");

		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {

//					System.out.println("从管理员关闭了注册frame");
				UserManagerPanel.getInstance().updateTable();

			}
		});
		setBounds(100, 100, 455, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		idTextField = new JTextField();
		idTextField.setBounds(108, 35, 278, 30);
		contentPane.add(idTextField);
		idTextField.setText(oUser.getId());
		idTextField.setColumns(10);

		JLabel idLabel = new JLabel("登录账号：");
		idLabel.setHorizontalAlignment(SwingConstants.CENTER);
		idLabel.setBounds(15, 36, 90, 24);
		
		contentPane.add(idLabel);

		JLabel pwdLabel = new JLabel("登录密码：");
		pwdLabel.setHorizontalAlignment(SwingConstants.CENTER);
		pwdLabel.setBounds(15, 75, 90, 24);
		contentPane.add(pwdLabel);

		pwdField = new JPasswordField();
		pwdField.setBounds(108, 74, 278, 30);
		pwdField.setText(oUser.getId());
		contentPane.add(pwdField);

		JButton btnIsPwdVisible = new JButton("显");
		btnIsPwdVisible.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		btnIsPwdVisible.setBounds(390, 74, 45, 30);
		contentPane.add(btnIsPwdVisible);
		btnIsPwdVisible.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (pwdField.getEchoChar() != (char) 0) {
					pwdField.setEchoChar((char) 0);
					btnIsPwdVisible.setText("隐");
				} else {
					pwdField.setEchoChar('●');
					btnIsPwdVisible.setText("显");
				}

			}
		});

		nameTextField = new JTextField();
		nameTextField.setColumns(10);
		nameTextField.setBounds(108, 114, 278, 30);
		nameTextField.setText(oUser.getName());
		contentPane.add(nameTextField);

		JLabel nameLabel = new JLabel("真实姓名：");
		nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		nameLabel.setBounds(15, 115, 90, 24);
		contentPane.add(nameLabel);

		contactTextField = new JTextField();
		contactTextField.setColumns(10);
		contactTextField.setBounds(108, 155, 278, 30);
		contactTextField.setText(oUser.getContact());
		contentPane.add(contactTextField);

		JLabel contactLabel = new JLabel("联系方式：");
		contactLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contactLabel.setBounds(15, 154, 90, 24);
		contentPane.add(contactLabel);

		JLabel typeLabel = new JLabel("注册类型：");
		typeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		typeLabel.setBounds(15, 190, 90, 24);
		contentPane.add(typeLabel);

		JRadioButton factoryRadioButton = new JRadioButton("云工厂");
		factoryRadioButton.setBounds(108, 191, 79, 27);
		contentPane.add(factoryRadioButton);
		
		

		JRadioButton dealerRadioButton = new JRadioButton("经销商");
		dealerRadioButton.setBounds(194, 191, 79, 27);
		contentPane.add(dealerRadioButton);

		
		
		ButtonGroup btnGroup = new ButtonGroup();
		btnGroup.add(factoryRadioButton);
		btnGroup.add(dealerRadioButton);

		JPanel subPanel = new JPanel();
		subPanel.setBounds(14, 218, 421, 90);
		contentPane.add(subPanel);
		subPanel.setLayout(null);

		facNameTextField = new JTextField();
		facNameTextField.setColumns(10);
		facNameTextField.setBounds(94, 13, 278, 30);
		subPanel.add(facNameTextField);

		JLabel facNameLabel = new JLabel("工厂名称：");
		facNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		facNameLabel.setBounds(5, 15, 83, 24);
		subPanel.add(facNameLabel);

		facDetailsTextField = new JTextField();
		facDetailsTextField.setColumns(10);
		facDetailsTextField.setBounds(94, 54, 278, 30);
		subPanel.add(facDetailsTextField);

		JLabel facDetailsLabel = new JLabel("工厂简介：");
		facDetailsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		facDetailsLabel.setBounds(5, 56, 83, 24);
		subPanel.add(facDetailsLabel);

		JButton btnRegister = new JButton("确认");
		btnRegister.setBounds(156, 310, 117, 34);
		contentPane.add(btnRegister);

		
		if(oUser.getClass().getSimpleName().equals("FacAdmin")) {
			FacAdmin oAdmin = (FacAdmin)oUser;
			facNameTextField.setText(oAdmin.getFacName());
			facDetailsTextField.setText(oAdmin.getFacDetail());
			factoryRadioButton.setSelected(true);
			dealerRadioButton.setVisible(false);
		}
		else {
			dealerRadioButton.setSelected(true);
			factoryRadioButton.setVisible(false);
			subPanel.setVisible(false);
		}
		factoryRadioButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
//				JOptionPane.showMessageDialog(null, "工厂","提示",JOptionPane.INFORMATION_MESSAGE);
				subPanel.setVisible(true);
			}
		});
		dealerRadioButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
//				JOptionPane.showMessageDialog(null, "工厂","提示",JOptionPane.INFORMATION_MESSAGE);
				subPanel.setVisible(false);
			}
		});

		btnRegister.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String id = idTextField.getText();
				String pwd = new String(pwdField.getPassword());
				if (id.equals("")) {
					JOptionPane.showMessageDialog(null, "用户名不能为空", "错误", JOptionPane.ERROR_MESSAGE);
					idTextField.requestFocus();
				} else if (pwd.equals("")) {
					JOptionPane.showMessageDialog(null, "密码不能为空", "错误", JOptionPane.ERROR_MESSAGE);
					pwdField.requestFocus();
				} else if (id.contains(" ")) {
					JOptionPane.showMessageDialog(null, "用户名不能包含空格", "错误", JOptionPane.ERROR_MESSAGE);
					idTextField.requestFocus();
				} else if (pwd.contains(" ")) {
					JOptionPane.showMessageDialog(null, "密码不能包含空格", "错误", JOptionPane.ERROR_MESSAGE);
					pwdField.requestFocus();
				} else {
					UserController userController = UserController.getInstance("User");
					String contact = contactTextField.getText();
					String name = nameTextField.getText();
					if (oUser.getClass().getSimpleName().equals("FacAdmin")) {// 云工厂管理员
						FacAdmin oAdmin = (FacAdmin)oUser;
						
						String facName = facNameTextField.getText();
						String facDetails = facDetailsTextField.getText();
						ArrayList<Device> deviceList=new ArrayList<Device>();
						FacAdmin facAdmin = new FacAdmin(id, pwd, name, contact, facName, facDetails, deviceList);
						
						User u = userController.findUser(facAdmin.getId());
						if (u == null || facAdmin.getId().equals(oUser.getId())) {
							
							facAdmin.setDeviceList(oAdmin.getDeviceList());
							facAdmin.setUid(oUser.getUid());
							userController.modifyUser(oUser.getId(), facAdmin);
							JOptionPane.showMessageDialog(null, "修改成功", "提示", JOptionPane.INFORMATION_MESSAGE);
							dispose();
							FacManagerPanel.getInstance().updateTable();
							UserManagerPanel.getInstance().updateTable();
						} else {
							JOptionPane.showMessageDialog(null, "账号和已有存在重复，修改失败", "错误", JOptionPane.ERROR_MESSAGE);
						}

					} else {// 经销商
						Dealer dealer = new Dealer(id, pwd, name, contact);
						User u = userController.findUser(dealer.getId());
						if (u == null || dealer.getId().equals(oUser.getId())) {
							dealer.setUid(oUser.getUid());
							userController.modifyUser(oUser.getId(), dealer);
							JOptionPane.showMessageDialog(null, "修改成功", "提示", JOptionPane.INFORMATION_MESSAGE);
							dispose();
							UserManagerPanel.getInstance().updateTable();
						} else {
							JOptionPane.showMessageDialog(null, "账号和已有存在重复，修改失败", "错误", JOptionPane.ERROR_MESSAGE);
						}
					}
				}

			}
		});
	}
}
