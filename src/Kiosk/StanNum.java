package Kiosk;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class StanNum extends JFrame{
	public JLabel orderP = new JLabel("[ 주 문 서 ]",JLabel.CENTER);
	public JLabel standNum = new JLabel("대기 번호" + "\n" + ex.number,JLabel.CENTER);
	public JLabel dateTime = new JLabel(" ", JLabel.CENTER);
	public JLabel menuList = new JLabel(" ", JLabel.CENTER);
	public JLabel totalM = new JLabel(" ", JLabel.CENTER);
	public JButton exit = new JButton("X");
	
	String menu_text = "<html>";
	int total_m;
 
	public StanNum(){
		for(Menu menu:OrderCheck_Screen.menus) {
			if(menu.count != 0) {
				menu_text += menu.name + ": " + menu.count + "개<br>";
				total_m += (menu.count * menu.price);
			}
		}
	
	setDisplay();
	setComp();
	}
	public void setDisplay() {
//		setUndecorated(true);
		setVisible(true);
		setSize(400,500);
		setResizable(false);
		setLocationRelativeTo(null);
		setLayout(null);
	}
	
	public void setComp() {
		add(exit);
		exit.setBounds( 368, 0, 32, 32);
		exit.setBackground(Color.red);
		exit.setFont(new Font("맑은 고딕" ,Font.BOLD, 8));
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Initial_Screen();
				dispose();
			}
		});
		
		add(orderP);
		orderP.setBounds(115, 15, 170, 30);
		orderP.setOpaque(true);
		orderP.setBackground(Color.LIGHT_GRAY);
		
		add(standNum);
		standNum.setBounds(50, 60 ,300, 140);
		standNum.setOpaque(true);
		standNum.setBackground(Color.LIGHT_GRAY);
		standNum.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		
		Date date = new Date();		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd a hh:mm:ss zzz");
		String datetime = df.format(date);
		
		add(dateTime);
		dateTime.setBounds(35, 215, 330, 40);
		dateTime.setOpaque(true);
		dateTime.setBackground(Color.LIGHT_GRAY);
		dateTime.setText(datetime);
		dateTime.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		
		add(menuList);
		menuList.setBounds(35, 265 ,330, 150);
		menuList.setOpaque(true);
		menuList.setBackground(Color.LIGHT_GRAY);
		menuList.setText(menu_text);
		menuList.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		
		add(totalM);
		totalM.setBounds(35, 430 ,330, 55);
		totalM.setOpaque(true);
		totalM.setBackground(Color.LIGHT_GRAY);
		totalM.setText("합계: " + total_m);
		totalM.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		
	}
}
