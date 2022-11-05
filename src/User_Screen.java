import javax.swing.JFrame;
import javax.swing.JLabel;

public class User_Screen extends JFrame{
	private static JLabel user;
	
	public User_Screen() {
		user = new JLabel("user page");
		add(user);
		user.setBounds(100,100,100,100);
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
