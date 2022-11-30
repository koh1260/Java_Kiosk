package Kiosk;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class SalesRecords_Screen extends JFrame {
	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	
	 JLabel label;//레이블 생성
     JTextField tf = new JTextField(); //텍스트 필드 초기화
     JButton btnDate = new JButton("날짜검색"); //버튼 생성
	
	public SalesRecords_Screen() {
		  //레이아웃 설정
	      getContentPane().setLayout(null);   
	      setVisible(true);
	      setSize(700,900);
	      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //닫기버튼 클릭시 프로그램 종료

	      // 레이블, 텍스트필드, 버튼 위치 설정

	      btnDate.setBounds(480, 10, 90, 30);
	      
	      
	      //프레임에 각 항목 추가
	     
	      add(tf);
	      tf.setBounds(300, 10, 130, 30);
	      add(btnDate);
	      
	      label = new JLabel("매출 관리");
	      add(label);
	      label.setFont(new Font("맑은 고딕", Font.BOLD, 20));
	      label.setBounds(20,20,300,40);

	      
	      //테이블 생성
	      JPanel dateSearch = new JPanel();
	      
	      JTable jt = new JTable();
	      JScrollPane sp = new JScrollPane(jt);
//	      sp.setBounds(90, 70, 500, 700);
	      sp.setBounds(0, 0, 500, 700);
	      dateSearch.add(sp);
	      
	      add(dateSearch);
	      dateSearch.setBounds(90, 70, 500, 700);
	      
	      btnDate.addActionListener(new ActionListener() {
	    	  public void actionPerformed(ActionEvent e) {
	    		  
	    	      String[] columns = {"날짜", "매출"};
	    	      DefaultTableModel tableModel = new DefaultTableModel(columns, 0);
	    	      try {
	    				Class.forName("com.mysql.cj.jdbc.Driver");
	    				con = DriverManager.getConnection(ex.db_url, ex.db_user, ex.db_pw);
	    				String query = "select sales_date, sum(total_m) from sales_records where date_format(sales_date, " + "\'%Y-%m\'" + ")between "+ tf.getText() +" and 2022-12 group by sales_date order by sales_date";
	    				
	    				ps = con.prepareStatement("select sales_date, sum(total_m) from sales_records where date_format(sales_date, " + "\'%Y-%m\'" + ")between ? and \'2022-12\' group by sales_date order by sales_date");
	    				
//	    				ps.setString(1, tf.getText());
//	    				ps.setString(2, Integer.toString(Integer.parseInt(tf.getText()) + 1));
	    				ps.setString(1, tf.getText());
	    				rs = ps.executeQuery();
	    
	    				while(rs.next()) {
	    					String date = rs.getString("sales_date");
	    					int money = rs.getInt("sum(total_m)");
	    					
	    					System.out.println(date + " " + money);
	    					
	    					String[] data = {date, Integer.toString(money)};
	    					tableModel.addRow(data);
	    				}
	    				jt.setModel(tableModel);
	    				System.out.println("connect");
	    						
	    			}catch(ClassNotFoundException e1) {
	    				e1.printStackTrace();
	    			}catch(SQLException e1) {
//	    				System.out.println("연결 실패.");
	    				e1.printStackTrace();
	    			}
	    	  }
	      });
	      
//	      String[] columns = {"날짜", "매출"};
//	      DefaultTableModel tableModel = new DefaultTableModel(columns, 0);
//	      try {
//				Class.forName("com.mysql.cj.jdbc.Driver");
//				con = DriverManager.getConnection(ex.db_url, ex.db_user, ex.db_pw);
//				ps = con.prepareStatement("select sales_date, sum(sales_counts) from sales_records where date_format(sales_date, '%Y-%m')between '2022-?' and '2022-?' group by sales_date order by sales_date");
//				
//				ps.setString(1, tf.getText());
//				rs = ps.executeQuery();
//
//				while(rs.next()) {
//					String date = rs.getString(0);
//					int money = rs.getInt(1);
//						
//					String[] data = {date, Integer.toString(money)};
//					tableModel.addRow(data);
//				}
//				jt.setModel(tableModel);
//						
//			}catch(ClassNotFoundException e1) {
//				e1.printStackTrace();
//			}catch(SQLException e1) {
////				System.out.println("연결 실패.");
//				e1.printStackTrace();
	      
	      

	      	      
	      
	      
	      //테이블에 스크롤팬 추가 및 테이블 크기 위치 설정

	      
	      //프레임에 테이블 추가

	}
}