package resposity;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

/**
 * Launch the application.
 */

	public class qq extends JFrame implements ActionListener {
	 
	// JLabel jlabel = new JLabel("��ʾ���ݿ��е�����");
	 JTable jtable = new JTable();
	 JScrollPane jscrollpane = new JScrollPane(jtable);//�����Ӹ�������
	 JButton display = new JButton("��ʾ�����б�");
	  
	 qq(String s){
	  super(s);
	  this.setLayout(new BorderLayout());
	//  this.add(jlabel,BorderLayout.NORTH);
	   this.add(jscrollpane,BorderLayout.CENTER);
	  this.add(display,BorderLayout.SOUTH);
	   
	  display.addActionListener(this);
	  this.setSize(250,200); //����JFrame���ڴ�С
	  this.setVisible(true);//���ô�����ʾtrue
	   
	 }
	  
	 public void actionPerformed(ActionEvent e) {
	  if(e.getSource()==display){
	   String[] col = { "ѧ��", "����" };
	   DefaultTableModel mm= new DefaultTableModel(col,0); //����һ�����ģ��
	    
	   try {
	    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); //����access���ݿ�����
	    System.out.println("���������ɹ���");
	   // String driverName="com.microsoft.sqlserver.jdbc.SQLServerDriver";
	   } catch (ClassNotFoundException e1) {
	    e1.printStackTrace();
	    
	   }
	   try{
		 //  String driverName="com.microsoft.sqlserver.jdbc.SQLServerDriver";
	   Connection conn = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=������ʹ���ϵͳ","sa","123"); //������������studentΪ���ݿ���
	   System.out.println("�������ݿ�ɹ���");
	   Statement stmt = conn.createStatement();
	   
	   ResultSet rs=stmt.executeQuery("select * from.����Ա ");//����ѯ�õ��Ľ������rs
	   while(rs.next()){
	      String id = rs.getString("�û���");
	      String name = rs.getString("����");
	     // String tel = rs.getString("tel");
	      String[] str_row={id,name}; //��һ�е����ݴ���str_row �ַ���������
	      mm.addRow(str_row);//����ڱ�ģ����
	      System.out.println("232qq");
	   }
	   jtable.setModel(mm);//��jtable����� ����Ϊ�ոն����ģ��
	   this.add(jscrollpane); //�������˱�Ĺ�������JFrame����ʾ
	   }catch(SQLException e1){
	   e1.getStackTrace();
	   }
	  }
	 }
	 public static void main(String[] args) {
	  // TODO Auto-generated method stub
	                new qq ("����JTable");
	                
	 }
	

	/**
	 * Create the frame.
	 */
	public qq() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		//setVisible(true);
	}

}
