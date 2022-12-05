package Kiosk;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
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
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login_Screen extends JFrame{
	private static Connection con;
	private static PreparedStatement ps1;
	private static ResultSet rs1;
	private static PreparedStatement ps2;
	private static ResultSet rs2;
	
	JLabel top= new JLabel(new ImageIcon(getClass().getClassLoader().getResource("logintop.png")));
	private JLabel userLabel;
	private JLabel Pw;
	private JTextField userTextF;
	private JPasswordField PwF;
	private JButton LoginB;
	private JButton Sign_upB;
	
	//생성자. 
	public Login_Screen() {
	    setDisplay();
	    init();
	    setLocationAndSize();
	}
	
	private void init() {
		add(top);
		top.setBounds(0,0,700,250);
		top.setOpaque(true);
		top.setBackground(Color.LIGHT_GRAY);
		
		userLabel = new JLabel("학번");
		add(userLabel);
		userLabel.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		Pw = new JLabel("비밀번호");
		add(Pw);
		Pw.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		userTextF = new JTextField();
		add(userTextF);
		userTextF.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		
		PwF = new JPasswordField();
		add(PwF);
		PwF.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		
		LoginB = new JButton(new ImageIcon(getClass().getClassLoader().getResource("log.png")));
		add(LoginB);
		LoginB.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		LoginB.setBorderPainted(false);
		LoginB.setFocusPainted(false);	
		LoginB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userID = userTextF.getText();
				String PW = new String(PwF.getPassword());
				
				if(userID.length() == 0) {
					JOptionPane.showMessageDialog(null, "아이디를 입력하세요.");
				}else if(PW.length() == 0) {
					JOptionPane.showMessageDialog(null, "비밀번호를 입력하세요.");
				}else {
					try {
						Class.forName("com.mysql.cj.jdbc.Driver");
						con = DriverManager.getConnection(ex.db_url, ex.db_user, ex.db_pw);
						ps1 = con.prepareStatement("select PW from userAccount where ID=?");
						ps1.setString(1, userID.trim());
						rs1 = ps1.executeQuery();
						
						ps2 = con.prepareStatement("select PW from adminAccount where ID=?");
						ps2.setString(1, userID.trim());
						rs2 = ps2.executeQuery();
						if(rs1.next()) {
							if(PW.equals(rs1.getString(1))) {
								new User_Screen();
								dispose();
							}else {
								JOptionPane.showMessageDialog(null, "비밀번호가 틀렸습니다.");
							}
						}else if(rs2.next()) {
							if(PW.equals(rs2.getString(1))){
								new Admin_Screen();
								dispose();
							}else {
								JOptionPane.showMessageDialog(null, "비밀번호가 틀렸습니다.");
							}
						}else {
							JOptionPane.showMessageDialog(null, "존재하지 않는 학번입니다.");
						}
						
						con.close();
						ps1.close();
						ps2.close();
						rs1.close();
						rs2.close();
					}catch(ClassNotFoundException e1) {
						e1.printStackTrace();
					}catch(SQLException e1) {
						System.out.println("fail");
						e1.printStackTrace();
					}
				}
			}
		});
		
		Sign_upB = new JButton(new ImageIcon(getClass().getClassLoader().getResource("sign.png")));
		add(Sign_upB);
		Sign_upB.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		Sign_upB.setBorderPainted(false);
		Sign_upB.setFocusPainted(false);	
		Sign_upB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sign_up_Screen si = new Sign_up_Screen();
				si.setVisible(true);
			}
		});
	}
	
	//화면 구성. 
	public void setDisplay() {
		Container c = getContentPane();
		setSize(700, 900);
		setLayout(null);
		getRootPane().setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.LIGHT_GRAY));
		c.setBackground(Color.white);
//		setUndecorated(true);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
	}
	
	//컨텐츠 배치, 사이즈. 
	public void setLocationAndSize() {
		userLabel.setBounds(150,407,100,32);
	    Pw.setBounds(150,470,100,32);
	    userTextF.setBounds(275,407,170,32);
	    PwF.setBounds(275,470,170,32);
	    LoginB.setBounds(275,530,80,32);	
	    Sign_upB.setBounds(363,530,80,32);
	}
}