import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Initial_Screen extends JFrame implements ActionListener{
	private JButton b; 
	
	public Initial_Screen(){
		init();
		setDisplay();

		b.setBounds(0,0,700, 900);
		
		addActionEvent();
	}
	private void init() {
		b = new JButton("Tap to next Screen");
	}
	public void setDisplay() {		
		setTitle("초기 화면");
		setVisible(true);
		setSize(700, 900);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		
		add(b);
	}
	public void addActionEvent() {
		b.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == b) {
			Login_Screen l = new Login_Screen();
			setVisible(false);
			l.setVisible(true);
		}
	}
}
