package Kiosk;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class User_Screen extends JFrame{
	
	int num = 1;
	private JPanel logo;
	private JPanel menu_panel;
	private JPanel basket;
	private JPanel control_panel;
	
	private JLabel count;
	private JButton init_btn;
	private JButton pay_btn;
	
	private JButton[] menus;
	
	//--------------------------------장바구니---------------------------------
	public class basket_panel extends JPanel{
		private basket_menu[] basket_menus = new basket_menu[7];
		
		public basket_panel() {
			setLayout(new GridLayout(7,1));
			setBackground(Color.BLUE);
			
			for(int i = 0 ; i < 7; i++) {
				 basket_menus[i] = new basket_menu();
				 add(basket_menus[i]);
			}
		}
	}
	
	public class basket_menu extends JPanel{ //width: 500 height: 400
		private JButton plus = new JButton("+");
		private JButton minus = new JButton("-"); 
		private JLabel counts = new JLabel("1");
		private JLabel menu_name = new JLabel("라면");
		
		public basket_menu() { //height: 57
			setLayout(new FlowLayout());
			add(plus);
			add(minus);
			add(counts);
			add(menu_name);
			setBackground(Color.LIGHT_GRAY);
		}
	}
	//--------------------------------장바구니---------------------------------
	
	public User_Screen(){
		setDisplay();
		setPanel();
		setControlPanel();
		menuBtn();
	}

	public void setPanel() {
		logo = new JPanel();
		add(logo);
		logo.setBounds(0,0,700,40);
		logo.setBackground(Color.LIGHT_GRAY);
		
		menu_panel = new JPanel();
		add(menu_panel);

		menu_panel.setBounds(43,71,600, 400);
//		menu_panel.setBackground(Color.LIGHT_GRAY);
		menu_panel.setLayout(new GridLayout(2,3,40,40));

		basket_panel basket_panel = new basket_panel();
//		basket = new JPanel();
		add(basket_panel);
		basket_panel.setBounds(0,500, 500, 375 );
		
		control_panel = new JPanel();
		add(control_panel);
		control_panel.setBounds(500,500, 200, 400 );
		control_panel.setLayout(null);
	}
	public void menuBtn() {
		menus = new JButton[6];
		
		for(int i = 0; i < 6; i++) {
			menus[i] = new JButton("menu" + Integer.toString(i+1));
			menus[i].setSize(30,30);
			menus[i].setBorderPainted(false);
			menus[i].setFocusPainted(false);
			menu_panel.add(menus[i]);
		}
	}
	
	public void setControlPanel() {
		init_btn = new JButton("초기화");
		control_panel.add(init_btn);
		init_btn.setBounds(0,0,190, 75);
		init_btn.setBorderPainted(false);
		init_btn.setFocusPainted(false);	
		
		count = new JLabel("총량  " + Integer.toString(num));
		control_panel.add(count);
		count.setBounds(0,75,190, 75);
		count.setBackground(Color.WHITE);
		count.setHorizontalAlignment(JLabel.CENTER);
		
		pay_btn = new JButton("결제");
		control_panel.add(pay_btn);
		pay_btn.setBounds(0,150,190, 220);
		pay_btn.setBorderPainted(false);
		pay_btn.setFocusPainted(false);	
	}
	public void setBasket() {
		
	}

	public void setDisplay() {		
		setTitle("주문창");
		setVisible(true);
		setSize(700, 900);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
	}
}
