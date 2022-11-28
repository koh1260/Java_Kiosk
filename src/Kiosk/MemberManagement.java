package Kiosk;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class MemberManagement extends JFrame {
   public static void main(String[] args) {
   
      JFrame frame = new JFrame("회원관리");
      
      
      //레이아웃 설정
      frame.getContentPane().setLayout(null);   
      
      
      JLabel label = new JLabel("회원관리");//레이블 생성
      label.setFont(new Font("맑은 고딕", Font.BOLD, 20));
      JTextField tf = new JTextField(); //텍스트 필드 초기화
      JButton bt = new JButton("검색하기"); //버튼 생성
   
      
      // 레이블, 텍스트필드, 버튼 위치 설정
      label.setBounds(100, 10, 120, 30);
      tf.setBounds(300, 10, 130, 30);
      bt.setBounds(480, 10, 90, 30);
      
      
      //프레임에 각 항목 추가
      frame.getContentPane().add(label);
      frame.getContentPane().add(tf);
      frame.getContentPane().add(bt);
      

      
      //테이블 생성
      String[] columns = {"학번", "이름", "전화번호", "구분"};
      Object[][] rows = {{" "," "," "," "}, 
            {" "," "," "," "}, 
            {" "," "," "," "}, 
            {" "," "," "," "}};
      
      JTable table = new JTable(rows, columns);      
      
      table.setFillsViewportHeight(true);
      table.setRowHeight(25);
      
      
      
      //테이블에 스크롤팬 추가 및 테이블 크기 위치 설정
      JScrollPane sp = new JScrollPane(table);
      sp.setBounds(90, 70, 500, 700);
      
      //프레임에 테이블 추가
      frame.getContentPane().add(sp);
      
      
      frame.setVisible(true);
      frame.setSize(700,900);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //닫기버튼 클릭시 프로그램 종료
   }
}