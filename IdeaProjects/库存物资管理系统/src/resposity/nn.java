package resposity;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class nn extends JFrame {
	private JTextField textField;
	private JTable table;
	private JTextField textField_1;
	JScrollPane jscrollpane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					nn frame = new nn();
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
	public nn() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 651, 366);
		
	
	
		JButton btnNewButton = new JButton("\u67E5\u8BE2");
		btnNewButton.setBounds(203, 250, 287, 27);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==btnNewButton){
					   String[] table1 = { "����", "��������","���","�ͺ�","����" };
					   DefaultTableModel mm= new DefaultTableModel(table1,0); //����һ�����ģ��
					 
				
				try { 
					Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); //����access���ݿ�����
			    System.out.println("���������ɹ���");
					
				} catch (Exception e2) {
					// TODO: handle exception
				}
				try {
					 Connection conn = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=������ʹ���ϵͳ","sa","123"); //������������studentΪ���ݿ���
					   System.out.println("�������ݿ�ɹ���");
					   Statement stmt = conn.createStatement();
					  
				
					   ResultSet rs=stmt.executeQuery("select * from.��Ʒ");//����ѯ�õ��Ľ������rs
				
					   while (rs.next()) {
						   
					 
							   String id = rs.getString("����");
							      String name = rs.getString("��������");
							      String id1 = rs.getString("���");
							      String name1 = rs.getString("�ͺ�");
							      String name2 = rs.getString("����");
							      String[] str_row={id,name,id1,name1,name2}; //��һ�е����ݴ���str_row �ַ���������
							      mm.addRow(str_row);//����ڱ�ģ����
							      System.out.println("232qq");
						 }
						 table.setModel(mm);//��jtable����� ����Ϊ�ոն����ģ��
						
						  System.out.println("232");
				
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
			}

		
		});
		getContentPane().setLayout(null);
		getContentPane().add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 0, 2, 2);
		getContentPane().add(scrollPane);
		table = new JTable();
		table.setBounds(0, 15, 633, 222);
		getContentPane().add(table);
		
	
	
		textField_1 = new JTextField();
		textField_1.setBounds(0, 250, 159, 24);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);

		
	}
}
