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

public class ������ʾ extends JFrame {

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
					������ʾ frame = new ������ʾ();
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
	public ������ʾ() {
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
				  String dbURL="jdbc:sqlserver://127.0.0.1:1433;DatabaseName=������ʹ���ϵͳ";//testΪ������ݿ���
				  String userName="sa";//������ݿ��û���
				  String userPwd="123";//�������
				 try
				{
				    Class.forName(driverName);
				    System.out.println("���������ɹ���");
				}catch(Exception e1){
				    e1.printStackTrace();
				    System.out.println("��������ʧ�ܣ�");
				}
				try{
				    
					Connection dbConn=DriverManager.getConnection(dbURL,userName,userPwd);
				        System.out.println("�������ݿ�ɹ���");
				        Statement statement = dbConn.createStatement();
				        String sql = "select * from dbo.����Ա";
				        ResultSet rs = statement.executeQuery(sql);
				     String mima=passwordField.getText();
				     int i=0;
				     while (rs.next()) {
				    	 String user =rs.getString("�û���");
				    	 String id =rs.getString("����");
				    	 user=user.trim();
						if (textField.getText().equals(user)) {
							i++;
							break;
						}
						
					}
				     if(mima.equals(rs.getString("����").trim())&&i!=0) {
							��� frame = new ���();
							frame.setVisible(true);

				
							dispose();
				     }
				        //rs.next();
				}catch(Exception e1)
				{
				    e1.printStackTrace();
				    System.out.print("SQL Server����ʧ�ܣ�");
				}        
			}
		});
		btnNewButton.setBounds(142, 185, 113, 27);
		contentPane.add(btnNewButton);
	}
}
