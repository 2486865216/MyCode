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

public class �޸����� extends JFrame {

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
					�޸����� frame = new �޸�����();
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
	public �޸�����() {
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
				      
				        String user =textField.getText();
				        String id =textField_1.getText();
			
				  
				        int k=0;
				        int i=0;
				       while (rs.next()) {
//				    	   
				    	   String string = rs.getString("�û���");
				    	   string = string.trim();
				    	   if(textField.getText().equals(string)) {
				    		   i++;
				    		   break;
				    	   }
				    	   }
				    	   if(i!=0) {
				    		   String sql2 = "UPDATE dbo.����Ա set ����=? where �û���=?";
					            PreparedStatement rs3 = dbConn.prepareStatement(sql2);
					            if (textField_1.getText().equals(textField_2.getText())) {
					            	rs3.setString(2, rs.getString("�û���"));
						            rs3.setString(1, textField_1.getText());
						           // rs3.setString(2, rs.getString("�û���"));
						            rs3.executeUpdate();
						            JOptionPane.showMessageDialog(null, "�޸ĳɹ���","tishi", JOptionPane.WARNING_MESSAGE);
						            dispose();
								}
					            else {
					            	JOptionPane.showMessageDialog(null, "�������벻��ͬ���޸�ʧ��","tishi", JOptionPane.WARNING_MESSAGE);
					            	
								}
//					            rs3.setString(2, rs.getString("�û���"));
//					            rs3.setString(1, textField_1.getText());
//					           // rs3.setString(2, rs.getString("�û���"));
//					            rs3.executeUpdate();
//					            JOptionPane.showMessageDialog(null, "�޸ĳɹ���","tishi", JOptionPane.WARNING_MESSAGE);
				    	   }else {
				    		   JOptionPane.showMessageDialog(null, "���û������ڣ�","tishi", JOptionPane.WARNING_MESSAGE);
				    	   }
//				    dispose();
//				     
				}catch(Exception e1)
				{
				    e1.printStackTrace();
				    System.out.print("SQL Server����ʧ�ܣ�");
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
