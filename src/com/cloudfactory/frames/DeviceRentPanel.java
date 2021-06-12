package com.cloudfactory.frames;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultCellEditor;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

import com.cloudfactory.controllers.DeviceController;
import com.cloudfactory.util.IconHandler;



class DeviceRentPanel extends JPanel {

	
	class MyButtonRender6 implements TableCellRenderer {
		private JPanel panel;
		private JButton button;
		public MyButtonRender6() {
			this.initButton();
			this.initPanel();
			// 添加按钮。
			this.panel.add(this.button);
		}
		private void initButton() {
			this.button = new JButton();
			// 设置按钮的大小及位置。
			this.button.setBounds(0, 0, 100, 30);
			// 在渲染器里边添加按钮的事件是不会触发的

		}
		private void initPanel() {
			this.panel = new JPanel();
			// panel使用绝对定位，这样button就不会充满整个单元格。
			this.panel.setLayout(null);
		}
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			// 只为按钮赋值即可。也可以作其它操作，如绘背景等。
//			this.button.setText(value == null ? "" : String.valueOf(value));
//			this.button.setIcon(IconHandler.resizeIcon("./img/edit.png"));
			this.button.setText("租用/解租");

			return this.panel;
		}

	}
	
	
	/**
	 * 自定义一个往列里边添加按钮的单元格编辑器。最好继承DefaultCellEditor，不然要实现的方法就太多了。
	 * 
	 */
	class MyButtonEditor6 extends DefaultCellEditor {
		private static final long serialVersionUID = -6546334664166791132L;
		private JPanel panel;
		private JButton button;
		public MyButtonEditor6(JTable table) {
			// DefautlCellEditor有此构造器，需要传入一个，但这个不会使用到，直接new一个即可。
			super(new JTextField());
			// 设置点击几次激活编辑。
			this.setClickCountToStart(1);
			this.initButton(table);
			this.initPanel();
			// 添加按钮。
			this.panel.add(this.button);
		}
		private void initButton(JTable table) {
			this.button = new JButton();
			// 设置按钮的大小及位置。
			this.button.setBounds(0, 0, 100, 30);
			// 为按钮添加事件。这里只能添加ActionListner事件，Mouse事件无效。
			this.button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// 触发取消编辑的事件，不会调用tableModel的setValue方法。
					MyButtonEditor6.this.fireEditingCanceled();
					System.out.println("stop " + table.getSelectedRow() + " " + table.getSelectedColumn());
//					TableModel t = table.getModel();
//					String id = (String) t.getValueAt(table.getSelectedRow(), 1);
//					UserController userController = UserController.getInstance("User");
//					ModifyUserFrame.mainForModify(userController.findUser(id));
					
					TableModel t = table.getModel();
					String id = (String) t.getValueAt(table.getSelectedRow(), 1);
					System.out.println(id);
					DeviceController deviceController = DeviceController.getInstance("Device");
					deviceController.RentDevice(id, userID);
					DeviceRentPanel.getInstance(userID).updateTable();
					MyFacDeviceManagerPanel.getInstance(userID).updateTable();
				}
			});


		}

		private void initPanel() {
			this.panel = new JPanel();

			// panel使用绝对定位，这样button就不会充满整个单元格。
			this.panel.setLayout(null);
		}

		/**
		 * 这里重写父类的编辑方法，返回一个JPanel对象即可（也可以直接返回一个Button对象，但是那样会填充满整个单元格）
		 */
		@Override
		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
			// 只为按钮赋值即可。也可以作其它操作。
//			this.button.setText(value == null ? "" : String.valueOf(value));
			
			this.button.setText("租用/解租");

			return this.panel;
		}

		/**
		 * 重写编辑单元格时获取的值。如果不重写，这里可能会为按钮设置错误的值。
		 */
		@Override
		public Object getCellEditorValue() {
			return this.button.getText();
		}

	}
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6895418201729686574L;

	private static DeviceRentPanel instance;
	private JTextField textField;
	static JTable table;
	static JScrollPane scrollPane;
	static JPanel panel;
	static DefaultTableModel tModel;
	static String userID;
	
	/**
	 * 实现单例模式
	 * 
	 * @return 返回UserManagerPanel这个对象
	 */
	public static DeviceRentPanel getInstance(String userID) {
		if (instance == null) {
			instance = new DeviceRentPanel(userID);
		}
		return instance;
	}

	/**
	 * Create the panel.
	 */
	private DeviceRentPanel(String id) {
		userID=id;
		JLabel lblNewLabel = new JLabel("检索：");

		textField = new JTextField();
		textField.setColumns(10);

		JButton btnSearch = new JButton("查询");
		btnSearch.setIcon(IconHandler.resizeIcon("./img/search.png"));

		JButton btnReset = new JButton("重置");

		panel = new JPanel();
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(125)
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, 357, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnSearch, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnReset, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE))
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 860, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSearch)
						.addComponent(btnReset, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 400, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
		panel.setLayout(new BorderLayout(0, 0));

		iniTable();
		btnReset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				updateTable();

			}
		});

		btnSearch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// 查找功能实现
				panel.removeAll();
				searchTable(textField.getText());
				panel.revalidate();
			}
		});
	}

	public void iniTable() {
		String[] columnNames = { "序号", "设备编号", "设备名称", "设备类型", "设备描述", "设备状态","操作" };
//		String[][] cm2= {{"1","11","111"},{"2","22","222"},{"3","33","333"}};
		DeviceController deviceController = DeviceController.getInstance("Device");
		String[][] data = deviceController.getRentableAll();
//		for (Factory factory : l) {
//			System.out.println(factory);
//		}
//		String[][] data = new String[10][8];
//		for (String[] s : data) {
//			System.out.println(s[0]+s[1]+s[2]+s[3]+s[4]+s[5]);
//		}
		tModel = new DefaultTableModel(data, columnNames);
		table = new JTable(tModel) {
			/**
			 * 默认的serialVersionUID
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				if (column == 6) {
					return true;
				}
				return false;
			}// 除了按钮那一列之外的表格不允许被编辑
		};
		scrollPane = new JScrollPane(table);
		panel.add(scrollPane);
		table.setRowHeight(30);
		table.getColumnModel().getColumn(6).setCellEditor(new MyButtonEditor6(table));
		table.getColumnModel().getColumn(6).setCellRenderer(new MyButtonRender6());

		table.getColumnModel().getColumn(0).setPreferredWidth(10);

		table.getColumnModel().getColumn(6).setPreferredWidth(50);

//		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setRowSelectionAllowed(false);// 禁止表格的选择功能。不然在点击按钮时表格的整行都会被选中。也可以通过其它方式来实现。
	}

	public void searchTable(String keyWord) {
		String[] columnNames = { "序号", "设备编号", "设备名称", "设备类型", "设备描述", "设备状态","操作" };
//		String[][] cm2= {{"1","11","111"},{"2","22","222"},{"3","33","333"}};
		DeviceController deviceController = DeviceController.getInstance("Device");
		String[][] data = deviceController.getRentableAll();
//		String[][] data = new String[10][8];
		ArrayList<String[]> arr = new ArrayList<String[]>();

		for (String[] s : data) {
			if (s[2].contains(keyWord)) {
				arr.add(s);
			}
//			System.out.println(s[0]+s[1]+s[2]+s[3]+s[4]+s[5]);
		}
		String[][] newData = new String[arr.size()][7];

		for (int i = 0; i < newData.length; i++) {
			newData[i] = arr.get(i);
		}
		tModel = new DefaultTableModel(newData, columnNames);
		table = new JTable(tModel) {
			/**
			 * 默认的serialVersionUID
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				if (column == 6) {
					return true;
				}
				return false;
			}// 除了按钮那一列之外的表格不允许被编辑
		};
		scrollPane = new JScrollPane(table);
		panel.add(scrollPane);
		table.setRowHeight(30);
		table.getColumnModel().getColumn(6).setCellEditor(new MyButtonEditor6(table));
		table.getColumnModel().getColumn(6).setCellRenderer(new MyButtonRender6());

		table.getColumnModel().getColumn(0).setPreferredWidth(10);

		table.getColumnModel().getColumn(6).setPreferredWidth(50);

//		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setRowSelectionAllowed(false);// 禁止表格的选择功能。不然在点击按钮时表格的整行都会被选中。也可以通过其它方式来实现。
	}

	public void updateTable() {
		panel.removeAll();
		iniTable();
		panel.revalidate();
	}
	

}