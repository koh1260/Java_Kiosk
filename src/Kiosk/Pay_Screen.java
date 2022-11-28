package Kiosk;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;



public class Pay_Screen extends JFrame {
	public Menu[] menus = new Menu[6];
	
   public Pay_Screen(Menu[] menus) {
      // 기본 설정
	   this.menus = menus;
	   setVisible(true);
	   setSize(700, 900);
	   setLayout(null);
	   setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   setResizable(false);
      setTitle("결제 수단 선택");
      setLocationRelativeTo(null);
      Container c = getContentPane();
      c.setLayout(null);
      
      // 배경 설정
      JPanel pay_background = new JPanel();
      pay_background.setLayout(null);
      pay_background.setBackground(Color.white);
      pay_background.setBounds(0, 0, 700, 900);
      c.add(pay_background);
      
      // 배경 윗부분
      JPanel pay_north = new JPanel();
      pay_north.setLayout(null);
      pay_north.setBackground(new Color(107,167,240));
      pay_north.setBounds(0, 0, 700, 150);
      pay_background.add(pay_north);
      
      // 배경 윗부분 - 제목
      JLabel title_label = new JLabel("결제 수단 선택");
      title_label.setBounds(10, 15, 410, 60);
      title_label.setForeground(Color.white);
      title_label.setFont(new Font("맑은 고딕", Font.BOLD, 60));
      pay_north.add(title_label);
      
      // 배경 윗부분 - 아이콘(동의대학교 로고)
      ImageIcon logo_icon = new ImageIcon("images/logo.png");
      JLabel logo_label = new JLabel(logo_icon);
      logo_label.setBounds(540, 10, 130, 130);
      logo_label.setForeground(Color.white);
      pay_north.add(logo_label);
      
      // 배경 윗부분2
      JPanel pay_north2 = new JPanel();
      pay_north2.setLayout(null);
      pay_north2.setBackground(new Color(195, 195, 195));
      pay_north2.setBounds(0, 150, 700, 50);
      pay_background.add(pay_north2);
      
      // 배경 윗부분2 - 멘트
      JLabel title_label2 = new JLabel("원하시는 결제방법을 선택해주세요");
      title_label2.setBounds(0, 0, 700, 50);
      title_label2.setFont(new Font("맑은 고딕", Font.BOLD, 20));
      title_label2.setHorizontalAlignment(JLabel.CENTER);
      pay_north2.add(title_label2);
      
      // 카드 결제 버튼
      JButton card_btn = new JButton();
      card_btn.setLayout(null);
      card_btn.setBorder(BorderFactory.createLineBorder(Color.white));
      card_btn.setBounds(32, 235, 622, 275);
      card_btn.setBackground(Color.white);
      card_btn.addActionListener(new ActionListener() {
    	  public void actionPerformed(ActionEvent e) {
    		  new creditcard();
    	  }
      });
      pay_background.add(card_btn);
      
      // 카드 결제 버튼 - 아이콘(카드 결제)
      ImageIcon card_icon = new ImageIcon("images/card.png");
      JLabel card_icon_label = new JLabel(card_icon);
      card_icon_label.setLayout(null);
      card_icon_label.setBounds(0, 12, 250, 250);
      card_btn.add(card_icon_label);
      
      // 카드 결제 버튼 - 멘트1
      JLabel card_label = new JLabel("카드 결제");
      card_label.setLayout(null);
      card_label.setBounds(300, 45, 300, 60);
      card_label.setForeground(new Color(51, 151, 232));
      card_label.setFont(new Font("맑은 고딕", Font.BOLD, 60));
      card_btn.add(card_label);
      
      // 카드 결제 버튼 - 멘트2
      JLabel card_label2 = new JLabel("신용/체크카드를 사용하여 결제");
      card_label2.setLayout(null);
      card_label2.setBounds(247, 170, 400, 60);
      card_label2.setFont(new Font("맑은 고딕", Font.BOLD, 25));
      card_btn.add(card_label2);
      
      // 현금 결제 버튼
      JButton cash_btn = new JButton();
      cash_btn.setLayout(null);
      cash_btn.setBorder(BorderFactory.createLineBorder(Color.white));
      cash_btn.setBounds(32, 555, 622, 275);
      cash_btn.setBackground(Color.white);
      cash_btn.addActionListener(new ActionListener() {
    	  public void actionPerformed(ActionEvent e) {
    		  new MoneyFrame();
    	  }
      });
      pay_background.add(cash_btn);
      
      // 현금 결제 버튼 - 아이콘(현금 결제)
      ImageIcon cash_icon = new ImageIcon("images/money.png");
      JLabel cash_icon_label = new JLabel(cash_icon);
      cash_icon_label.setLayout(null);
      cash_icon_label.setBounds(0, 12, 250, 250);
      cash_btn.add(cash_icon_label);
      
      // 현금 결제 버튼 - 멘트1
      JLabel cash_label = new JLabel("현금 결제");
      cash_label.setLayout(null);
      cash_label.setBounds(300, 45, 300, 60);
      cash_label.setForeground(new Color(68, 192, 134));
      cash_label.setFont(new Font("맑은 고딕", Font.BOLD, 60));
      cash_btn.add(cash_label);
      
      // 현금 결제 버튼 - 멘트2
      JLabel cash_label2 = new JLabel("현금를 사용하여 결제");
      cash_label2.setLayout(null);
      cash_label2.setBounds(309, 170, 400, 60);
      cash_label2.setFont(new Font("맑은 고딕", Font.BOLD, 25));
      cash_btn.add(cash_label2);
      
      setSize(700, 900);
      setVisible(true);
   }
}