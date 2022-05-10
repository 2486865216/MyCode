package 库存物资管理系统;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class 修改密码1 extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	ImageIcon background;
	JPanel myPanel;
	JLabel label;//用于放标签
	JLabel label2;
	JButton button;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					修改密码1 frame = new 修改密码1();
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
	public 修改密码1() {
		init();		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		
	}
	void init(){
	

		background = new ImageIcon("密码.jpeg");	//创建一个背景图片
		label = new JLabel(background);		//把背景图片添加到标签里
		label.setBounds(0, 0, background.getIconWidth(), background.getIconHeight());	//把标签设置为和图片等高等宽
		myPanel = (JPanel)this.getContentPane();		//把我的面板设置为内容面板
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u7528\u6237\u540D");
		lblNewLabel.setFont(new Font("楷体", Font.PLAIN, 20));
		lblNewLabel.setBounds(63, 76, 72, 18);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u65B0\u5BC6\u7801");
		lblNewLabel_1.setFont(new Font("楷体", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(56, 160, 92, 18);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("\u786E\u8BA4\u5BC6\u7801");
		lblNewLabel_2.setFont(new Font("楷体", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(39, 240, 85, 18);
		getContentPane().add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("\u4FEE\u6539");
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
		btnNewButton.setBounds(74, 320, 113, 27);
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("\u53D6\u6D88");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnNewButton_1.setBounds(323, 320, 113, 27);
		getContentPane().add(btnNewButton_1);
		
		textField = new JTextField();
		textField.setFont(new Font("楷体", Font.PLAIN, 18));
		textField.setBounds(198, 73, 218, 24);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("楷体", Font.PLAIN, 18));
		textField_1.setBounds(198, 159, 218, 24);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("楷体", Font.PLAIN, 18));
		textField_2.setBounds(198, 239, 218, 24);
		getContentPane().add(textField_2);
		textField_2.setColumns(10);
		myPanel.setOpaque(false);
		this.getLayeredPane().setLayout(null);		//把分层面板的布局置空						
		
		this.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));		//把标签添加到分层面板的最底层
		//设置界面属性
		setTitle("\u4FEE\u6539\u5BC6\u7801");
		setBounds(800, 100, 552, 472);
	   }
}
