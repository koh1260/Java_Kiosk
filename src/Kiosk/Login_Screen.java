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

public class Login_Screen extends JFrame implements ActionListener{
	private static Connection con;
	private static PreparedStatement ps1;
	private static ResultSet rs1;
	private static PreparedStatement ps2;
	private static ResultSet rs2;
	
	private static String db_url = "jdbc:mysql://localhost:13306/java_kiosk";
	private static String db_user = "root";
	private static String db_pw = "1306";
	
	private JLabel userLabel;
	private JLabel Pw;
	private JTextField userTextF;
	private JPasswordField PwF;
	private JButton LoginB;
	private JButton Sign_upB;
	
	//생성자. 
	public Login_Screen() {
	    init();
	    setDisplay();
	    setLocationAndSize();
	    addActionEvent();
	    
	    
		add(userLabel);
		add(Pw);
		add(userTextF);
		add(PwF);
		add(LoginB);
		add(Sign_upB);
	}
	
	
	private void init() {
		userLabel = new JLabel("학번");
		userLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		Pw = new JLabel("비밀번호");
		Pw.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		userTextF = new JTextField();
		PwF = new JPasswordField();
		LoginB = new JButton(new ImageIcon("images/log.png"));
		LoginB.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		LoginB.setBorderPainted(false);
		LoginB.setFocusPainted(false);	
		
		Sign_upB = new JButton(new ImageIcon("images/sign.png"));
		Sign_upB.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		Sign_upB.setBorderPainted(false);
		Sign_upB.setFocusPainted(false);	
	}
	
	//화면 구성. 
	public void setDisplay() {
		Container c = getContentPane();
		setSize(700, 900);
		setLayout(null);
		getRootPane().setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.LIGHT_GRAY));
		c.setBackground(Color.white);
		setUndecorated(true);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
	}
	
	//컨텐츠 배치, 사이즈. 
	public void setLocationAndSize() {
		userLabel.setBounds(150,427,100,40);
	    Pw.setBounds(150,490,100,40);
	    userTextF.setBounds(275,427,170,40);
	    PwF.setBounds(275,490,170,40);
	    LoginB.setBounds(275,550,80,32);	
	    Sign_upB.setBounds(363,550,80,32);
	}

	//이벤트.
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == LoginB) {
			String userID = userTextF.getText();
			String PW = new String(PwF.getPassword());
			
			if(userID.length() == 0) {
				JOptionPane.showMessageDialog(this, "아이디를 입력하세요.");
			}else if(PW.length() == 0) {
				JOptionPane.showMessageDialog(this, "비밀번호를 입력하세요.");
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
					
					System.out.println("연결 성공.");
					
					if(rs1.next()) {
						if(PW.equals(rs1.getString(1))) {
							new User_Screen();
							dispose();
						}else {
							JOptionPane.showMessageDialog(this, "비밀번호가 틀렸습니다.");
						}
					}else if(rs2.next()) {
						if(PW.equals(rs2.getString(1))){
							new Admin_Screen();
							dispose();
						}else {
							JOptionPane.showMessageDialog(this, "비밀번호가 틀렸습니다.");
						}
					}else {
						JOptionPane.showMessageDialog(this, "존재하지 않는 학번입니다.");
					}
				}catch(ClassNotFoundException e1) {
					e1.printStackTrace();
				}catch(SQLException e1) {
//					System.out.println("연결 실패.");
					e1.printStackTrace();
				}
			}
		}
		
		if(e.getSource() == Sign_upB) {
			new Sign_up_Screen();
		}
	}
	 
	public void addActionEvent() {
		LoginB.addActionListener(this);
		Sign_upB.addActionListener(this);
	}
}