package com.cloudfactory.frames;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import com.cloudfactory.controllers.UserController;
import com.cloudfactory.entity.FacAdmin;
import com.cloudfactory.util.IconHandler;

import java.awt.Container;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class FacAdminFrame extends MyFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8253700479372253085L;
	private JPanel contentPane;
	JPanel subPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void run(String id) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FacAdminFrame frame = new FacAdminFrame(id);
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
	public FacAdminFrame(String id) {
		ImageIcon frameIcon = new ImageIcon("./img/loginFrame_icon.png");
		setIconImage(frameIcon.getImage());
		setTitle("云工厂-云工厂管理员: " + id);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		UserController userController = UserController.getInstance("User");
		FacAdmin u = (FacAdmin) userController.findUser(id);
		if (u.getState() == 0) {
			JOptionPane.showMessageDialog(null, "工厂已被关停，请联系超级管理员", "错误", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}

		setBounds(100, 100, 1000, 600);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);

		subPanel.setBounds(0, 25, 996, 547);
		contentPane.add(subPanel);


		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 996, 25);
		contentPane.add(menuBar);

		ImageIcon facManagerIcon = IconHandler.resizeIcon("./img/list.png");
		JMenu facMenu = new JMenu("我的工厂");
		facMenu.setIcon(facManagerIcon);
		menuBar.add(facMenu);

		JMenuItem facMenuItem = new JMenuItem("我的设备");
		facMenuItem.setIcon(facManagerIcon);
		facMenu.add(facMenuItem);
		facMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				changeContentPane(MyFacDeviceManagerPanel.getInstance(id));

			}
		});

		ImageIcon orderManagerIcon = IconHandler.resizeIcon("./img/coupon.png");
		JMenu orderMenu = new JMenu("订单管理");
		orderMenu.setIcon(orderManagerIcon);
		menuBar.add(orderMenu);

		JMenuItem orderMenuItem = new JMenuItem("订单接单");
		orderMenuItem.setIcon(orderManagerIcon);
		orderMenu.add(orderMenuItem);

	}

	// 切换面板
	public void changeContentPane(Container contentPane) {
//        this.setContentPane(contentPane);
		this.subPanel.removeAll();
		this.revalidate();
		this.repaint();
		this.subPanel.add(contentPane);
		this.revalidate();
	}

}
