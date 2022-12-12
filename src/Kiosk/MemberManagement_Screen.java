package Kiosk;

import Kiosk.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;
import java.sql.*;
import java.util.ArrayList;

public class MemberManagement_Screen {
   
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;
    
    public static int tjsrow = 5;
    public static int tjscol = 5;
    public static Object tjsxorID = new Object();
    public static Object tjsxorPW = new Object();
    public static Object tjsxorname = new Object();
    public static Object tjsxorphoneNum = new Object();
    
    public MemberManagement_Screen() {
          // 프레임 설정
         JFrame frame = new JFrame();
         frame.getContentPane().setLayout(null);
         frame.getContentPane().setBackground(Color.white);
         frame.setResizable(false);
         
         // 타이틀 판넬
         JLabel title = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("titlebar.png")));
         title.setBounds(0, 0, 700, 40);
         title.setBackground(new Color(107, 167, 240));
         frame.getContentPane().add(title);
         
         // 행, 열
         String[] col = {"학번", "비밀번호", "이름", "전화번호"};
         String[][] row = new String[0][4];
         
         // 테이블
         JTable table = new JTable(row, col);
         table.setFillsViewportHeight(true);
         table.setRowHeight(25);
         
         // 선택 마우스 리스너
         table.addMouseListener(new MouseListener() {
         public void mouseReleased(MouseEvent e) {}
         public void mousePressed(MouseEvent e) {}
         public void mouseExited(MouseEvent e) {}
         public void mouseEntered(MouseEvent e) {}
         public void mouseClicked(MouseEvent e) {
          tjscol = table.getSelectedColumn();
          if(tjscol == 0) {
              tjsrow = table.getSelectedRow();
              tjsxorID = table.getValueAt(tjsrow, tjscol);
              tjsxorPW = table.getValueAt(tjsrow, tjscol + 1);
              tjsxorname = table.getValueAt(tjsrow, tjscol + 2);
              tjsxorphoneNum = table.getValueAt(tjsrow, tjscol + 3);
          }
       }
    });
         
         // 테이블 모델
         DefaultTableModel model = new DefaultTableModel(row, col) {
            public boolean isCellEditable(int row, int col) {
               return false;
               }
            };
        
        // 스크롤 테이블
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(50, 165, 600, 680);
        frame.getContentPane().add(sp);
        
        // 검색창 텍스트 필드
        JTextField tf = new JTextField();
        tf.setBounds(60, 121, 140, 32);
        frame.getContentPane().add(tf);
        
        // 홈 버튼
        JButton home = new JButton(new ImageIcon(getClass().getClassLoader().getResource("home.png")));
        home.setBounds(624, 40, 60, 60);
        home.setBackground(Color.white);
        frame.getContentPane().add(home);
        home.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		new Admin_Screen();
        		frame.dispose();
        	}
        });
        
        // 검색을 위한 라디오 버튼
        JRadioButton rd1 = new JRadioButton("학번");
        rd1.setBounds(60, 91, 50, 30);
        rd1.setBackground(Color.white);
        rd1.setFont(new Font("맑은 고딕", Font.BOLD, 12));
        frame.getContentPane().add(rd1);
        JRadioButton rd2 = new JRadioButton("전화번호");
        rd2.setBounds(120, 91, 80, 30);
        rd2.setBackground(Color.white);
        rd2.setFont(new Font("맑은 고딕", Font.BOLD, 12));
        frame.getContentPane().add(rd2);
        
        // 버튼 그룹
        ButtonGroup g = new ButtonGroup();
        g.add(rd1);
        g.add(rd2);
        
        // 검색 버튼
        JButton rjator = new JButton(new ImageIcon(getClass().getClassLoader().getResource("001.png")));
        frame.getContentPane().add(rjator);
        rjator.setBounds(210, 120, 80, 32);
        rjator.setBorderPainted(false);
        rjator.setFocusPainted(false);	
        rjator.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        rjator.setForeground(Color.white);
        rjator.setBackground(new Color(107, 167, 240));
        rjator.setBorder(BorderFactory.createLineBorder(new Color(107, 167, 240)));
        rjator.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            model.setNumRows(0);
                  try {
                     Class.forName("com.mysql.cj.jdbc.Driver");
                        Connection con=DriverManager.getConnection(ex.db_url,ex.db_user,ex.db_pw);
                        
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

        // 조회 버튼
        JButton whghl = new JButton(new ImageIcon(getClass().getClassLoader().getResource("refresh.png")));
        frame.getContentPane().add(whghl);
        whghl.setBounds(425, 120, 32, 32);
        whghl.setForeground(Color.white);
        whghl.setBackground(new Color(107, 167, 240));
        whghl.setBorder(BorderFactory.createLineBorder(new Color(107, 167, 240)));
        whghl.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
              model.setNumRows(0);
              try {
                 Class.forName("com.mysql.cj.jdbc.Driver");
                 Connection con=DriverManager.getConnection(ex.db_url,ex.db_user,ex.db_pw);
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
        
        // 수정 버튼;
        JButton vuswlq = new JButton(new ImageIcon(getClass().getClassLoader().getResource("002.png")));
        vuswlq.setBorderPainted(false);
        vuswlq.setFocusPainted(false);	
        vuswlq.setBounds(465, 120, 80, 32);
        vuswlq.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        vuswlq.setForeground(Color.white);
        vuswlq.setBackground(new Color(107, 167, 240));
        vuswlq.setBorder(BorderFactory.createLineBorder(new Color(107, 167, 240)));
        frame.getContentPane().add(vuswlq);
        vuswlq.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            if(tjscol == 0) {
               new MemberEdit(tjsxorID.toString());
               }
            else {
               JOptionPane.showMessageDialog(null,"ID 셀을 클릭해주십시요.");
               }
            }
           });
        
        // 삭제 버튼
        JButton tkrwp = new JButton(new ImageIcon(getClass().getClassLoader().getResource("003.png")));
        tkrwp.setBorderPainted(false);
        tkrwp.setFocusPainted(false);	
        tkrwp.setBounds(555, 120, 80, 32);
        tkrwp.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        tkrwp.setForeground(Color.white);
        tkrwp.setBackground(new Color(107, 167, 240));
        tkrwp.setBorder(BorderFactory.createLineBorder(new Color(107, 167, 240)));
        frame.getContentPane().add(tkrwp);
        tkrwp.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            if(tjscol == 0) {
               int answer = JOptionPane.showConfirmDialog(null, "정말 삭제하시겠습니까?", "주의", JOptionPane.YES_NO_OPTION );
               if(answer == JOptionPane.YES_OPTION){
                  try {
                     Class.forName("com.mysql.cj.jdbc.Driver");
                        Connection con=DriverManager.getConnection(ex.db_url,ex.db_user,ex.db_pw);        
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
            else if(tjscol != 0) {
               JOptionPane.showMessageDialog(null,"ID 셀을 클릭하여 삭제해주십시요.");
               }
            }
         });
        
        
        frame.setVisible(true);
        frame.setSize(700,900);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        }

   public static void main(String[] args) {
      new MemberManagement_Screen();
      }
}