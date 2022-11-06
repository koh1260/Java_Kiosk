import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class User_Screen extends JFrame{
	private JPanel top;
	private JPanel mid;
	private JPanel basket;
	private JPanel control_panel;
	private JButton control_btn;

	
	public User_Screen(){
		setDisplay();
		setBasePanel();

	}
	
	public void setBasePanel() {
		top = new JPanel();
		add(top);
		top.setLayout(null);
		top.setBackground(Color.BLUE);
		top.setBounds(0,0,700, 50);
				
		mid = new JPanel();
		add(mid);
		mid.setLayout(null);
		mid.setBackground(Color.CYAN);
		mid.setBounds(0, 50, 700, 400);
		
		basket = new JPanel();
		add(basket);
		basket.setLayout(new GridLayout(1,7));
		basket.setBackground(Color.ORANGE);
		basket.setBounds(0, 450, 515, 450);
		
		control_panel = new JPanel();
		add(control_panel);
		control_panel.setLayout(null);
		control_panel.setBackground(Color.GREEN);
		control_panel.setBounds(515, 450, 185, 450);

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
