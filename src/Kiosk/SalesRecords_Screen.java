package Kiosk;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class SalesRecords_Screen extends JFrame {
	Connection con;
	PreparedStatement ps;
	ResultSet rs;

	JLabel title = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("titlebar.png")));
	JLabel label;// 레이블 생성ㅣ
	JTextField tf = new JTextField("ex) 2022-10"); // 텍스트 필드 초기화
	JButton btnDate = new JButton(new ImageIcon(getClass().getClassLoader().getResource("btnsearch.png"))); // 버튼 생성
	JButton btnHome = new JButton(new ImageIcon(getClass().getClassLoader().getResource("home.png")));
	JButton btnMenu = new JButton(new ImageIcon(getClass().getClassLoader().getResource("btnmenu.png"))); // 버튼 생성

	public SalesRecords_Screen() {
		// 레이아웃 설정
		this.getContentPane().setBackground(Color.white);
		getContentPane().setLayout(null);
//		setUndecorated(true);
		getRootPane().setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.LIGHT_GRAY));
		setVisible(true);
		setSize(700, 900);
		setLocationRelativeTo(null);
		// 프레임에 각 항목 추가
		add(title);
		title.setBounds(0,0,700,40);
		
		//홈 버튼
		add(btnHome);
		btnHome.setBounds(622, 40, 60,60);
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Admin_Screen();
				dispose();
			}
		});

		//날짜 텍스트 박스, 버튼
		add(tf);
		tf.setBounds(113, 110, 130, 32);
		add(btnDate);
		btnDate.setBounds(257, 110, 90, 32);
		btnDate.setBorderPainted(false);
		btnDate.setFocusPainted(false);	
		
		add(btnMenu);
		btnMenu.setBounds(113, 480, 130, 32);
		btnMenu.setBorderPainted(false);
		btnMenu.setFocusPainted(false);

//		label = new JLabel("매출 관리");
//		add(label);
//		label.setFont(new Font("맑은 고딕", Font.BOLD, 20));
//		label.setBounds(20, 50, 300, 40);

		// 날짜 검색
		JPanel dateSearch = new JPanel();
		dateSearch.setLayout(new FlowLayout());
		JTable jt = new JTable();
		JScrollPane sp = new JScrollPane(jt);
		sp.setBounds(0, 0, 500, 300);
		sp.setBackground(Color.white);
		dateSearch.setBackground(Color.white);
		dateSearch.add(sp);

		add(dateSearch);
		dateSearch.setBounds(90, 150, 500, 300);
		//메뉴 이름 검색
		JPanel menuSearch = new JPanel();
		menuSearch.setLayout(new FlowLayout());
		JTable jt2 = new JTable();
		JScrollPane sp2 = new JScrollPane(jt2);
		sp2.setBounds(0, 0, 500, 300);
		sp2.setBackground(Color.white);
		menuSearch.setBackground(Color.white);
		menuSearch.add(sp2);

		add(menuSearch);
		menuSearch.setBounds(90, 520, 500, 300);

		//날짜별 매출 조회
		btnDate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String[] columns = { "날짜", "매출" };
				DefaultTableModel tableModel = new DefaultTableModel(columns, 0);
				try {
					int total_m = 0;
					String total_money = null;
					
					Class.forName("com.mysql.cj.jdbc.Driver");
					con = DriverManager.getConnection(ex.db_url, ex.db_user, ex.db_pw);

					ps = con.prepareStatement(
							"select sales_date, sum(total_m) from sales_records where date_format(sales_date, "
									+ "\'%Y-%m\'" + ")between ? and ? group by sales_date order by sales_date");

					ps.setString(1, tf.getText().trim());
					ps.setString(2, tf.getText().trim());

					rs = ps.executeQuery();

					while (rs.next()) {
						String date = rs.getString("sales_date");
						int money = rs.getInt("sum(total_m)");
						String m = money + "원";
						total_m += money;
						total_money = total_m + "원";

						System.out.println(date + " " + money);

						String[] data = { date,m };
						tableModel.addRow(data);
					}
					String[] total = { "총 매출", total_money};
					tableModel.addRow(total);

					jt.setModel(tableModel);

					DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
					centerRenderer.setHorizontalAlignment(JLabel.CENTER);
					jt.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
					jt.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);

					con.close();

				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
//	    				System.out.println("연결 실패.");
					e1.printStackTrace();
				}
			}
		});
		//메뉴별 매출 조회
		btnMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String[] columns = { "메뉴", "판매량" , "매출"};
				DefaultTableModel tableModel = new DefaultTableModel(columns, 0);
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					con = DriverManager.getConnection(ex.db_url, ex.db_user, ex.db_pw);

					ps = con.prepareStatement(
							"select menu_name ,sum(sales_counts), sum(total_m) from sales_records\r\n"
							+ "group by menu_name\r\n"
							+ "order by sum(sales_counts) desc");
					
					rs = ps.executeQuery();

					while (rs.next()) {
						String menu_name = rs.getString("menu_name");
						String counts = Integer.toString(rs.getInt("sum(sales_counts)")) + "개";
						String money = Integer.toString(rs.getInt("sum(total_m)")) + "원";
						
						String[] data = { menu_name, counts, money };
						tableModel.addRow(data);
					}
					jt2.setModel(tableModel);

					DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
					centerRenderer.setHorizontalAlignment(JLabel.CENTER);
					jt2.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
					jt2.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
					jt2.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);

					con.close();

				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
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

		// 테이블에 스크롤팬 추가 및 테이블 크기 위치 설정

		// 프레임에 테이블 추가

	}
}