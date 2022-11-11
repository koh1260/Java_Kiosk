package Kiosk;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Initial_Screen extends JFrame implements ActionListener{
	private JButton b; 

	public Initial_Screen(){
		init();
		setDisplay();
		b_setting();
		addActionEvent();
	}
	
	private void init() {
		b = new JButton(new ImageIcon("images/init.png"));
	}
	public void b_setting() {
		b.setBounds(0,0,700, 900);
		b.setBorderPainted(false);
		b.setFocusPainted(false);	
	}
	
	public void setDisplay() {		
		setTitle("초기 화면");
		setVisible(true);
		setSize(700, 900);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		
		add(b);
	}
	
	public void addActionEvent() {
		b.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == b) {
			Login_Screen l = new Login_Screen();
			setVisible(false);
			l.setVisible(true);
		}
	}
}
