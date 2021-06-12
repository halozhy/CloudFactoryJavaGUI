package com.cloudfactory.frames;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import com.cloudfactory.util.IconHandler;

import java.awt.Container;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class SuAdminFrame extends MyFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8253700479372253085L;
	private JPanel contentPane;
	JPanel subPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SuAdminFrame frame = new SuAdminFrame();
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
	public SuAdminFrame() {
		ImageIcon frameIcon = new ImageIcon("./img/loginFrame_icon.png");
		setIconImage(frameIcon.getImage());
		setTitle("云工厂-超级管理员");
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		setBounds(100, 100, 1000, 600);
		contentPane = new JPanel();		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		subPanel.setBounds(0, 25, 996, 547);
		contentPane.add(subPanel);
//		subPanel.setLayout(null);
//		subPanel.add(MyPanel.getInstance());
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 996, 25);
		contentPane.add(menuBar);
		
		ImageIcon userManagerIcon = IconHandler.resizeIcon("./img/id.png");
		JMenu userMenu = new JMenu("用户管理");
		userMenu.setIcon(userManagerIcon);
		menuBar.add(userMenu);
		
		JMenuItem userMenuItem = new JMenuItem("用户管理");
		userMenuItem.setIcon(userManagerIcon);
		userMenu.add(userMenuItem);
		userMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			changeContentPane(UserManagerPanel.getInstance());	
				
			}
		});
		
		ImageIcon facManagerIcon = IconHandler.resizeIcon("./img/list.png");
		JMenu facMenu = new JMenu("云工厂");
		facMenu.setIcon(facManagerIcon);
		menuBar.add(facMenu);
		
		JMenuItem facMenuItem = new JMenuItem("云工厂信息");
		facMenuItem.setIcon(facManagerIcon);
		facMenu.add(facMenuItem);
		facMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			changeContentPane(FacManagerPanel.getInstance());	
				
			}
		});
		
		ImageIcon productManagerIcon = IconHandler.resizeIcon("./img/express.png");
		JMenu productMenu = new JMenu("产品管理");
		productMenu.setIcon(productManagerIcon);
		menuBar.add(productMenu);
		
		
		ImageIcon productSortIcon = IconHandler.resizeIcon("./img/label.png");
		JMenuItem productSortMenuItem = new JMenuItem("产品类别管理");
		productSortMenuItem.setIcon(productSortIcon);
		productMenu.add(productSortMenuItem);
		
		JMenuItem productInfoMenuItem = new JMenuItem("产品信息管理");
		productInfoMenuItem.setIcon(productManagerIcon);
		productMenu.add(productInfoMenuItem);
		productInfoMenuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				changeContentPane(ProductManagerPanel.getInstance());
				
			}
		});
		
		
		ImageIcon facCenterManagerIcon = IconHandler.resizeIcon("./img/classify.png");
		JMenu facCenterMenu = new JMenu("产能中心");
		facCenterMenu.setIcon(facCenterManagerIcon);
		menuBar.add(facCenterMenu);

		
		ImageIcon deviceSortManagerIcon = IconHandler.resizeIcon("./img/order.png");
		JMenuItem deviceSortMenuItem = new JMenuItem("设备类型管理");
		deviceSortMenuItem.setIcon(deviceSortManagerIcon);
		facCenterMenu.add(deviceSortMenuItem);
		
		JMenuItem deviceMenuItem = new JMenuItem("设备管理");
		deviceMenuItem.setIcon(facCenterManagerIcon);
		facCenterMenu.add(deviceMenuItem);
		deviceMenuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				changeContentPane(DeviceManagerPanel.getInstance());
				
			}
		});
		
		ImageIcon orderManagerIcon = IconHandler.resizeIcon("./img/coupon.png");
		JMenu orderMenu = new JMenu("订单管理");
		orderMenu.setIcon(orderManagerIcon);
		menuBar.add(orderMenu);
		
		JMenuItem orderMenuItem = new JMenuItem("订单基本信息");
		orderMenuItem.setIcon(orderManagerIcon);
		orderMenu.add(orderMenuItem);
		
		
		
		
		
		
		
	}
	
	//切换面板
    public void changeContentPane(Container contentPane) {
//        this.setContentPane(contentPane);
    	this.subPanel.removeAll();
    	this.revalidate();
    	this.repaint();
    	this.subPanel.add(contentPane);
        this.revalidate();
    }
}
