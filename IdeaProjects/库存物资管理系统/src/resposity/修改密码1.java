package ������ʹ���ϵͳ;

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

public class �޸�����1 extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	ImageIcon background;
	JPanel myPanel;
	JLabel label;//���ڷű�ǩ
	JLabel label2;
	JButton button;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					�޸�����1 frame = new �޸�����1();
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
	public �޸�����1() {
		init();		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		
	}
	void init(){
	

		background = new ImageIcon("����.jpeg");	//����һ������ͼƬ
		label = new JLabel(background);		//�ѱ���ͼƬ��ӵ���ǩ��
		label.setBounds(0, 0, background.getIconWidth(), background.getIconHeight());	//�ѱ�ǩ����Ϊ��ͼƬ�ȸߵȿ�
		myPanel = (JPanel)this.getContentPane();		//���ҵ��������Ϊ�������
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u7528\u6237\u540D");
		lblNewLabel.setFont(new Font("����", Font.PLAIN, 20));
		lblNewLabel.setBounds(63, 76, 72, 18);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u65B0\u5BC6\u7801");
		lblNewLabel_1.setFont(new Font("����", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(56, 160, 92, 18);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("\u786E\u8BA4\u5BC6\u7801");
		lblNewLabel_2.setFont(new Font("����", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(39, 240, 85, 18);
		getContentPane().add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("\u4FEE\u6539");
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
		textField.setFont(new Font("����", Font.PLAIN, 18));
		textField.setBounds(198, 73, 218, 24);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("����", Font.PLAIN, 18));
		textField_1.setBounds(198, 159, 218, 24);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("����", Font.PLAIN, 18));
		textField_2.setBounds(198, 239, 218, 24);
		getContentPane().add(textField_2);
		textField_2.setColumns(10);
		myPanel.setOpaque(false);
		this.getLayeredPane().setLayout(null);		//�ѷֲ����Ĳ����ÿ�						
		
		this.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));		//�ѱ�ǩ��ӵ��ֲ�������ײ�
		//���ý�������
		setTitle("\u4FEE\u6539\u5BC6\u7801");
		setBounds(800, 100, 552, 472);
	   }
}
