package resposity;
import java.sql.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;




public class RuKu extends JFrame {
	//private static SimpleDateFormat sdf =new SimpleDateFormat("yyyy-mm-dd");
	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	
	
	
	JTextArea textArea;
	private JTable table;
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RuKu frame = new RuKu();
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
	public RuKu() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1008, 524);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JButton button = new JButton("\u5165\u5E93");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 ruku = new ��ⴰ��();
				ruku.setVisible(true);
			}
		});
		button.setBounds(259, 312, 113, 27);
		panel.add(button);
		
		JButton button_1 = new JButton("\u51FA\u5E93");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				���ⴰ�� chuku = new ���ⴰ��();
				chuku.setVisible(true);
			}
		});
		button_1.setBounds(438, 312, 113, 27);
		panel.add(button_1);
		
		textField = new JTextField();
		textField.setBounds(166, 383, 304, 24);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel label = new JLabel("\u8BF7\u8F93\u5165\u8981\u67E5\u8BE2\u7684\u4FE1\u606F\uFF1A");
		label.setBounds(14, 386, 200, 18);
		panel.add(label);
		
		
		
		JTextArea textArea = new JTextArea();
//		scrollPane.setViewportView(textArea);
		
		table = new JTable();
		table.setBounds(14, 258, 952, -243);
		panel.add(table);
		
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"\u5546\u54C1", "\u51FA\u5E93\u8868", "\u5165\u5E93\u8868"}));
		comboBox.setBounds(42, 313, 146, 27);
		panel.add(comboBox);
		
		JButton button_2 = new JButton("\u540D\u79F0\u67E5\u8BE2");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				{
					String[] col = { "����", "��������", "���","�ͺ�","����",};
					String[] rukubiao= {"����","��������","�ͺ�","���","����","����","ʱ��","��ⵥλ","�ͻ���"};
					String[] chukubiao= {"����","��������","�ͺ�","���","����","����","ʱ��","���ⵥλ","�ͻ���"};
					DefaultTableModel mm= new DefaultTableModel(col,0); //����һ�����ģ��
					DefaultTableModel ruku = new DefaultTableModel(rukubiao,0);
					DefaultTableModel chuku = new DefaultTableModel(chukubiao,0);
					  String driverName="com.microsoft.sqlserver.jdbc.SQLServerDriver";
					  String dbURL="jdbc:sqlserver://127.0.0.1:1433;DatabaseName=������ʹ���ϵͳ";//testΪ������ݿ���
					  String userName="sa";//������ݿ��û���
					  String userPwd="123";//�������
					 try
					{
					    Class.forName(driverName);
					    System.out.println("���������ɹ���");
					}catch(Exception e){
					    e.printStackTrace();
					    System.out.println("��������ʧ�ܣ�");
					}
					try{
					    
						Connection dbConn=DriverManager.getConnection(dbURL,userName,userPwd);
					        System.out.println("�������ݿ�ɹ���");
					        String string2=comboBox.getSelectedItem().toString();
					        String string=textField.getText();
					        Statement statement = dbConn.createStatement();
					        String sql = "select * from dbo."+string2;
					        ResultSet rs = statement.executeQuery(sql);
					        int i=0;
					        string = string.trim();
					        while(rs.next()) {
					        	
					        	
					        	if(rs.getString("����").equals(string)){
					        		i++;
					        		if(string2.equals("��Ʒ")) {
						        		
						        		String id = rs.getString("����");
						        		String shengchanchangjia =rs.getString("��������");
						        		String guige = rs.getString("���");
						        		String xinghao = rs.getString("�ͺ�");
						        		String shuliang = rs.getString("����");
						        		
						        		String[] str_row = {id,shengchanchangjia,guige,xinghao,shuliang};
						        		 //��һ�е����ݴ���str_row �ַ���������
						      	        mm.addRow(str_row);//����ڱ�ģ����
						        		table.setModel(mm);

						        	}
						        	else if(string2.equals("����")) {
						        		String id = rs.getString("����");
						        		String shengchanchangjia =rs.getString("��������");
						        		String guige = rs.getString("���");
						        		String xinghao = rs.getString("�ͺ�");
						        		String shuliang = rs.getString("����");
						        		String riqi = rs.getString("����");
						        		String shijian = rs.getString("ʱ��");
						        		String rukudanwei = rs.getString("��ⵥλ");
						        		String songhuoren = rs.getString("�ͻ���");
						        		String[] row_2 = {id,shengchanchangjia,xinghao,guige,shuliang,riqi,shijian,rukudanwei,songhuoren};
						        		ruku.addRow(row_2);
						        		table.setModel(ruku);

						        	}
						        	
						        	else if(string2.equals("�����")){
						        		String id = rs.getString("����");
						        		String shengchanchangjia =rs.getString("��������");
						        		String guige = rs.getString("���");
						        		String xinghao = rs.getString("�ͺ�");
						        		String shuliang = rs.getString("����");
						        		String riqi = rs.getString("����");
						        		String shijian = rs.getString("ʱ��");
						        		String rukudanwei = rs.getString("���ⵥλ");
						        		String songhuoren = rs.getString("�ͻ���");
						        		String[] row_3 = {id,shengchanchangjia,xinghao,guige,shuliang,riqi,shijian,rukudanwei,songhuoren};
						        		chuku.addRow(row_3);
						        		table.setModel(chuku);
				    
						        	}
					        		
					        		
					        	}
					        	
					        	}
					        if (i==0) {
					        	JOptionPane.showMessageDialog(null, "�ֿ���û�и���Ʒ��","error!",JOptionPane.WARNING_MESSAGE);
							}
					        	 
					        	}
					catch(Exception e)
					{
					    e.printStackTrace();
					    System.out.print("SQL Server����ʧ�ܣ�");
					}        
					}
			
			}

		});
		button_2.setBounds(519, 382, 113, 27);
		panel.add(button_2);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(603, 316, 294, 18);
		panel.add(lblNewLabel);
		
		JButton button_5 = new JButton("\u65E5\u671F\u67E5\u8BE2");
		button_5.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
				 String[] rukubiao1= {"����","��������","�ͺ�","���","����","����","ʱ��","��ⵥλ","�ͻ���"};
			        DefaultTableModel rukubiao11= new DefaultTableModel(rukubiao1,0); //����һ�����ģ��
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
				 try {
					 Connection dbConn=DriverManager.getConnection(dbURL,userName,userPwd);
				        System.out.println("�������ݿ�ɹ���");
				        Statement statement = dbConn.createStatement();
				        String string2 =textField.getText();
				        string2=string2.trim();
				        String string3;
				        String string4;
				        String string5;
				        String string1=comboBox.getSelectedItem().toString();
				        String sql;
				        if(string2.length()==4) {
				        	string3 = string2.substring(0,4);
				        	sql = "select*from dbo."+string1+"  where year([����]) = "+string3;
				        }else {
				        	if(string2.length()==7) {
				        		string3 = string2.substring(0,4);
				        		string4 = string2.substring(5,7);
				        		sql = "select*from dbo."+string1+"  where year([����]) = "+string3+" and month([����])="+string4;
				        	}else {
				        		string3 = string2.substring(0,4);
				        		string4 = string2.substring(5,7);
				        		string5 = string2.substring(8,10);
				        		sql = "select*from dbo."+string1+"  where year([����]) = "+string3+" and month([����])="+string4+" and day ([����])="+string5 ;
				        	}
				        }
				        ResultSet rs = statement.executeQuery(sql);
				        	int m=0;
				        	String string = null;
				        	int i=0;
					       while(rs.next()) {
						       		if(string1.equals("����"))	{
						       			string ="��";
						        		String id1 = rs.getString("����");
							        	String shengchanchangjia1 =rs.getString("��������");
							        	String guige1 = rs.getString("���");
							        	String xinghao1 = rs.getString("�ͺ�");
							        	String shuliang1= rs.getString("����");
							        	m=m+Integer.valueOf(shuliang1);
							        	String riqi = rs.getString("����");
							        	String shijian = rs.getString("ʱ��");
							        	String rukudanwei = rs.getString("��ⵥλ");
							        	String songhuoren = rs.getString("�ͻ���");
							        		
							        		String[] str_row1 = {id1,shengchanchangjia1,guige1,xinghao1,shuliang1,riqi,shijian,rukudanwei,songhuoren};
							        		 //��һ�е����ݴ���str_row �ַ���������
							        		
							        		rukubiao11.addRow(str_row1);//����ڱ�ģ����
						        
							        		
							        		table.setModel(rukubiao11);
							        		i++;
							        	
						        		}
						       		else if(string1.equals("�����"))	{
						       			string ="��";
					        			String id1 = rs.getString("����");
						        		String shengchanchangjia1 =rs.getString("��������");
						        		String guige1 = rs.getString("���");
						        		String xinghao1 = rs.getString("�ͺ�");
						        		String shuliang1= rs.getString("����");
						        		m=m+Integer.valueOf(shuliang1);
						        		String riqi = rs.getString("����");
						        		String shijian = rs.getString("ʱ��");
						        		String rukudanwei = rs.getString("���ⵥλ");
						        		String songhuoren = rs.getString("�ͻ���");
						        		
						        		String[] str_row2 = {id1,shengchanchangjia1,guige1,xinghao1,shuliang1,riqi,shijian,rukudanwei,songhuoren};
						        		 //��һ�е����ݴ���str_row �ַ���������
						        		
						        		rukubiao11.addRow(str_row2);//����ڱ�ģ����
					        
						        		
						        		table.setModel(rukubiao11);
						        	
					        		}
					       }
					       if(string2.length()==4) {
					    	   lblNewLabel.setText("�����"+string+"����Ϊ��"+m);
					        }else {
					        	if(string2.length()==7) {
					        		lblNewLabel.setText("���µ�"+string+"����Ϊ��"+m);
					        	}else {
					        		lblNewLabel.setText("���յ�"+string+"����Ϊ��"+m);
					        	}
					        }
					       if (i==0) {
					    	   JOptionPane.showMessageDialog(null, "���ڲ�����","error!",JOptionPane.WARNING_MESSAGE);
					       }
				     } catch (Exception e2) {
					// TODO: handle exception
				
				    	 JOptionPane.showMessageDialog(null, "����ѡ������������в�ѯ��","error!",JOptionPane.WARNING_MESSAGE);
				     
				     }
			}

			private char[] getType(String string1) {
				// TODO Auto-generated method stub
				return null;
			}
		});
		button_5.setBounds(642, 382, 113, 27);
		panel.add(button_5);
		
		JScrollPane scrollPane=new JScrollPane(table);
		scrollPane.setBounds(14, 13, 952, 254);
		panel.add(scrollPane);
		
	}
}
