package Kiosk;

import javax.swing.*;
import java.awt.*;

public class MoneyFrame extends JFrame {
      private Font f1;
      private Font f2;
      private Font f3;
      private Font f4;
      private JLabel lbl1, lbl2, lbl3, lbl4;
      
   public MoneyFrame() {
      setTitle("현금 결제 창");
         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         Container c = getContentPane();
         c.setLayout(new BorderLayout());
         
         JPanel pa1 = new JPanel();
         JPanel pa2 = new JPanel();
         JPanel pa3 = new JPanel();
         
         f1 = new Font("맑은 고딕", Font.BOLD, 40);
         f2 = new Font("맑은 고딕", Font.BOLD, 30);
         f3 = new Font("맑은 고딕", Font.BOLD, 30);
         f4 = new Font("맑은 고딕", Font.BOLD, 30);
         
         lbl1 = new JLabel("동의대학교 학생식당 키오스크");
         
         ImageIcon img = new ImageIcon("img/현금결제.jpg");
         JLabel imageLabel = new JLabel(img);
         
         lbl2 = new JLabel("현금을 넣어주세요.");
         
         lbl3 = new JLabel("결제 중입니다.");
         
         lbl4 = new JLabel("결제가 완료되었습니다.");

         lbl1.setFont(f1);
         lbl2.setFont(f2);
         lbl3.setFont(f3);
         lbl4.setFont(f4);
         
         lbl1.setForeground(Color.WHITE);
         c.setBackground(Color.WHITE);
         
         c.add(pa1, BorderLayout.NORTH);
         c.add(pa2, BorderLayout.CENTER);
         c.add(pa3, BorderLayout.SOUTH);
         pa1.setBackground(Color.BLUE);
         pa1.add(lbl1);
         pa2.setLayout(null);
         c.add(imageLabel);
         pa3.setBackground(Color.LIGHT_GRAY);
         pa3.add(lbl2);
      
          setSize(670,600);
          
         setVisible(true);
         try {
            Thread.sleep(3000);
         } catch(InterruptedException ie) {
            
         }
         pa3.remove(lbl2);
         
         setVisible(true);
         
           pa3.add(lbl3);
           setVisible(true);
           try {
            Thread.sleep(2500);
         } catch (InterruptedException e) {
            
         }
           pa3.remove(lbl3);
           
            setVisible(true);
           
            pa3.add(lbl4);
            setVisible(true);
              try {
               Thread.sleep(2000);
            } catch (InterruptedException e) {
               
            }
            
           setVisible(true);
         
         setVisible(false);
          }
      public static void main(String[] args) {
         new MoneyFrame(); 
      }

}