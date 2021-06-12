package com.cloudfactory.frames;

import java.awt.EventQueue;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class DeviceRentFrame extends MyFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8253700479372253085L;
	private JPanel contentPane;
	JPanel subPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void run(String userid) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeviceRentFrame frame = new DeviceRentFrame(userid);
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
	public DeviceRentFrame(String userID) {
		ImageIcon frameIcon = new ImageIcon("./img/loginFrame_icon.png");
		setIconImage(frameIcon.getImage());
		setTitle("设备租用: " + userID);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		setBounds(100, 100, 900, 500);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);

		subPanel.setBounds(0, 0, 896, 472);
		contentPane.add(subPanel);
		DeviceRentPanel.getInstance(userID).updateTable();
		subPanel.add(DeviceRentPanel.getInstance(userID));

	}

}
