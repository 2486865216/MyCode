package resposity;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class ll extends JFrame {

	private JPanel contentPane;
	private final JPanel panel = new JPanel();
	private JTable table;
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ll frame = new ll();
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
	public ll() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 414);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		panel.setBounds(0, 0, 632, 236);
		contentPane.add(panel);
		panel.setLayout(null);
		
		
		
		
		
		table = new JTable();
		table.setBounds(0, 27, 632, 209);
		panel.add(table);
		
		//scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 236, 632, 131);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
	
		
		JButton btnNewButton = new JButton("\u67E5\u8BE2");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==btnNewButton){
					   String[] table1 = { "名称", "生产厂家","规格","型号","数量","11" };
					   DefaultTableModel mm= new DefaultTableModel(table1,1); //定义一个表的模板
					 
				
				try { 
					Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); //加载access数据库驱动
			    System.out.println("加载驱动成功！");
					
				} catch (Exception e2) {
					// TODO: handle exception
				}
				try {
					 Connection conn = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=库存物资管理系统","sa","123"); //建立连接这里student为数据库名
					   System.out.println("连接数据库成功！");
					   Statement stmt = conn.createStatement();
					  
				
					   ResultSet rs=stmt.executeQuery("select * from.商品");//将查询得到的结果集给rs
				
					   while (rs.next()) {
						   
					 
							   String id = rs.getString("名称");
							      String name = rs.getString("生产厂家");
							      String id1 = rs.getString("规格");
							      String name1 = rs.getString("型号");
							      String name2 = rs.getString("数量");
							      String[] str_row={id,name,id1,name1,name2}; //将一行的数据存在str_row 字符串数组里
							      mm.addRow(str_row);//添加在表模板中
							      System.out.println("232qq");
						 }
						 table.setModel(mm);//将jtable这个表 设置为刚刚定义的模板
					
						  System.out.println("232");
				
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
			}
		});
		btnNewButton.setBounds(241, 62, 113, 27);
		panel_1.add(btnNewButton);
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 0, 632, 236);
		panel.add(scrollPane);
	}

}
