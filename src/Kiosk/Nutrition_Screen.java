package Kiosk;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Nutrition_Screen extends JFrame{
	private JLabel carbo = new JLabel("12g",JLabel.CENTER);
	private JLabel protein = new JLabel("12g",JLabel.CENTER);
	private JLabel fat = new JLabel("12g",JLabel.CENTER);
	private JLabel kcal = new JLabel("12g",JLabel.CENTER);
	
	private JLabel carbo_t = new JLabel("탄수화물",JLabel.CENTER);
	private JLabel protein_t = new JLabel("단백질",JLabel.CENTER);
	private JLabel fat_t = new JLabel("지방",JLabel.CENTER);
	private JLabel kcal_t = new JLabel("kcal",JLabel.CENTER);
	
	private JPanel carbo_p = new JPanel();
	private JPanel protein_p = new JPanel();
	private JPanel fat_p = new JPanel();
	private JPanel kcal_p = new JPanel();
	
	private JLabel titlebar = new JLabel(new ImageIcon("images/nutitle.png"));

	class JFrameWindowClosingEventHandler extends WindowAdapter {
		public void windowClosing(WindowEvent e) {
			JFrame frame = (JFrame)e.getWindow();
			frame.dispose();
		}
	}

	public Nutrition_Screen(){
		this.getContentPane().setBackground(Color.white);
		setVisible(true);
		setSize(300,400);
		setResizable(false);
		setLocationRelativeTo(null);
		setLayout(null);
		addWindowListener(new JFrameWindowClosingEventHandler());
		
		add(titlebar);
		titlebar.setBounds(0,0,300,40);
		titlebar.setOpaque(true);
		titlebar.setBackground(Color.BLACK);
		
		add(carbo_p);
		carbo_p.setBounds(0,60,300,40);
		
		add(protein_p);
		protein_p.setBounds(0,120,300,40);
		
		add(fat_p);
		fat_p.setBounds(0,180,300,40);
		
		add(kcal_p);
		kcal_p.setBounds(0,240,300,40);
		
		kcal_p.setLayout(null);
		fat_p.setLayout(null);
		carbo_p.setLayout(null);
		protein_p.setLayout(null);
		carbo_p.setBackground(Color.white);
		protein_p.setBackground(Color.white);
		fat_p.setBackground(Color.white);
		kcal_p.setBackground(Color.white);
		
		
		carbo_p.add(carbo_t);
		carbo_p.add(carbo);
		carbo_t.setBounds(10,0, 150,40);
		carbo.setBounds(150, 0,150, 40 );
		
		protein_p.add(protein_t);
		protein_p.add(protein);
		protein_t.setBounds(10,0, 150,40);
		protein.setBounds(150, 0,150, 40 );
		
		
		fat_p.add(fat_t);
		fat_p.add(fat);
		fat_t.setBounds(10,0, 150,40);
		fat.setBounds(150, 0,150, 40 );
		
		kcal_p.add(kcal_t);
		kcal_p.add(kcal);
		kcal_t.setBounds(10,0, 150,40);
		kcal.setBounds(150, 0,150, 40 );
		
		carbo_t.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		carbo.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		protein_t.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		protein.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		fat_t.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		fat.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		kcal_t.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		kcal.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		
		

	}
	public void receive_Nu(int carbo, int pro, int fat, int kcal) {
		this.carbo.setText(carbo+"g");
		this.protein.setText(pro+"g");
		this.fat.setText(fat+"g");
		this.kcal.setText(kcal+"kcal");
	}
}
