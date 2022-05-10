package resposity;
import java.sql.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;




public class RuKu extends JFrame {
	//private static SimpleDateFormat sdf =new SimpleDateFormat("yyyy-mm-dd");
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
	public RuKu() {
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
				 ruku = new 入库窗口();
				ruku.setVisible(true);
			}
		});
		button.setBounds(259, 312, 113, 27);
		panel.add(button);
		
		JButton button_1 = new JButton("\u51FA\u5E93");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				出库窗口 chuku = new 出库窗口();
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
		
		
		
		JTextArea textArea = new JTextArea();
//		scrollPane.setViewportView(textArea);
		
		table = new JTable();
		table.setBounds(14, 258, 952, -243);
		panel.add(table);
		
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"\u5546\u54C1", "\u51FA\u5E93\u8868", "\u5165\u5E93\u8868"}));
		comboBox.setBounds(42, 313, 146, 27);
		panel.add(comboBox);
		
		JButton button_2 = new JButton("\u540D\u79F0\u67E5\u8BE2");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				{
					String[] col = { "名称", "生产厂家", "规格","型号","数量",};
					String[] rukubiao= {"名称","生产厂家","型号","规格","数量","日期","时间","入库单位","送货人"};
					String[] chukubiao= {"名称","生产厂家","型号","规格","数量","日期","时间","出库单位","送货人"};
					DefaultTableModel mm= new DefaultTableModel(col,0); //定义一个表的模板
					DefaultTableModel ruku = new DefaultTableModel(rukubiao,0);
					DefaultTableModel chuku = new DefaultTableModel(chukubiao,0);
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
					        		if(string2.equals("商品")) {
						        		
						        		String id = rs.getString("名称");
						        		String shengchanchangjia =rs.getString("生产厂家");
						        		String guige = rs.getString("规格");
						        		String xinghao = rs.getString("型号");
						        		String shuliang = rs.getString("数量");
						        		
						        		String[] str_row = {id,shengchanchangjia,guige,xinghao,shuliang};
						        		 //将一行的数据存在str_row 字符串数组里
						      	        mm.addRow(str_row);//添加在表模板中
						        		table.setModel(mm);

						        	}
						        	else if(string2.equals("入库表")) {
						        		String id = rs.getString("名称");
						        		String shengchanchangjia =rs.getString("生产厂家");
						        		String guige = rs.getString("规格");
						        		String xinghao = rs.getString("型号");
						        		String shuliang = rs.getString("数量");
						        		String riqi = rs.getString("日期");
						        		String shijian = rs.getString("时间");
						        		String rukudanwei = rs.getString("入库单位");
						        		String songhuoren = rs.getString("送货人");
						        		String[] row_2 = {id,shengchanchangjia,xinghao,guige,shuliang,riqi,shijian,rukudanwei,songhuoren};
						        		ruku.addRow(row_2);
						        		table.setModel(ruku);

						        	}
						        	
						        	else if(string2.equals("出库表")){
						        		String id = rs.getString("名称");
						        		String shengchanchangjia =rs.getString("生产厂家");
						        		String guige = rs.getString("规格");
						        		String xinghao = rs.getString("型号");
						        		String shuliang = rs.getString("数量");
						        		String riqi = rs.getString("日期");
						        		String shijian = rs.getString("时间");
						        		String rukudanwei = rs.getString("出库单位");
						        		String songhuoren = rs.getString("送货人");
						        		String[] row_3 = {id,shengchanchangjia,xinghao,guige,shuliang,riqi,shijian,rukudanwei,songhuoren};
						        		chuku.addRow(row_3);
						        		table.setModel(chuku);
				    
						        	}
					        		
					        		
					        	}
					        	
					        	}
					        if (i==0) {
					        	JOptionPane.showMessageDialog(null, "仓库中没有该物品！","error!",JOptionPane.WARNING_MESSAGE);
							}
					        	 
					        	}
					catch(Exception e)
					{
					    e.printStackTrace();
					    System.out.print("SQL Server连接失败！");
					}        
					}
			
			}

		});
		button_2.setBounds(519, 382, 113, 27);
		panel.add(button_2);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(603, 316, 294, 18);
		panel.add(lblNewLabel);
		
		JButton button_5 = new JButton("\u65E5\u671F\u67E5\u8BE2");
		button_5.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
				 String[] rukubiao1= {"名称","生产厂家","型号","规格","数量","日期","时间","入库单位","送货人"};
			        DefaultTableModel rukubiao11= new DefaultTableModel(rukubiao1,0); //定义一个表的模板
				String driverName="com.microsoft.sqlserver.jdbc.SQLServerDriver";
				  String dbURL="jdbc:sqlserver://127.0.0.1:1433;DatabaseName=库存物资管理系统";//test为你的数据库名
				  String userName="sa";//你的数据库用户名
				  String userPwd="123";//你的密码
				 try
				{
				    Class.forName(driverName);
				    System.out.println("加载驱动成功！");
				}catch(Exception e1){
				    e1.printStackTrace();
				    System.out.println("加载驱动失败！");
				}
				 try {
					 Connection dbConn=DriverManager.getConnection(dbURL,userName,userPwd);
				        System.out.println("连接数据库成功！");
				        Statement statement = dbConn.createStatement();
				        String string2 =textField.getText();
				        string2=string2.trim();
				        String string3;
				        String string4;
				        String string5;
				        String string1=comboBox.getSelectedItem().toString();
				        String sql;
				        if(string2.length()==4) {
				        	string3 = string2.substring(0,4);
				        	sql = "select*from dbo."+string1+"  where year([日期]) = "+string3;
				        }else {
				        	if(string2.length()==7) {
				        		string3 = string2.substring(0,4);
				        		string4 = string2.substring(5,7);
				        		sql = "select*from dbo."+string1+"  where year([日期]) = "+string3+" and month([日期])="+string4;
				        	}else {
				        		string3 = string2.substring(0,4);
				        		string4 = string2.substring(5,7);
				        		string5 = string2.substring(8,10);
				        		sql = "select*from dbo."+string1+"  where year([日期]) = "+string3+" and month([日期])="+string4+" and day ([日期])="+string5 ;
				        	}
				        }
				        ResultSet rs = statement.executeQuery(sql);
				        	int m=0;
				        	String string = null;
				        	int i=0;
					       while(rs.next()) {
						       		if(string1.equals("入库表"))	{
						       			string ="入";
						        		String id1 = rs.getString("名称");
							        	String shengchanchangjia1 =rs.getString("生产厂家");
							        	String guige1 = rs.getString("规格");
							        	String xinghao1 = rs.getString("型号");
							        	String shuliang1= rs.getString("数量");
							        	m=m+Integer.valueOf(shuliang1);
							        	String riqi = rs.getString("日期");
							        	String shijian = rs.getString("时间");
							        	String rukudanwei = rs.getString("入库单位");
							        	String songhuoren = rs.getString("送货人");
							        		
							        		String[] str_row1 = {id1,shengchanchangjia1,guige1,xinghao1,shuliang1,riqi,shijian,rukudanwei,songhuoren};
							        		 //将一行的数据存在str_row 字符串数组里
							        		
							        		rukubiao11.addRow(str_row1);//添加在表模板中
						        
							        		
							        		table.setModel(rukubiao11);
							        		i++;
							        	
						        		}
						       		else if(string1.equals("出库表"))	{
						       			string ="出";
					        			String id1 = rs.getString("名称");
						        		String shengchanchangjia1 =rs.getString("生产厂家");
						        		String guige1 = rs.getString("规格");
						        		String xinghao1 = rs.getString("型号");
						        		String shuliang1= rs.getString("数量");
						        		m=m+Integer.valueOf(shuliang1);
						        		String riqi = rs.getString("日期");
						        		String shijian = rs.getString("时间");
						        		String rukudanwei = rs.getString("出库单位");
						        		String songhuoren = rs.getString("送货人");
						        		
						        		String[] str_row2 = {id1,shengchanchangjia1,guige1,xinghao1,shuliang1,riqi,shijian,rukudanwei,songhuoren};
						        		 //将一行的数据存在str_row 字符串数组里
						        		
						        		rukubiao11.addRow(str_row2);//添加在表模板中
					        
						        		
						        		table.setModel(rukubiao11);
						        	
					        		}
					       }
					       if(string2.length()==4) {
					    	   lblNewLabel.setText("该年的"+string+"货量为："+m);
					        }else {
					        	if(string2.length()==7) {
					        		lblNewLabel.setText("该月的"+string+"货量为："+m);
					        	}else {
					        		lblNewLabel.setText("该日的"+string+"货量为："+m);
					        	}
					        }
					       if (i==0) {
					    	   JOptionPane.showMessageDialog(null, "日期不存在","error!",JOptionPane.WARNING_MESSAGE);
					       }
				     } catch (Exception e2) {
					// TODO: handle exception
				
				    	 JOptionPane.showMessageDialog(null, "请在选择出库或入库表进行查询！","error!",JOptionPane.WARNING_MESSAGE);
				     
				     }
			}

			private char[] getType(String string1) {
				// TODO Auto-generated method stub
				return null;
			}
		});
		button_5.setBounds(642, 382, 113, 27);
		panel.add(button_5);
		
		JScrollPane scrollPane=new JScrollPane(table);
		scrollPane.setBounds(14, 13, 952, 254);
		panel.add(scrollPane);
		
	}
}
