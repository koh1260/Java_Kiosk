package Kiosk;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

//메뉴 객체 받아와서, 큰 JLabel하나 만들어서 해당 메뉴의 count가 1 이상일 시 출력.

public class OrderCheck_Screen extends JFrame{
	JPanel title = new JPanel();
	JLabel order_li = new JLabel();
	JLabel total = new JLabel();
	JLabel img = new JLabel();
	JButton ok = new JButton("OK");
	JButton cancel = new JButton("CON");
	
	String order_list = "<html>";
	
	class JFrameWindowClosingEventHandler extends WindowAdapter {
		public void windowClosing(WindowEvent e) {
			JFrame frame = (JFrame)e.getWindow();
			frame.dispose();
		}
	}

	public OrderCheck_Screen(Menu[] menus){
		setDisplay();

		for(Menu menu:menus) {
			if(menu.count != 0) {
				order_list += menu.name + ": " + menu.count + "개<br>";
			}
		}
		
		setComponent();

	}
	public void setComponent() {
		add(title);
		title.setBounds(0, 0, 670, 60);
		title.setBackground(Color.blue);

		add(img);
		img.setOpaque(true);
		img.setBounds(10, 70, 180, 425);
		img.setBackground(Color.LIGHT_GRAY);
		
		add(order_li);
		order_li.setOpaque(true);
		order_li.setBounds(200, 70, 460, 355);
		order_li.setBackground(Color.LIGHT_GRAY);
		order_li.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		order_li.setText(order_list);
		
		add(total);
		total.setOpaque(true);
		total.setBounds(200, 440, 460, 55);
		total.setBackground(Color.LIGHT_GRAY);
		
		add(ok);
		ok.setBounds(5, 505, 327, 95);
		ok.setBackground(Color.LIGHT_GRAY);
		
		add(cancel);
		cancel.setBounds(338, 505, 327, 95);
		cancel.setBackground(Color.LIGHT_GRAY);
	}
	public void setDisplay() {
		setUndecorated(true);
		setVisible(true);
		setSize(670,600);
		setResizable(false);
		setLocationRelativeTo(null);
		setLayout(null);
		addWindowListener(new JFrameWindowClosingEventHandler());
	}
}
