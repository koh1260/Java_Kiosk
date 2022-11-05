import javax.swing.JFrame;
import javax.swing.JLabel;

public class Admin_Screen extends JFrame{
	private static JLabel admin;
	
	public Admin_Screen() {
		admin = new JLabel("admin page");
		add(admin);
		admin.setBounds(100,100,100,100);
		setDisplay();
	}
	public void setDisplay() {
		setSize(700, 900);
		setLayout(null);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
}
