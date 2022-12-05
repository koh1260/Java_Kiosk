package Kiosk;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Nutrition_Screen extends JFrame{
	private JLabel carbo = new JLabel();
	private JLabel protein = new JLabel();
	private JLabel fat = new JLabel();
	private JLabel kcal = new JLabel();
	
	private JLabel carbo_t = new JLabel("탄수화물");
	private JLabel protein_t = new JLabel("단백질");
	private JLabel fat_t = new JLabel("지방");
	private JLabel kcal_t = new JLabel("kcal");
	
	private JPanel carbo_p = new JPanel();
	private JPanel protein_p = new JPanel();
	private JPanel fat_p = new JPanel();
	private JPanel kcal_p = new JPanel();

	class JFrameWindowClosingEventHandler extends WindowAdapter {
		public void windowClosing(WindowEvent e) {
			JFrame frame = (JFrame)e.getWindow();
			frame.dispose();
		}
	}

	public Nutrition_Screen(){
		setVisible(true);
		setSize(300,400);
		setResizable(false);
		setLocationRelativeTo(null);
		setLayout(new GridLayout(6, 1));
		addWindowListener(new JFrameWindowClosingEventHandler());
		
		add(carbo_p);
		add(protein_p);
		add(fat_p);
		add(kcal_p);
		kcal_p.setLayout(new FlowLayout());
		fat_p.setLayout(new FlowLayout());
		carbo_p.setLayout(new FlowLayout());
		protein_p.setLayout(new FlowLayout());
		carbo_p.setBackground(Color.white);
		protein_p.setBackground(Color.white);
		fat_p.setBackground(Color.white);
		kcal_p.setBackground(Color.white);
		
		
		carbo_p.add(carbo_t);
		carbo_p.add(carbo);
		
		
		protein_p.add(protein_t);
		protein_p.add(protein);
		
		
		fat_p.add(fat_t);
		fat_p.add(fat);
		
		kcal_p.add(kcal_t);
		kcal_p.add(kcal);
		
		carbo_t.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		carbo.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		protein_t.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		protein.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		fat_t.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		fat.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		kcal_t.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		kcal.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		
		

	}
	public void receive_Nu(int carbo, int pro, int fat, int kcal) {
		this.carbo.setText(carbo+"g");
		this.protein.setText(pro+"g");
		this.fat.setText(fat+"g");
		this.kcal.setText(kcal+"kcal");
	}
}
