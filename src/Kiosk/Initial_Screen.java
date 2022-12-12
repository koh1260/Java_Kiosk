package Kiosk;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Initial_Screen extends JFrame{
	private JButton b = new JButton(new ImageIcon(getClass().getClassLoader().getResource("init_screen.png")));; 

	public Initial_Screen(){
		setDisplay();
		b_setting();
	}

	public void b_setting() {
		b.setBounds(0,0,700, 900);
		b.setBorderPainted(false);
		b.setFocusPainted(false);	
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Login_Screen();
				dispose();
			}
		});
		add(b);
	}
	
	public void setDisplay() {		
//		setUndecorated(true);
		setVisible(true);
		setSize(700, 900);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		
//		add(b);
//		b.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				new Login_Screen();
//				dispose();
//			}
//		});
	}
}
