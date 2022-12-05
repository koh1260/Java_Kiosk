package Kiosk;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

//메뉴 객체 받아와서, 큰 JLabel하나 만들어서 해당 메뉴의 count가 1 이상일 시 출력.

public class OrderCheck_Screen extends JFrame {
	JPanel title = new JPanel();
	JLabel total = new JLabel();
	JLabel order_li = new JLabel();

	ImageIcon im = new ImageIcon(getClass().getClassLoader().getResource("ok.png"));
	ImageIcon im2 = new ImageIcon(getClass().getClassLoader().getResource("cancel.png"));
	ImageIcon im3 = new ImageIcon(getClass().getClassLoader().getResource("orcheck.png"));

	String date;
	String order_list = "<html>";
	int total_m = 0;
	public static Menu[] menus = new Menu[6];

	private Connection con;
	private PreparedStatement ps;

	class JFrameWindowClosingEventHandler extends WindowAdapter {
		public void windowClosing(WindowEvent e) {
			JFrame frame = (JFrame) e.getWindow();
			frame.dispose();
		}
	}

	public OrderCheck_Screen(Menu[] menus, User_Screen us) {
		getRootPane().setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.LIGHT_GRAY));
		setDisplay();
		this.menus = menus;

		for (Menu menu : menus) {
			if (menu.count != 0) {
				order_list += menu.name + ": " + menu.count + "개<br>";
				total_m += (menu.count * menu.price);
			}
		}
		// -------------------------현재
		// 날짜------------------------------------------------
		java.util.Date nowDate = new java.util.Date();
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		date = simpleDateFormat.format(nowDate);
		// -------------------------현재
		// 날짜------------------------------------------------

		setComponent();

	}

	public void setComponent() {
		JButton ok = new JButton(im);
		JButton cancel = new JButton(im2);
		JLabel img = new JLabel(im3);
		JPanel title = new JPanel();
		JLabel ti = new JLabel();

		Font font = new Font("맑은고딕", Font.BOLD, 35);
		ti.setFont(font);
		ti.setForeground(Color.WHITE);

		add(title);
		title.add(ti);
		title.setBounds(0, 0, 670, 40);
		title.setBackground(new Color(109, 180, 242));

		add(img);
		img.setOpaque(true);
		img.setBounds(5, 65, 220, 50);
		img.setBackground(Color.WHITE);

		add(order_li);
		order_li.setOpaque(true);
		order_li.setBounds(5, 120, 660, 330);
		order_li.setBackground(new Color(245, 245, 245));
		order_li.setFont(new Font("맑은 고딕", Font.BOLD, 27));
		order_li.setText(order_list);
		order_li.setHorizontalAlignment(JLabel.CENTER);

		add(total);
		total.setOpaque(true);
		total.setBounds(5, 450, 660, 55);
		total.setText("총 결제 금액 : " + total_m);
		total.setHorizontalAlignment(JLabel.CENTER);
		total.setBackground(Color.WHITE);
		total.setFont(new Font("맑은 고딕", Font.BOLD, 15));

		add(ok);
		ok.setBounds(10, 505, 322, 90);
		ok.setBackground(Color.LIGHT_GRAY);
		ok.setBorderPainted(false);
		ok.setContentAreaFilled(false);
		ok.setFocusPainted(false);

		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				User_Screen.us.dispose();
				
				String inQuery = "insert into sales_records (sales_date, sales_counts, menu_name, menu_price, total_m) values(?,?, ?, ?, ?)";
				for (Menu menu : menus) {
					if (menu.count > 0) {
						try {
							Class.forName("com.mysql.cj.jdbc.Driver");
							con = DriverManager.getConnection(ex.db_url, ex.db_user, ex.db_pw);
							ps = con.prepareStatement(inQuery);

							ps.setString(1, date);
							ps.setInt(2, menu.count);
							ps.setString(3, menu.name);
							ps.setInt(4, menu.price);
							ps.setInt(5, menu.count * menu.price);

							ps.executeUpdate();

							ps.close();
							con.close();

						} catch (ClassNotFoundException e1) {
							e1.printStackTrace();
						} catch (SQLException e1) {
//							System.out.println("연결 실패.");
							e1.printStackTrace();
						}
					}
				}
				new Pay_Screen();
				setVisible(false);
			}
		});
		
		add(cancel);
		cancel.setBounds(338, 505, 322, 90);
		cancel.setBackground(Color.LIGHT_GRAY);
		cancel.setBorderPainted(false);
		cancel.setContentAreaFilled(false);
		cancel.setFocusPainted(false);
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}

	public void setDisplay() {
		getContentPane().setBackground(Color.WHITE);
		setBackground(Color.WHITE);
//		setUndecorated(true);
		setVisible(true);
		setSize(670, 600);
		setResizable(false);
		setLocationRelativeTo(null);
		setLayout(null);
		addWindowListener(new JFrameWindowClosingEventHandler());

	}
}

//import java.awt.Color;
//import java.awt.Font;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.WindowAdapter;
//import java.awt.event.WindowEvent;
//import java.sql.Connection;
//import java.sql.Date;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//import java.text.SimpleDateFormat;
//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JPanel;
//
////메뉴 객체 받아와서, 큰 JLabel하나 만들어서 해당 메뉴의 count가 1 이상일 시 출력.
//
//public class OrderCheck_Screen extends JFrame{
//	JPanel title = new JPanel();
//	JLabel order_li = new JLabel();
//	JLabel total = new JLabel();
//	JLabel img = new JLabel();
//	JButton ok = new JButton("OK");
//	JButton cancel = new JButton("CON");
//	
//	User_Screen us = new User_Screen();
//	
//	String date;
//	String order_list = "<html>";
//	int total_m = 0;
//	public static Menu[] menus = new Menu[6];
//	
//	private Connection con;
//	private PreparedStatement ps;
//	
//	class JFrameWindowClosingEventHandler extends WindowAdapter {
//		public void windowClosing(WindowEvent e) {
//			JFrame frame = (JFrame)e.getWindow();
//			frame.dispose();
//		}
//	}
//
//	public OrderCheck_Screen(Menu[] menus, User_Screen us){
//		this.us = us;
//		setDisplay();
//		this.menus = menus;
//
//		for(Menu menu:menus) {
//			if(menu.count != 0) {
//				order_list += menu.name + ": " + menu.count + "개<br>";
//				total_m += (menu.count * menu.price);
//			}
//		}
//		//-------------------------현재 날짜------------------------------------------------
//		java.util.Date nowDate = new  java.util.Date();
//		String pattern = "yyyy-MM-dd";
//		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
//		date = simpleDateFormat.format(nowDate);
//		//-------------------------현재 날짜------------------------------------------------
//		
//		setComponent();
//		
//		
//
//	}
//	public void setComponent() {
//		add(title);
//		title.setBounds(0, 0, 670, 60);
//		title.setBackground(Color.blue);
//
//		add(img);
//		img.setOpaque(true);
//		img.setBounds(10, 70, 180, 425);
//		img.setBackground(Color.LIGHT_GRAY);
//		
//		add(order_li);
//		order_li.setOpaque(true);
//		order_li.setBounds(200, 70, 460, 355);
//		order_li.setBackground(Color.LIGHT_GRAY);
//		order_li.setFont(new Font("맑은 고딕", Font.BOLD, 15));
//		order_li.setText(order_list);
//		
//		add(total);
//		total.setOpaque(true);
//		total.setBounds(200, 440, 460, 55);
//		total.setText("총 결제 금액: " + total_m);
//		total.setBackground(Color.LIGHT_GRAY);
//		total.setFont(new Font("맑은 고딕", Font.BOLD, 15));
//		
//		
//		add(ok);
//		ok.setBounds(10, 505, 322, 90);
//		ok.setBackground(Color.LIGHT_GRAY);
//		ok.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				String inQuery = "insert into sales_records (sales_date, sales_counts, menu_name, menu_price, total_m) values(?,?, ?, ?, ?)";
//				for(Menu menu : menus) {
//					if(menu.count > 0) {
//				
//						try {
//							Class.forName("com.mysql.cj.jdbc.Driver");
//							con = DriverManager.getConnection(ex.db_url, ex.db_user, ex.db_pw);
//							ps = con.prepareStatement(inQuery);
//							
//							ps.setString(1, date);
//							ps.setInt(2, menu.count);
//							ps.setString(3, menu.name);
//							ps.setInt(4, menu.price);
//							ps.setInt(5, menu.count * menu.price);
//							
//							ps.executeUpdate();
//							
//							ps.close();
//							con.close();
//							
//						}catch(ClassNotFoundException e1) {
//							e1.printStackTrace();
//						}catch(SQLException e1) {
////							System.out.println("연결 실패.");
//							e1.printStackTrace();
//						}
//					}
//				}
//				 new Pay_Screen(menus);
//				 us.dispose();
//				 dispose();
//			}
//		});
//		
//		add(cancel);
//		cancel.setBounds(338, 505, 322, 90);
//		cancel.setBackground(Color.LIGHT_GRAY);
//		cancel.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				dispose();
//			}
//		});
//	}
//	public void setDisplay() {
//		setUndecorated(true);
//		setVisible(true);
//		setSize(670,600);
//		setResizable(false);
//		setLocationRelativeTo(null);
//		setLayout(null);
//		addWindowListener(new JFrameWindowClosingEventHandler());
//	}
//}