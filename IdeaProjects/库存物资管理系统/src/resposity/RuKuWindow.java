package resposity;
import java.sql.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.TextArea;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.CardLayout;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;




public class RuKuTest extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	
	
	
	JTextArea textArea;
	private JTable table;
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RuKu frame = new RuKu();
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
	public RuKuTest() {
		JTable jtable = new JTable();
		JScrollPane jscrollpane = new JScrollPane(jtable);//将表格加个滚动条
		
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1008, 524);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JButton button = new JButton("\u5165\u5E93");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RuKuTest ruku = new RuKuTest();
				ruku.setVisible(true);
			}
		});
		button.setBounds(259, 312, 113, 27);
		panel.add(button);
		
		JButton button_1 = new JButton("\u51FA\u5E93");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 chuku = new 出库窗口();
				chuku.setVisible(true);
			}
		});
		button_1.setBounds(438, 312, 113, 27);
		panel.add(button_1);
		
		textField = new JTextField();
		textField.setBounds(166, 383, 304, 24);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel label = new JLabel("\u8BF7\u8F93\u5165\u8981\u67E5\u8BE2\u7684\u4FE1\u606F\uFF1A");
		label.setBounds(14, 386, 200, 18);
		panel.add(label);
		
		
		
		table = new JTable();
		table.setBounds(0, 265, 966, -264);
		panel.add(table);
		
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"\u5546\u54C1", "\u51FA\u5E93\u8868", "\u5165\u5E93\u8868"}));
		comboBox.setBounds(42, 313, 146, 27);
		panel.add(comboBox);
		
		JButton button_2 = new JButton("查询");
		button_2.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				{
					String[] col = { "学号", "姓名" };
					DefaultTableModel mm= new DefaultTableModel(col,0); //定义一个表的模板
					  String driverName="com.microsoft.sqlserver.jdbc.SQLServerDriver";
					  String dbURL="jdbc:sqlserver://127.0.0.1:1433;DatabaseName=库存物资管理系统";//test为你的数据库名
					  String userName="sa";//你的数据库用户名
					  String userPwd="123";//你的密码
					 try
					{
					    Class.forName(driverName);
					    System.out.println("加载驱动成功！");
					}catch(Exception e){
					    e.printStackTrace();
					    System.out.println("加载驱动失败！");
					}
					try{
					    
						Connection dbConn=DriverManager.getConnection(dbURL,userName,userPwd);
					        System.out.println("连接数据库成功！");
					        String string2=comboBox.getSelectedItem().toString();
					        String string=textField.getText();
					        Statement statement = dbConn.createStatement();
					        String sql = "select * from dbo."+string2;
					        ResultSet rs = statement.executeQuery(sql);
					        int i=0;
					        string = string.trim();
					        while(rs.next()) {
					        	
					        	
					        	if(rs.getString("名称").equals(string)){
					        		i++;
					        		
					        		break;
					        	}
					        	}
					        	if(i!=0) {
					        	if(string2.equals("商品")) {
//					        	textArea.append("名称："+rs.getString("名称")+"\n");
//					        	textArea.append("生产厂家："+rs.getString("生产厂家")+"\n");
//					        	textArea.append("型号："+rs.getString("型号")+"\n");
//					        	textArea.append("规格："+rs.getString("规格")+"\n");
//					        	textArea.append("数量："+rs.getString("数量")+"\n");
					        		System.out.println("123");
					        		String id = rs.getString("名称");
					        		String shengchanchangjia =rs.getString("生产厂家");		
					        		String[] str_row = {id,shengchanchangjia};
					        		 //将一行的数据存在str_row 字符串数组里
					      	        mm.addRow(str_row);//添加在表模板中
					        		table.setModel(mm);
//					        		textArea_1.add(table);
					        	}
					        	else if(string2.equals("入库表")) {
//					        	textArea_1.append("名称："+rs.getString("名称")+"\n");
//					        	textArea_1.append("生产厂家："+rs.getString("生产厂家")+"\n");
//					        	textArea_1.append("型号："+rs.getString("型号")+"\n");
//					        	textArea_1.append("规格："+rs.getString("规格")+"\n");
//					        	textArea_1.append("数量："+rs.getString("数量")+"\n");
//					        	textArea_1.append("日期："+rs.getString("日期")+"\n");
//					        	textArea_1.append("时间："+rs.getString("时间")+"\n");
//					        	textArea_1.append("[入库单为（或出库单位）]："+rs.getString("入库单位")+"\n");
//					        	textArea_1.append("[送货（或提货）人姓名]："+rs.getString("送货人")+"\n");
					        	}
					        	
					        	else if(string2.equals("出库表")){
//					        		textArea_1.append("名称："+rs.getString("名称")+"\n");
//						        	textArea_1.append("生产厂家："+rs.getString("生产厂家")+"\n");
//						        	textArea_1.append("型号："+rs.getString("型号")+"\n");
//						        	textArea_1.append("规格："+rs.getString("规格")+"\n");
//						        	textArea_1.append("数量："+rs.getString("数量")+"\n");
//						        	textArea_1.append("日期："+rs.getString("日期")+"\n");
//						        	textArea_1.append("时间："+rs.getString("时间")+"\n");
//						        	textArea_1.append("[入库单为（或出库单位）]："+rs.getString("出库单位")+"\n");
//						        	textArea_1.append("[送货（或提货）人姓名]："+rs.getString("送货人")+"\n");
					        	} 
					        	}
					}catch(Exception e)
					{
					    e.printStackTrace();
					    System.out.print("SQL Server连接失败！");
					}        
					}
				textField.setText(null);
			}

		});
		button_2.setBounds(519, 382, 113, 27);
		panel.add(button_2);
		
		JButton button_3 = new JButton("\u7EDF\u8BA1");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				统计 s统计 = new 统计();
				s统计.setVisible(true);
				 
					
				        
			}
		});
		button_3.setBounds(686, 382, 113, 27);
		panel.add(button_3);
		
		JButton button_5 = new JButton("\u5237\u65B0");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
//				textArea_1.setText(null);
			}
		});
		button_5.setBounds(618, 312, 113, 27);
		panel.add(button_5);
		
		JScrollPane scrollPane=new JScrollPane(table);
		scrollPane.setBounds(14, 13, 952, 254);
		panel.add(scrollPane);


		
	}
}
