package Kiosk;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class OrderCom_Screen extends JFrame{
	JLabel logo = new JLabel();
	JLabel orderComTx = new JLabel();
	JLabel orderNumTx = new JLabel();
	JLabel orderNum = new JLabel();
	JLabel waitTx = new JLabel();
	JLabel initTx = new JLabel();
	
	public OrderCom_Screen() {
		setDisplay();
		setLabels();
	}
	
	public void setDisplay() {		
		setUndecorated(true);
		setVisible(true);
		setSize(700, 900);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
	}
	
	public void setLabels() {
		add(logo);
		logo.setBounds(300, 20, 200, 200);
		logo.setOpaque(true);
		logo.setBackground(Color.LIGHT_GRAY);
		
		add(orderComTx);
		orderComTx.setBounds(300, 240, 450, 120);
		orderComTx.setOpaque(true);
		orderComTx.setBackground(Color.LIGHT_GRAY);
		
		add(orderNumTx);
		orderNumTx.setBounds(300, 390, 150, 200);
		orderNumTx.setOpaque(true);
		orderNumTx.setBackground(Color.LIGHT_GRAY);
		
		add(orderNum);
		orderNum.setBounds(300, 605, 300, 100);
		orderNum.setOpaque(true);
		orderNum.setBackground(Color.LIGHT_GRAY);
		
		add(waitTx);
		waitTx.setBounds(300, 720, 300, 100);
		waitTx.setOpaque(true);
		waitTx.setBackground(Color.LIGHT_GRAY);
		
		add(initTx);
		initTx.setBounds(300, 840, 300, 50);
		initTx.setOpaque(true);
		initTx.setBackground(Color.LIGHT_GRAY);
	}
}
