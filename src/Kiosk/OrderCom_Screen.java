package Kiosk;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

public class OrderCom_Screen extends JFrame{
	JLabel logo = new JLabel(new ImageIcon("images/check_logo.png"));
	JLabel orderComTx = new JLabel("주문이 완료되었습니다!", JLabel.CENTER);
	JLabel orderNumTx = new JLabel("주문 번호", JLabel.CENTER);
	JLabel orderNum = new JLabel("0 0 1", JLabel.CENTER);
	JLabel waitTx = new JLabel("잠시만 기다려주세요.", JLabel.CENTER);
	JLabel initTx = new JLabel("6초 뒤 처음 화면으로 이동합니다.", JLabel.CENTER);
	
	public OrderCom_Screen() {
		Container c = getContentPane();
		c.setBackground(Color.WHITE);
		setDisplay();
		setLabels();
		
		 class setTx implements ActionListener
         {
         	int n=6;
         	
         	public void actionPerformed(ActionEvent e) {
         		n--;
         		if(n == -1) {
         			try {
         				Thread.sleep(800);
         			}catch(InterruptedException e1) {
         				
         			}
         			new Initial_Screen();
        			dispose();
         		}
         		initTx.setText(n + "초 뒤 처음 화면으로 이동합니다.");
         	}
         }
         
         ActionListener listener = new setTx();
         Timer t = new Timer(1200, listener);
         t.start();
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
		logo.setBounds(300, 150, 100, 100);
		logo.setOpaque(true);
		logo.setBackground(Color.WHITE);
		
		add(orderComTx);
		orderComTx.setBounds(0, 280,700, 50);
		orderComTx.setOpaque(true);
		orderComTx.setBackground(Color.WHITE);
		orderComTx.setFont(new Font("맑은 고딕", Font.BOLD, 45));
		
		add(orderNumTx);
		orderNumTx.setBounds(0, 370, 700, 30);
		orderNumTx.setOpaque(true);
		orderNumTx.setBackground(Color.WHITE);
		orderNumTx.setFont(new Font("맑은 고딕", Font.BOLD, 23));
		orderNumTx.setForeground(new Color(107,167,240));
		
		add(orderNum);
		orderNum.setBounds(0, 405, 700, 90);
		orderNum.setOpaque(true);
		orderNum.setBackground(Color.WHITE);
		orderNum.setFont(new Font("맑은 고딕", Font.BOLD, 95));
		orderNum.setForeground(new Color(107,167,240));
		orderNum.setText("0 " + "0 " + ex.stan_number);
		
		add(waitTx);
		waitTx.setBounds(0, 520, 700, 100);
		waitTx.setOpaque(true);
		waitTx.setBackground(Color.WHITE);
		waitTx.setFont(new Font("맑은 고딕", Font.BOLD, 35));
		
		add(initTx);
		initTx.setBounds(50, 765, 600, 40);
		initTx.setOpaque(true);
		initTx.setBackground(Color.WHITE);
		initTx.setFont(new Font("맑은 고딕", Font.BOLD, 35));
	}
}
