package resposity;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class ll extends JFrame {

	private JPanel contentPane;
	private final JPanel panel = new JPanel();
	private JTable table;
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ll frame = new ll();
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
	public ll() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 414);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		panel.setBounds(0, 0, 632, 236);
		contentPane.add(panel);
		panel.setLayout(null);
		
		
		
		
		
		table = new JTable();
		table.setBounds(0, 27, 632, 209);
		panel.add(table);
		
		//scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 236, 632, 131);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
	
		
		JButton btnNewButton = new JButton("\u67E5\u8BE2");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==btnNewButton){
					   String[] table1 = { "����", "��������","���","�ͺ�","����","11" };
					   DefaultTableModel mm= new DefaultTableModel(table1,1); //����һ�����ģ��
					 
				
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
		btnNewButton.setBounds(241, 62, 113, 27);
		panel_1.add(btnNewButton);
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 0, 632, 236);
		panel.add(scrollPane);
	}

}
