package resposity;
import java.sql.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.TextArea;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.CardLayout;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;




public class RuKuTest extends JFrame {

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
	public RuKuTest() {
		JTable jtable = new JTable();
		JScrollPane jscrollpane = new JScrollPane(jtable);//�����Ӹ�������
		
		
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
				RuKuTest ruku = new RuKuTest();
				ruku.setVisible(true);
			}
		});
		button.setBounds(259, 312, 113, 27);
		panel.add(button);
		
		JButton button_1 = new JButton("\u51FA\u5E93");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 chuku = new ���ⴰ��();
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
		
		
		
		table = new JTable();
		table.setBounds(0, 265, 966, -264);
		panel.add(table);
		
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"\u5546\u54C1", "\u51FA\u5E93\u8868", "\u5165\u5E93\u8868"}));
		comboBox.setBounds(42, 313, 146, 27);
		panel.add(comboBox);
		
		JButton button_2 = new JButton("��ѯ");
		button_2.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				{
					String[] col = { "ѧ��", "����" };
					DefaultTableModel mm= new DefaultTableModel(col,0); //����һ�����ģ��
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
					        		
					        		break;
					        	}
					        	}
					        	if(i!=0) {
					        	if(string2.equals("��Ʒ")) {
//					        	textArea.append("���ƣ�"+rs.getString("����")+"\n");
//					        	textArea.append("�������ң�"+rs.getString("��������")+"\n");
//					        	textArea.append("�ͺţ�"+rs.getString("�ͺ�")+"\n");
//					        	textArea.append("���"+rs.getString("���")+"\n");
//					        	textArea.append("������"+rs.getString("����")+"\n");
					        		System.out.println("123");
					        		String id = rs.getString("����");
					        		String shengchanchangjia =rs.getString("��������");		
					        		String[] str_row = {id,shengchanchangjia};
					        		 //��һ�е����ݴ���str_row �ַ���������
					      	        mm.addRow(str_row);//����ڱ�ģ����
					        		table.setModel(mm);
//					        		textArea_1.add(table);
					        	}
					        	else if(string2.equals("����")) {
//					        	textArea_1.append("���ƣ�"+rs.getString("����")+"\n");
//					        	textArea_1.append("�������ң�"+rs.getString("��������")+"\n");
//					        	textArea_1.append("�ͺţ�"+rs.getString("�ͺ�")+"\n");
//					        	textArea_1.append("���"+rs.getString("���")+"\n");
//					        	textArea_1.append("������"+rs.getString("����")+"\n");
//					        	textArea_1.append("���ڣ�"+rs.getString("����")+"\n");
//					        	textArea_1.append("ʱ�䣺"+rs.getString("ʱ��")+"\n");
//					        	textArea_1.append("[��ⵥΪ������ⵥλ��]��"+rs.getString("��ⵥλ")+"\n");
//					        	textArea_1.append("[�ͻ����������������]��"+rs.getString("�ͻ���")+"\n");
					        	}
					        	
					        	else if(string2.equals("�����")){
//					        		textArea_1.append("���ƣ�"+rs.getString("����")+"\n");
//						        	textArea_1.append("�������ң�"+rs.getString("��������")+"\n");
//						        	textArea_1.append("�ͺţ�"+rs.getString("�ͺ�")+"\n");
//						        	textArea_1.append("���"+rs.getString("���")+"\n");
//						        	textArea_1.append("������"+rs.getString("����")+"\n");
//						        	textArea_1.append("���ڣ�"+rs.getString("����")+"\n");
//						        	textArea_1.append("ʱ�䣺"+rs.getString("ʱ��")+"\n");
//						        	textArea_1.append("[��ⵥΪ������ⵥλ��]��"+rs.getString("���ⵥλ")+"\n");
//						        	textArea_1.append("[�ͻ����������������]��"+rs.getString("�ͻ���")+"\n");
					        	} 
					        	}
					}catch(Exception e)
					{
					    e.printStackTrace();
					    System.out.print("SQL Server����ʧ�ܣ�");
					}        
					}
				textField.setText(null);
			}

		});
		button_2.setBounds(519, 382, 113, 27);
		panel.add(button_2);
		
		JButton button_3 = new JButton("\u7EDF\u8BA1");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ͳ�� sͳ�� = new ͳ��();
				sͳ��.setVisible(true);
				 
					
				        
			}
		});
		button_3.setBounds(686, 382, 113, 27);
		panel.add(button_3);
		
		JButton button_5 = new JButton("\u5237\u65B0");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
//				textArea_1.setText(null);
			}
		});
		button_5.setBounds(618, 312, 113, 27);
		panel.add(button_5);
		
		JScrollPane scrollPane=new JScrollPane(table);
		scrollPane.setBounds(14, 13, 952, 254);
		panel.add(scrollPane);


		
	}
}
