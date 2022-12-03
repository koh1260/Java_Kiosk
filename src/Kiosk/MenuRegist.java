package Kiosk;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MenuRegist extends JFrame {
	menuImage menuImg = new menuImage();
	JLabel menuName = new JLabel("메뉴명", JLabel.CENTER);
	JLabel menuPrice = new JLabel("가격", JLabel.CENTER);
	
	JTextField tfName = new JTextField("name");
	JTextField tfPrice = new JTextField("price");

	ImageIcon icon;
	Image img;
	
	JButton btnOk;
	NuPanel np = new NuPanel();
	int index;
	
	public MenuRegist(int index) {
		this.index = index;
		setDisplay();
		setComponent();
	}
	
	//메뉴 이미지 그리는 패널
	public class menuImage extends JPanel{
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(img, 0, 0, 170, 180, this);
		}
	}
	
	//영양 성분 패널
	public class NuPanel extends JPanel{
		JLabel car= new JLabel("탄수화물(g)", JLabel.CENTER);
		JLabel protein = new JLabel("단백질(g)", JLabel.CENTER);
		JLabel fat = new JLabel("지방(g)", JLabel.CENTER);
		JLabel kcal = new JLabel("칼로리(kcal)", JLabel.CENTER);
		
		JTextField tfCar = new JTextField();
		JTextField tfProtein = new JTextField();
		JTextField tfFat = new JTextField();
		JTextField tfKcal = new JTextField();
		
		public NuPanel() {
			setSize(400, 150);
			setBackground(Color.LIGHT_GRAY);
			
			add(car);
			car.setBounds(10,40,80,30);
			car.setFont(new Font("맑은 고딕", Font.BOLD, 14));
			
			add(protein);
			protein.setBounds(110, 40, 80, 30);
			protein.setFont(new Font("맑은 고딕", Font.BOLD, 14));
			
			add(fat);
			fat.setBounds(210, 40, 80, 30);
			fat.setFont(new Font("맑은 고딕", Font.BOLD, 14));
			
			add(kcal);
			kcal.setBounds(310, 40, 80, 30);
			kcal.setFont(new Font("맑은 고딕", Font.BOLD, 14));
			
			add(tfCar);
			tfCar.setBounds(10, 80, 80, 30);
			
			add(tfProtein);
			tfProtein.setBounds(110, 80, 80, 30);
			
			add(tfFat);
			tfFat.setBounds(210, 80, 80, 30);
			
			add(tfKcal);
			tfKcal.setBounds(310, 80, 80, 30);
		}
	}
	
	public void setComponent() {
//		pictureBox = new JLabel();
//		add(pictureBox);
//
//		pictureBox.setOpaque(true);
//		pictureBox.setBackground(Color.LIGHT_GRAY);
		
		menuName.setBounds(175, 225, 150, 30);
		menuName.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		add(menuName);

		menuPrice.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		menuPrice.setBounds(175, 305, 150, 30);
		add(menuPrice);
		
		tfName.setBounds(175, 260, 150, 30);
		add(tfName);
		
		tfPrice.setBounds(175,340 , 150, 30);
		add(tfPrice);

		icon = new ImageIcon("images/titlebar.png");
		img = icon.getImage();
		add(menuImg);
		menuImg.setBounds(165,30, 170, 180);
		menuImg.setBackground(Color.LIGHT_GRAY);
		
		add(np);
		np.setLocation(50, 395);
		np.setLayout(null);
	}
	
	public void setDisplay() {
		setUndecorated(true);
		setVisible(true);
		setSize(500,600);
		this.getContentPane().setBackground(Color.white);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
	}
	
}