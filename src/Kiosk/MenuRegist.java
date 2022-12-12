package Kiosk;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MenuRegist extends JFrame {
	Connection con;
	PreparedStatement ps;
	
	menuImage menuImg = new menuImage();
	JLabel menuName = new JLabel("메뉴명", JLabel.CENTER);
	JLabel menuPrice = new JLabel("가격", JLabel.CENTER);
	JLabel pictureBox = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("titlebar.png")));
	
	JTextField tfName = new JTextField();
	JTextField tfPrice = new JTextField();
	JTextField tfCar = new JTextField();
	JTextField tfProtein = new JTextField();
	JTextField tfFat = new JTextField();
	JTextField tfKcal = new JTextField();
	
	BufferedImage bImage;
	ByteArrayOutputStream bos = new ByteArrayOutputStream();
	FileInputStream fis;
	
	ImageIcon icon;
	Image img;
	byte [] data;
	
	String imgPath;
	
	JButton btnImgsel;
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

		public NuPanel() {
			setSize(400, 100);
			setBackground(Color.LIGHT_GRAY);
			
			add(car);
			car.setBounds(10,10,80,30);
			car.setFont(new Font("맑은 고딕", Font.BOLD, 14));
			
			add(protein);
			protein.setBounds(110, 10, 80, 30);
			protein.setFont(new Font("맑은 고딕", Font.BOLD, 14));
			
			add(fat);
			fat.setBounds(210, 10, 80, 30);
			fat.setFont(new Font("맑은 고딕", Font.BOLD, 14));
			
			add(kcal);
			kcal.setBounds(310, 10, 80, 30);
			kcal.setFont(new Font("맑은 고딕", Font.BOLD, 14));
			
			add(tfCar);
			tfCar.setBounds(10, 45, 80, 30);
			tfCar.setFont(new Font("맑은 고딕", Font.BOLD, 13));
			tfCar.setHorizontalAlignment(JTextField.CENTER);
			
			add(tfProtein);
			tfProtein.setBounds(110, 45, 80, 30);
			tfProtein.setFont(new Font("맑은 고딕", Font.BOLD, 13));
			tfProtein.setHorizontalAlignment(JTextField.CENTER);
			
			add(tfFat);
			tfFat.setBounds(210, 45, 80, 30);
			tfFat.setFont(new Font("맑은 고딕", Font.BOLD, 13));
			tfFat.setHorizontalAlignment(JTextField.CENTER);
			
			add(tfKcal);
			tfKcal.setBounds(310, 45, 80, 30);
			tfKcal.setFont(new Font("맑은 고딕", Font.BOLD, 13));
			tfKcal.setHorizontalAlignment(JTextField.CENTER);
		}
	}
	
	public void setComponent() {
		pictureBox = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("demenu.png")));
		add(pictureBox);
		pictureBox.setOpaque(true);
		pictureBox.setBackground(Color.LIGHT_GRAY);
		pictureBox.setBounds(getBounds());
		pictureBox.setBounds(165,30, 170, 180);
		
		btnOk = new JButton(new ImageIcon(getClass().getClassLoader().getResource("dng.png")));
		btnOk.setBorderPainted(false);
		btnOk.setFocusPainted(false);	
		add(btnOk);
		btnOk.setBounds(205, 510, 90, 32);
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String menuQuery = "INSERT INTO menu (menu_num, menu_name, menu_price, menu_carbo, menu_protein, menu_fat, menu_kcal, image) values(?,?,?,?,?,?,?,?)";
				
				try {
					fis = new FileInputStream(imgPath);
					
					con = DriverManager.getConnection(ex.db_url, ex.db_user, ex.db_pw);
					ps = con.prepareStatement(menuQuery);
					ps.setInt(1, index);
					ps.setString(2, tfName.getText());
					ps.setInt(3, Integer.parseInt(tfPrice.getText()));
					ps.setInt(4, Integer.parseInt(tfCar.getText()));
					ps.setInt(5, Integer.parseInt(tfProtein.getText()));
					ps.setInt(6, Integer.parseInt(tfFat.getText()));
					ps.setInt(7, Integer.parseInt(tfKcal.getText()));
					ps.setBinaryStream(8, fis);
					
					ps.executeUpdate();

					
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				new MenuManage_Screen();
				dispose();
			}
		});
		
		btnImgsel = new JButton(new ImageIcon(getClass().getClassLoader().getResource("imgsel.png")));
		add(btnImgsel);
		btnImgsel.setBounds(133,30,32,32);
		btnImgsel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser(System.getProperty("user.home"));
				fc.addChoosableFileFilter(new FileNameExtensionFilter("Image files",
				          new String[] { "png", "jpg", "jpeg"}));
				fc.showOpenDialog(null);
				imgPath = fc.getSelectedFile().toString();
				
				try {
					bImage = ImageIO.read(new File(imgPath));
					Image chImg = bImage.getScaledInstance(170, 180, Image.SCALE_SMOOTH);
					pictureBox.setIcon(new ImageIcon(chImg));
					System.out.println(data);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				System.out.println(imgPath);
			}
		});
		
		menuName.setBounds(175, 225, 150, 30);
		menuName.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		add(menuName);

		menuPrice.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		menuPrice.setBounds(175, 305, 150, 30);
		add(menuPrice);
		
		tfName.setBounds(175, 260, 150, 30);
		add(tfName);
		tfName.setHorizontalAlignment(JTextField.CENTER);
		tfName.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		
		tfPrice.setBounds(175,340 , 150, 30);
		add(tfPrice);
		tfPrice.setHorizontalAlignment(JTextField.CENTER);
		tfPrice.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		
		icon = new ImageIcon(getClass().getClassLoader().getResource("titlebar.png"));
		
		add(np);
		np.setLocation(50, 390);
		np.setLayout(null);
	}
	
	public void setDisplay() {
		setVisible(true);
		setSize(500,600);
		this.getContentPane().setBackground(Color.white);
		getRootPane().setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.LIGHT_GRAY));
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
	}
}