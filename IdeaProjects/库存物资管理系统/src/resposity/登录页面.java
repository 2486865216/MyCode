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

public class ��¼ҳ�� extends JFrame {
	ImageIcon background;
	JPanel myPanel;
	JLabel label;//���ڷű�ǩ
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
					new ��¼ҳ��();
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
	public ��¼ҳ��() {
		setTitle("\u5E93\u5B58\u7BA1\u7406\u7CFB\u7EDF");

		init();		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	//	this.setBounds(60,100,188,108);
		this.setVisible(true);
	}
	
void init(){


	background = new ImageIcon("11.png");	//����һ������ͼƬ
	label = new JLabel(background);		//�ѱ���ͼƬ��ӵ���ǩ��
	label.setBounds(0, 0, background.getIconWidth(), background.getIconHeight());	//�ѱ�ǩ����Ϊ��ͼƬ�ȸߵȿ�
	myPanel = (JPanel)this.getContentPane();
	getContentPane().setLayout(null);
	
	JLabel lblNewLabel = new JLabel("\u7528\u6237\u540D");
	lblNewLabel.setBounds(191, 125, 72, 22);
	lblNewLabel.setFont(new Font("΢���ź�", Font.PLAIN, 20));
	getContentPane().add(lblNewLabel);
	
	textField = new JTextField();
	textField.setBounds(322, 123, 174, 24);
	textField.setFont(new Font("����", Font.PLAIN, 18));
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
				JOptionPane.showMessageDialog(null, "�û�������Ϊ��!");
				return;
			}
		}
	});
	btnNewButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
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
			        Statement statement = dbConn.createStatement();
			        String sql = "select * from dbo.����Ա";
			        ResultSet rs = statement.executeQuery(sql);
					String ����=passwordField.getText();
					int k=0;
					int i=0;
					while (rs.next()) {
						String string = rs.getString("�û���");
						String string2= rs.getString("����");
				    	   string = string.trim();
				    	   if(textField.getText().equals(string)) {
				    		   i++;
				    		   break;
				    	   }
				    	   
						}
					if(i==0) {
						JOptionPane.showMessageDialog(null, "���û������ڣ�","error!",JOptionPane.WARNING_MESSAGE);
					}
					if(����.equals(rs.getString("����").trim())&&i!=0) {
						��� frame = new ���();
						frame.setVisible(true);

			
					//	dispose();
					}else {
						JOptionPane.showMessageDialog(null, "�������","error!",JOptionPane.WARNING_MESSAGE);
					}

			        
			}catch(Exception e)
			{
			    e.printStackTrace();
			    System.out.print("SQL Server����ʧ�ܣ�");
			}        
			}
	
		
	});
	getContentPane().add(btnNewButton);
	
	JLabel lblNewLabel_1 = new JLabel("\u5BC6\u7801");
	lblNewLabel_1.setBounds(203, 197, 72, 18);
	lblNewLabel_1.setFont(new Font("΢���ź�", Font.PLAIN, 20));
	getContentPane().add(lblNewLabel_1);
	
	JLabel lblNewLabel_2 = new JLabel("\u767B\u5F55\u9875\u9762");
	lblNewLabel_2.setBounds(317, 42, 216, 29);
	lblNewLabel_2.setFont(new Font("����", Font.PLAIN, 30));
	lblNewLabel_2.setForeground(Color.GRAY);
	getContentPane().add(lblNewLabel_2);
	
	JLabel lblNewLabel_3 = new JLabel("\u5E93\u5B58\u7BA1\u7406\u7CFB\u7EDF");
	lblNewLabel_3.setBounds(554, 426, 191, 31);
	lblNewLabel_3.setForeground(Color.DARK_GRAY);
	lblNewLabel_3.setFont(new Font("����", Font.PLAIN, 30));
	getContentPane().add(lblNewLabel_3);
	
	JButton btnNewButton_2 = new JButton("\u4FEE\u6539\u5BC6\u7801");
	btnNewButton_2.setBounds(150, 318, 113, 30);
	btnNewButton_2.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			�޸�����1 frame = new �޸�����1();
			frame.setVisible(true);
		}
	});
	btnNewButton_2.setFont(new Font("�����ֺ��μ���", Font.PLAIN, 15));
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
	this.getLayeredPane().setLayout(null);		//�ѷֲ����Ĳ����ÿ�						

	this.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));		//�ѱ�ǩ��ӵ��ֲ�������ײ�
	//���ý�������
	
	setBounds(400, 100, 763, 504);
   }
}
