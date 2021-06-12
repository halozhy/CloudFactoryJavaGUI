package com.cloudfactory.frames;

import java.awt.EventQueue;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import com.cloudfactory.util.IconHandler;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class DealerFrame extends MyFrame {

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
					DealerFrame frame = new DealerFrame(null);
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
	public DealerFrame(String id) {
		ImageIcon frameIcon = new ImageIcon("./img/loginFrame_icon.png");
		setIconImage(frameIcon.getImage());
		setTitle("云工厂-经销商: " + id);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		setBounds(100, 100, 1000, 600);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);

		subPanel.setBounds(0, 25, 996, 547);
		contentPane.add(subPanel);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 996, 25);
		contentPane.add(menuBar);

		ImageIcon orderManagerIcon = IconHandler.resizeIcon("./img/coupon.png");
		JMenu orderMenu = new JMenu("订单管理");
		orderMenu.setIcon(orderManagerIcon);
		menuBar.add(orderMenu);

		JMenuItem orderMenuItem = new JMenuItem("我的订单");
		orderMenuItem.setIcon(orderManagerIcon);
		orderMenu.add(orderMenuItem);

	}

	public static void run(String id) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DealerFrame frame = new DealerFrame(id);
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}
}
