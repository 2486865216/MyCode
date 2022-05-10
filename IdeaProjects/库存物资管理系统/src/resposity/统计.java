package resposity;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.DefaultComboBoxModel;

public class 统计 extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					统计 frame = new 统计();
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
	public 统计() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 761, 421);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(new GridLayout(2, 1, 0, 0));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 733, 182);
		panel_1.add(scrollPane);
		
		JTextArea jTextArea = new JTextArea();
		scrollPane.setViewportView(jTextArea);
		
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		
		textField = new JTextField();
		textField.setBounds(149, 87, 178, 24);
		panel_2.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
				        String string=textField.getText();
				        String sql = "select sum(数量),month([日期]) from dbo.入库表 where year([日期])="+string+" group by month([日期])";
				       // String sql1 = "select sum(数量),month([日期]) from dbo.出库表 where year([日期])="+string+" group by month([日期])";
				     
				        ResultSet rs = statement.executeQuery(sql);
				        while(rs.next()) {
				        	jTextArea.append("月份；"+rs.getString(2)+",  数量；"+rs.getString(1)+"\n");
				        }
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		btnNewButton.setBounds(383, 86, 113, 27);
		panel_2.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.setBounds(519, 86, 113, 27);
		panel_2.add(btnNewButton_1);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"\u51FA\u5E93\u8868", "\u5165\u5E93\u8868"}));
		comboBox.setBounds(14, 87, 106, 24);
		panel_2.add(comboBox);

	}
}
