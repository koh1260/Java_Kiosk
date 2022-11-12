package Kiosk;
import java.awt.Color;
import java.awt.FlowLayout;
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


public class User_Screen extends JFrame{
	private static Connection con;
	private static PreparedStatement ps;
	private static ResultSet rs;
	
	private static String db_url = "jdbc:mysql://localhost:3306/Mydatabase";
	private static String db_user = "root";
	private static String db_pw = "1306";

	private Menu[] Menus = new Menu[6];
	int menu_panel_num = -1;
	int num = 1;
	private JPanel logo;
	private JPanel menu_panel;
	private JPanel basket;
	private JPanel control_panel;
	private Menus_panel menus_panel;
	private basket_panel basket_panel;
	
	private int menu_state = -1;
	private int total = 0;
	private JLabel total_count;
	private int total_money = 0;
	private JLabel timer;
	private JButton init_btn;
	private JButton pay_btn;
	
	private JButton[] menus;
	
	//--------------------------------생성자----------------------------------
	public User_Screen() {
		setDisplay();
		SqlQuery();
		setPanel();
		setControlPanel();
		System.out.println(menu_state);
		menuBtn();
		System.out.println(menu_state);
		timer();
	}
	
	//--------------------------------장바구니---------------------------------
	public class basket_panel extends JPanel{
		basket_menu[] basket_menus = new basket_menu[7];
		
		public basket_panel() {
			setLayout(new GridLayout(7,1));
			setBackground(Color.LIGHT_GRAY);
//			setBorder(new RoundedBorder(50));
			
			for(int i = 0 ; i < 7; i++) {
				 basket_menus[i] = new basket_menu();
				 add(basket_menus[i]);
				 basket_menus[i].setVisible(false);
			}
//			basket_menus[6].setVisible(true);
		}
	}
	public class basket_col extends JPanel{
		JLabel menu_name;
	}
	public class basket_menu extends JPanel{ //width: 500 height: 400
		private JButton plus = new JButton("+");
		private JButton minus = new JButton("-"); 
		private JLabel counts = new JLabel("1");
		private JLabel menu_name = new JLabel("라면");
		private JLabel price = new JLabel("1000원");
		
		public basket_menu() { //height: 57
			setLayout(new FlowLayout());
			add(menu_name);
			add(plus);
			add(counts);
			add(minus);
			add(price);
			setBackground(Color.LIGHT_GRAY);
		}
	}
	
	//--------------------------------메뉴 목록---------------------------------
	public class Menus_panel extends JPanel {
		Menu_panel [] menu_panels = new Menu_panel[6];
		
		public Menus_panel() {
			setLayout(new GridLayout(2,3, 55, 25));
			setBorder(BorderFactory.createEmptyBorder(10,15,20,15));
//			setBackground(Color.CYAN);
			for(int i = 0;i < 6; i++) {
				menu_panels[i] = new Menu_panel();
				add(menu_panels[i]);
			}
			menu_panels[0].menu_btns[0].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Menus[0].setCount(Menus[0].getCount() + 1);
					menu_state++;
					total++;
					basket_panel.basket_menus[menu_state].setVisible(true);
					menu_panels[0].menu_btns[0].setEnabled(false);
				}
			});
			menu_panels[1].menu_btns[0].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Menus[1].setCount(Menus[1].getCount() + 1);
					menu_state++;
					total++;
					basket_panel.basket_menus[menu_state].setVisible(true);
					menu_panels[1].menu_btns[0].setEnabled(false);
				}
			});
			menu_panels[2].menu_btns[0].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Menus[2].setCount(Menus[2].getCount() + 1);
					menu_state++;
					total++;
					basket_panel.basket_menus[menu_state].setVisible(true);
					menu_panels[2].menu_btns[0].setEnabled(false);
				}
			});
			menu_panels[3].menu_btns[0].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Menus[3].setCount(Menus[3].getCount() + 1);
					menu_state++;
					total++;
					basket_panel.basket_menus[menu_state].setVisible(true);
					menu_panels[3].menu_btns[0].setEnabled(false);
				}
			});
			menu_panels[4].menu_btns[0].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Menus[4].setCount(Menus[4].getCount() + 1);
					menu_state++;
					total++;
					basket_panel.basket_menus[menu_state].setVisible(true);
					menu_panels[4].menu_btns[0].setEnabled(false);
				}
			});
			menu_panels[5].menu_btns[0].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Menus[5].setCount(Menus[5].getCount() + 1);
					menu_state++;
					total++;
					basket_panel.basket_menus[menu_state].setVisible(true);
					menu_panels[5].menu_btns[0].setEnabled(false);
				}
			});
		}
	}
	public class Menu_panel extends JPanel {
		JButton[] menu_btns = new JButton[2];
		private JLabel menu_icon;
		JLabel menu_name;
		private ImageIcon img = new ImageIcon("images/meal.jpeg");
		
		public Menu_panel() {
			setLayout(null);			
			setBackground(Color.LIGHT_GRAY);
			
			add(menu_icon = new JLabel());
			menu_icon.setOpaque(true); //true 때만 옵션 지정 가
			menu_icon.setBounds(0,0,186,150);
			menu_icon.setBackground(Color.YELLOW);
			
//			add(menu_name);
//			menu_name.setBounds(10,10,30,30);
			
			
			add(menu_btns[0] = new JButton("선택"));
			menu_btns[0].setBounds(0, 169, 94, 35);
			add(menu_btns[1] = new JButton("영양 성분"));
			menu_btns[1].setBounds(92, 169, 94, 35);
		}
		public void setMenu_name(String menu_name) {
			this.menu_name.setText(menu_name);
		}

	}

	//---------------------------MySQL 연동, 쿼리 작성----------------------------
	public void SqlQuery() {
		try {
			con = DriverManager.getConnection(db_url, db_user, db_pw);
			ps = con.prepareStatement("select * from menu where menu_num=?");
			
			for(int i = 0 ; i < 6; i++) {

				ps.setInt(1, (i+1));
				rs = ps.executeQuery();
			
				System.out.println("연결 성공.");
				
				if(rs.next()) {
					Menus[i] = new Menu();
					Menus[i].setName(rs.getString("menu_name"));
					Menus[i].setPrice(rs.getInt("menu_price"));
					Menus[i].setCarbo(rs.getInt("menu_carbo"));
					Menus[i].setProtein(rs.getInt("menu_protein"));
					Menus[i].setFat(rs.getInt("menu_fat"));
					Menus[i].setKcal(rs.getInt("menu_kcal"));
				}
			}
		}catch(SQLException e1) {
//			System.out.println("연결 실패.");
			e1.printStackTrace();
		}
	}
	//---------------------------MySQL 연동, 쿼리 작성----------------------------

	public void setPanel() {
		logo = new JPanel();
		add(logo);
		logo.setBounds(0,0,700,40);
		logo.setBackground(Color.LIGHT_GRAY);
		
		menus_panel = new Menus_panel();
		add(menus_panel);
		menus_panel.setBounds(1,40, 700, 459);
		
		
		basket_panel = new basket_panel();
		add(basket_panel);
		basket_panel.setBounds(0,500, 500, 375 );
		
		control_panel = new JPanel();
		add(control_panel);
		control_panel.setBounds(500,500, 200, 400 );
		control_panel.setLayout(null);
		control_panel.setBackground(Color.gray);
	}
	public void menusPanelSet() {
		for(int i = 0 ; i < 6; i ++) {
			menus_panel.menu_panels[i].setMenu_name(Menus[i].getName());
		}
	}
	
	public void menuBtn() {
		menus = new JButton[6];
		
		for(int i = 0; i < 6; i++) {
			menus[i] = new JButton("menu" + Integer.toString(i+1));
			menus[i].setSize(30,30);
			menus[i].setBorderPainted(false);
			menus[i].setFocusPainted(false);
			add(menus[i]);
		}     
	}
	
	public void setControlPanel() {
		timer = new JLabel("Timer");		
		control_panel.add(timer);
		timer.setOpaque(true);
		timer.setBounds(0,0,200, 75);
		timer.setBackground(Color.WHITE);
		timer.setHorizontalAlignment(JLabel.CENTER);
		
		total_count = new JLabel("총량  " + Integer.toString(num));
		control_panel.add(total_count);
		total_count.setOpaque(true);
		total_count.setBounds(0,75,200, 75);
		total_count.setBackground(Color.WHITE);
		total_count.setHorizontalAlignment(JLabel.CENTER);	
		total_count.setBackground(Color.yellow);
		
		init_btn = new JButton("초기화");
		control_panel.add(init_btn);
		init_btn.setOpaque(true);
		init_btn.setBounds(0,150,200, 75);
		init_btn.setBorderPainted(false);
		init_btn.setFocusPainted(false);	
		init_btn.setBackground(Color.magenta);
		
		pay_btn = new JButton("결제");
		control_panel.add(pay_btn);
		pay_btn.setOpaque(true);
		pay_btn.setBounds(0,226,200, 150);
		pay_btn.setBorderPainted(false);
		pay_btn.setFocusPainted(false);	
		pay_btn.setBackground(Color.GREEN);
	}
	public void timer() {
		for(int i = 120; i >= 0; i--) {
			timer.setText(i + "초");
			
				try {
					Thread.sleep(1000);
				}catch(InterruptedException e){
					e.printStackTrace();
					break;
				}
				
				if(i == 0) {
					Login_Screen l = new Login_Screen();
					l.setVisible(true);
					this.setVisible(false);
				}
		}
	}

	public void setDisplay() {		
		setTitle("주문창");
//		setUndecorated(true);
		setVisible(true);
		setSize(700, 900);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
	}
}
