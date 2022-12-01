package Kiosk;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MenuManage_Screen extends JFrame {
	Connection con;
	PreparedStatement ps;
	Statement st;
	MenusPanel menusPanel = new MenusPanel();;
	
	public MenuManage_Screen() {
		setDisplay();
		
		add(menusPanel);
		menusPanel.setBackground(Color.LIGHT_GRAY);
		menusPanel.setBounds(0,300,700, 600);

		
		
		
	}
	
	public class MenusPanel extends JPanel{
		Connection con;
		PreparedStatement ps;
		ResultSet rs;
		
		Menu[] Menus = new Menu[6];
		MenuPanel[] menuPanels = new MenuPanel[6];
		
		public MenusPanel() {
			setLayout(new GridLayout(2,3,15,15));
			setBorder(BorderFactory.createEmptyBorder(0 , 15 , 15 , 15));
			SqlQuery();
			 
			//각 패널에 해당 번호에 맞는 메뉴 객체 할당.
			for(int i = 0; i < 6; i++) {
				menuPanels[i] = new MenuPanel(Menus[i]);
				add(menuPanels[i]);
			}
			
		}
		
		//메뉴 가져오기.
		//---------------------------MySQL 연동, 쿼리 작성----------------------------
		public void SqlQuery() {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				con = DriverManager.getConnection(ex.db_url, ex.db_user, ex.db_pw);
				ps = con.prepareStatement("select * from menu where menu_num=?");
				
				for(int i = 0 ; i < 6; i++) {

					ps.setInt(1, (i+1));
					rs = ps.executeQuery();
					
					if(rs.next()) {
						Menus[i] = new Menu();
						Menus[i].setMenu_num(rs.getInt("menu_num"));
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
//				System.out.println("연결 실패.");
				e1.printStackTrace();
			}
		}
		//---------------------------MySQL 연동, 쿼리 작성----------------------------
	}
	public class MenuPanel extends JPanel{
		Connection con;
		PreparedStatement ps;
		
		ImageIcon imgIcon = new ImageIcon("images/card.png");
		JLabel img;
		JLabel menuName = new JLabel("Name", JLabel.CENTER);
		JButton btnIn = new JButton("In");
		JButton btnOut = new JButton("Out");
		Menu menu;
		
		public MenuPanel(Menu menu) {
			setBackground(Color.DARK_GRAY);
			
			this.menu = menu;
			img = new JLabel("dd");
			setLayout(null);
			
			add(img);
			img.setBounds(0,0, 213, 190);
			img.setOpaque(true);
			img.setBackground(Color.GREEN);
			
			add(menuName);
			menuName.setBounds(0,190, 213, 50);
			menuName.setOpaque(true);
			menuName.setBackground(Color.CYAN);
			menuName.setFont(new Font("맑은 고딕", Font.BOLD, 20));
			//-------------------------삭제 버튼-------------------------------------
			add(btnOut);
			btnOut.setBounds(108, 242, 102, 40);
			btnOut.setBackground(Color.LIGHT_GRAY);
			btnOut.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String delQuery = String.format("delete from menu where menu_num = %s", menu.menu_num);
					
					try {
						Class.forName("com.mysql.cj.jdbc.Driver");
						con = DriverManager.getConnection(ex.db_url, ex.db_user, ex.db_pw);
						st = con.createStatement();
						st.executeUpdate(delQuery);
					}catch(ClassNotFoundException exc) {
						exc.printStackTrace();
					}catch(SQLException ex) {
//						System.out.println("연결 실패.");
						ex.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "메뉴가 삭제되었습니다." , "메뉴 삭제", JOptionPane.PLAIN_MESSAGE);
					btnOut.setEnabled(false);
					btnIn.setEnabled(true);
				}
			});
			//-------------------------등록 버튼-------------------------------------
			add(btnIn);
			btnIn.setBackground(Color.LIGHT_GRAY);
			btnIn.setBounds(getVisibleRect());
			btnIn.setBounds(3, 242, 102, 40);
			btnIn.setEnabled(false);
			if(menu == null) {
				btnIn.setEnabled(true);
				btnOut.setEnabled(false);
			}
			btnIn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String delQuery = String.format("delete from menu where menu_num = %s", menu.menu_num);
					
					try {
						Class.forName("com.mysql.cj.jdbc.Driver");
						con = DriverManager.getConnection(ex.db_url, ex.db_user, ex.db_pw);
						st = con.createStatement();
						st.executeUpdate(delQuery);
					}catch(ClassNotFoundException exc) {
						exc.printStackTrace();
					}catch(SQLException ex) {
//						System.out.println("연결 실패.");
						ex.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "메뉴가 등록되었습니다." , "메뉴 등록", JOptionPane.PLAIN_MESSAGE);
					btnIn.setEnabled(false);
					btnOut.setEnabled(true);
				}
			});
			
			
			
			
			
		}
	}
	
	public void setDisplay() {	
		this.getContentPane().setBackground(Color.LIGHT_GRAY);
		setUndecorated(true);
		setVisible(true);
		setSize(700, 900);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
	}
}