package resposity;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class 修改密码 extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					修改密码 frame = new 修改密码();
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
	public 修改密码() {
		setTitle("\u91CD\u7F6E\u5BC6\u7801");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 478, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u7528\u6237\u540D");
		lblNewLabel.setBounds(86, 54, 72, 18);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u65B0\u5BC6\u7801");
		lblNewLabel_1.setBounds(86, 134, 72, 18);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("\u786E\u8BA4\u5BC6\u7801");
		lblNewLabel_2.setBounds(74, 215, 72, 18);
		contentPane.add(lblNewLabel_2);
		
		textField = new JTextField();
		textField.setBounds(156, 51, 178, 24);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(156, 134, 178, 24);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(156, 212, 178, 24);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnNewButton = new JButton("\u786E\u8BA4");
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
				try{
				    
					Connection dbConn=DriverManager.getConnection(dbURL,userName,userPwd);
				        System.out.println("连接数据库成功！");
				        Statement statement = dbConn.createStatement();
				        String sql = "select * from dbo.管理员";
				        
				        
				        
				        
				        ResultSet rs = statement.executeQuery(sql);
				      
				        String user =textField.getText();
				        String id =textField_1.getText();
			
				  
				        int k=0;
				        int i=0;
				       while (rs.next()) {
//				    	   
				    	   String string = rs.getString("用户名");
				    	   string = string.trim();
				    	   if(textField.getText().equals(string)) {
				    		   i++;
				    		   break;
				    	   }
				    	   }
				    	   if(i!=0) {
				    		   String sql2 = "UPDATE dbo.管理员 set 密码=? where 用户名=?";
					            PreparedStatement rs3 = dbConn.prepareStatement(sql2);
					            if (textField_1.getText().equals(textField_2.getText())) {
					            	rs3.setString(2, rs.getString("用户名"));
						            rs3.setString(1, textField_1.getText());
						           // rs3.setString(2, rs.getString("用户名"));
						            rs3.executeUpdate();
						            JOptionPane.showMessageDialog(null, "修改成功！","tishi", JOptionPane.WARNING_MESSAGE);
						            dispose();
								}
					            else {
					            	JOptionPane.showMessageDialog(null, "两次密码不相同，修改失败","tishi", JOptionPane.WARNING_MESSAGE);
					            	
								}
//					            rs3.setString(2, rs.getString("用户名"));
//					            rs3.setString(1, textField_1.getText());
//					           // rs3.setString(2, rs.getString("用户名"));
//					            rs3.executeUpdate();
//					            JOptionPane.showMessageDialog(null, "修改成功！","tishi", JOptionPane.WARNING_MESSAGE);
				    	   }else {
				    		   JOptionPane.showMessageDialog(null, "该用户不存在！","tishi", JOptionPane.WARNING_MESSAGE);
				    	   }
//				    dispose();
//				     
				}catch(Exception e1)
				{
				    e1.printStackTrace();
				    System.out.print("SQL Server连接失败！");
				}        
				}
			
			
		});
		btnNewButton.setBounds(46, 263, 113, 27);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("\u53D6\u6D88");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				    dispose();

			}
		});
		btnNewButton_1.setBounds(258, 263, 113, 27);
		contentPane.add(btnNewButton_1);
	}
}
