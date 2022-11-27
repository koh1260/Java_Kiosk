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
import javax.swing.Timer;


public class User_Screen extends JFrame{
	private static Connection con;
	private static PreparedStatement ps;
	private static ResultSet rs;
	
	private static String db_url = "jdbc:mysql://localhost:13306/java_kiosk";
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
	
	private int[] menu_num_list = new int[6];
	private int menu_num;
	private int menu_state = -1;
	private int total = 0;
	private JLabel total_count;
	private int total_money = 0;
	private JLabel timer;
	private JButton init_btn;
	private JButton pay_btn;
	
//	private JButton[] menus;
	
	//--------------------------------생성자----------------------------------
	public User_Screen() {
		setDisplay();
		SqlQuery();
		setPanel();
		setControlPanel();
//		menuBtn();
		
		ActionListener listener = new setTimer();
	    Timer t = new Timer(1000, listener);
	    t.start();
//		timer();
	}
	//--------------------------------Timer---------------------------------
	class setTimer implements ActionListener
    {
    	int n=120;
    	
    	public void actionPerformed(ActionEvent e) {
    		n--;
    		if(n == 0) {
    			try {
    				Thread.sleep(1000);
    			}catch(InterruptedException e1) {
    				
    			}
    			new Login_Screen();
    			dispose();
    		}
    		timer.setText(n+"초");
    	}
    }
	//--------------------------------Timer---------------------------------
    
	
	//--------------------------------장바구니---------------------------------
	public class basket_panel extends JPanel{
		basket_menu[] basket_menus = new basket_menu[6];
		JPanel total_panel;
		JLabel total_m;
		
		public basket_panel() {
			setLayout(new GridLayout(7,1));
			setBackground(Color.white);
			
			for(int i = 0 ; i < 6; i++) {
				 basket_menus[i] = new basket_menu();
//				 add(basket_menus[i]);
//				 basket_menus[i].setVisible(false);
			}
			total_panel = new JPanel();
			total_panel.setLayout(new FlowLayout());
			total_m = new JLabel("0원");
			total_m.setFont(new Font("맑은 고딕", Font.BOLD, 20));
			total_panel.add(total_m);
			total_panel.setBackground(Color.white);
			add(total_panel);
			
			
			basket_menus[0].plus.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					basket_menus[0].Plus();
				}
			});
			basket_menus[1].plus.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					basket_menus[1].Plus();
				}
			});
			basket_menus[2].plus.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					basket_menus[2].Plus();
				}
			});
			basket_menus[3].plus.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					basket_menus[3].Plus();
				}
			});
			basket_menus[4].plus.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					basket_menus[4].Plus();
				}
			});
			basket_menus[5].plus.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					basket_menus[5].Plus();
				}
			});
			
			basket_menus[0].minus.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					basket_menus[0].Minus();
					if(Menus[0].getCount() == 0) {
						basket_menus[0].setVisible(false);
						remove(basket_menus[0]);
						menus_panel.menu_panels[0].menu_btns[0].setEnabled(true);
					}
				}
			});
			basket_menus[1].minus.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					basket_menus[1].Minus();
					if(Menus[1].getCount() == 0) {
						basket_menus[1].setVisible(false);
						remove(basket_menus[1]);
						menus_panel.menu_panels[1].menu_btns[0].setEnabled(true);
					}
				}
			});
			basket_menus[2].minus.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					basket_menus[2].Minus();
					if(Menus[2].getCount() == 0) {
						basket_menus[2].setVisible(false);
						remove(basket_menus[2]);
						menus_panel.menu_panels[2].menu_btns[0].setEnabled(true);
					}
				}
			});
			basket_menus[3].minus.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					basket_menus[3].Minus();
					if(Menus[3].getCount() == 0) {
						basket_menus[3].setVisible(false);
						remove(basket_menus[3]);
						menus_panel.menu_panels[3].menu_btns[0].setEnabled(true);
					}
				}
			});
			basket_menus[4].minus.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					basket_menus[4].Minus();
					if(Menus[4].getCount() == 0) {
						basket_menus[4].setVisible(false);
						remove(basket_menus[4]);
						menus_panel.menu_panels[4].menu_btns[0].setEnabled(true);
					}
				}
			});
			basket_menus[5].minus.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					basket_menus[5].Minus();
					if(Menus[5].getCount() == 0) {
						basket_menus[5].setVisible(false);
						remove(basket_menus[5]);
						menus_panel.menu_panels[5].menu_btns[0].setEnabled(true);
					}
				}
			});

		}
	}
	public class basket_col extends JPanel{
		JLabel menu_name;
	}
	public class basket_menu extends JPanel{ //width: 500 height: 400
		int index;
		int count = 0;
		Menu menu = new Menu();
		JButton plus = new JButton(new ImageIcon("images/plus.png"));
		JButton minus = new JButton(new ImageIcon("images/minus.png")); 
		JLabel counts = new JLabel();
		JLabel menu_name = new JLabel();
		JLabel price = new JLabel();
		
		public basket_menu() { //height: 57
			setLayout(null);
			add(menu_name);
			menu_name.setBounds(10,10,250,30);
			menu_name.setFont(new Font("맑은 고딕", Font.BOLD, 18));
			add(plus);
			plus.setBounds(270, 10 ,30,30);
			plus.setFont(new Font("맑은 고딕", Font.BOLD, 16));
			
			add(counts);
			counts.setBounds(320, 10, 30,30);
			counts.setFont(new Font("맑은 고딕", Font.BOLD, 16));
			
			add(minus);
			minus.setBounds(350,10, 30,30);
			
			add(price);
			price.setBounds(430,10, 80,30 );
			price.setFont(new Font("맑은 고딕", Font.BOLD, 16));
			
			setBackground(Color.white);
		}
		
		public void setCounts() {
			counts.setText(Menus[index].getCount()+"");
		}
		
		public void setPrice() {
			price.setText(Menus[index].getCount() * Menus[index].getPrice() + "원");
		}
		
		public void setMenuName() {
			menu_name.setText(Menus[index].getName());
		}
		
		public void Plus() {
			Menus[index].setCount(Menus[index].getCount()+1);
			total++;
			total_money += Menus[index].getPrice();
			basket_panel.total_m.setText(total_money +"원");
			total_count.setText("선택 메뉴  " + total+"개");
			setCounts();
			setPrice();
		}
		
		public void Minus() {
			Menus[index].setCount(Menus[index].getCount() - 1);
			total--;
			total_money -= Menus[index].getPrice();
			basket_panel.total_m.setText(total_money +"원");
			total_count.setText("선택 메뉴  " + total+"개");
			setCounts();
			setPrice();
		}
	}
	
	//--------------------------------메뉴 목록---------------------------------
	public class Menus_panel extends JPanel {
		Menu_panel [] menu_panels = new Menu_panel[6];
		String[] menu_images = {"images/don.png","images/noodle.png","images/om.png","images/gook.png","images/pasta.png","images/kimchi.png"};
		public Menus_panel() {
			setLayout(new GridLayout(2,3, 55, 25));
			setBorder(BorderFactory.createEmptyBorder(10,15,20,35));
//			setBackground(Color.CYAN);
			for(int i = 0;i < 6; i++) {
				menu_panels[i] = new Menu_panel();
				menu_panels[i].menu_icon.setIcon(new ImageIcon(menu_images[i]));
				add(menu_panels[i]);
			}
			
			menu_panels[0].menu_btns[0].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Menus[0].setCount(Menus[0].getCount() + 1);
					System.out.println(Menus[0].getCount());
					menu_state++;
					basket_panel.add(basket_panel.basket_menus[0]);
					basket_panel.basket_menus[0].setVisible(true);
					basket_panel.basket_menus[0].index = 0;
					basket_panel.basket_menus[0].counts.setText(Menus[0].getCount() +"");
					basket_panel.basket_menus[0].setPrice();
					basket_panel.basket_menus[0].setMenuName();
					total++;
					total_money += Menus[0].getPrice();
//					menu_num_list[menu_state] = 0;
//					System.out.println(menu_num_list[menu_state]);
//					basket_panel.basket_menus[menu_state].setVisible(true);
					menu_panels[0].menu_btns[0].setEnabled(false);
					basket_panel.total_m.setText(total_money +"원");
					total_count.setText("선택 메뉴  " + total+"개");
				}
			});
			menu_panels[1].menu_btns[0].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Menus[1].setCount(Menus[1].getCount() + 1);
					System.out.println(Menus[1].getCount());
					menu_state++;
					basket_panel.add(basket_panel.basket_menus[1]);
					basket_panel.basket_menus[1].setVisible(true);
					basket_panel.basket_menus[1].index = 1;
					basket_panel.basket_menus[1].counts.setText(Menus[1].getCount() +"");
					basket_panel.basket_menus[1].setPrice();
					basket_panel.basket_menus[1].setMenuName();
					total++;
					total_money += Menus[1].getPrice();
//					menu_num_list[menu_state] = 1;
//					System.out.println(menu_num_list[menu_state]);
//					basket_panel.basket_menus[menu_state].setVisible(true);
					menu_panels[1].menu_btns[0].setEnabled(false);
					basket_panel.total_m.setText(total_money +"원");
					total_count.setText("선택 메뉴  " + total+"개");
				}
			});
			menu_panels[2].menu_btns[0].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Menus[2].setCount(Menus[2].getCount() + 1);
					System.out.println(Menus[2].getCount());
					menu_state++;
					basket_panel.add(basket_panel.basket_menus[2]);
					basket_panel.basket_menus[2].setVisible(true);
					basket_panel.basket_menus[2].index = 2;
					basket_panel.basket_menus[2].counts.setText(Menus[2].getCount() +"");
					basket_panel.basket_menus[2].setPrice();
					basket_panel.basket_menus[2].setMenuName();
					total++;
					total_money += Menus[2].getPrice();
//					menu_num_list[menu_state] = 2;
//					System.out.println(menu_num_list[menu_state]);
//					basket_panel.basket_menus[menu_state].setVisible(true);
					menu_panels[2].menu_btns[0].setEnabled(false);
					basket_panel.total_m.setText(total_money +"원");
					total_count.setText("선택 메뉴  " + total+"개");
				}
			});
			menu_panels[3].menu_btns[0].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Menus[3].setCount(Menus[3].getCount() + 1);
					System.out.println(Menus[3].getCount());
					menu_state++;
					basket_panel.add(basket_panel.basket_menus[3]);
					basket_panel.basket_menus[3].setVisible(true);
					basket_panel.basket_menus[3].index = 3;
					basket_panel.basket_menus[3].counts.setText(Menus[3].getCount() +"");
					basket_panel.basket_menus[3].setPrice();
					basket_panel.basket_menus[3].setMenuName();
					total++;
					total_money += Menus[3].getPrice();
//					menu_num_list[menu_state] = 3;
//					System.out.println(menu_num_list[menu_state]);
//					basket_panel.basket_menus[menu_state].setVisible(true);
					menu_panels[3].menu_btns[0].setEnabled(false);
					basket_panel.total_m.setText(total_money +"원");
					total_count.setText("선택 메뉴  " + total+"개");
				}
			});
			menu_panels[4].menu_btns[0].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Menus[4].setCount(Menus[4].getCount() + 1);
					System.out.println(Menus[4].getCount());
					menu_state++;
					basket_panel.add(basket_panel.basket_menus[4]);
					basket_panel.basket_menus[4].setVisible(true);
					basket_panel.basket_menus[4].index = 4;
					basket_panel.basket_menus[4].counts.setText(Menus[4].getCount() +"");
					basket_panel.basket_menus[4].setPrice();
					basket_panel.basket_menus[4].setMenuName();
					total++;
					total_money += Menus[4].getPrice();
//					menu_num_list[menu_state] = 4;
//					System.out.println(menu_num_list[menu_state]);
//					basket_panel.basket_menus[menu_state].setVisible(true);
					menu_panels[4].menu_btns[0].setEnabled(false);
					basket_panel.total_m.setText(total_money +"원");
					total_count.setText("선택 메뉴  " + total+"개");
				}
			});
			menu_panels[5].menu_btns[0].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Menus[5].setCount(Menus[5].getCount() + 1);
					menu_state++;
					basket_panel.add(basket_panel.basket_menus[5]);
					basket_panel.basket_menus[5].setVisible(true);
					basket_panel.basket_menus[5].index = 5;
					basket_panel.basket_menus[5].counts.setText(Menus[5].getCount() +"");
					basket_panel.basket_menus[5].setPrice();
					basket_panel.basket_menus[5].setMenuName();
					total++;
					total_money += Menus[5].getPrice();
//					menu_num_list[menu_state] = 5;
//					System.out.println(menu_num_list[menu_state]);
//					basket_panel.basket_menus[menu_state].setVisible(true);
					menu_panels[5].menu_btns[0].setEnabled(false);
					basket_panel.total_m.setText(total_money +"원");
					total_count.setText("선택 메뉴  " + total+"개");
				}
			});
			
			menu_panels[0].menu_btns[1].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Nutrition_Screen n = new Nutrition_Screen();
					n.receive_Nu(Menus[0].getCarbo(), Menus[0].getProtein(), Menus[0].getFat(), Menus[0].getKcal());
//					n.setVisible(true);
				}
			});
			menu_panels[1].menu_btns[1].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Nutrition_Screen n = new Nutrition_Screen();
					n.receive_Nu(Menus[1].getCarbo(), Menus[1].getProtein(), Menus[1].getFat(), Menus[1].getKcal());
//					n.setVisible(true);
				}
			});
			menu_panels[2].menu_btns[1].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Nutrition_Screen n = new Nutrition_Screen();
					n.receive_Nu(Menus[2].getCarbo(), Menus[2].getProtein(), Menus[2].getFat(), Menus[2].getKcal());
//					n.setVisible(true);
				}
			});menu_panels[3].menu_btns[1].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Nutrition_Screen n = new Nutrition_Screen();
					n.receive_Nu(Menus[3].getCarbo(), Menus[3].getProtein(), Menus[3].getFat(), Menus[3].getKcal());
//					n.setVisible(true);
				}
			});menu_panels[4].menu_btns[1].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Nutrition_Screen n = new Nutrition_Screen();
					n.receive_Nu(Menus[4].getCarbo(), Menus[4].getProtein(), Menus[4].getFat(), Menus[4].getKcal());
					n.setVisible(true);
				}
			});menu_panels[5].menu_btns[1].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Nutrition_Screen n = new Nutrition_Screen();
					n.receive_Nu(Menus[5].getCarbo(), Menus[5].getProtein(), Menus[5].getFat(), Menus[5].getKcal());
//					n.setVisible(true);
				}
			});
			
		}
	}
	public class Menu_panel extends JPanel {
		JButton[] menu_btns = new JButton[2];
		JLabel menu_icon;
		JLabel menu_name;
		private ImageIcon img = new ImageIcon("images/meal.jpeg");
		
		public Menu_panel() {
			setLayout(null);			
			setBackground(Color.white);
			
			add(menu_icon = new JLabel());
			menu_icon.setOpaque(true); //true 때만 옵션 지정 가
			menu_icon.setBounds(0,0,186,172);
			menu_icon.setBackground(Color.white);
			
//			add(menu_name);
//			menu_name.setBounds(10,10,30,30);
			
			add(menu_btns[0] = new JButton(new ImageIcon("images/sel.png")));
			menu_btns[0].setBounds(0, 172, 91, 30);
			menu_btns[0].setBorderPainted(false);
			menu_btns[0].setFocusPainted(false);	
			add(menu_btns[1] = new JButton(new ImageIcon("images/nu.png")));
			menu_btns[1].setBounds(89, 172, 91, 30);
			menu_btns[1].setBorderPainted(false);
			menu_btns[1].setFocusPainted(false);
		}
		public void setMenu_name(String menu_name) {
			this.menu_name.setText(menu_name);
		}
	}

	//---------------------------MySQL 연동, 쿼리 작성----------------------------
	public void SqlQuery() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(db_url, db_user, db_pw);
			ps = con.prepareStatement("select * from menu where menu_num=?");
			
			for(int i = 0 ; i < 6; i++) {

				ps.setInt(1, (i+1));
				rs = ps.executeQuery();
				
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
			System.out.println("연결 성공.");
		}catch(ClassNotFoundException e1) {
			e1.printStackTrace();
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
		logo.setBackground(new Color(109, 180, 242));
		
		menus_panel = new Menus_panel();
		add(menus_panel);
		menus_panel.setBounds(1,40, 700, 459);
		
		basket_panel = new basket_panel();
		add(basket_panel);
		basket_panel.setBounds(0,505, 500, 355 );
		
		control_panel = new JPanel();
		add(control_panel);
		control_panel.setBounds(500,500, 200, 400 );
		control_panel.setLayout(null);
		control_panel.setBackground(new Color(238, 238, 238));
	}
	public void menusPanelSet() {
		for(int i = 0 ; i < 6; i ++) {
			menus_panel.menu_panels[i].setMenu_name(Menus[i].getName());
		}
	}
	
//	public void menuBtn() {
//		menus = new JButton[6];
//		
//		for(int i = 0; i < 6; i++) {
//			menus[i] = new JButton("menu" + Integer.toString(i+1));
//			menus[i].setSize(30,30);
//			menus[i].setBorderPainted(false);
//			menus[i].setFocusPainted(false);
//			add(menus[i]);
//		}     
//	}
	
	public void setControlPanel() {
		timer = new JLabel("120초");		
		control_panel.add(timer);
		timer.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		timer.setOpaque(true);
		timer.setBounds(0,0,185, 75);
		timer.setBackground(new Color(238, 238, 238));
		timer.setHorizontalAlignment(JLabel.CENTER);
		
		total_count = new JLabel("선택 메뉴  " + total+"개");
		control_panel.add(total_count);
		total_count.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		total_count.setOpaque(true);
		total_count.setBounds(0,75,185, 75);
		total_count.setBackground(new Color(238, 238, 238));
		total_count.setHorizontalAlignment(JLabel.CENTER);	
		
		init_btn = new JButton(new ImageIcon("images/init_btn.png"));
		control_panel.add(init_btn);
		init_btn.setBorder(null);
		init_btn.setOpaque(true);
		init_btn.setBounds(0,150,185, 75);
		init_btn.setBorderPainted(false);
		init_btn.setFocusPainted(false);	
		init_btn.setBackground(Color.magenta);
		init_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(int i = 0; i < 6; i++) {
					menu_state = -1;
					Menus[i].setCount(0);
					basket_panel.basket_menus[i].setVisible(false);
					basket_panel.remove(basket_panel.basket_menus[i]);
					menus_panel.menu_panels[i].menu_btns[0].setEnabled(true);
					total_money = 0;
					total = 0;
					total_count.setText("선택 메뉴  " + total+"개");
					basket_panel.total_m.setText(total_money +"원");
				}
			}
		});
		
		pay_btn = new JButton(new ImageIcon("images/pay_btn.png"));
		control_panel.add(pay_btn);
		pay_btn.setOpaque(true);
		pay_btn.setBounds(0,226,185, 135);
		pay_btn.setBorderPainted(false);
		pay_btn.setFocusPainted(false);	
		pay_btn.setBackground(Color.GREEN);
		pay_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Pay_Screen();
				dispose();
			}
		});
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
