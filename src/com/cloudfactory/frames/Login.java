package com.cloudfactory.frames;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.cloudfactory.controllers.UserController;

import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class Login extends MyFrame {

	/**
	 * 使用自动生成的serialVersionUID
	 */
	private static final long serialVersionUID = 8230467997049794395L;
	private JPanel contentPane;
	private JTextField idTextField;
	private JPasswordField pwdField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		ImageIcon frameIcon = new ImageIcon("./img/loginFrame_icon.png");
		setIconImage(frameIcon.getImage());
		setTitle("云工厂登录");
		setResizable(false);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.control);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel showMainIcon = new JLabel("");
		showMainIcon.setBounds(180, 10, 117, 92);
		ImageIcon mainIcon = new ImageIcon(frameIcon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH));
		showMainIcon.setIcon(mainIcon);
		contentPane.add(showMainIcon);

		JLabel idIconLabel = new JLabel("");
		idIconLabel.setBounds(49, 115, 47, 44);
		ImageIcon idIconSource = new ImageIcon("./img/id.png");
		ImageIcon idIcon = new ImageIcon(idIconSource.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
		idIconLabel.setIcon(idIcon);
		contentPane.add(idIconLabel);

		JLabel pwdIconLabel = new JLabel("");
		pwdIconLabel.setBounds(49, 155, 47, 44);
		ImageIcon pwdIconSource = new ImageIcon("./img/pwd.png");
		ImageIcon pwdIcon = new ImageIcon(pwdIconSource.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
		pwdIconLabel.setIcon(pwdIcon);
		contentPane.add(pwdIconLabel);

		idTextField = new JTextField();
		idTextField.setBounds(133, 119, 221, 34);
		contentPane.add(idTextField);
		idTextField.setColumns(10);

		JLabel lblNewLabel = new JLabel("密码");
		lblNewLabel.setBounds(88, 173, 44, 15);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_2 = new JLabel("账号");
		lblNewLabel_2.setBounds(88, 127, 43, 15);
		contentPane.add(lblNewLabel_2);

		pwdField = new JPasswordField();
		pwdField.setBounds(133, 164, 221, 34);
		contentPane.add(pwdField);

		JButton btnLogin = new JButton("登录");
		btnLogin.setIcon(null);
		btnLogin.setBounds(83, 207, 117, 34);
		contentPane.add(btnLogin);
		btnLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String id = idTextField.getText();
				String pwd = new String(pwdField.getPassword());
				if (id.equals("")) {
					JOptionPane.showMessageDialog(null, "用户名不能为空", "错误", JOptionPane.ERROR_MESSAGE);
					idTextField.requestFocus();
				}
				else if(pwd.equals("")) {
					JOptionPane.showMessageDialog(null, "密码不能为空", "错误", JOptionPane.ERROR_MESSAGE);
					pwdField.requestFocus();
				}
				else {
					UserController userController = UserController.getInstance("User");
					int re = userController.login(id, pwd);
					switch (re) {
					case 1: {
						JOptionPane.showMessageDialog(null, "欢迎超级管理员登录", "提示", JOptionPane.INFORMATION_MESSAGE);
						dispose();
						SuAdminFrame.main(null);
						break;
					}
					case 2: {
						JOptionPane.showMessageDialog(null, "欢迎云工厂管理员登录", "提示", JOptionPane.INFORMATION_MESSAGE);
						dispose();
						FacAdminFrame.run(id);
						break;
					}
					case 3: {
						JOptionPane.showMessageDialog(null, "欢迎经销商登录", "提示", JOptionPane.INFORMATION_MESSAGE);
						dispose();
						DealerFrame.run(id);
						break;
					}
					case -1: {
						JOptionPane.showMessageDialog(null, "无此用户", "错误", JOptionPane.ERROR_MESSAGE);
						idTextField.requestFocus();
						idTextField.selectAll();
						break;
					}
					case -2: {
						JOptionPane.showMessageDialog(null, "密码错误", "错误", JOptionPane.ERROR_MESSAGE);
						pwdField.requestFocus();
						break;
					}
					}
				}
			}
		});
		idTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER) {
					pwdField.requestFocus();
				}

			}

		});

		pwdField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER) {
					btnLogin.doClick();
				}

			}

		});

		JButton btnRegister = new JButton("注册");
		btnRegister.setBounds(224, 207, 117, 34);
		contentPane.add(btnRegister);
		btnRegister.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				Register.main(null);
			}
		});
	}
}
