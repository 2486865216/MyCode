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
	 
	// JLabel jlabel = new JLabel("显示数据库中的数据");
	 JTable jtable = new JTable();
	 JScrollPane jscrollpane = new JScrollPane(jtable);//将表格加个滚动条
	 JButton display = new JButton("显示数据列表");
	  
	 qq(String s){
	  super(s);
	  this.setLayout(new BorderLayout());
	//  this.add(jlabel,BorderLayout.NORTH);
	   this.add(jscrollpane,BorderLayout.CENTER);
	  this.add(display,BorderLayout.SOUTH);
	   
	  display.addActionListener(this);
	  this.setSize(250,200); //设置JFrame窗口大小
	  this.setVisible(true);//设置窗口显示true
	   
	 }
	  
	 public void actionPerformed(ActionEvent e) {
	  if(e.getSource()==display){
	   String[] col = { "学号", "姓名" };
	   DefaultTableModel mm= new DefaultTableModel(col,0); //定义一个表的模板
	    
	   try {
	    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); //加载access数据库驱动
	    System.out.println("加载驱动成功！");
	   // String driverName="com.microsoft.sqlserver.jdbc.SQLServerDriver";
	   } catch (ClassNotFoundException e1) {
	    e1.printStackTrace();
	    
	   }
	   try{
		 //  String driverName="com.microsoft.sqlserver.jdbc.SQLServerDriver";
	   Connection conn = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=库存物资管理系统","sa","123"); //建立连接这里student为数据库名
	   System.out.println("连接数据库成功！");
	   Statement stmt = conn.createStatement();
	   
	   ResultSet rs=stmt.executeQuery("select * from.管理员 ");//将查询得到的结果集给rs
	   while(rs.next()){
	      String id = rs.getString("用户名");
	      String name = rs.getString("密码");
	     // String tel = rs.getString("tel");
	      String[] str_row={id,name}; //将一行的数据存在str_row 字符串数组里
	      mm.addRow(str_row);//添加在表模板中
	      System.out.println("232qq");
	   }
	   jtable.setModel(mm);//将jtable这个表 设置为刚刚定义的模板
	   this.add(jscrollpane); //将加载了表的滚动条在JFrame中显示
	   }catch(SQLException e1){
	   e1.getStackTrace();
	   }
	  }
	 }
	 public static void main(String[] args) {
	  // TODO Auto-generated method stub
	                new qq ("测试JTable");
	                
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
