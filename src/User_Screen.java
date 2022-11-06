import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class User_Screen extends JFrame{
	private JPanel top;
	private JPanel mid;
	private JPanel bot;

	
	public User_Screen(){
		setDisplay();
		setBasePanel();
	}
	
	public void setBasePanel() {
		top = new JPanel();
		add(top);
		top.setVisible(true);
		top.setLayout(null);
		top.setBackground(Color.BLUE);
		top.setBounds(0,0,700, 50);
				
		mid = new JPanel();
		add(mid);
		mid.setBackground(Color.CYAN);
		mid.setBounds(0, 50, 700, 400);
		
		bot = new JPanel();
		add(bot);
		bot.setBackground(Color.GRAY);
		mid.setBounds(0, 450, 700, 450);
	}
	public void setSubPanel() {
		
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
