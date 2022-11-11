package Kiosk;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;


public class User_Screen extends JFrame{
	
	int num = 1;
	private JPanel logo;
	private JPanel menu_panel;
	private JPanel basket;
	private JPanel control_panel;
	
	private JLabel total;
	private JLabel timer;
	private JButton init_btn;
	private JButton pay_btn;
	
	private JButton[] menus;
	
	
	 private static class RoundedBorder implements Border {
	        
	        private int radius;
	        
	        RoundedBorder(int radius) {
	            this.radius = radius;
	        }
	        @Override
	        public Insets getBorderInsets(Component c) {
	            return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
	        }

	        @Override
	        public boolean isBorderOpaque() {
	            return true;
	        }

	        @Override
	        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
	            g.drawRoundRect(x,y,width-1,height-1,radius,radius);
	        }
	    }
	
	//--------------------------------장바구니---------------------------------
	public class basket_panel extends JPanel{
		private basket_menu[] basket_menus = new basket_menu[7];
		
		public basket_panel() {
			setLayout(new GridLayout(7,1));
			setBackground(Color.LIGHT_GRAY);
//			setBorder(new RoundedBorder(50));
			
			for(int i = 0 ; i < 7; i++) {
				 basket_menus[i] = new basket_menu();
				 add(basket_menus[i]);
			}
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
	//--------------------------------장바구니---------------------------------
	
	//--------------------------------메뉴 목록---------------------------------
	public class Menus_panel extends JPanel {
		Menu_panel [] menu_panels = new Menu_panel[6];
		
		public Menus_panel() {
			setLayout(new GridLayout(2,3, 55, 25));
			setBorder(BorderFactory.createEmptyBorder(35,35,35,35));
//			setBackground(Color.CYAN);
			for(int i = 0;i < 6; i++) {
				menu_panels[i] = new Menu_panel();
				add(menu_panels[i]);
			}
		}
	}
	public class Menu_panel extends JPanel {
		JButton[] menu_btns = new JButton[2];
		JLabel menu_icon;
		ImageIcon img = new ImageIcon("images/meal.jpeg");
		
		public Menu_panel() {
			setLayout(null);			
			setBackground(Color.LIGHT_GRAY);
			
			add(menu_icon = new JLabel(img));
			menu_icon.setOpaque(true); //true 때만 옵션 지정 가
			menu_icon.setBounds(0,0,172,150);
			menu_icon.setBackground(Color.LIGHT_GRAY);
			
			add(menu_btns[0] = new JButton("선택"));
			menu_btns[0].setBounds(0, 150, 86, 35);
			add(menu_btns[1] = new JButton("영양 성분"));
			menu_btns[1].setBounds(86, 150, 86, 35);

		}
	}
	//--------------------------------메뉴 목록---------------------------------
	
	
	
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
		
		Menus_panel menus_panel = new Menus_panel();
		add(menus_panel);
		menus_panel.setBounds(1,40, 700, 459);
//		menu_panel.setBackground(Color.LIGHT_GRAY);
		
		
		basket_panel basket_panel = new basket_panel();
//		basket = new JPanel();
		add(basket_panel);
		basket_panel.setBounds(0,500, 500, 375 );
		
		control_panel = new JPanel();
		add(control_panel);
		control_panel.setBounds(500,500, 200, 400 );
		control_panel.setLayout(null);
		control_panel.setBackground(Color.gray);
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
		timer = new JLabel("Timer");		
		control_panel.add(timer);
		timer.setOpaque(true);
		timer.setBounds(0,0,200, 75);
		timer.setBackground(Color.WHITE);
		timer.setHorizontalAlignment(JLabel.CENTER);
		
		total = new JLabel("총량  " + Integer.toString(num));
		control_panel.add(total);
		total.setOpaque(true);
		total.setBounds(0,75,200, 75);
		total.setBackground(Color.WHITE);
		total.setHorizontalAlignment(JLabel.CENTER);	
		total.setBackground(Color.yellow);
		
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
