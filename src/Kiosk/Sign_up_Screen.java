package Kiosk;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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

public class Sign_up_Screen extends JFrame implements ActionListener{	
	private static Connection con;
	private static PreparedStatement ps1;
	private static ResultSet rs1;
	private static PreparedStatement ps2;
	private static ResultSet rs2;
	
	private static String db_url = "jdbc:mysql://localhost:13306/java_kiosk";
	private static String db_user = "root";
	private static String db_pw = "1306";
	
	private JLabel Name;
	private JLabel StudentID;
	private JLabel Pw;
	private JLabel Phone_N;
	private JTextField Name_tf;
	private JTextField StudentID_tf;
	private JPasswordField Pw_tf;
	private JTextField Phone_N_tf;
	private JButton Ok;
	
	class JFrameWindowClosingEventHandler extends WindowAdapter {
		public void windowClosing(WindowEvent e) {
			JFrame frame = (JFrame)e.getWindow();
			frame.dispose();
		}
	}
	
	public Sign_up_Screen() {
		init();
		setDisplay();
		setLocationAndSize();
		addActionEvent();
	}
	
	public void init() {
		Name = new JLabel("이름");
		Name.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		StudentID = new JLabel("학번");
		StudentID.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		Pw = new JLabel("비밀번호");
		Pw.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		Phone_N = new JLabel("전화번호");
		Phone_N.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		
		Name_tf = new JTextField();
		StudentID_tf = new JTextField();
		Pw_tf = new JPasswordField();
		Phone_N_tf = new JTextField();
		
		Ok = new JButton(new ImageIcon(getClass().getClassLoader().getResource("signok.png")));
		Ok.setBorderPainted(false);
		Ok.setFocusPainted(false);
	}
	public void setDisplay() {
		add(Name);
		add(StudentID);
		add(Pw);
		add(Phone_N);
		
		add(Name_tf);
		add(StudentID_tf);
		add(Pw_tf);
		add(Phone_N_tf);
		
		add(Ok);
		
		Container c = getContentPane();
		c.setBackground(Color.white);
		getRootPane().setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.LIGHT_GRAY));
//		setUndecorated(true);
		setTitle("회원가입");
		setVisible(true);
		setSize(400, 550);
		setLayout(null);
		addWindowListener(new JFrameWindowClosingEventHandler());
		setResizable(false);
		setLocationRelativeTo(null);
	}
	
	public void setLocationAndSize() {
		Name.setBounds(85,120,100,40);
		StudentID.setBounds(85,190,100,40);
		Pw.setBounds(85,260,260,40);
		Phone_N.setBounds(85,330,330,40);
		
		Name_tf.setBounds(143,120,120,40);
		StudentID_tf.setBounds(143,190,120,40);
		Pw_tf.setBounds(143,260,120,40);
		Phone_N_tf.setBounds(143,330,120,40);
		
		Ok.setBounds(152,415,100,40);
	}	
	
	public void addActionEvent() {
		Ok.addActionListener(this);
	}
	
	public static void DbConnect(){

	}
	
	@Override  
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == Ok) {
			String ID = StudentID_tf.getText();
			String pw = new String(Pw_tf.getPassword());
			String name = Name_tf.getText();
			String phone_number = Phone_N_tf.getText();
			
			if(ID.length() == 0 || pw.length() == 0 || name.length() == 0 || phone_number.length() == 0) {
				JOptionPane.showMessageDialog(this, "정보를 모두 입력하세요.");
			}else {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					con = DriverManager.getConnection(db_url, db_user, db_pw);
					System.out.println("conect.");
					ps1 = con.prepareStatement("select * from userAccount where ID=?");
					ps1.setString(1, ID.trim());
					rs1 = ps1.executeQuery();
					
					ps2 = con.prepareStatement("select * from userAccount where phoneNum=?");
					ps2.setString(1, phone_number.trim());
					rs2 = ps2.executeQuery();
					
					if(rs1.next()) {
						JOptionPane.showMessageDialog(this, "이미 존재하는 학번입니다.");
					}else if(rs2.next()) {
						JOptionPane.showMessageDialog(this, "이미 존재하는 전화번호입니다.");
					}else{
						ps1 = con.prepareStatement("insert into userAccount values(?,?,?,?)");
						ps1.setString(1, ID.trim());
						ps1.setString(2, pw.trim());
						ps1.setString(3, name.trim());
						ps1.setString(4, phone_number.trim());
						ps1.executeUpdate();
						
						con.close();
						JOptionPane.showMessageDialog(this, "회원가입 완료.");
						dispose();

					}                       
				}catch(SQLException | ClassNotFoundException e1) {
					System.out.println("fail.");
	
				}
			}
		}
	}
}
