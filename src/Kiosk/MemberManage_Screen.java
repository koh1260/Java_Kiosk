package Kiosk;

import Kiosk.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;
import java.sql.*;
import java.util.ArrayList;

public class MemberManage_Screen extends JFrame {
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;
    
    public static int tjsrow;
    public static int tjscol;
    public static Object tjsxorID = new Object();
    public static Object tjsxorPW = new Object();
    public static Object tjsxorname = new Object();
    public static Object tjsxorphoneNum = new Object();
    
   public MemberManage_Screen() {
       // 프레임 설정
      JFrame frame = new JFrame("회원관리");
      frame.getContentPane().setLayout(null);   
        
      // 행, 열
        String[] col = {"아이디", "비밀번호", "이름", "전화번호"};
       String[][] row = new String[0][4];
       
       // 테이블
        JTable table = new JTable(row, col);
        table.setFillsViewportHeight(true);
        table.setRowHeight(25);
        
        // 테이블 모델
       DefaultTableModel model = new DefaultTableModel(row, col) {
           public boolean isCellEditable(int row, int col) {
              return false;
           }
       };
       
        // 스크롤 테이블
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(42, 140, 600, 700);
        frame.getContentPane().add(sp);
        
        // 조회 버튼
        JButton whghl = new JButton("조회");
        frame.getContentPane().add(whghl);
        whghl.setBounds(200, 10, 90, 30);
      whghl.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            model.setNumRows(0);
                  try {
                     Class.forName("com.mysql.cj.jdbc.Driver");
                        Connection con=DriverManager.getConnection(ex.db_url, ex.db_user, ex.db_pw);
                        ps = con.prepareStatement("SELECT * FROM userAccount");
                     rs = ps.executeQuery();
                     while(rs.next()) {
                               String ID = rs.getString("ID");
                               String PW = rs.getString("PW");
                               String name = rs.getString("name");
                               String phoneNum = rs.getString("phoneNum");
                               //System.out.println(ID + " " + PW + " " + name + " " + phoneNum);
                               String[] colums = {ID, PW, name, phoneNum};
                               model.addRow(colums);
                               table.setModel(model);
                            }
                     ps.close();
                     con.close();
                  }catch(ClassNotFoundException e1) {
                     e1.printStackTrace();
                  }catch(SQLException e1) {
                     //System.out.println("연결 실패");
                     e1.printStackTrace();
               }
            }
      });
        
      // 검색창 텍스트 필드
        JTextField tf = new JTextField();
        tf.setBounds(100, 90, 140, 30);
        frame.getContentPane().add(tf);
        
        // 검색을 위한 라디오 버튼
        JRadioButton rd1 = new JRadioButton("아이디");
        rd1.setBounds(100, 60, 70, 30);
        frame.getContentPane().add(rd1);
        JRadioButton rd2 = new JRadioButton("전화번호");
        rd2.setBounds(170, 60, 80, 30);
        frame.getContentPane().add(rd2);
        
        // 버튼 그룹
        ButtonGroup g = new ButtonGroup();
        g.add(rd1);
        g.add(rd2);
        
        // 검색 버튼
        JButton rjator = new JButton("검색");
        frame.getContentPane().add(rjator);
        rjator.setBounds(320, 90, 90, 30);
      rjator.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            model.setNumRows(0);
                  try {
                     Class.forName("com.mysql.cj.jdbc.Driver");
                        Connection con=DriverManager.getConnection(ex.db_url,ex.db_user, ex.db_pw);
                        
                        String cho = null;
                        if(rd1.isSelected())
                           cho = "ID";
                        else if(rd2.isSelected())
                           cho = "phoneNum";
                        
                        ps = con.prepareStatement("SELECT * FROM userAccount WHERE " + cho + " = '" + tf.getText() + "'");
                     rs = ps.executeQuery();
                     while(rs.next()) {
                               String ID = rs.getString("ID");
                               String PW = rs.getString("PW");
                               String name = rs.getString("name");
                               String phoneNum = rs.getString("phoneNum");
                               
                               String[] colums = {ID, PW, name, phoneNum};
                               model.addRow(colums);
                               table.setModel(model);
                            }
                     ps.close();
                     con.close();
                  }catch(ClassNotFoundException e1) {
                     e1.printStackTrace();
                  }catch(SQLException e1) {
                     // System.out.println("연결 실패.");
                     e1.printStackTrace();
               }
            }
         });
      
      // 선택 마우스 리스너
        table.addMouseListener(new MouseListener() {
           public void mouseReleased(MouseEvent e) {}
           public void mousePressed(MouseEvent e) {}
           public void mouseExited(MouseEvent e) {}
           public void mouseEntered(MouseEvent e) {}
           public void mouseClicked(MouseEvent e) {
              tjsrow = table.getSelectedRow();
              tjscol = table.getSelectedColumn();
              tjsxorID = table.getValueAt(tjsrow, tjscol);
              tjsxorPW = table.getValueAt(tjsrow, tjscol + 1);
              tjsxorname = table.getValueAt(tjsrow, tjscol + 2);
              tjsxorphoneNum = table.getValueAt(tjsrow, tjscol + 3);
           }
        });
      
        // 편집 버튼;
       JButton vuswlq = new JButton("편집");
        vuswlq.setBounds(500, 30, 90, 30);
        frame.getContentPane().add(vuswlq);
        vuswlq.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            if(tjscol == 0) {
               new MemberEdit(tjsxorID.toString(), tjsxorPW.toString(), tjsxorname.toString(), tjsxorphoneNum.toString());
               }
            else {
               JOptionPane.showMessageDialog(null,"ID 셀을 클릭해주십시요.");
               }
            }
           });
        
        // 삭제 버튼
        JButton tkrwp = new JButton("삭제");
        tkrwp.setBounds(500, 80, 90, 30);
        frame.getContentPane().add(tkrwp);
        tkrwp.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            if(tjscol == 0) {
               int answer = JOptionPane.showConfirmDialog(null, "정말 삭제하시겠습니까?", "주의", JOptionPane.YES_NO_OPTION );
               if(answer == JOptionPane.YES_OPTION){
                  try {
                     Class.forName("com.mysql.cj.jdbc.Driver");
                        Connection con=DriverManager.getConnection(ex.db_url, ex.db_user,ex.db_pw);        
                        ps = con.prepareStatement("DELETE FROM userAccount where ID = '" + tjsxorID.toString() + "'");
                        ps.executeUpdate();
                        JOptionPane.showMessageDialog(null,"삭제가 완료되었습니다.");
                        ps.close();
                     con.close();
                  }catch(ClassNotFoundException e1) {
                     e1.printStackTrace();
                  }catch(SQLException e1) {
                     // System.out.println("연결 실패.");
                     e1.printStackTrace();
                     }
                  } else{
                  JOptionPane.showMessageDialog(null,"삭제가 취소되었습니다.");
                  }
            }
            else {
               JOptionPane.showMessageDialog(null,"ID 셀을 클릭하여 삭제해주십시요.");
               }
            }
         });
        
      // 회원관리 레이블
       JLabel label = new JLabel("회원관리");
      label.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        label.setBounds(100, 10, 120, 30);
        frame.getContentPane().add(label);
        
        frame.setVisible(true);
        frame.setSize(700,900);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }

}