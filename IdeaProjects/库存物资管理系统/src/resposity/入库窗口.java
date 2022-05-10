package 库存物资管理系统;

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
import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class 入库窗口 extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					入库窗口 frame = new 入库窗口();
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
	JFrame frame;
	JLabel lblNewLabel_9;
	JLabel lblNewLabel_10;
	private JTextField textField_8;
	public 入库窗口() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1288, 663);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u5165\u5E93\u4FE1\u606F");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 36));
		lblNewLabel.setForeground(Color.CYAN);
		lblNewLabel.setBounds(398, 13, 160, 33);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u540D\u79F0\uFF1A");
		lblNewLabel_1.setBounds(40, 82, 72, 18);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("\u751F\u4EA7\u5382\u5BB6\uFF1A");
		lblNewLabel_2.setBounds(40, 136, 85, 18);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("\u578B\u53F7\uFF1A");
		lblNewLabel_3.setBounds(40, 194, 72, 18);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("\u89C4\u683C\uFF1A");
		lblNewLabel_4.setBounds(40, 249, 72, 18);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("\u6570\u91CF\uFF1A");
		lblNewLabel_5.setBounds(40, 306, 72, 18);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("\u5165\u5E93\u65F6\u95F4\uFF1A");
		lblNewLabel_6.setBounds(40, 523, 85, 18);
		contentPane.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("\u9001\u8D27\u5458\uFF1A");
		lblNewLabel_7.setBounds(40, 424, 72, 18);
		contentPane.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("\u5355\u4F4D\u540D\u79F0\uFF1A");
		lblNewLabel_8.setBounds(40, 483, 85, 18);
		contentPane.add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("");
		lblNewLabel_9.setForeground(Color.RED);
		lblNewLabel_9.setBounds(512, 82, 217, 18);
		contentPane.add(lblNewLabel_9);
		
		textField = new JTextField();
		textField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				if ("".equals(textField.getText())) {
					lblNewLabel_9.setText("商品名称不能为空哦！");
					lblNewLabel_9.setForeground(Color.red);
				}
			}
			@Override
			public void focusGained(FocusEvent arg0) {
				lblNewLabel_9.setText(null);
			}
		});
		
		textField.setBounds(178, 79, 320, 24);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(178, 133, 320, 24);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(178, 191, 320, 24);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(178, 246, 320, 24);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblNewLabel_10 = new JLabel("");
		lblNewLabel_10.setForeground(Color.RED);
		lblNewLabel_10.setBounds(507, 307, 197, 18);
		contentPane.add(lblNewLabel_10);
		
		textField_4 = new JTextField();
		textField_4.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				if ("".equals(textField.getText())) {
					lblNewLabel_10.setText("数量不能为空哦！");
					lblNewLabel_10.setForeground(Color.red);
				}
				
			}
			@Override
			public void focusGained(FocusEvent arg0) {
				lblNewLabel_10.setText(null);
			}
		});
		textField_4.setBounds(178, 303, 320, 24);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setBounds(178, 520, 320, 24);
		contentPane.add(textField_5);
		textField_5.setColumns(10);
		
		textField_6 = new JTextField();
		textField_6.setBounds(178, 421, 320, 24);
		contentPane.add(textField_6);
		textField_6.setColumns(10);
		
		textField_7 = new JTextField();
		textField_7.setBounds(178, 480, 320, 24);
		contentPane.add(textField_7);
		textField_7.setColumns(10);
		
		JButton button = new JButton("\u786E\u5B9A");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				{
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
						if(!"".equals(textField.getText()) && !"".equals(textField_4.getText()) && !"".equals(textField_4.getText())){
						Connection dbConn=DriverManager.getConnection(dbURL,userName,userPwd);
					        System.out.println("连接数据库成功！");
					        Statement statement = dbConn.createStatement();
					        String sql = "insert into dbo.入库表(名称,生产厂家,型号,规格,数量,时间,送货人,入库单位,日期)"+"values(?,?,?,?,?,?,?,?,?)";

					        PreparedStatement rs = dbConn.prepareStatement(sql);
					        if(textField.getText().equals(null)) {
					        	lblNewLabel_9.setText("物品名称不能为空哦！");
					        	lblNewLabel_9.setForeground(Color.RED);
					        }else if(textField_4.getText().equals(null)) {
					        	lblNewLabel_10.setText("物品数量不能为空！");
					        	lblNewLabel_10.setForeground(Color.RED);
					        }else {
					        rs.setString(1, textField.getText());
					        rs.setString(2, textField_1.getText());
					        rs.setString(3, textField_2.getText());
					        rs.setString(4, textField_3.getText());
					        rs.setString(5, textField_4.getText());
					        rs.setString(6, textField_5.getText());
					        rs.setString(7, textField_6.getText());
					        rs.setString(8, textField_7.getText());
					        rs.setString(9, textField_8.getText());
					        rs.execute();
					        

					        String sql2 = "select * from dbo.商品";
					        ResultSet rs2 = statement.executeQuery(sql2);
					        
					        int i=0;
					        while(rs2.next()) {
					        	if(textField.getText().equals(rs2.getString("名称"))) {
					        		i++;
					        		break;
					        	}
					        }
					        if(i!=0) {
						        int sum;
						        sum = Integer.parseInt(rs2.getString("数量"))+Integer.parseInt(textField_4.getText());
					            String sql3 = "UPDATE dbo.商品 set 数量=? where 名称=?";
					            PreparedStatement rs3 = dbConn.prepareStatement(sql3);
					            rs3.setInt(1, sum);
					            rs3.setString(2, textField.getText());
					            rs3.executeUpdate();
					            JOptionPane.showMessageDialog(null, "库存成功","提示！",JOptionPane.WARNING_MESSAGE);
					        }else {
					        	String sql4 = "insert into dbo.商品(名称,生产厂家,型号,规格,数量)"+"values(?,?,?,?,?)";
					        	PreparedStatement rs4 = dbConn.prepareStatement(sql4);
					        	rs4.setString(1, textField.getText());
						        rs4.setString(2, textField_1.getText());
						        rs4.setString(3, textField_2.getText());
						        rs4.setString(4, textField_3.getText());
						        rs4.setString(5, textField_4.getText());
						        rs4.execute();
						        JOptionPane.showMessageDialog(null, "库存成功","error!",JOptionPane.WARNING_MESSAGE);
					        }
					        }
					}
						}
					catch(Exception e1)
					{
					    e1.printStackTrace();
					    System.out.print("SQL Server连接失败！");
					}
					
					}
			}
		});
		button.setBounds(742, 190, 113, 27);
		contentPane.add(button);
		
		JButton button_1 = new JButton("\u53D6\u6D88");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 dispose();
			}
		});
		button_1.setBounds(742, 382, 113, 27);
		contentPane.add(button_1);
		
		JLabel label = new JLabel("\u65E5\u671F\uFF1A");
		label.setBounds(40, 362, 72, 18);
		contentPane.add(label);
		
		textField_8 = new JTextField();
		textField_8.setBounds(178, 359, 320, 24);
		contentPane.add(textField_8);
		textField_8.setColumns(10);
		
;
		
		
	}
}
