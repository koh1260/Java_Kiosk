package Kiosk;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class PaySelect_Screen extends JFrame {
      private Font f1;
      private Font f2;
      private Font f3;
      private Font f4;
      private JLabel lbl1, lbl2, lbl3, lbl4;
      
   public PaySelect_Screen() {
	   setTitle("현금 결제 창");
	   setVisible(true);
	   setSize(670,600);
         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         setLocationRelativeTo(null);
         Container c = getContentPane();
         c.setLayout(new BorderLayout());
         
         JPanel pa1 = new JPanel();
         JPanel pa2 = new JPanel();
         JPanel pa3 = new JPanel();
         
         f1 = new Font("맑은 고딕", Font.BOLD, 40);
         f2 = new Font("맑은 고딕", Font.BOLD, 30);
         f3 = new Font("맑은 고딕", Font.BOLD, 30);
         f4 = new Font("맑은 고딕", Font.BOLD, 30);
         
         
         lbl1 = new JLabel("현금 결제");
         
         ImageIcon img = new ImageIcon("images/money.png");
         JLabel imageLabel = new JLabel(img);
         
         lbl2 = new JLabel("현금을 넣어주세요.");
         


         lbl1.setFont(f1);
         lbl2.setFont(f2);
         
         lbl1.setForeground(Color.WHITE);
         c.setBackground(Color.WHITE);
         
         c.add(pa1, BorderLayout.NORTH);
         c.add(pa2, BorderLayout.CENTER);
         c.add(pa3, BorderLayout.SOUTH);
         pa1.setBackground(new Color(82, 113, 255));
         pa1.add(lbl1);
         pa2.setLayout(null);
         c.add(imageLabel);
         pa3.setBackground(Color.LIGHT_GRAY);
         pa3.add(lbl2);
          }
}