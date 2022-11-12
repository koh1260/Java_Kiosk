package Kiosk;

import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Nutrition_Screen extends JFrame{
	private JLabel carbo;
	private JLabel protein;
	private JLabel fat;
	private JLabel kcal;
	
	class JFrameWindowClosingEventHandler extends WindowAdapter {
		public void windowClosing(WindowEvent e) {
			JFrame frame = (JFrame)e.getWindow();
			frame.dispose();
		}
	}

	public Nutrition_Screen(){
		setVisible(true);
		setSize(670,600);
		setResizable(false);
		setLocationRelativeTo(null);
		setLayout(new FlowLayout());
		
		carbo = new JLabel();
		protein = new JLabel();
		fat = new JLabel();
		kcal = new JLabel();
		
		add(carbo);
		add(protein);
		add(fat);
		add(kcal);

		addWindowListener(new JFrameWindowClosingEventHandler());
	}
	public void receive_Nu(int carbo, int pro, int fat, int kcal) {
		this.carbo.setText(carbo+"g");
		this.protein.setText(pro+"g");
		this.fat.setText(fat+"g");
		this.kcal.setText(kcal+"kcal");
	}
}
