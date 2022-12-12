package Kiosk;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
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
//	JLabel order_li = new JLabel();
	JPanel orderLiPanel = new JPanel();

	ImageIcon im = new ImageIcon(getClass().getClassLoader().getResource("ok.png"));
	ImageIcon im2 = new ImageIcon(getClass().getClassLoader().getResource("cancel.png"));
	ImageIcon im3 = new ImageIcon(getClass().getClassLoader().getResource("orcheck.png"));

	String date;
	String order_list = "<html>";
	int total_m = 0;
	public static Menu[] menus = new Menu[6];

	private Connection con;
	private PreparedStatement ps;

	public class orderCa extends JPanel {
		JLabel menuLi = new JLabel("메뉴", JLabel.CENTER);
		JLabel menuCo = new JLabel("개수", JLabel.CENTER);
		JLabel menuMo = new JLabel("가격", JLabel.CENTER);

		public orderCa() {
			setBackground(Color.LIGHT_GRAY);
			setLayout(null);
			add(menuLi);
			menuLi.setBounds(0, 0, 213, 47);
			menuLi.setFont(new Font("맑은 고딕", Font.BOLD, 16));
			add(menuCo);
			menuCo.setBounds(213, 0, 213, 47);
			menuCo.setFont(new Font("맑은 고딕", Font.BOLD, 16));
			add(menuMo);
			menuMo.setBounds(426, 0, 213, 47);
			menuMo.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		}
	}

	public class orderMenu extends JPanel {
		JLabel menuName = new JLabel();
		JLabel menuCount = new JLabel();
		JLabel menuPrice = new JLabel();

		public orderMenu() {
			setBackground(new Color(245, 245, 245));
			setLayout(null);
			add(menuName);
			menuName.setBounds(0, 0, 213, 47);
			menuName.setHorizontalAlignment(JLabel.CENTER);
			menuName.setFont(new Font("맑은 고딕", Font.BOLD, 16));
			add(menuCount);
			menuCount.setBounds(213, 0, 213, 47);
			menuCount.setHorizontalAlignment(JLabel.CENTER);
			menuCount.setFont(new Font("맑은 고딕", Font.BOLD, 16));
			add(menuPrice);
			menuPrice.setBounds(426, 0, 213, 47);
			menuPrice.setHorizontalAlignment(JLabel.CENTER);
			menuPrice.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		}
	}

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
				order_list += menu.name + ": " + menu.count + "개 " + menu.count * menu.price + "원" + "<br>";
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
		JLabel title = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("titlebar.png")));
		JLabel ti = new JLabel();

		Font font = new Font("맑은고딕", Font.BOLD, 35);
		ti.setFont(font);
		ti.setForeground(Color.WHITE);

		add(title);
		title.add(ti);
		title.setBounds(0, 0, 667, 40);
		title.setBackground(new Color(109, 180, 242));

//		add(order_li);
//		order_li.setOpaque(true);
//		order_li.setBounds(5, 120, 640, 330);
//		order_li.setBackground(new Color(245, 245, 245));
//		order_li.setFont(new Font("맑은 고딕", Font.BOLD, 27));
//		order_li.setText(order_list);
//		order_li.setHorizontalAlignment(JLabel.CENTER);

		add(orderLiPanel);
		orderLiPanel.setLayout(new GridLayout(7, 2));
		orderLiPanel.setBounds(5, 80, 640, 330);
		orderLiPanel.setBackground(new Color(245, 245, 245));
		orderLiPanel.add(new orderCa());

		for (int i = 0; i < 6; i++) {
			if (menus[i].count != 0) {
				orderMenu mp = new orderMenu();
				mp.menuName.setText(menus[i].name);
				mp.menuCount.setText(Integer.toString(menus[i].count) + "개");
				mp.menuPrice.setText(Integer.toString(menus[i].count * menus[i].price) + "원");
				orderLiPanel.add(mp);
			}
		}

		add(total);
		total.setOpaque(true);
		total.setBounds(5, 415, 660, 55);
		total.setText("총 결제 금액 : " + total_m);
		total.setHorizontalAlignment(JLabel.CENTER);
		total.setBackground(Color.WHITE);
		total.setFont(new Font("맑은 고딕", Font.BOLD, 15));

		add(ok);
		ok.setBounds(22, 465, 300, 90);
		ok.setOpaque(true);
		ok.setBackground(Color.red);
		ok.setBorderPainted(false);
		ok.setContentAreaFilled(false);
		ok.setFocusPainted(false);

		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				User_Screen.us.t.stop();
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
		cancel.setBounds(312, 465, 300, 90);
		cancel.setOpaque(true);
		cancel.setBackground(Color.red);
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