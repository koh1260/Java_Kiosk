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

public class MoneyFrame extends JFrame {
      private Font f1;
      private Font f2;
      private Font f3;
      private Font f4;
      private JLabel lbl1, lbl2, lbl3, lbl4;
      
   public MoneyFrame() {
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
         
         String[] text = {"현금을 넣어주세요. .","현금을 넣어주세요. . .","결제 중입니다.", "결제 중입니다. .","결제 중입니다. . .",  "결제가 완료되었습니다.","결제가 완료되었습니다.","결제가 완료되었습니다."};
         
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
         pa1.setBackground(new Color(107,167,240));
         pa1.add(lbl1);
         pa2.setLayout(null);
         c.add(imageLabel);
         pa3.setBackground(Color.LIGHT_GRAY);
         pa3.add(lbl2);
      
          
          
         
         class setTx implements ActionListener
         {
         	int n=-1;
         	
         	public void actionPerformed(ActionEvent e) {
         		n++;
         		if(n == 6) {
         			try {
         				Thread.sleep(800);
         			}catch(InterruptedException e1) {
         				
         			}
         			ex.number++;
         			new OrderCom_Screen();
         			dispose();
         			return;
         		}
         		lbl2.setText(text[n]);
         	}
         }
         
         ActionListener listener = new setTx();
         Timer t = new Timer(800, listener);
         t.start();
         
         
         
//         try {
//            Thread.sleep(3000);
//         } catch(InterruptedException ie) {
//            
//         }
////         pa3.remove(lbl2);
////         
////         setVisible(true);
////         
////           pa3.add(lbl3);
//           lbl2.setText("결제 중입니다.");
//           try {
//            Thread.sleep(2500);
//         } catch (InterruptedException e) {
//            
//         }
////           pa3.remove(lbl3);
////           
////            setVisible(true);
////           
////            pa3.add(lbl4);
//           lbl2.setText("결제가 완료되었습니다.");
//            setVisible(true);
//              try {
//               Thread.sleep(2000);
//            } catch (InterruptedException e) {
//               
//            }
          }
}