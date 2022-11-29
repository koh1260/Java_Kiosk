package Kiosk;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Admin_Screen extends JFrame {
   
   private JPanel contentPane;
   private JButton MemberBtn;
   private JButton OrderBtn;
   
   public Admin_Screen() {
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setTitle("AdminPage");
      setSize(700, 900);
      setLocationRelativeTo(null);
      contentPane = new JPanel();
      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
      setContentPane(contentPane);
      contentPane.setLayout(null);

      MemberBtn = new JButton("회원 관리");
      MemberBtn.setBounds(70, 60, 550, 250);
      contentPane.add(MemberBtn);
      
      OrderBtn = new JButton("주문 관리");
      OrderBtn.setBounds(70, 370, 550, 250);
      contentPane.add(OrderBtn);
      OrderBtn.addActionListener(new ActionListener() {
    	  public void actionPerformed(ActionEvent e) {
    		  new MenuManage_Screen();
    		  dispose();
    	  }
      });
      
      setVisible(true);
   }
}