package Kiosk;

import Kiosk.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;
import java.sql.*;
import java.util.ArrayList;

public class MemberEdit extends JFrame {
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;
    
   public MemberEdit(String tjsxorID, String tjsxorPW, String tjsxorname, String tjsxorphoneNum) {
      JFrame frame = new JFrame("회원 정보 수정");
      frame.getContentPane().setLayout(null); 
        
      // 아이디
       JLabel label0 = new JLabel("아이디");
        label0.setBounds(90, 40, 120, 30);
        frame.getContentPane().add(label0);
        JTextField tf0 = new JTextField(tjsxorID);
        tf0.setBounds(170, 40, 120, 30);
        tf0.setEditable(false);
        frame.getContentPane().add(tf0);
        
        // 비밀번호
       JLabel label1 = new JLabel("비밀번호");
        label1.setBounds(10, 110, 120, 30);
        frame.getContentPane().add(label1);
        JTextField tf1 = new JTextField(tjsxorPW);
        tf1.setBounds(100, 110, 100, 30);
        tf1.setEditable(false);
        frame.getContentPane().add(tf1);
        JTextField tnwjd1 = new JTextField();
        tnwjd1.setBounds(250, 110, 100, 30);
        frame.getContentPane().add(tnwjd1);
        
        // 이름
       JLabel label2 = new JLabel("이름");
        label2.setBounds(10, 160, 120, 30);
        frame.getContentPane().add(label2);
        JTextField tf2 = new JTextField(tjsxorname);
        tf2.setBounds(100, 160, 100, 30);
        tf2.setEditable(false);
        frame.getContentPane().add(tf2);
        JTextField tnwjd2 = new JTextField();
        tnwjd2.setBounds(250, 160, 100, 30);
        frame.getContentPane().add(tnwjd2);
        
        // 전화번호
       JLabel label3 = new JLabel("전화번호");
        label3.setBounds(10, 210, 120, 30);
        frame.getContentPane().add(label3);
        JTextField tf3 = new JTextField(tjsxorphoneNum);
        tf3.setBounds(100, 210, 100, 30);
        tf3.setEditable(false);
        frame.getContentPane().add(tf3);
        JTextField tnwjd3 = new JTextField();
        tnwjd3.setBounds(250, 210, 100, 30);
        frame.getContentPane().add(tnwjd3);
        
//        // 조회 버튼
//        JButton whghl = new JButton("조회");
//        whghl.setBounds(270, 30, 90, 30);
//        frame.getContentPane().add(whghl);
//      whghl.addActionListener(new ActionListener() {
//         public void actionPerformed(ActionEvent e) {
//            String str = tf0.getText();
//            if(str.isEmpty() == true) {
//               JOptionPane.showMessageDialog(null,"아이디를 입력해 주십시요.");
//            }
//            else {
//               try {
//                  Class.forName("com.mysql.cj.jdbc.Driver");
//                     Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sqldb","root","3213");
//                     ps = con.prepareStatement("SELECT * FROM student WHERE ID = '" + tf0.getText() + "'");
//                  rs = ps.executeQuery();
//                  while(rs.next()) {
//                            String PW = rs.getString("PW");
//                            String name = rs.getString("name");
//                            String phoneNum = rs.getString("phoneNum");
//                            tf1.setText(PW);
//                            tf2.setText(name);
//                            tf3.setText(phoneNum);
//                         }
//                  ps.close();
//                  con.close();
//               }catch(ClassNotFoundException e1) {
//                  e1.printStackTrace();
//               }catch(SQLException e1) {
//                  //System.out.println("연결 실패");
//                  e1.printStackTrace();
//               }
//            }
//         }
//      });
      
      // 편집 버튼
       JButton vuswlq = new JButton("편집");
        vuswlq.setBounds(115, 270, 150, 40);
        frame.getContentPane().add(vuswlq);
        vuswlq.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
               String[] rlqhswjdqh = { tjsxorPW, tjsxorname, tjsxorphoneNum};
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
        ekerl.setBounds(300, 315, 60, 25);
        frame.getContentPane().add(ekerl);
        ekerl.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent arg0) {
            frame.dispose();
         }
      });
        
        frame.setVisible(true);
        frame.setSize(400,400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }
//   public static void main(String[] args) {
//      new MemberEdit();
//   }
}