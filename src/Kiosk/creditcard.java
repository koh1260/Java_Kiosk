package Kiosk;

import javax.swing.*;
import java.awt.*;


public class creditcard extends JFrame {
   private Font f1;
   private Font f2;
   private JLabel a,b;
   
   public creditcard() {
      
      setTitle("카드 결제창");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      Container c = getContentPane();
      c.setLayout(new BorderLayout());
      JPanel panel1 = new JPanel();
      JPanel panel2 = new JPanel();
      JPanel panel3 = new JPanel();
      f1 = new Font("맑은 고딕", Font.BOLD, 43);
      f2 = new Font("맑은 고딕", Font.BOLD, 30);
      a = new JLabel("동의대학교 학생식당 키오스크");
      
      
      ImageIcon img = new ImageIcon("image/card.jpg");
      JLabel imageLabel = new JLabel(img);
      b = new JLabel("결제중입니다. 카드를 뽑지마세요.");
      
      JLabel quest = new JLabel();
      quest.setText("결제가 완료되었습니다.");
      quest.setForeground(Color.black);
      quest.setFont(new Font("맑은 고딕", Font.BOLD, 30));
      
   
      a.setFont(f1);
      b.setFont(f2);
      a.setForeground(Color.WHITE);
      c.setBackground(Color.lightGray);
      
      c.add(panel1, BorderLayout.NORTH);
      c.add(panel2, BorderLayout.CENTER);
      c.add(panel3, BorderLayout.SOUTH);
      panel1.setBackground(Color.pink);
      panel1.add(a);
      panel2.setLayout(null);
      c.add(imageLabel);
      panel3.setBackground(Color.LIGHT_GRAY);
      panel3.add(b);
   
   
       setSize(670,600);
      setVisible(true);
      try {
         Thread.sleep(3000);
      }catch(InterruptedException ie) {
      }
      panel3.remove(b);
      setVisible(true);
        panel3.add(quest);
        setVisible(true);
        try {
         Thread.sleep(2000);
      } catch (InterruptedException e) {
         
      }
      
      
      
      setVisible(false);
       }
   public static void main(String[] args) {
      new creditcard();
   }
   

}