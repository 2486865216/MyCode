package resposity;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.DefaultComboBoxModel;

public class ͳ�� extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ͳ�� frame = new ͳ��();
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
	public ͳ��() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 761, 421);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(new GridLayout(2, 1, 0, 0));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 733, 182);
		panel_1.add(scrollPane);
		
		JTextArea jTextArea = new JTextArea();
		scrollPane.setViewportView(jTextArea);
		
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		
		textField = new JTextField();
		textField.setBounds(149, 87, 178, 24);
		panel_2.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("New button");
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
				 try {
					 Connection dbConn=DriverManager.getConnection(dbURL,userName,userPwd);
				        System.out.println("�������ݿ�ɹ���");
				        Statement statement = dbConn.createStatement();
				        String string=textField.getText();
				        String sql = "select sum(����),month([����]) from dbo.���� where year([����])="+string+" group by month([����])";
				       // String sql1 = "select sum(����),month([����]) from dbo.����� where year([����])="+string+" group by month([����])";
				     
				        ResultSet rs = statement.executeQuery(sql);
				        while(rs.next()) {
				        	jTextArea.append("�·ݣ�"+rs.getString(2)+",  ������"+rs.getString(1)+"\n");
				        }
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		btnNewButton.setBounds(383, 86, 113, 27);
		panel_2.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.setBounds(519, 86, 113, 27);
		panel_2.add(btnNewButton_1);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"\u51FA\u5E93\u8868", "\u5165\u5E93\u8868"}));
		comboBox.setBounds(14, 87, 106, 24);
		panel_2.add(comboBox);

	}
}
