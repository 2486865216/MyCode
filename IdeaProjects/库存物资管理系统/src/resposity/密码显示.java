package resposity;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JButton;

public class 密码显示 extends JFrame {

	private JPanel contentPane;
	private JPasswordField passwordField;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					密码显示 frame = new 密码显示();
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
	public 密码显示() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(116, 120, 139, 24);
		contentPane.add(passwordField);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("\u663E\u793A");
		chckbxNewCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(chckbxNewCheckBox.isSelected())   

					passwordField.setEchoChar('\0');   

					else  

						passwordField.setEchoChar( '*'); 
			}
		});
		chckbxNewCheckBox.setBounds(265, 119, 133, 27);
		contentPane.add(chckbxNewCheckBox);
		
		textField = new JTextField();
		textField.setBounds(124, 64, 133, 24);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("\u767B\u5F55");
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
				     String mima=passwordField.getText();
				     int i=0;
				     while (rs.next()) {
				    	 String user =rs.getString("用户名");
				    	 String id =rs.getString("密码");
				    	 user=user.trim();
						if (textField.getText().equals(user)) {
							i++;
							break;
						}
						
					}
				     if(mima.equals(rs.getString("密码").trim())&&i!=0) {
							入库 frame = new 入库();
							frame.setVisible(true);

				
							dispose();
				     }
				        //rs.next();
				}catch(Exception e1)
				{
				    e1.printStackTrace();
				    System.out.print("SQL Server连接失败！");
				}        
			}
		});
		btnNewButton.setBounds(142, 185, 113, 27);
		contentPane.add(btnNewButton);
	}
}
