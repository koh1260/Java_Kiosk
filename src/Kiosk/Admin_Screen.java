package Kiosk;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Admin_Screen extends JFrame {

	private JPanel contentPane;
	private JButton MemberBtn;
	private JButton OrderBtn;
	private JButton SalesBtn;
	private JLabel title = new JLabel(new ImageIcon("images/titlebar.png"));

	public Admin_Screen() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("AdminPage");
		setSize(700, 900);
		setLocationRelativeTo(null);
		setUndecorated(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		add(title);
		title.setBounds(0, 0, 700, 40);

		MemberBtn = new JButton(new ImageIcon("images/usermanage.png"));
		MemberBtn.setBounds(70, 100, 550, 200);
		MemberBtn.setBorderPainted(false);
		MemberBtn.setFocusPainted(false);
		contentPane.add(MemberBtn);

		OrderBtn = new JButton(new ImageIcon("images/menumanage.png"));
		OrderBtn.setBounds(70, 370, 550, 200);
		OrderBtn.setBorderPainted(false);
		OrderBtn.setFocusPainted(false);
		contentPane.add(OrderBtn);
		OrderBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new MenuManage_Screen();
				dispose();
			}
		});
		SalesBtn = new JButton(new ImageIcon("images/moneymanage.png"));
		add(SalesBtn);
		SalesBtn.setBounds(70, 640, 550, 200);
		SalesBtn.setBorderPainted(false);
		SalesBtn.setFocusPainted(false);
		SalesBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new SalesRecords_Screen();
				dispose();
			}
		});

		setVisible(true);
		this.getContentPane().setBackground(Color.white);
	}
}