package Kiosk;

import Kiosk.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;
import java.sql.*;
import java.util.ArrayList;

public class MemberEdit {
   
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;
    
   public MemberEdit(String tjsxorID) {
       JFrame frame = new JFrame();
       frame.getContentPane().setLayout(null);
       frame.getContentPane().setBackground(Color.white);
       frame.setLocation(192, 250);
       frame.setResizable(false);
       
       // 타이틀 판넬
       JPanel panel = new JPanel();
       panel.setBounds(0, 0, 700, 40);
       panel.setBackground(new Color(107, 167, 240));
       frame.getContentPane().add(panel);
       
      // 학번
       JLabel label0 = new JLabel("학번");
        label0.setBounds(40, 60, 120, 30);
        label0.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        frame.getContentPane().add(label0);
        JLabel jl = new JLabel(tjsxorID);
        jl.setBounds(125, 60, 120, 30);
        jl.setBackground(Color.white);
        jl.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        frame.getContentPane().add(jl);
        
        // 비밀번호
       JLabel label1 = new JLabel("비밀번호");
        label1.setBounds(25, 120, 120, 30);
        label1.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        frame.getContentPane().add(label1);
        JTextField tnwjd1 = new JTextField();
        tnwjd1.setBounds(120, 120, 150, 30);
        tnwjd1.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        tnwjd1.setBackground(Color.white);
        frame.getContentPane().add(tnwjd1);
        
        // 이름
       JLabel label2 = new JLabel("이름");
        label2.setBounds(40, 180, 120, 30);
        label2.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        frame.getContentPane().add(label2);
        JTextField tnwjd2 = new JTextField();
        tnwjd2.setBounds(120, 180, 150, 30);
        tnwjd2.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        tnwjd2.setBackground(Color.white);
        frame.getContentPane().add(tnwjd2);
        
        // 전화번호
       JLabel label3 = new JLabel("전화번호");
        label3.setBounds(25, 240, 120, 30);
        label3.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        frame.getContentPane().add(label3);
        JTextField tnwjd3 = new JTextField();
        tnwjd3.setBounds(120, 240, 150, 30);
        tnwjd3.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        tnwjd3.setBackground(Color.white);
        frame.getContentPane().add(tnwjd3);
        
      // 편집 버튼
       JButton vuswlq = new JButton(new ImageIcon(getClass().getClassLoader().getResource("edit.png")));
        vuswlq.setBounds(20, 305, 257, 45);
        vuswlq.setFont(new Font("맑은 고딕", Font.BOLD, 17));
        vuswlq.setForeground(Color.white);
        vuswlq.setBackground(new Color(107, 167, 240));
        vuswlq.setBorder(BorderFactory.createLineBorder(new Color(222, 222, 222)));
        frame.getContentPane().add(vuswlq);
        vuswlq.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
               String[] tnwjdwjdqh = { tnwjd1.getText(), tnwjd2.getText(), tnwjd3.getText()};
            String[] xpdlqmf = { "PW", "name", "phoneNum" };
            if(tnwjd1.getText().isEmpty() == true || tnwjd2.getText().isEmpty() == true || tnwjd3.getText().isEmpty() == true) {
               JOptionPane.showMessageDialog(null,"정보를 모두 입력해 주십시요.");
            }
            else {
               try {
                  Class.forName("com.mysql.cj.jdbc.Driver");
                     Connection con=DriverManager.getConnection(ex.db_url,ex.db_user,ex.db_pw);
                  for(int i=0; i<3; i++) {
                     ps = con.prepareStatement("update userAccount set " + xpdlqmf[i] + " = '" + tnwjdwjdqh[i] + "'  where ID = '" + tjsxorID + "'");
                        ps.executeUpdate();
                  }
                  ps.close();
                     con.close();
                 }catch(ClassNotFoundException e1) {
                        e1.printStackTrace();
               }catch(SQLException e1) {
                  // System.out.println("연결 실패.");
                  e1.printStackTrace();
               }
               JOptionPane.showMessageDialog(null,"편집이 완료되었습니다.");
               frame.dispose();
            }
         }
      });
        
        // 닫기 버튼
        JButton ekerl = new JButton("닫기");
        ekerl.setBounds(233, 366, 60, 28);
        ekerl.setFont(new Font("맑은 고딕", Font.BOLD, 12));
        ekerl.setForeground(Color.white);
        ekerl.setBackground(new Color(107, 167, 240));
        ekerl.setBorder(BorderFactory.createLineBorder(new Color(222, 222, 222)));
        frame.getContentPane().add(ekerl);
        ekerl.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent arg0) {
            frame.dispose();
         }
      });
        
        frame.setVisible(true);
        frame.setSize(300,400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
   }
}