package resposity;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



import java.awt.FlowLayout;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;
import java.awt.Window.Type;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JPasswordField;
import javax.swing.JToggleButton;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;

public class 登录页面 extends JFrame {
	ImageIcon background;
	JPanel myPanel;
	JLabel label;//用于放标签
	JLabel label2;
	JButton button;
	private JTextField textField;
	private JPasswordField passwordField;
//	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new 登录页面();
					//new Background();
				//	frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public 登录页面() {
		setTitle("\u5E93\u5B58\u7BA1\u7406\u7CFB\u7EDF");

		init();		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	//	this.setBounds(60,100,188,108);
		this.setVisible(true);
	}
	
void init(){


	background = new ImageIcon("11.png");	//创建一个背景图片
	label = new JLabel(background);		//把背景图片添加到标签里
	label.setBounds(0, 0, background.getIconWidth(), background.getIconHeight());	//把标签设置为和图片等高等宽
	myPanel = (JPanel)this.getContentPane();
	getContentPane().setLayout(null);
	
	JLabel lblNewLabel = new JLabel("\u7528\u6237\u540D");
	lblNewLabel.setBounds(191, 125, 72, 22);
	lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));
	getContentPane().add(lblNewLabel);
	
	textField = new JTextField();
	textField.setBounds(322, 123, 174, 24);
	textField.setFont(new Font("楷体", Font.PLAIN, 18));
	textField.addFocusListener(new FocusAdapter() {
		@Override
		public void focusLost(FocusEvent arg0) {
			if(textField.getText().equals(null)) {
			}
		}
	});
	getContentPane().add(textField);
	textField.setColumns(10);
	
	JButton btnNewButton = new JButton("\u767B\u5F55");
	btnNewButton.setBounds(313, 320, 113, 27);
	btnNewButton.addFocusListener(new FocusAdapter() {
		@Override
		public void focusGained(FocusEvent arg0) {
		}

		@Override
		public void focusLost(FocusEvent e) {
			String user =textField.getText();
			if (user.equals(null)) {
				JOptionPane.showMessageDialog(null, "用户名不能为空!");
				return;
			}
		}
	});
	btnNewButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
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
			        Statement statement = dbConn.createStatement();
			        String sql = "select * from dbo.管理员";
			        ResultSet rs = statement.executeQuery(sql);
					String 密码=passwordField.getText();
					int k=0;
					int i=0;
					while (rs.next()) {
						String string = rs.getString("用户名");
						String string2= rs.getString("密码");
				    	   string = string.trim();
				    	   if(textField.getText().equals(string)) {
				    		   i++;
				    		   break;
				    	   }
				    	   
						}
					if(i==0) {
						JOptionPane.showMessageDialog(null, "该用户不存在！","error!",JOptionPane.WARNING_MESSAGE);
					}
					if(密码.equals(rs.getString("密码").trim())&&i!=0) {
						入库 frame = new 入库();
						frame.setVisible(true);

			
					//	dispose();
					}else {
						JOptionPane.showMessageDialog(null, "密码错误！","error!",JOptionPane.WARNING_MESSAGE);
					}

			        
			}catch(Exception e)
			{
			    e.printStackTrace();
			    System.out.print("SQL Server连接失败！");
			}        
			}
	
		
	});
	getContentPane().add(btnNewButton);
	
	JLabel lblNewLabel_1 = new JLabel("\u5BC6\u7801");
	lblNewLabel_1.setBounds(203, 197, 72, 18);
	lblNewLabel_1.setFont(new Font("微软雅黑", Font.PLAIN, 20));
	getContentPane().add(lblNewLabel_1);
	
	JLabel lblNewLabel_2 = new JLabel("\u767B\u5F55\u9875\u9762");
	lblNewLabel_2.setBounds(317, 42, 216, 29);
	lblNewLabel_2.setFont(new Font("楷体", Font.PLAIN, 30));
	lblNewLabel_2.setForeground(Color.GRAY);
	getContentPane().add(lblNewLabel_2);
	
	JLabel lblNewLabel_3 = new JLabel("\u5E93\u5B58\u7BA1\u7406\u7CFB\u7EDF");
	lblNewLabel_3.setBounds(554, 426, 191, 31);
	lblNewLabel_3.setForeground(Color.DARK_GRAY);
	lblNewLabel_3.setFont(new Font("楷体", Font.PLAIN, 30));
	getContentPane().add(lblNewLabel_3);
	
	JButton btnNewButton_2 = new JButton("\u4FEE\u6539\u5BC6\u7801");
	btnNewButton_2.setBounds(150, 318, 113, 30);
	btnNewButton_2.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			修改密码1 frame = new 修改密码1();
			frame.setVisible(true);
		}
	});
	btnNewButton_2.setFont(new Font("方正粗黑宋简体", Font.PLAIN, 15));
	getContentPane().add(btnNewButton_2);
	
	JButton btnNewButton_1 = new JButton("\u9000\u51FA");
	btnNewButton_1.setBounds(492, 320, 113, 27);
	btnNewButton_1.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			dispose();
		}
	});
	getContentPane().add(btnNewButton_1);
	
	passwordField = new JPasswordField();
	passwordField.setBounds(322, 197, 174, 24);
	getContentPane().add(passwordField);
	
	JCheckBox chckbxNewCheckBox = new JCheckBox("\u663E\u793A\u5BC6\u7801");
	chckbxNewCheckBox.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			if(chckbxNewCheckBox.isSelected())   

				passwordField.setEchoChar('\0');   

				else  

					passwordField.setEchoChar( '*'); 
		}
		
	});
	chckbxNewCheckBox.setBounds(506, 196, 82, 27);
	getContentPane().add(chckbxNewCheckBox);
	myPanel.setOpaque(false);
	this.getLayeredPane().setLayout(null);		//把分层面板的布局置空						

	this.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));		//把标签添加到分层面板的最底层
	//设置界面属性
	
	setBounds(400, 100, 763, 504);
   }
}
